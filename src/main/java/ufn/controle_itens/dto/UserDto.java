package ufn.controle_itens.dto;

import lombok.Data;

@Data
public class UserDto {

    private String nome;

    private String codigo;

    public UserDto(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }
}
