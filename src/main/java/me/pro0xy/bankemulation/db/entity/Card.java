package me.pro0xy.bankemulation.db.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import me.pro0xy.bankemulation.currency.Currency;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 01:25</p>
 */

@Entity
@Table(name = "bank_cards")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    User user;

    String cardNumber;

    String cardHolderName;

    LocalDate expirationDate;

    String cvv;

    BigDecimal balance;

    @Enumerated(EnumType.STRING)
    Currency currency;

    @NotNull
    @Min(1000)
    @Max(9999)
    @Digits(integer = 4, fraction = 0)
    Integer pin;
}
