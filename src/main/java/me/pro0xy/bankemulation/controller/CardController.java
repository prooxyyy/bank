package me.pro0xy.bankemulation.controller;

import lombok.extern.slf4j.Slf4j;
import me.pro0xy.bankemulation.db.entity.Card;
import me.pro0xy.bankemulation.db.entity.User;
import me.pro0xy.bankemulation.request.card.OpenCardRequest;
import me.pro0xy.bankemulation.response.ApiResponse;
import me.pro0xy.bankemulation.response.card.ActiveCardResponse;
import me.pro0xy.bankemulation.service.CardService;
import me.pro0xy.bankemulation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 08:45</p>
 */

@RestController
@RequestMapping("/card")
@Slf4j
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public ResponseEntity<ApiResponse<Object>> newCard(@RequestBody OpenCardRequest openCardRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();

        User user = userService.loadUserByUserName(username);

        Card newCard = cardService.openNewCard(user, openCardRequest.getPin(), openCardRequest.getCurrency());
        return new ApiResponse.ResponseBuilder()
                .setStatus(HttpStatus.OK)
                .addKeyValue("id", newCard.getId())
                .addKeyValue("number", newCard.getCardNumber())
                .addKeyValue("holder_name", newCard.getCardHolderName())
                .addKeyValue("cvv", newCard.getCvv())
                .addKeyValue("balance", newCard.getBalance())
                .build();
    }

    @GetMapping("/activeCards")
    public ResponseEntity<ApiResponse<Object>> getActiveCard(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();

        User user = userService.loadUserByUserName(username);

        List<ActiveCardResponse> activeCardResponseList = new ArrayList<>();
        ApiResponse.ResponseBuilder builder = new ApiResponse.ResponseBuilder();
        builder.setStatus(HttpStatus.OK);

        userService.getActiveCards(user)
                .stream()
                .filter(card -> cardService.isCardActive(card))
                .forEach(card -> {
                    ActiveCardResponse cardDTO = new ActiveCardResponse();
                    cardDTO.setCardNumber(card.getCardNumber());
                    cardDTO.setCardHolderName(card.getCardHolderName());
                    cardDTO.setExpirationDate(card.getExpirationDate());
                    cardDTO.setBalance(card.getBalance());
                    cardDTO.setCvv(card.getCvv());
                    cardDTO.setCurrency(card.getCurrency());
                    activeCardResponseList.add(cardDTO);
                });

        builder.addKeyValue("cards", activeCardResponseList);
        return builder.build();
    }
}
