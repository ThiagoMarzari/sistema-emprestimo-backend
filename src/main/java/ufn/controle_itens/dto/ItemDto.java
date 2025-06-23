package ufn.controle_itens.dto;

import lombok.Data;
import ufn.controle_itens.model.User;

@Data
public class ItemDto {

    private String nome;

    private String codigo;
    private String usuarioAtual;
    private String codigoUsuario;

    public ItemDto(String nome, String codigo, String usuarioAtual, String codigoUsuario) {
        this.nome = nome;
        this.codigo = codigo;
        this.usuarioAtual = usuarioAtual;
        this.codigoUsuario = codigoUsuario;
    }
}
