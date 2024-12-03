package utils.usuario;


import com.fiap.tc.ms.gestao_clientes.dto.request.CadastrarClienteRequest;
import com.fiap.tc.ms.gestao_clientes.dto.response.ClienteDeletadoResponse;
import com.fiap.tc.ms.gestao_clientes.dto.response.ClienteResponse;
import com.fiap.tc.ms.gestao_clientes.model.Cliente;
import com.fiap.tc.ms.gestao_clientes.service.AtualizarClienteRequest;

public class ClienteHelper {
  public static ClienteDeletadoResponse gerarUsuarioDeletadoReponse() {
    return new ClienteDeletadoResponse(true);
  }

  public static ClienteResponse gerarClienteResponse() {
    Long id = 1L;
    String nome = "Lucas";
    int idade = 19;
    String cpf = "03335923014";
    String email = "lucas@mail.com";
    String endereco = "Quintino Bocaiuva";
    String cep = "99701520";
    return new ClienteResponse(id, nome, idade, cpf, email, endereco, cep);
  }

  public static ClienteResponse gerarUClienteResponseAtualizado() {
    Long id = 1L;
    String nome = "Lucas";
    int idade = 19;
    String cpf = "03335923014";
    String email = "lucas@mail.com";
    String endereco = "Quintino Bocaiuva";
    String cep = "99701520";
    return new ClienteResponse(id, nome, idade, cpf, email, endereco, cep);
  }

  public static AtualizarClienteRequest gerarAtualizarClienteRequest() {
    String nome = "João";
    int idade = 20;
    String cpf = "03335923055";
    String email = "lucasPaulo@mail.com";
    String endereco = "Quintino endereco";
    return new AtualizarClienteRequest(nome, idade, cpf, email, endereco, email);
  }


  public static Cliente gerarClienteValidoComId() {
    Long id = 1L;
    String nome = "João";
    int idade = 20;
    String cpf = "03335923055";
    String email = "lucasPaulo@mail.com";
    String endereco = "Quintino endereco";
    String cep = "99701520";
    Cliente usuario = new Cliente(id, nome, email, cpf, idade, endereco, cep);
    usuario.setClienteId(id);
    return usuario;
  }

  public static Cliente gerarClienteValido() {
    Long id = 2L;
    String nome = "João Felipe";
    int idade = 20;
    String cpf = "03335923225";
    String email = "lucasPaulo@mail.com";
    String endereco = "Quintino endereco";
    String cep = "99701520";
    return  new Cliente(id, nome, email, cpf, idade, endereco, cep);
  }

  public static Cliente gerarClienteEntity() {
    Long id = 2L;
    String nome = "João Felipe";
    int idade = 20;
    String cpf = "03335923225";
    String email = "lucasPaulo@mail.com";
    String endereco = "Quintino endereco";
    String cep = "99701520";
    return  new Cliente(id, nome, email, cpf, idade, endereco, cep);
  }

  public static Cliente gerarClienteComNomeVazio() {
    Long id = 2L;
    String nome = "";
    int idade = 20;
    String cpf = "03335923225";
    String email = "lucasPaulo@mail.com";
    String endereco = "Quintino endereco";
    String cep = "99701520";
    return  new Cliente(id, nome, email, cpf, idade, endereco, cep);
  }

  public static Cliente gerarClienteComEmailInvalido() {
    Long id = 3L;
    String nome = "João Felipe";
    int idade = 20;
    String cpf = "03335923225";
    String email = "lucasPaulo.com";
    String endereco = "Quintino endereco";
    String cep = "99701520";
    return  new Cliente(id, nome, email, cpf, idade, endereco, cep);
  }
}
