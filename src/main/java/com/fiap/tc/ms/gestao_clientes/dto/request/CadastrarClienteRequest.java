package com.fiap.tc.ms.gestao_clientes.dto.request;

public record CadastrarClienteRequest(
        String nome,
        String email,
        String cpf,
        int idade,
        String endereco,
        String cep
) {

}
