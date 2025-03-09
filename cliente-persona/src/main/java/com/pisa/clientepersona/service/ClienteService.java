package com.pisa.clientepersona.service;


import com.pisa.common.shared.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {

    public List<ClienteDTO> getAllClientes();

    public ClienteDTO getClienteById(Long id);

    public ClienteDTO getClienteByIdForRabbit(Long id);

    public ClienteDTO saveCliente(ClienteDTO clienteDTO);

    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO);

    public void deleteCliente(Long id);
}
