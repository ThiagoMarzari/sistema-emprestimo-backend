package ufn.controle_itens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ufn.controle_itens.model.Item;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    Optional<Item> findByCodigo(String codigo);
    @Query("select t from Item t where t.habilitado = true")
    List<Item> buscaItensHabilitados();

    @Query("select t from Item t where t.habilitado = false")
    List<Item> buscarItensDesabilitados();
}
