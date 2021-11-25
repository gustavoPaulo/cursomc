package com.g.cursomc.services.validation;

import com.g.cursomc.domain.enums.TipoCliente;
import com.g.cursomc.domain.enums.TipoTextoPadrao;
import com.g.cursomc.dto.ClienteNewDTO;
import com.g.cursomc.exception.FieldMessage;
import com.g.cursomc.services.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Override
    public void initialize(ClienteInsert constraintAnnotation) {
    }

    @Override
    public boolean isValid(ClienteNewDTO clienteNewDTO, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> list = new ArrayList<>();

        if (clienteNewDTO.getTipo().equals(TipoCliente.PESSOA_FISICA.getCodigo())
                && !BR.isValidCPF(clienteNewDTO.getCpfCnpj())) {
            list.add(new FieldMessage("cpfCnpj", TipoTextoPadrao.CPF_INVALIDO.getDescricao()));
        }

        if (clienteNewDTO.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCodigo())
                && !BR.isValidCNPJ(clienteNewDTO.getCpfCnpj())) {
            list.add(new FieldMessage("cpfCnpj", TipoTextoPadrao.CNPJ_INVALIDO.getDescricao()));
        }

        list.forEach(erro -> {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(erro.getMessage())
                    .addPropertyNode(erro.getFieldName()).addConstraintViolation();
        });

        return list.isEmpty();
    }
}
