package me.pro0xy.bankemulation.response.card;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import me.pro0xy.bankemulation.currency.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 09:53</p>
 */

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActiveCardResponse {
    String cardNumber;
    String cardHolderName;
    LocalDate expirationDate;
    String cvv;
    BigDecimal balance;
    Currency currency;
}
