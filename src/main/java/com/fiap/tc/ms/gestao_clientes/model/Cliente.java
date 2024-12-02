package com.fiap.tc.ms.gestao_clientes.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
    @Table(name = "tb_cliente")
    @Data
    @Getter
    @Setter
    public class Cliente {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long clienteId;
        private String nome;
        private String email;
        private String cpf;
        private int idade;
        private String endereco;
        private String cep;

    public Cliente() {
    }

    public Cliente(Long clienteId, String nome, String email, String cpf, int idade, String endereco, String cep) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email deve ser válido");
        }
        this.clienteId = clienteId;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.idade = idade;
        this.endereco = endereco;
        this.cep = cep;
    }

}

