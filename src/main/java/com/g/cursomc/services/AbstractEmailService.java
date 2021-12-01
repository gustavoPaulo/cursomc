package com.g.cursomc.services;

import com.g.cursomc.domain.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        sendEmail(prepareSimpleMailMessageFromPedido(pedido));
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(sender);
        message.setTo(pedido.getCliente().getEmail());
        message.setSubject("Pedido confirmado! Código: " + pedido.getId());
        message.setSentDate(new Date(System.currentTimeMillis()));
        message.setText(pedido.toString());

        return message;
    }
}
