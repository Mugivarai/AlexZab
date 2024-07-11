package ru.AlexZab.tgBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.AlexZab.tgBot.entity.OrderProduct;

import java.math.BigDecimal;
import java.util.List;


@RepositoryRestResource(collectionResourceRel = "orderProducts", path = "orderProducts")
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    @Query("SELECT op FROM OrderProduct op WHERE op.clientOrder.id = :id " +
            "AND op.product.id = :productId")
    OrderProduct hasProduct(Long id,Long productId);

    @Modifying
    @Query("UPDATE OrderProduct op SET op.countProduct = op.countProduct + 1 " +
            "WHERE  op.clientOrder.id = :id AND op.product.id = :productId")
    int updateCountProduct(Long id, Long productId);

    @Query("SELECT SUM(op.countProduct * p.price) " +
            "FROM OrderProduct op " +
            "JOIN op.product p " +
            "WHERE op.clientOrder.id = :orderId")
    BigDecimal getTotalOrderPrice(Long orderId);

    @Query("SELECT p.name, op.countProduct, p.price " +
            "FROM OrderProduct op JOIN op.product p " +
            "WHERE op.clientOrder.id = :orderId")
    List<Object[]> findOrderProductDetails(Long orderId);

}
