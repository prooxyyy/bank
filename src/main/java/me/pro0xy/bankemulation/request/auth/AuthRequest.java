package me.pro0xy.bankemulation.request.auth;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 02:33</p>
 */

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthRequest {
    String email;
    Integer pin;
}
