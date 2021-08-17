package com.g.cursomc.services;

import com.g.cursomc.domain.Cliente;
import com.g.cursomc.exceptionhandler.ObjectNotFoundExcepton;
import com.g.cursomc.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscarPorId(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundExcepton(Cliente.class.getSimpleName(), id));
    }
}
