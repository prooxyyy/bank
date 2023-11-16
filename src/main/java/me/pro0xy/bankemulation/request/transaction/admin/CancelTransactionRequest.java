package me.pro0xy.bankemulation.request.transaction.admin;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 22:41</p>
 */

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CancelTransactionRequest {
    @NonNull
    UUID transactionId;
    String reason;
}
