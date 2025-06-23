package ufn.controle_itens.service;

import org.springframework.stereotype.Service;
import ufn.controle_itens.dto.*;
import ufn.controle_itens.model.Item;
import ufn.controle_itens.model.Loan;
import ufn.controle_itens.model.User;
import ufn.controle_itens.repository.ItemRepository;
import ufn.controle_itens.repository.LoanRepository;
import ufn.controle_itens.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final LoanRepository loanRepository;

    public ItemService(ItemRepository itemRepository, UserRepository userRepository, LoanRepository loanRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
    }

    public Item salvar(ItemDto itemDto) {
        try {
            Item item = new Item();
            item.setNome(itemDto.getNome());
            item.setCodigo(itemDto.getCodigo());
            return itemRepository.save(item);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o item: " + e.getMessage());
        }
    }

    public List<ItemDto> listar() {
        List<Item> itens = itemRepository.findAll();

        List<ItemDto> itemDtos = itens.stream()
                .map(item -> {
                    String status = item.isDisponivel() ? "Disponível" : "Indisponível";
                    String usuarioNome = "N/A";

                    // Se o item estiver emprestado, tentamos pegar o último usuário da lista de empréstimos
                    if (!item.isDisponivel() && item.getLoans() != null && !item.getLoans().isEmpty()) {
                        // Pegamos o último empréstimo (o mais recente)
                        Loan ultimoLoan = item.getLoans().get(item.getLoans().size() - 1);
                        usuarioNome = ultimoLoan.getUsuario().getNome();
                    }

                    return new ItemDto(
                            item.getNome(),
                            item.getCodigo(),
                            item.isDisponivel()
                    );
                })
                .toList();

        // Retorna a lista invertida (mais recente no topo)
        return itemDtos.reversed();
    }

    public Item deletar(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
        itemRepository.delete(item);
        return item;
    }

    public void emprestar(EmprestimoRequestDto dto) {
        Item item = itemRepository.findByCodigo(dto.getItemCodigo())
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        if (!item.isDisponivel()) {
            throw new RuntimeException("Item já está emprestado");
        }

        User usuario = userRepository.findByCodigo(dto.getUsuarioCodigo())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        item.setDisponivel(false);

        Loan loan = new Loan();
        loan.setItem(item);
        loan.setUsuario(usuario);
        loan.setTipo("EMPRESTIMO");
        loan.setDataHora(LocalDateTime.now());

        loanRepository.save(loan);
        itemRepository.save(item);

        System.out.println("Item emprestado: " + item.getNome() + " para usuário: " + usuario.getNome());
    }

    public void devolver(DevolverRequestDto dto) {
        Item item = itemRepository.findByCodigo(dto.getItemCodigo())
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        if (item.isDisponivel()) {
            throw new RuntimeException("Item já está disponível");
        }

        item.setDisponivel(true);

        // Buscar o último empréstimo do item
        Loan ultimoEmprestimo = loanRepository.findTopByItemAndTipoOrderByDataHoraDesc(item, "EMPRESTIMO");

        Loan loan = new Loan();
        loan.setItem(item);
        loan.setUsuario(ultimoEmprestimo != null ? ultimoEmprestimo.getUsuario() : null);
        loan.setTipo("DEVOLUCAO");
        loan.setDataHora(LocalDateTime.now());

        loanRepository.save(loan);
        itemRepository.save(item);

        System.out.println("Item devolvido: " + item.getNome());
    }

    public List<ItemLoanDto> listarItensEmprestados() {
        return itemRepository.findAll().stream()
                .filter(item -> !item.isDisponivel())
                .map(item -> {
                    String usuarioNome = "Desconhecido";
                    String usuarioCodigo = "N/A";

                    if (item.getLoans() != null && !item.getLoans().isEmpty()) {
                        Loan ultimoLoan = item.getLoans().get(item.getLoans().size() - 1);
                        if (ultimoLoan.getUsuario() != null) {
                            usuarioNome = ultimoLoan.getUsuario().getNome();
                            usuarioCodigo = ultimoLoan.getUsuario().getCodigo();
                        }
                    }

                    return new ItemLoanDto(
                            item.getNome(),
                            item.getCodigo(),
                            usuarioNome,
                            usuarioCodigo
                    );
                })
                .toList()
                .reversed();
    }

    public List<LoanLogDto> listarLogs() {
        List<Loan> logs = loanRepository.findAll();

        return logs.stream()
                .map(log -> new LoanLogDto(
                        log.getTipo(),
                        log.getItem().getNome(),
                        log.getItem().getCodigo(),
                        log.getUsuario() != null ? log.getUsuario().getNome() : "Desconhecido",
                        log.getUsuario() != null ? log.getUsuario().getCodigo() : "N/A",
                        log.getDataHora()))
                .toList().reversed();
    }
}
