package me.pro0xy.bankemulation.error.user;

import me.pro0xy.bankemulation.error.BaseException;
import org.springframework.http.HttpStatus;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 01:54</p>
 */

public class UserAlreadyExist extends BaseException {
    public UserAlreadyExist() {
        super("USER_ALREADY_EXIST", HttpStatus.BAD_REQUEST);
    }
}
