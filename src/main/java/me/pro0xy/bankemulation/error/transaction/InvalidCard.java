package me.pro0xy.bankemulation.error.transaction;

import lombok.NonNull;
import me.pro0xy.bankemulation.error.BaseException;
import org.springframework.http.HttpStatus;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 22:07</p>
 */

public class InvalidCard extends BaseException {
    public InvalidCard() {
        super("INVALID_CARD", HttpStatus.FORBIDDEN);
    }
}
