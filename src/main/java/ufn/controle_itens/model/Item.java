package ufn.controle_itens.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String codigo;

    private boolean disponivel = true;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuarioAtual;
}
