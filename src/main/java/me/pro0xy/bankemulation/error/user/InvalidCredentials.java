package me.pro0xy.bankemulation.error.user;

import me.pro0xy.bankemulation.error.BaseException;
import org.springframework.http.HttpStatus;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 02:32</p>
 */

public class InvalidCredentials extends BaseException {
    public InvalidCredentials() {
        super("INVALID_CRED", HttpStatus.FORBIDDEN);
    }
}
