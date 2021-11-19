package com.g.cursomc.services;

import com.g.cursomc.domain.Pedido;
import com.g.cursomc.exceptionhandler.ObjectNotFoundExcepton;
import com.g.cursomc.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido buscarPorId(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundExcepton(Pedido.class.getSimpleName(), id));
    }
}
