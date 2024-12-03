package com.fiap.tc.ms.gestao_clientes.service;

import com.fiap.tc.ms.gestao_clientes.dto.request.CadastrarClienteRequest;
import com.fiap.tc.ms.gestao_clientes.dto.response.ClienteDeletadoResponse;
import com.fiap.tc.ms.gestao_clientes.dto.response.ClienteResponse;
import com.fiap.tc.ms.gestao_clientes.exceptions.ClienteNotFoundException;
import com.fiap.tc.ms.gestao_clientes.mapper.ClienteMapper;
import com.fiap.tc.ms.gestao_clientes.model.Cliente;
import com.fiap.tc.ms.gestao_clientes.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    private ClienteResponse clienteResponse;

    @BeforeEach
    void setUp() {
        clienteResponse = new ClienteResponse(1L, "Lucas", 14, "03335923014", "lucas@gmail.com", "QUintino Bocaiuva", "99701520");
    }

    @Test
    void deveCadastrarCliente() {
        CadastrarClienteRequest cadastrarClienteRequest = new CadastrarClienteRequest("Lucas", "lucas@gmail.com", "03335923014", 14, "Quintino Bocaiuva", "99701520");
        Cliente cliente = new Cliente(1L, "Lucas", "lucas@gmail.com", "03335923014", 14, "Quintino Bocaiuva", "99701520");

        Mockito.when(clienteMapper.toCliente(cadastrarClienteRequest)).thenReturn(cliente);
        Mockito.when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        Mockito.when(clienteMapper.toClienteResponse(any(Cliente.class))).thenReturn(clienteResponse);

        ClienteResponse response = clienteService.cadastrarCliente(cadastrarClienteRequest);

        assertNotNull(response);
        assertEquals("Lucas", response.nome());
        assertEquals(14, response.idade());
    }

    @Test
    void deveBuscarClientePorId() {

        Cliente cliente = new Cliente(1L, "Lucas", "lucas@gmail.com", "03335923014", 14, "Quintino Bocaiuva", "99701520");
        Mockito.when(clienteRepository.findById(anyLong())).thenReturn(java.util.Optional.of(cliente));


        Mockito.when(clienteMapper.toClienteResponse(any(Cliente.class))).thenReturn(clienteResponse);

        ClienteResponse response = clienteService.buscarClientePorId(1L);

        assertNotNull(response);
        assertEquals(1L, response.clienteId());
    }

    @Test
    void deveListarClientes() {
        // Configuração do mock
        Cliente cliente = new Cliente(1L, "Lucas", "lucas@gmail.com", "03335923014", 14, "Quintino Bocaiuva", "99701520");
        Page<Cliente> clientes = new PageImpl<>(Collections.singletonList(cliente));

        Mockito.when(clienteRepository.findAll(any(Pageable.class))).thenReturn(clientes);

        ClienteResponse clienteResponse = new ClienteResponse(1L, "Lucas", 14, "03335923014", "lucas@gmail.com", "Quintino Bocaiuva", "99701520");
        Mockito.mockStatic(ClienteMapper.class).when(() -> ClienteMapper.toClienteResponse(cliente)).thenReturn(clienteResponse);

        // Execução
        Page<ClienteResponse> result = clienteService.listarClientes(PageRequest.of(0, 10));

        // Validação
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.getTotalElements());
        assertEquals("Lucas", result.getContent().get(0).nome());
    }

    @Test
    void deveExcluirCliente() {
        Mockito.when(clienteRepository.findById(anyLong())).thenReturn(java.util.Optional.of(new Cliente(1L, "Lucas", "lucas@gmail.com", "03335923014", 14, "Quintino Bocaiuva", "99701520")));

        ClienteDeletadoResponse response = clienteService.excluirCliente(1L);

        assertNotNull(response);
        assertTrue(response.clienteDeletado());
    }

    @Test
    void deveAtualizarCliente() {
        AtualizarClienteRequest atualizarClienteRequest = new AtualizarClienteRequest("Lucas", 13, "033356923014", "Rua B", "99701520", "lucasct1996@gmail.com");
        Cliente clienteExistente = new Cliente(1L, "Lucas", "lucas@gmail.com", "03335923014", 14, "Quintino Bocaiuva", "99701520");

        Mockito.when(clienteRepository.findById(anyLong())).thenReturn(java.util.Optional.of(clienteExistente));
        Mockito.when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteExistente);
        Mockito.when(clienteMapper.toClienteResponse(any(Cliente.class))).thenReturn(clienteResponse);

        ClienteResponse response = clienteService.atualizarCliente(1L, atualizarClienteRequest);

        assertNotNull(response);
        assertEquals(1L, response.clienteId());
        assertEquals("Lucas", response.nome());
    }

    @Test
    void deveLancarExcecaoSeClienteNaoEncontrado() {
        Mockito.when(clienteRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());

        assertThrows(ClienteNotFoundException.class, () -> clienteService.buscarClientePorId(999L));
    }
}
