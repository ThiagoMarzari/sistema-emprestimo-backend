package ufn.controle_itens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufn.controle_itens.model.Item;
import ufn.controle_itens.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long >{
    Loan findTopByItemAndTipoOrderByDataHoraDesc(Item item, String tipo);
}
