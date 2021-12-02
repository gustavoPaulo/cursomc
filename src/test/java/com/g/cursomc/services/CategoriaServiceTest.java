package com.g.cursomc.services;

import com.g.cursomc.domain.Categoria;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void crypto() {
        String valor = "gustavo1315";
        String chave = criptogranfandoSenha(valor);
        String chaveErrada = "s6gf5sd4gs654g65sf4g56sdf4g65df4g65dfff5";

        Map<String, String> senhas = new HashMap<>();
        senhas.put(chave, valor);

        if (senhas.get(chaveErrada) == null) {
            System.out.printf("Valor não encontrado para essa chave.");
        } else {
            System.out.printf("Valor encontrado: " + senhas.get(chave));
        }
    }

    private String criptogranfandoSenha(String senha) {
        return "s6gf5sd4gs654g65sf4g56sdf4g65df4g65dfff54";
    }
}
