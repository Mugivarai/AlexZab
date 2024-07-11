package ru.AlexZab.tgBot.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.AlexZab.tgBot.entity.OrderProduct;
import ru.AlexZab.tgBot.repository.OrderProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;

    public OrderProductService(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    public Boolean hasProduct(Long id,Long productId){
        if(orderProductRepository.hasProduct(id,productId)==null){
            return false;
        }else{
            return true;
        }
    }

    public void updateCountProduct(Long id,Long productId){
        orderProductRepository.updateCountProduct(id,productId);
    }

    public void save(OrderProduct orderProduct){
        orderProductRepository.save(orderProduct);
    }

    public BigDecimal getTotalOrderProduct(Long id){
        return orderProductRepository.getTotalOrderPrice(id);
    }

    public List<Object[]> findOrderProductDetails(Long orderId){
        return orderProductRepository.findOrderProductDetails(orderId);
    }

}
