package com.fiap.tc.ms.gestao_clientes.dto.request;

import jakarta.validation.constraints.Positive;

public record CadastrarClienteRequest(
        String nome,
        int idade,
        String cpf,
        String endereco,
        String cep
) {
}
