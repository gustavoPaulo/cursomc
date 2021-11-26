package com.g.cursomc.resources;

import com.g.cursomc.domain.Produto;
import com.g.cursomc.dto.ProdutoDTO;
import com.g.cursomc.resources.utils.URL;
import com.g.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/{id}")
    public ResponseEntity<Produto> find(@PathVariable Integer id) {
        Produto produto = produtoService.find(id);
        return ResponseEntity.ok().body(produto);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "nome", defaultValue = "") String nome,
             @RequestParam(value = "categorias", defaultValue = "") String categorias,
             @RequestParam(value = "page", defaultValue = "0") Integer page,
             @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
             @RequestParam(value = "direction", defaultValue = "ASC") String direction,
             @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {

        return ResponseEntity.ok().body(produtoService.search(URL.decodeParam(nome), URL.decodeIntList(categorias),
                page, linesPerPage, direction, orderBy).map(produto -> new ProdutoDTO(produto)));
    }
}
