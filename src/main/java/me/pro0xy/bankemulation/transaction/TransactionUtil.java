package me.pro0xy.bankemulation.transaction;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import me.pro0xy.bankemulation.db.entity.Card;

import java.math.BigDecimal;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 21:53</p>
 */

@UtilityClass
public class TransactionUtil {
    public static BigDecimal withdraw(@NonNull Card card, @NonNull BigDecimal amount){
        return card.getBalance().subtract(amount);
    }

    public static BigDecimal deposit(@NonNull Card card, @NonNull BigDecimal amount){
        return card.getBalance().add(amount);
    }
}
