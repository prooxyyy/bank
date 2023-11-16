package me.pro0xy.bankemulation.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 01:04</p>
 */

@Data
public class ApiResponse<T> {
    private HttpStatus code;
    private T response;

    public ApiResponse(HttpStatus code, T response) {
        this.code = code;
        this.response = response;
    }

    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ResponseBuilder {
        private HttpStatus status;
        private Object response;

        public ResponseBuilder setStatus(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ResponseBuilder setResponse(Object response) {
            this.response = response;
            return this;
        }

        public ResponseBuilder addKeyValue(String key, Object value) {
            if (response == null || !(response instanceof Map)) {
                response = new HashMap<>();
            }
            ((Map<String, Object>) response).put(key, value);
            return this;
        }

        public ResponseEntity<ApiResponse<Object>> build() {
            return ResponseEntity
                    .status(status)
                    .body(new ApiResponse<>(status, response));
        }
    }
}
