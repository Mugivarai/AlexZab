package ru.AlexZab.tgBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.AlexZab.tgBot.entity.ClientOrder;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "clientOrders", path = "clientOrders")
public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long> {

    @Query("SELECT co FROM ClientOrder co WHERE co.client.id = :id")
    public List<ClientOrder> getClientOrders(Long id);

}
