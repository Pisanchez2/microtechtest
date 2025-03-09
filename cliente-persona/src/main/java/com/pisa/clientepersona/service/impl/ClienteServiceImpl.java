package com.pisa.clientepersona.service.impl;

import com.pisa.clientepersona.domain.Cliente;
import com.pisa.clientepersona.repository.ClienteRepository;
import com.pisa.clientepersona.service.ClienteService;
import com.pisa.clientepersona.service.mapper.ClienteMapper;
import com.pisa.common.shared.dto.ClienteDTO;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public List<ClienteDTO> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.toDto(clientes);
    }

    public ClienteDTO getClienteById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado")
        );
        return clienteMapper.toDto(cliente);
    }

    public ClienteDTO getClienteByIdForRabbit(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(clienteMapper::toDto).orElse(null);
    }

    public ClienteDTO saveCliente(ClienteDTO clienteDTO) {
        Optional<Cliente> optionalCliente = clienteRepository.findClienteByIdentificacion(clienteDTO.getIdentificacion());
        if (optionalCliente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cliente con identificacion " + clienteDTO.getIdentificacion() + " ya existe");
        }
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente savedCliente = clienteRepository.save(cliente);
        return clienteMapper.toDto(savedCliente);
    }

    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado")
        );

        clienteMapper.updateClienteFromDto(clienteDTO, cliente);
        Cliente updatedCliente = clienteRepository.save(cliente);
        return clienteMapper.toDto(updatedCliente);

    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}