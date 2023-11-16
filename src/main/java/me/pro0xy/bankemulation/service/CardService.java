package me.pro0xy.bankemulation.service;

import lombok.NonNull;
import lombok.SneakyThrows;
import me.pro0xy.bankemulation.currency.Currency;
import me.pro0xy.bankemulation.db.entity.Card;
import me.pro0xy.bankemulation.db.entity.User;
import me.pro0xy.bankemulation.db.repo.CardRepository;
import me.pro0xy.bankemulation.db.repo.UserRepository;
import me.pro0xy.bankemulation.error.transaction.InvalidCard;
import me.pro0xy.bankemulation.transaction.TransactionUtil;
import me.pro0xy.bankemulation.utils.CardInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 01:38</p>
 */

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;

    public Card openNewCard(@NonNull User user, @NonNull Integer pin, @NonNull Currency currency){
        Card card = new Card();
        card.setCardNumber(CardInfoUtil.generateCardNumber());
        card.setCardHolderName(CardInfoUtil.generateCardHolderName(user.getFirstName(), user.getLastName()));
        card.setCvv(CardInfoUtil.generateCvv());
        card.setPin(pin);
        card.setExpirationDate(LocalDate.now().plusYears(3));
        card.setBalance(new BigDecimal(0));
        card.setCurrency(currency);
        card.setUser(user);

        List<Card> newCards = user.getCards();
        newCards.add(card);

        user.setCards(newCards);
        userRepository.save(user);
        return cardRepository.save(card);
    }

    public boolean isCardActive(@NonNull Card card){
        return card.getExpirationDate().isAfter(LocalDate.now());
    }

    @SneakyThrows
    public Card getCardByNumber(@NonNull String number) {
        return cardRepository.findByCardNumber(number).orElseThrow(InvalidCard::new);
    }

    @SneakyThrows
    public void withdraw(@NonNull Card card, @NonNull BigDecimal amount){
        card.setBalance(TransactionUtil.withdraw(card, amount));
        cardRepository.save(card);
    }

    @SneakyThrows
    public void deposit(@NonNull Card card, @NonNull BigDecimal amount){
        card.setBalance(TransactionUtil.deposit(card, amount));
        cardRepository.save(card);
    }
}
