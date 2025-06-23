package ufn.controle_itens.dto;

import lombok.Data;
import ufn.controle_itens.model.User;

@Data
public class ItemDto {

    private String nome;

    private String codigo;

    private boolean disponivel;

    public ItemDto(String nome, String codigo, boolean disponivel) {
        this.nome = nome;
        this.codigo = codigo;
        this.disponivel = disponivel;
    }
}
