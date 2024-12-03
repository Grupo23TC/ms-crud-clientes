package com.fiap.tc.ms.gestao_clientes.service;

import com.fiap.tc.ms.gestao_clientes.dto.request.CadastrarClienteRequest;
import com.fiap.tc.ms.gestao_clientes.dto.response.ClienteResponse;
import com.fiap.tc.ms.gestao_clientes.dto.response.ClienteDeletadoResponse;
import com.fiap.tc.ms.gestao_clientes.exceptions.ClienteNotFoundException;
import com.fiap.tc.ms.gestao_clientes.mapper.ClienteMapper;
import com.fiap.tc.ms.gestao_clientes.model.Cliente;
import com.fiap.tc.ms.gestao_clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;


  @Transactional(readOnly = true)
  public Page<ClienteResponse> listarClientes(Pageable pageable) {
    return clienteRepository.findAll(pageable)
            .map(cliente -> {
              if (cliente == null) {
                throw new IllegalStateException("Cliente nulo retornado do repositório");
              }
              return ClienteMapper.toClienteResponse(cliente);
            });
  }

  @Transactional(readOnly = true)
  public ClienteResponse buscarClientePorId(Long id) {
    Cliente cliente = buscarClientePorIdOuLancarExcecao(id);
    return ClienteMapper.toClienteResponse(cliente);
  }


  @Transactional
  public ClienteResponse cadastrarCliente(CadastrarClienteRequest clienteRequest) {
    Cliente cliente = ClienteMapper.toCliente(clienteRequest);
    return ClienteMapper.toClienteResponse(clienteRepository.save(cliente));
  }


  @Transactional
  public ClienteDeletadoResponse excluirCliente(Long id) {
    buscarClientePorIdOuLancarExcecao(id);
    clienteRepository.deleteById(id);
    return new ClienteDeletadoResponse(true);
  }

  public ClienteResponse atualizarCliente(Long id, AtualizarClienteRequest clienteAtualizado) {
    Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new ClienteNotFoundException("Cliente com id: " + id + " não encontrado"));

    if (clienteAtualizado.nome() != null) {
      cliente.setNome(clienteAtualizado.nome());
    }
    if (clienteAtualizado.endereco() != null) {
      cliente.setEndereco(clienteAtualizado.endereco());
    }
    if (clienteAtualizado.cpf() != null) {
      cliente.setCpf(clienteAtualizado.cpf());
    }
    if (clienteAtualizado.cep() != null) {
      cliente.setCep(clienteAtualizado.cep());
    }
    if (clienteAtualizado.idade() > 0) {
      cliente.setIdade(clienteAtualizado.idade());
    }
    if (clienteAtualizado.email() != null) {
      cliente.setEmail(clienteAtualizado.email());
    }

    Cliente clienteSalvo = clienteRepository.save(cliente);

    return ClienteMapper.toClienteResponse(clienteSalvo);
  }

  private Cliente buscarClientePorIdOuLancarExcecao(Long id) {
    return clienteRepository.findById(id).orElseThrow(
            () -> new ClienteNotFoundException("Cliente com id: " + id + " não encontrado")
    );
  }
}