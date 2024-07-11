package ru.AlexZab.tgBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.AlexZab.tgBot.entity.ClientOrder;

import java.math.BigDecimal;
import java.util.List;


@RepositoryRestResource(collectionResourceRel = "clientOrders", path = "clientOrders")
public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long> {

    @Query("SELECT co FROM ClientOrder co WHERE co.client.id = :id")
    public List<ClientOrder> getClientOrders(Long id);

    @Query("SELECT co FROM ClientOrder co WHERE co.status = 1 " +
            "AND co.client.id = :id")
    public ClientOrder getClientByStatusAndId(Long id);

    @Modifying
    @Query("UPDATE ClientOrder co SET co.status = 2, " +
            "co.total = :price " +
            "WHERE co.status = 1 " +
            "AND co.client.id = :id")
    public void updateStatusAndPrice(Long id, BigDecimal price);

}
