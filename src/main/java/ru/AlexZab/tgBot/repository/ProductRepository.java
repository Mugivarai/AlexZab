package ru.AlexZab.tgBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.AlexZab.tgBot.entity.Product;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.category.id = :id")
    List<Product> getProductsByCategoryId(Long id);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<Product> getProductsByName(@Param("name") String name);

    @Query(value = "SELECT PRODUCT.* " +
            "FROM ORDER_PRODUCT sort " +
            "JOIN PRODUCT ON sort.PRODUCT_ID = PRODUCT.ID " +
            "GROUP BY PRODUCT_ID " +
            "ORDER BY SUM(COUNT_PRODUCT) DESC " +
            "LIMIT :limit ",nativeQuery = true)
    List<Product> getPopularProducts(Integer limit);

    @Query(value = "SELECT DISTINCT p.* FROM PRODUCT p " +
            "JOIN ORDER_PRODUCT op ON p.ID = op.PRODUCT_ID " +
            "JOIN CLIENT_ORDER co ON op.CLIENT_ORDER_ID = co.ID " +
            "WHERE co.CLIENT_ID = :id",nativeQuery = true)
    List<Product> getClientProducts(Long id);



}
