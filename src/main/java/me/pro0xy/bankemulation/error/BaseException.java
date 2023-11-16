package me.pro0xy.bankemulation.error;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 01:50</p>
 */

@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Data
public abstract class BaseException extends Exception {
    HttpStatus status;

    public BaseException(@NonNull String message, @NonNull HttpStatus status){
        super(message);
        this.status = status;
    }
}