package ufn.controle_itens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufn.controle_itens.model.LoanLog;

public interface LoanLogRepository extends JpaRepository<LoanLog, Long> {
}
