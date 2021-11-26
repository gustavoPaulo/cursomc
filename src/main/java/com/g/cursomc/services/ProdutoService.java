package com.g.cursomc.services;

import com.g.cursomc.domain.Produto;
import com.g.cursomc.repositories.CategoriaRepository;
import com.g.cursomc.repositories.ProdutoRepository;
import com.g.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto find(Integer id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto " + Produto.class.getSimpleName() + " com o ID " + id + ", não encontrado!"));
    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage,
                                String direction, String orderBy) {
        return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categoriaRepository.findAllById(ids),
                PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy));
    }
}
