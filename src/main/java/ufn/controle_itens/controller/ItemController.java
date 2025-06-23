package ufn.controle_itens.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufn.controle_itens.dto.EmprestimoRequestDto;
import ufn.controle_itens.dto.ItemDto;
import ufn.controle_itens.dto.LoanLogDto;
import ufn.controle_itens.model.Item;
import ufn.controle_itens.model.LoanLog;
import ufn.controle_itens.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/itens")
@CrossOrigin(origins = "*") // Permite acesso de qualquer origem
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<?> salvarItem(@RequestBody ItemDto item){
        try {
            return ResponseEntity.ok(itemService.salvar(item));
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(400).body("Código já existente.");
        } catch (Exception ex){
            return ResponseEntity.status(500).body("Erro ao salvar o item: " + ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<?>> listarItens() {
        try {
            List<Item> itens = itemService.listar();
            return ResponseEntity.ok(itens);
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/emprestados")
    public ResponseEntity<List<ItemDto>> listarItensEmprestados() {
        return ResponseEntity.ok(itemService.listarItensEmprestados());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarItem(@PathVariable Long id) {
        try {
            Item itemDeletado = itemService.deletar(id);
            return ResponseEntity.ok(itemDeletado);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body("Item não encontrado: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Erro ao deletar o item: " + ex.getMessage());
        }
    }

    @PostMapping("/movimentacao")
    public ResponseEntity<?> registarMovimentacao(@RequestBody EmprestimoRequestDto dto) {
        try {
            itemService.registarMovimentacoes(dto.getItemCodigo(), dto.getUsuarioCodigo());
            return ResponseEntity.ok("Movimentação registrada com sucesso.");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body("Erro: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Erro ao registrar movimentação: " + ex.getMessage());
        }
    }

    @GetMapping("/logs")
    public ResponseEntity<List<LoanLogDto>> listarLogs() {
        return ResponseEntity.ok(itemService.listarLogs());
    }


}
