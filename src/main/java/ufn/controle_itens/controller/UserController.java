package ufn.controle_itens.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufn.controle_itens.dto.UserDto;
import ufn.controle_itens.service.UserService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*") // Permite acesso de qualquer origem
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> salvarUsuario(@RequestBody UserDto userDto){
        try {
            return ResponseEntity.ok(userService.salvar(userDto));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Erro ao salvar o usuário: " + ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarUsuarios() {
        try {
            return ResponseEntity.ok(userService.listar());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Erro ao listar usuários: " + ex.getMessage());
        }
    }
}
