package com.pisa.cuentamovimiento.service.impl;

import com.pisa.common.shared.dto.ClienteDTO;
import com.pisa.cuentamovimiento.domain.Cuenta;
import com.pisa.cuentamovimiento.repository.CuentaRepository;
import com.pisa.cuentamovimiento.service.ClientePersonaCommunicationService;
import com.pisa.cuentamovimiento.service.CuentaService;
import com.pisa.cuentamovimiento.service.dto.CuentaDTO;
import com.pisa.cuentamovimiento.service.mapper.CuentaMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final CuentaMapper cuentaMapper;
    private final ClientePersonaCommunicationService clientePersonaCommunicationService;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, CuentaMapper cuentaMapper, ClientePersonaCommunicationService clientePersonaCommunicationService) {
        this.cuentaRepository = cuentaRepository;
        this.cuentaMapper = cuentaMapper;
        this.clientePersonaCommunicationService = clientePersonaCommunicationService;
    }


    public List<CuentaDTO> getAllCuentas() {
        List<Cuenta> cuentas = cuentaRepository.findAll();
        return cuentas.stream().map(cuentaMapper::toDto).collect(Collectors.toList());
    }

    public CuentaDTO getCuentaById(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cuenta no encontrada"));
        return cuentaMapper.toDto(cuenta);
    }

    @Override
    public List<CuentaDTO> getCuentasByClientId(Long id) {
        List<Cuenta> cuentas = cuentaRepository.getCuentasByClienteId(id);
        return cuentaMapper.toDto(cuentas);
    }

    public CuentaDTO getCuentaByNumeroCuenta(String numeroCuenta) {
        Cuenta cuenta = cuentaRepository.getTopByNumeroCuentaOrderByIdDesc(numeroCuenta).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cuenta no encontrada"));
        return cuentaMapper.toDto(cuenta);
    }

    public CuentaDTO saveCuenta(CuentaDTO cuentaDTO) {
        clientePersonaCommunicationService.getClienteById(cuentaDTO.getClienteId());
        Optional<Cuenta> optionalCuenta = cuentaRepository.getCuentaByNumeroCuenta(cuentaDTO.getNumeroCuenta());
        if(optionalCuenta.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cuenta con numero de cuenta " + cuentaDTO.getNumeroCuenta() + " ya existe");
        }
        Cuenta cuenta = cuentaMapper.toEntity(cuentaDTO);
        Cuenta savedCuenta = cuentaRepository.save(cuenta);
        return cuentaMapper.toDto(savedCuenta);
    }

    public CuentaDTO updateCuenta(Long id, CuentaDTO cuentaDTO) {
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cuenta no encontrada"));

        cuentaMapper.updateCuentaFromDto(cuentaDTO, cuenta);
        Cuenta updatedCuenta = cuentaRepository.save(cuenta);
        return cuentaMapper.toDto(updatedCuenta);
    }

    public void deleteCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }
}