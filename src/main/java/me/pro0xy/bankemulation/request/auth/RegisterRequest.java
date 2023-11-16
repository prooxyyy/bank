package me.pro0xy.bankemulation.request.auth;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 01:40</p>
 */

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    @NotNull
    String email;
    @NotNull
    String firstName;
    @NotNull
    String lastName;
    @NotNull
    @Min(1000)
    @Max(9999)
    @Digits(integer = 4, fraction = 0)
    Integer pin;
}
