package com.g.cursomc.resources;

import com.g.cursomc.domain.Categoria;
import com.g.cursomc.dto.CategoriaDTO;
import com.g.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        return ResponseEntity.ok().body(categoriaService.findAll()
                .stream().map(categoria -> new CategoriaDTO(categoria)).collect(Collectors.toList()));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {

        return ResponseEntity.ok().body(categoriaService.findPage(page, linesPerPage, direction, orderBy)
                .map(categoria -> new CategoriaDTO(categoria)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> find(@PathVariable Integer id) {
        Categoria categoria = categoriaService.find(id);
        return ResponseEntity.ok().body(categoria);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO categoriaDto) {
        Categoria categoria = categoriaService.fromDTO(categoriaDto);
        categoria = categoriaService.insert(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(categoria.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO categoriaDto, @PathVariable Integer id) {
        Categoria categoria = categoriaService.fromDTO(categoriaDto);
        categoria.setId(id);
        categoriaService.update(categoria);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
