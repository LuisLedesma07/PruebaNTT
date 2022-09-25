package com.luisledesma.app.models.services;

import com.luisledesma.app.models.dao.IClienteDao;
import com.luisledesma.app.models.entity.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ClienteServiceImplTest {

    @Mock
    private IClienteDao clienteDao;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        cliente = new Cliente();
        cliente.setId(1);
        cliente.setTipoDoc("C");
        cliente.setNumeroDoc(23445322);
        cliente.setPrimerApellido("Ledesma");
        cliente.setSegundoApellido("Mariño");
        cliente.setPrimerNombre("Luis");
        cliente.setSegundoNombre("David");
        cliente.setCiudad("Pereira");
        cliente.setDireccion("Dosquebradas 26");
        cliente.setTelefono(335);
    }

  /* @Test
    void findAll() {



       /when(clienteDao.findAll()).thenReturn(Arrays.asList(cliente));
        assertNotNull(clienteService.findAll());
    }

    @Test
    void findById() {

    }

    @Test
    void save() {
    }*/
}