package me.pro0xy.bankemulation.db.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import me.pro0xy.bankemulation.currency.Currency;
import me.pro0xy.bankemulation.transaction.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 21:35</p>
 */

@Entity
@Table(name = "bank_transactions")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @ManyToOne
    @JoinColumn(name = "from_card_id")
    Card fromCard;

    @ManyToOne
    @JoinColumn(name = "to_card_id")
    Card toCard;

    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    Currency currency;

    LocalDateTime transactionDate;

    String comment;

    @Enumerated(EnumType.STRING)
    TransactionStatus status;

    String cancelReason;
}
