package com.fiap.tc.ms.gestao_clientes.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.tc.ms.gestao_clientes.dto.request.CadastrarClienteRequest;
import com.fiap.tc.ms.gestao_clientes.dto.response.ClienteDeletadoResponse;
import com.fiap.tc.ms.gestao_clientes.dto.response.ClienteResponse;
import com.fiap.tc.ms.gestao_clientes.service.AtualizarClienteRequest;
import com.fiap.tc.ms.gestao_clientes.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;


import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
@ExtendWith(SpringExtension.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    private static final String BASE_URL = "/clientes";


    @Test
    void deveListarClientes() throws Exception {
        Page<ClienteResponse> page = new PageImpl<>(List.of(
                new ClienteResponse(1L, "Lucas", 13, "03335923014", "lucas@email.com", "Rua A", "99701520")
        ));

        Mockito.when(clienteService.listarClientes(Mockito.any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].clienteId").value(1L))  // Mudança aqui
                .andExpect(jsonPath("$.content[0].nome").value("Lucas"))
                .andExpect(jsonPath("$.content[0].idade").value(13))
                .andExpect(jsonPath("$.content[0].cpf").value("03335923014"))
                .andExpect(jsonPath("$.content[0].endereco").value("Rua A"))
                .andExpect(jsonPath("$.content[0].email").value("lucas@email.com"))
                .andExpect(jsonPath("$.content[0].cep").value("99701520"));
    }

    @Test
    void deveSalvarCliente() throws Exception {
        ClienteResponse clienteResponse = new ClienteResponse(1L, "Lucas", 13, "03335923014", "lucas@gmail.com", "Quintino Bocaiuva", "99701520");
        CadastrarClienteRequest clienteRequest = new CadastrarClienteRequest(
                "Lucas",
                "lucas@gmail.com",
                "03335923014",
                17,
                "Quintino Bocaiuva",
                "99701520"
        );

        Mockito.when(clienteService.cadastrarCliente(Mockito.any(CadastrarClienteRequest.class)))
                .thenReturn(clienteResponse);

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(clienteRequest)))
                .andExpect(status().isCreated())  // Espera o status 201 (Created)
                .andExpect(jsonPath("$.clienteId").value(1L))  // Mudança aqui: Espera "usuarioId" em vez de "id"
                .andExpect(jsonPath("$.nome").value("Lucas"))
                .andExpect(jsonPath("$.idade").value(13))
                .andExpect(jsonPath("$.cpf").value("03335923014"))
                .andExpect(jsonPath("$.endereco").value("Quintino Bocaiuva"))
                .andExpect(jsonPath("$.email").value("lucas@gmail.com"))
                .andExpect(jsonPath("$.cep").value("99701520"));
    }


    @Test
    void deveDeletarCliente() throws Exception {
        ClienteDeletadoResponse deletadoResponse = new ClienteDeletadoResponse(true);

        Mockito.when(clienteService.excluirCliente(Mockito.anyLong()))
                .thenReturn(deletadoResponse);

        mockMvc.perform(delete(BASE_URL + "/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteDeletado").value(true));

    }

    @Test
    void deveAtualizarCliente() throws Exception {
        ClienteResponse clienteResponse = new ClienteResponse(1L, "Lucas", 26 , "03335923014", "lucas@email.com", "Rua B", "99701520");
        AtualizarClienteRequest atualizarClienteRequest = new AtualizarClienteRequest("Lucas", 13, "033356923014", "Qintino Bocaiuva","99701520", "lucasct1996@gmail.com");

        Mockito.when(clienteService.atualizarCliente(Mockito.anyLong(), Mockito.any(AtualizarClienteRequest.class)))
                .thenReturn(clienteResponse);

        mockMvc.perform(put(BASE_URL + "/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(atualizarClienteRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteId").value(1L))  // Alterado para 'usuarioId'
                .andExpect(jsonPath("$.nome").value("Lucas"))
                .andExpect(jsonPath("$.idade").value(26))
                .andExpect(jsonPath("$.cpf").value("03335923014"))
                .andExpect(jsonPath("$.endereco").value("Rua B"))
                .andExpect(jsonPath("$.email").value("lucas@email.com"))
                .andExpect(jsonPath("$.cep").value("99701520"));
    }

    @Test
    void deveBuscarClientePorId() throws Exception {
        ClienteResponse clienteResponse = new ClienteResponse(1L, "Lucas", 14, "03335923014", "lucas@gmail.com", "Rua A", "99701520");

        Mockito.when(clienteService.buscarClientePorId(Mockito.anyLong()))
                .thenReturn(clienteResponse);

        mockMvc.perform(get(BASE_URL + "/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteId").value(1L))  // Alterado para 'usuarioId'
                .andExpect(jsonPath("$.nome").value("Lucas"))
                .andExpect(jsonPath("$.idade").value(14))
                .andExpect(jsonPath("$.cpf").value("03335923014"))
                .andExpect(jsonPath("$.endereco").value("Rua A"))
                .andExpect(jsonPath("$.email").value("lucas@gmail.com"))
                .andExpect(jsonPath("$.cep").value("99701520"));
    }







}
