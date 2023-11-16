package me.pro0xy.bankemulation.db.repo;

import me.pro0xy.bankemulation.db.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 21:45</p>
 */

public interface TransactionRepository extends CrudRepository<Transaction, UUID> {
}
