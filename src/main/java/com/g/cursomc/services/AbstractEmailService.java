package com.g.cursomc.services;

import com.g.cursomc.domain.Pedido;
import com.g.cursomc.domain.enums.TipoTextoPadrao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        sendEmail(prepareSimpleMailMessageFromPedido(pedido));
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(sender);
        message.setTo(pedido.getCliente().getEmail());
        message.setSubject(TipoTextoPadrao.PEDIDO_CONFIMADO.getDescricao());
        message.setSentDate(new Date(System.currentTimeMillis()));
        message.setText(pedido.toString());

        return message;
    }

    protected String htmlFromTemplatePedido(Pedido pedido) {
        Context context = new Context();
        context.setVariable("pedido", pedido);
        return templateEngine.process(TipoTextoPadrao.PATH_CORPO_EMAIL.getDescricao(), context);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
        try {
            sendHtmlEmail(prepareMimeMessageFromPedido(pedido));
        } catch (MessagingException e) {
            sendOrderConfirmationEmail(pedido);
        }
    }

    private MimeMessage prepareMimeMessageFromPedido(Pedido pedido) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(pedido.getCliente().getEmail());
        mimeMessageHelper.setSubject(TipoTextoPadrao.PEDIDO_CONFIMADO.getDescricao());
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        mimeMessageHelper.setText(htmlFromTemplatePedido(pedido), true);

        return mimeMessage;
    }
}
