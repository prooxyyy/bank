package me.pro0xy.bankemulation.controller;

import lombok.NonNull;
import lombok.SneakyThrows;
import me.pro0xy.bankemulation.db.entity.Transaction;
import me.pro0xy.bankemulation.db.entity.User;
import me.pro0xy.bankemulation.error.transaction.InvalidCard;
import me.pro0xy.bankemulation.request.transaction.admin.CancelTransactionRequest;
import me.pro0xy.bankemulation.response.ApiResponse;
import me.pro0xy.bankemulation.response.transaction.TransferMoneyResponse;
import me.pro0xy.bankemulation.service.CardService;
import me.pro0xy.bankemulation.service.TransactionService;
import me.pro0xy.bankemulation.service.UserService;
import me.pro0xy.bankemulation.request.transaction.TransferMoneyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 22:02</p>
 */

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CardService cardService;

    @PostMapping("/transfer")
    @SneakyThrows
    public ResponseEntity<ApiResponse<Object>> transfer(@RequestBody @NonNull TransferMoneyRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();

        User user = userService.loadUserByUserName(username);

        // If user is not owner of FROM card, throwing exception
        if(!user.getCards().contains(cardService.getCardByNumber(request.getFrom()))){
            throw new InvalidCard();
        }

        Transaction transaction = transactionService.transferTo(request);
        TransferMoneyResponse response = new TransferMoneyResponse();
        response.setTime(transaction.getTransactionDate());
        response.setTransactionStatus(transaction.getStatus());

        return new ApiResponse.ResponseBuilder()
                .setStatus(HttpStatus.OK)
                .setResponse(response)
                .build();
    }

    @PostMapping("/cancelTransaction")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @SneakyThrows
    public ResponseEntity<ApiResponse<Object>> cancelTransaction(@RequestBody @NonNull CancelTransactionRequest request){
        return new ApiResponse.ResponseBuilder()
                .setStatus(HttpStatus.OK)
                .addKeyValue("canceled", transactionService.cancelTransaction(request))
                .build();
    }
}
