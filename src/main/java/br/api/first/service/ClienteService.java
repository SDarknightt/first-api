package br.api.first.service;

import br.api.first.model.Cliente;
import br.api.first.repository.ClienteRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostConstruct
    public void init() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente(1L, "Cliente 1"));
        clientes.add(new Cliente(2L, "Cliente 2"));
        clientes.add(new Cliente(3L, "Cliente 3"));
        clienteRepository.saveAll(clientes);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente adicionarCliente(Cliente cliente) {
        if (cliente.getNome().length() > 0){
            clienteRepository.save(cliente);
        }else{
            System.out.println("Nome Inválido.");
        }
        return cliente;
    }

    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));

        clienteExistente.setNome(clienteAtualizado.getNome());
        return clienteRepository.save(clienteExistente);
    }

    public void removerCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
