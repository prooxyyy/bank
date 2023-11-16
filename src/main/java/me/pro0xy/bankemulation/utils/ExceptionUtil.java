package me.pro0xy.bankemulation.utils;

import lombok.experimental.UtilityClass;
import me.pro0xy.bankemulation.error.BaseException;
import me.pro0xy.bankemulation.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 02:48</p>
 */
@UtilityClass
public class ExceptionUtil {

    public static ResponseEntity<ApiResponse<Object>> makeExceptionResponse(Exception exception){

        return new ApiResponse.ResponseBuilder()
                .setStatus(HttpStatus.BAD_REQUEST)
                .addKeyValue("exception", exception.getMessage())
                .build();
    }

    public static ResponseEntity<ApiResponse<Object>> makeExceptionResponse(BaseException exception){
        return new ApiResponse.ResponseBuilder()
                .setStatus(exception.getStatus())
                .addKeyValue("exception", exception.getMessage())
                .build();
    }
}
