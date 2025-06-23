package ufn.controle_itens.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "usuario")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String codigo;

    @OneToMany(mappedBy = "usuario")
    private List<Loan> loans;
}
