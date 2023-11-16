package me.pro0xy.bankemulation.db.repo;

import lombok.NonNull;
import me.pro0xy.bankemulation.db.entity.Card;
import me.pro0xy.bankemulation.db.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 01:26</p>
 */

public interface CardRepository extends CrudRepository<Card, Long> {
    List<Card> findAllByUser(@NonNull User user);

    Optional<Card> findByCardNumber(@NonNull String cardNumber);
}
