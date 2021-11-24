package com.g.cursomc.services;

import com.g.cursomc.domain.Cliente;
import com.g.cursomc.domain.enums.TipoTextoPadrao;
import com.g.cursomc.dto.ClienteDTO;
import com.g.cursomc.repositories.ClienteRepository;
import com.g.cursomc.services.exceptions.DataIntegrityException;
import com.g.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente find(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto " + Cliente.class.getSimpleName() + " com o ID " + id + ", não encontrado!"));
    }

    public Cliente update(Cliente cliente) {
        return clienteRepository.save(updateData(find(cliente.getId()), cliente));
    }

    public void delete(Integer id) {
        find(id);

        try {
            clienteRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(TipoTextoPadrao.ERRO_DATA_INTEGRITY_CLIENTE.getDescricao());
        }
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
        return clienteRepository.findAll(PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy));
    }

    public Cliente fromDTO(ClienteDTO clienteDto) {
        return new Cliente(clienteDto.getId(), clienteDto.getNome(), clienteDto.getEmail(), null, null);
    }

    private Cliente updateData(Cliente newCliente, Cliente cliente) {
        newCliente.setNome(cliente.getNome());
        newCliente.setEmail(cliente.getEmail());
        return newCliente;
    }
}
