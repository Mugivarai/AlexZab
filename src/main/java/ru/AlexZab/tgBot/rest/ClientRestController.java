package ru.AlexZab.tgBot.rest;

import org.springframework.web.bind.annotation.*;
import ru.AlexZab.tgBot.entity.Client;
import ru.AlexZab.tgBot.entity.ClientOrder;
import ru.AlexZab.tgBot.entity.Product;
import ru.AlexZab.tgBot.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/rest/clients")
public class ClientRestController {

    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}/orders")
    List<ClientOrder> getClientOrders(@PathVariable Long id){
        return clientService.getClientOrders(id);
    }

    @GetMapping("/{id}/products")
    List<Product> getClientProducts(@PathVariable Long id){
        return clientService.getClientProducts(id);
    }

    @GetMapping("/search")
    List<Client> getClientByName(@RequestParam(value = "name") String name){
        return clientService.getClientByName(name);
    }

}
