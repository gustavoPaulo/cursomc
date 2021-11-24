package com.g.cursomc.services;

import com.g.cursomc.domain.Categoria;
import com.g.cursomc.domain.Cliente;
import com.g.cursomc.domain.enums.TipoTextoPadrao;
import com.g.cursomc.dto.CategoriaDTO;
import com.g.cursomc.services.exceptions.DataIntegrityException;
import com.g.cursomc.services.exceptions.ObjectNotFoundException;
import com.g.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto " + Categoria.class.getSimpleName() + " com o ID " + id + ", não encontrado!"));
    }

    public Categoria insert(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Categoria categoria) {
        return categoriaRepository.save(updateData(find(categoria.getId()), categoria));
    }

    public void delete(Integer id) {
        find(id);

        try {
            categoriaRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(TipoTextoPadrao.ERRO_DATA_INTEGRITY_CATEGORIA.getDescricao());
        }
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
        return categoriaRepository.findAll(PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy));
    }

    public Categoria fromDTO(CategoriaDTO categoriaDto) {
        return new Categoria(categoriaDto.getId(), categoriaDto.getNome());
    }

    private Categoria updateData(Categoria newCategoria, Categoria categoria) {
        newCategoria.setNome(categoria.getNome());
        return newCategoria;
    }
}
