package me.pro0xy.bankemulation.request.card;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import me.pro0xy.bankemulation.currency.Currency;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 08:46</p>
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class OpenCardRequest {
    Integer pin;
    Currency currency;
}
