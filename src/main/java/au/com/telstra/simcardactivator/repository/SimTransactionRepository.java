package au.com.telstra.simcardactivator.repository;

import au.com.telstra.simcardactivator.domain.SimTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimTransactionRepository extends JpaRepository<SimTransaction, Long> { }
