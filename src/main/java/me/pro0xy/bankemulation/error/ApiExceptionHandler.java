package me.pro0xy.bankemulation.error;

import lombok.extern.slf4j.Slf4j;
import me.pro0xy.bankemulation.response.ApiResponse;
import me.pro0xy.bankemulation.utils.ExceptionUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 02:46</p>
 */

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<Object>> handleBaseException(BaseException ex) {
        ex.printStackTrace();
        return ExceptionUtil.makeExceptionResponse(ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception ex) {
        ex.printStackTrace();
        return ExceptionUtil.makeExceptionResponse(ex);
    }
}
