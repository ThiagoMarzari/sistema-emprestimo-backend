package ufn.controle_itens.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoanLogDto {
    private Long id;
    private String tipo;
    private String itemNome;
    private String itemCodigo;
    private String usuarioNome;
    private String usuarioCodigo;
    private LocalDateTime dataEmprestimo;

    public LoanLogDto(String tipo, String nome, String codigo, String nome1, String codigo1, LocalDateTime dataEmprestimo) {
        this.tipo = tipo;
        this.itemNome = nome;
        this.itemCodigo = codigo;
        this.usuarioNome = nome1 != null ? nome1 : "Desconhecido";
        this.usuarioCodigo = codigo1 != null ? codigo1 : "Desconhecido";
        this.dataEmprestimo = dataEmprestimo;
    }
}
