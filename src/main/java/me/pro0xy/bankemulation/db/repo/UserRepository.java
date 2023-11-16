package me.pro0xy.bankemulation.db.repo;

import lombok.NonNull;
import me.pro0xy.bankemulation.db.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 01:38</p>
 */

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByEmail(@NonNull String email);
    Optional<User> findByEmail(@NonNull String email);
}
