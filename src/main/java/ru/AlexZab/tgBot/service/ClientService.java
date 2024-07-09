package ru.AlexZab.tgBot.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.AlexZab.tgBot.entity.Client;
import ru.AlexZab.tgBot.entity.ClientOrder;
import ru.AlexZab.tgBot.entity.Product;
import ru.AlexZab.tgBot.repository.ClientOrderRepository;
import ru.AlexZab.tgBot.repository.ClientRepository;
import ru.AlexZab.tgBot.repository.ProductRepository;

import java.util.List;

@Service
@Transactional
public class ClientService {

    @Autowired
    private final ClientRepository clientRepository;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final ClientOrderRepository clientOrderRepository;

    public ClientService(
            ClientRepository clientRepository,
            ProductRepository productRepository,
            ClientOrderRepository clientOrderRepository
    ) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.clientOrderRepository = clientOrderRepository;
    }

    public List<ClientOrder> getClientOrders(Long id){
        return clientOrderRepository.getClientOrders(id);
    }

    public List<Product> getClientProducts(Long id){
        return productRepository.getClientProducts(id);
    }

    public List<Client> getClientByName(String name){
        return clientRepository.getClientByName(name);
    }

}
