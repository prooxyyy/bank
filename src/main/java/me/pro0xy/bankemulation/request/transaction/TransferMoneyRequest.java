package me.pro0xy.bankemulation.request.transaction;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 21:48</p>
 */

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferMoneyRequest {
    @NonNull
    String from;
    @NonNull
    String to;
    @NonNull
    BigDecimal amount;

    String comment;
}
