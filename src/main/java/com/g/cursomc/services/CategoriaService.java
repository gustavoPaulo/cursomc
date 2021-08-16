package com.g.cursomc.services;

import com.g.cursomc.domain.Categoria;
import com.g.cursomc.repositoryes.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Optional<Categoria> buscarPorCodigo(Integer codigo) {
        return categoriaRepository.findById(codigo);
    }
}
