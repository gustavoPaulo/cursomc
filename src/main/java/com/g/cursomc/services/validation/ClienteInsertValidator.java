package com.g.cursomc.services.validation;

import com.g.cursomc.domain.enums.TipoCliente;
import com.g.cursomc.domain.enums.TipoTextoPadrao;
import com.g.cursomc.dto.ClienteNewDTO;
import com.g.cursomc.exception.FieldMessage;
import com.g.cursomc.repositories.ClienteRepository;
import com.g.cursomc.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    private static List<FieldMessage> list = new ArrayList<>();

    @Override
    public void initialize(ClienteInsert constraintAnnotation) {
    }

    @Override
    public boolean isValid(ClienteNewDTO clienteNewDTO, ConstraintValidatorContext constraintValidatorContext) {
        validaCpfCnpj(clienteNewDTO);
        validaEmail(clienteNewDTO.getEmail());

        list.forEach(erro -> {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(erro.getMessage())
                    .addPropertyNode(erro.getFieldName()).addConstraintViolation();
        });

        return list.isEmpty();
    }

    private void validaCpfCnpj(ClienteNewDTO clienteNewDTO) {
        if (clienteNewDTO.getTipo().equals(TipoCliente.PESSOA_FISICA.getCodigo())
                && !BR.isValidCPF(clienteNewDTO.getCpfCnpj())) {
            list.add(new FieldMessage("cpfCnpj", TipoTextoPadrao.CPF_INVALIDO.getDescricao()));
        }

        if (clienteNewDTO.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCodigo())
                && !BR.isValidCNPJ(clienteNewDTO.getCpfCnpj())) {
            list.add(new FieldMessage("cpfCnpj", TipoTextoPadrao.CNPJ_INVALIDO.getDescricao()));
        }
    }

    private void validaEmail(String email) {
        if (clienteRepository.findByEmail(email) != null) {
            list.add(new FieldMessage("email", TipoTextoPadrao.EMAIL_JA_EXISTENTE.getDescricao()));
        }
    }
}
