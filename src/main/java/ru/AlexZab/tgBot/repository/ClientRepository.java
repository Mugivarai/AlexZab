package ru.AlexZab.tgBot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.AlexZab.tgBot.entity.Client;
import ru.AlexZab.tgBot.entity.ClientOrder;
import ru.AlexZab.tgBot.entity.Product;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "clients", path = "clients")
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE LOWER(c.fullName) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<Client> getClientByName(@Param("name") String name);

}
