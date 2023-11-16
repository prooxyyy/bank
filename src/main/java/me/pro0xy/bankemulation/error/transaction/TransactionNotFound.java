package me.pro0xy.bankemulation.error.transaction;

import lombok.NonNull;
import me.pro0xy.bankemulation.error.BaseException;
import org.springframework.http.HttpStatus;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 22:43</p>
 */

public class TransactionNotFound extends BaseException {
    public TransactionNotFound() {
        super("TRANSACTION_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}
