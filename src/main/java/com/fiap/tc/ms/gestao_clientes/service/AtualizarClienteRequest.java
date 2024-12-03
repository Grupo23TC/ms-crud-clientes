package com.fiap.tc.ms.gestao_clientes.service;

public record AtualizarClienteRequest(
        String nome,
        int idade,
        String cpf,
        String endereco,
        String cep,
        String email
) {}