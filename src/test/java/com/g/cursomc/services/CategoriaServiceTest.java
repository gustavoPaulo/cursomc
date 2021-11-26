package com.g.cursomc.services;

import com.g.cursomc.domain.Categoria;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

public class CategoriaServiceTest {

    private CategoriaService categoriaService;
    private Categoria categoria;

    @Before
    public void setUp() {
        categoria = new Categoria();
    }

    @Test
    public void insert() {
        //cenario
        categoria.setId(1);
        categoria.setNome("Moveis usados");

        //acao
        //Criar uma nova categoria

        //verificacao
        Assert.isTrue(categoria != null);
    }
}
