package com.pisa.clientepersona.service;

import com.pisa.clientepersona.config.RabbitMQConfig;
import com.pisa.common.shared.dto.ClienteDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ClienteMessageListener {

    private final ClienteService clienteService;

    public ClienteMessageListener(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public ClienteDTO handleClienteRequest(Long clienteId) {
        ClienteDTO clienteDTO = clienteService.getClienteByIdForRabbit(clienteId);
        return clienteDTO;
    }
}