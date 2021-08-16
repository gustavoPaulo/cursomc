package com.g.cursomc.resources;

import com.g.cursomc.domain.Categoria;
import com.g.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/{codigo}")
    public ResponseEntity<Categoria> buscarPorCodigo(@PathVariable Integer codigo) {
        Optional<Categoria> categoria = categoriaService.buscarPorCodigo(codigo);

        return categoria.isPresent() ? ResponseEntity.ok(categoria.get())
                : ResponseEntity.noContent().build();
    }
}
