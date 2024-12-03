package com.fiap.tc.ms.gestao_clientes.mapper;

import com.fiap.tc.ms.gestao_clientes.dto.request.CadastrarClienteRequest;
import com.fiap.tc.ms.gestao_clientes.dto.response.ClienteResponse;
import com.fiap.tc.ms.gestao_clientes.model.Cliente;

public class ClienteMapper {


    public static ClienteResponse toClienteResponse(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo");
        }
        return new ClienteResponse(
                cliente.getClienteId(),
                cliente.getNome(),
                cliente.getIdade(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getEndereco(),
                cliente.getCep()
        );
    }

    public static Cliente toCliente(CadastrarClienteRequest request) {
        Cliente cliente = new Cliente();
        cliente.setNome(request.nome());
        cliente.setEmail(request.email());
        cliente.setCpf(request.cpf());
        cliente.setIdade(request.idade());
        cliente.setEndereco(request.endereco());
        cliente.setCep(request.cep());

        return cliente;
    }
}
