package com.g.cursomc.services;

import com.g.cursomc.domain.Pedido;
import com.g.cursomc.services.exceptions.ObjectNotFoundException;
import com.g.cursomc.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido find(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto " + Pedido.class.getSimpleName() + " com o ID " + id + ", não encontrado!"));
    }
}
