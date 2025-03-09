package com.pisa.clientepersona;

import com.pisa.clientepersona.domain.Cliente;
import com.pisa.clientepersona.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class ClienteRepositoryIntegrationTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testSaveAndRetrieveCliente() {
        Cliente cliente = new Cliente();

        cliente.setNombre("Pablo Sanchez");
        cliente.setGenero("F");
        cliente.setEdad(25);
        cliente.setIdentificacion("987654321");
        cliente.setDireccion("Las Nubes");
        cliente.setTelefono("0987654321");
        cliente.setContraseña("1234");
        cliente.setEstado(true);

        Cliente savedCliente = clienteRepository.save(cliente);

        Optional<Cliente> retrievedCliente = clienteRepository.findById(savedCliente.getId());

        assertThat(retrievedCliente).isPresent();
        assertThat(retrievedCliente.get().getNombre()).isEqualTo(cliente.getNombre());
        assertThat(retrievedCliente.get().getGenero()).isEqualTo(cliente.getGenero());
        assertThat(retrievedCliente.get().getEdad()).isEqualTo(cliente.getEdad());
        assertThat(retrievedCliente.get().getIdentificacion()).isEqualTo(cliente.getIdentificacion());
        assertThat(retrievedCliente.get().getDireccion()).isEqualTo(cliente.getDireccion());
        assertThat(retrievedCliente.get().getTelefono()).isEqualTo(cliente.getTelefono());
        assertThat(retrievedCliente.get().getContraseña()).isEqualTo(cliente.getContraseña());
        assertThat(retrievedCliente.get().isEstado()).isTrue();
    }
}