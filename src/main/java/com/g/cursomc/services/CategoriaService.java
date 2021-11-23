package com.g.cursomc.services;

import com.g.cursomc.domain.Categoria;
import com.g.cursomc.services.exceptions.DataIntegrityException;
import com.g.cursomc.services.exceptions.ObjectNotFoundException;
import com.g.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException(Categoria.class.getSimpleName(), id));
    }

    public Categoria insert(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Categoria categoria) {
        find(categoria.getId());
        return categoriaRepository.save(categoria);
    }

    public void delete(Integer id) {
        find(id);

        try {
            categoriaRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos vinculados.");
        }
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }
}
