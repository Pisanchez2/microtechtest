package com.pisa.cuentamovimiento.service;

import com.pisa.common.shared.dto.ClienteDTO;
import com.pisa.cuentamovimiento.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClientePersonaCommunicationService {

    private final RabbitTemplate rabbitTemplate;

    public ClientePersonaCommunicationService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public ClienteDTO getClienteById(Long clienteId) {
        ClienteDTO clienteDTO = (ClienteDTO) rabbitTemplate.convertSendAndReceive(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.CLIENTE_ROUTING_KEY,
                clienteId
        );

        if (clienteDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo obtener la información del cliente con ID " + clienteId);
        }
        return clienteDTO;
    }
}