package ufn.controle_itens.dto;

import lombok.Data;

@Data
public class ItemLoanDto {
    private String nome;
    private String codigo;
    private String usuarioNome;
    private String usuarioCodigo;

    public ItemLoanDto(String nome, String codigo, String usuarioNome, String usuarioCodigo) {
        this.nome = nome;
        this.codigo = codigo;
        this.usuarioNome = usuarioNome;
        this.usuarioCodigo = usuarioCodigo;
    }
}
