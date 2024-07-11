package ru.AlexZab.tgBot.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.AlexZab.tgBot.entity.ClientOrder;
import ru.AlexZab.tgBot.repository.ClientOrderRepository;

import java.math.BigDecimal;

@Service
@Transactional
public class ClientOrderService {

    private final ClientOrderRepository clientOrderRepository;

    public ClientOrderService(ClientOrderRepository clientOrderRepository) {
        this.clientOrderRepository = clientOrderRepository;
    }

    public void save(ClientOrder clientOrder){
        clientOrderRepository.save(clientOrder);
    }

    public ClientOrder getClientOrderByClientIdAndStatusOne(Long id){
        return clientOrderRepository.getClientByStatusAndId(id);
    }

    public void closeOrder(Long id, BigDecimal price){
        clientOrderRepository.updateStatusAndPrice(id,price);
    }

}
