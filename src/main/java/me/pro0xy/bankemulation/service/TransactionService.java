package me.pro0xy.bankemulation.service;

import lombok.NonNull;
import lombok.SneakyThrows;
import me.pro0xy.bankemulation.db.entity.Card;
import me.pro0xy.bankemulation.db.entity.Transaction;
import me.pro0xy.bankemulation.db.repo.TransactionRepository;
import me.pro0xy.bankemulation.error.transaction.InsufficientFunds;
import me.pro0xy.bankemulation.error.transaction.TransactionNotFound;
import me.pro0xy.bankemulation.request.transaction.admin.CancelTransactionRequest;
import me.pro0xy.bankemulation.transaction.TransactionStatus;
import me.pro0xy.bankemulation.request.transaction.TransferMoneyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 21:34</p>
 */

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CardService cardService;

    @SneakyThrows
    public Transaction transferTo(@NonNull TransferMoneyRequest request) {
        Card from = cardService.getCardByNumber(request.getFrom());
        Card to = cardService.getCardByNumber(request.getTo());
        BigDecimal amount = request.getAmount();

        boolean validBalance = from.getBalance().compareTo(amount) > 0;

        if (!validBalance){
            throw new InsufficientFunds();
        }

        Transaction transaction = new Transaction();
        transaction.setFromCard(from);
        transaction.setToCard(to);
        transaction.setAmount(amount);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setCurrency(from.getCurrency());
        transaction.setComment(request.getComment());
        transaction.setStatus(TransactionStatus.IN_PROCCESS);
        transactionRepository.save(transaction);

        cardService.withdraw(from, amount);
        cardService.deposit(to, amount);

        transaction.setStatus(TransactionStatus.CONFIRMED);
        transactionRepository.save(transaction);
        return transaction;
    }

    @SneakyThrows
    public boolean cancelTransaction(@NonNull CancelTransactionRequest request) {
        Transaction transaction = transactionRepository.findById(request.getTransactionId()).orElseThrow(TransactionNotFound::new);
        Card from = transaction.getFromCard();
        Card to = transaction.getToCard();
        BigDecimal amount = transaction.getAmount();

        cardService.withdraw(to, amount);
        cardService.deposit(from, amount);

        transaction.setStatus(TransactionStatus.CANCELED);
        transaction.setCancelReason(request.getReason());
        transactionRepository.save(transaction);

        return true;
    }
}
