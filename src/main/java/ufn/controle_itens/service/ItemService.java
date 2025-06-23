package ufn.controle_itens.service;

import org.springframework.stereotype.Service;
import ufn.controle_itens.dto.ItemDto;
import ufn.controle_itens.dto.LoanLogDto;
import ufn.controle_itens.model.Item;
import ufn.controle_itens.model.LoanLog;
import ufn.controle_itens.model.User;
import ufn.controle_itens.repository.ItemRepository;
import ufn.controle_itens.repository.LoanLogRepository;
import ufn.controle_itens.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final LoanLogRepository loanLogRepository;

    public ItemService(ItemRepository itemRepository, UserRepository userRepository, LoanLogRepository loanLogRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.loanLogRepository = loanLogRepository;
    }

    public Item salvar(ItemDto itemDto){
        try {
        Item item = new Item();
        item.setNome(itemDto.getNome());
        item.setCodigo(itemDto.getCodigo());
        return itemRepository.save(item);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o item: " + e.getMessage());
        }
    }

    public List<Item> listar() {
        return itemRepository.findAll().reversed();
    }

    public Item deletar(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
        itemRepository.delete(item);
        return item;
    }

    public void registarMovimentacoes(String codigoItem, String codigoUsuario) {
        Item item = itemRepository.findByCodigo(codigoItem)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        User usuario = userRepository.findByCodigo(codigoUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        //Empresta o item para o usuário
        if (item.isDisponivel()) {
            item.setDisponivel(false);
            item.setUsuarioAtual(usuario);

            LoanLog loanLog = new LoanLog();
            loanLog.setItem(item);
            loanLog.setUsuario(usuario);
            loanLog.setTipo("EMPRESTIMO");
            loanLog.setDataEmprestimo(LocalDateTime.now());

            loanLogRepository.save(loanLog);

            System.out.println("Item emprestado: " + item.getNome() + " para usuário: " + usuario.getNome());
        } else {
            //Devolve o item
            item.setDisponivel(true);
            item.setUsuarioAtual(null);

            LoanLog loanLog = new LoanLog();
            loanLog.setItem(item);
            loanLog.setUsuario(usuario);
            loanLog.setTipo("DEVOLUÇAO");
            loanLog.setDataEmprestimo(LocalDateTime.now());

            loanLogRepository.save(loanLog);

            System.out.println("Item devolvido: " + item.getNome());
        }

        itemRepository.save(item);
    }

    public List<ItemDto> listarItensEmprestados() {

//        List<Item> itens = itemRepository.findAll();
//        List<ItemDto> dtos = new ArrayList<>();
//
//        for (Item item : itens) {
//            dtos.add(new ItemDto(item.getNome(), item.getCodigo()));
//        }
//
//        Collections.reverse(dtos); // Inverter a lista
//        return dtos;

        List<Item> itens = itemRepository.findAll();

        // Filtra os itens que estão emprestados (não disponíveis)
        itens = itens.stream().filter(item -> !item.isDisponivel()).toList();

        return itens.stream().map(item -> new ItemDto(
                item.getNome(),
                item.getCodigo(),
                item.getUsuarioAtual().getNome(),
                item.getUsuarioAtual().getCodigo()
        )).toList().reversed();
    }

    public List<LoanLogDto> listarLogs() {
        List<LoanLog> logs = loanLogRepository.findAll(); // ou filtrado por data etc.

        return logs.stream().map(log -> new LoanLogDto(
                log.getTipo(),
                log.getItem().getNome(),
                log.getItem().getCodigo(),
                log.getUsuario().getNome(),
                log.getUsuario().getCodigo(),
                log.getDataEmprestimo())).toList().reversed();
    }
}
