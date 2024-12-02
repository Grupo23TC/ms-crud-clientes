package com.fiap.tc.ms.gestao_clientes.domain.entity;

import com.fiap.tc.ms.gestao_clientes.model.Cliente;
import org.junit.jupiter.api.Test;
import utils.usuario.ClienteHelper;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ClienteTest {
  @Test
  void deveCriarUsuario() {
    Cliente cliente = new Cliente(
            1L,
            "Lucas",
            "lucas@mail.com",
            "03335923014",
            19,
            "Quintino Bocaiuva",
            "99701520"
    );

    assertThat(cliente)
        .isNotNull()
        .isInstanceOf(Cliente.class);

    assertThat(cliente.getNome())
        .isNotEmpty()
        .isNotNull();

    assertThat(cliente.getEmail())
        .isNotEmpty()
        .isNotNull();

    assertThat(cliente.getEndereco())
        .isNotNull();

    assertThat(cliente.getIdade())
        .isNotNull();
  }

  @Test
  void deveGerarExcecao_QuandoNomeVazio() {
    assertThatThrownBy(ClienteHelper::gerarClienteComNomeVazio)
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Nome não pode ser nulo ou vazio");
  }


  @Test
  void deveGerarExcecao_QuandoEmailForInvalido() {
    assertThatThrownBy(ClienteHelper::gerarClienteComEmailInvalido)
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Email deve ser válido");
  }


}
