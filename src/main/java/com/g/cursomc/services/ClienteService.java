package com.g.cursomc.services;

import com.g.cursomc.domain.Cidade;
import com.g.cursomc.domain.Cliente;
import com.g.cursomc.domain.Endereco;
import com.g.cursomc.domain.enums.TipoCliente;
import com.g.cursomc.domain.enums.TipoTextoPadrao;
import com.g.cursomc.dto.ClienteDTO;
import com.g.cursomc.dto.ClienteNewDTO;
import com.g.cursomc.repositories.ClienteRepository;
import com.g.cursomc.repositories.EnderecoRepository;
import com.g.cursomc.services.exceptions.DataIntegrityException;
import com.g.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto " + Cliente.class.getSimpleName() + " com o ID " + id + ", não encontrado!"));
    }

    @Transactional
    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        cliente = clienteRepository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
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

    public Cliente fromDTO(ClienteNewDTO clienteNewDTO) {
        Cliente cliente = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(),
                clienteNewDTO.getCpfCnpj(), TipoCliente.toEnum(clienteNewDTO.getTipo()));

        cliente.getEnderecos().add(new Endereco(null, clienteNewDTO.getLogradouro(), clienteNewDTO.getNumero(),
                clienteNewDTO.getComplemento(), clienteNewDTO.getBairro(), clienteNewDTO.getCep(), cliente,
                new Cidade(clienteNewDTO.getCidadeId(), null, null)));

        cliente.getTelefones().add(clienteNewDTO.getTelefone1());
        cliente.getTelefones().add(clienteNewDTO.getTelefone2() == null ? null : clienteNewDTO.getTelefone2());
        cliente.getTelefones().add(clienteNewDTO.getTelefone3() == null ? null : clienteNewDTO.getTelefone3());

        return cliente;
    }

    private Cliente updateData(Cliente newCliente, Cliente cliente) {
        newCliente.setNome(cliente.getNome());
        newCliente.setEmail(cliente.getEmail());
        return newCliente;
    }
}
