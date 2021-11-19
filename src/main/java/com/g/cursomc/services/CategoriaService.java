package com.g.cursomc.services;

import com.g.cursomc.domain.Categoria;
import com.g.cursomc.exceptionhandler.ObjectNotFoundExcepton;
import com.g.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscarPorId(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundExcepton(Categoria.class.getSimpleName(), id));
    }

    public Categoria inserir(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
}
