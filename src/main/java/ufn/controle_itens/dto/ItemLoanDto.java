package ufn.controle_itens.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ItemLoanDto {
    private String itemNome;
    private String itemCodigo;
    private String usuarioNome;
    private String usuarioCodigo;
    private LocalDateTime dataEmprestimo;

    public ItemLoanDto(String nome, String codigo, String usuarioNome, String usuarioCodigo, LocalDateTime dataEmprestimo) {
        this.itemNome = nome;
        this.itemCodigo = codigo;
        this.usuarioNome = usuarioNome;
        this.usuarioCodigo = usuarioCodigo;
        this.dataEmprestimo = dataEmprestimo;
    }
}
