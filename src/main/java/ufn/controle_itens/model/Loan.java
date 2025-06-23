package ufn.controle_itens.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Item item;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;


    private String tipo; // "EMPRESTIMO" ou "DEVOLUCAO"

    private LocalDateTime dataHora;
}
