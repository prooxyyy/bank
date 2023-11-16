package me.pro0xy.bankemulation.error.transaction;

import me.pro0xy.bankemulation.error.BaseException;
import org.springframework.http.HttpStatus;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 21:52</p>
 */

public class InsufficientFunds extends BaseException {
    public InsufficientFunds() {
        super("INSUFFICIENT_FUNDS", HttpStatus.FORBIDDEN);
    }
}
