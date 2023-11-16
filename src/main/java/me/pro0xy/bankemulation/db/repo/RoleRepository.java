package me.pro0xy.bankemulation.db.repo;

import me.pro0xy.bankemulation.db.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 02:28</p>
 */

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
