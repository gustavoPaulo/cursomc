package com.g.cursomc.services.validation;

import com.g.cursomc.domain.Cliente;
import com.g.cursomc.domain.enums.TipoTextoPadrao;
import com.g.cursomc.dto.ClienteDTO;
import com.g.cursomc.exception.FieldMessage;
import com.g.cursomc.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HttpServletRequest request;

    private static List<FieldMessage> list = new ArrayList<>();

    @Override
    public void initialize(ClienteUpdate constraintAnnotation) {
    }

    @Override
    public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext constraintValidatorContext) {
        validaEmail(clienteDTO);

        list.forEach(erro -> {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(erro.getMessage())
                    .addPropertyNode(erro.getFieldName()).addConstraintViolation();
        });

        return list.isEmpty();
    }

    private void validaEmail(ClienteDTO clienteDTO) {
        Map<String, String> map = (Map<String, String>)
                request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        Cliente clienteAux = clienteRepository.findByEmail(clienteDTO.getEmail());

        if (clienteAux != null && !clienteAux.getId().equals(Integer.parseInt(map.get("id"))) ) {
            list.add(new FieldMessage("email", TipoTextoPadrao.EMAIL_JA_EXISTENTE.getDescricao()));
        }
    }
}
