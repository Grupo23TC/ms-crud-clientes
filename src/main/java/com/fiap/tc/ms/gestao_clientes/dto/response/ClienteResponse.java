package com.fiap.tc.ms.gestao_clientes.dto.response;


public record ClienteResponse(
        Long clienteId,
        String nome,
        int idade,
        String cpf,
        String email,
        String endereco,
        String cep){


}
