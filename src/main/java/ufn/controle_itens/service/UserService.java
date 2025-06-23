package ufn.controle_itens.service;

import org.springframework.stereotype.Service;
import ufn.controle_itens.dto.LoanLogDto;
import ufn.controle_itens.dto.UserDto;
import ufn.controle_itens.model.LoanLog;
import ufn.controle_itens.model.User;
import ufn.controle_itens.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User salvar(UserDto userDto){
        try {
        User user = new User();
        user.setNome(userDto.getNome());
        user.setCodigo(userDto.getCodigo());

        return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o usuário: " + e.getMessage());
        }

    }

    public List<UserDto> listar() {

//        List<LoanLog> logs = loanLogRepository.findAll(); // ou filtrado por data etc.
//
//        return logs.stream().map(log -> new LoanLogDto(
//                log.getTipo(),
//                log.getItem().getNome(),
//                log.getItem().getCodigo(),
//                log.getUsuario().getNome(),
//                log.getUsuario().getCodigo(),
//                log.getDataEmprestimo())).toList().reversed();
//    }
        List<User> users = userRepository.findAll();

        return users.stream().map(user -> new UserDto(
                user.getNome(),
                user.getCodigo()
        )).toList().reversed();
    }
}
