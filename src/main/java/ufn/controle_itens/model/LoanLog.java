package ufn.controle_itens.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class LoanLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // "EMPRESTIMO" | "DEVOLUCAO"

    @ManyToOne
    private Item item;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    private User usuario;

    private LocalDateTime dataEmprestimo;
}
