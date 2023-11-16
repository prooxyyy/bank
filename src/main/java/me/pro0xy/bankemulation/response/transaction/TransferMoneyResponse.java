package me.pro0xy.bankemulation.response.transaction;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import me.pro0xy.bankemulation.transaction.TransactionStatus;

import java.time.LocalDateTime;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 22:25</p>
 */

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferMoneyResponse {
    TransactionStatus transactionStatus;
    LocalDateTime time;
}
