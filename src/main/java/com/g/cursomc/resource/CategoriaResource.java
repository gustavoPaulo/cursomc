package com.g.cursomc.resource;

import com.g.cursomc.domain.Categoria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @GetMapping
    public List<Categoria> lista() {
        Categoria categoria1 = new Categoria(1, "Eletrônicos");
        Categoria categoria2 = new Categoria(2, "Cama e banho");

        List<Categoria> lista = new ArrayList<>();
        lista.add(categoria1);
        lista.add(categoria2);

        return lista;
    }
}
