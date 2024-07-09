package ru.AlexZab.tgBot.entity;

import jakarta.persistence.*;
import org.jetbrains.annotations.Contract;

@Entity
@Table(name = "OrderProduct")
public class OrderProduct {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer countProduct;

    @ManyToOne
    private Product product;

    @ManyToOne
    private ClientOrder clientOrder;

    @Contract(pure = true)
    public OrderProduct(Integer countProduct, Product product, ClientOrder clientOrder) {
        this.countProduct = countProduct;
        this.product = product;
        this.clientOrder = clientOrder;
    }

    @Contract(pure = true)
    public OrderProduct(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(Integer countProduct) {
        this.countProduct = countProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ClientOrder getClientOrder() {
        return clientOrder;
    }

    public void setClientOrder(ClientOrder clientOrder) {
        this.clientOrder = clientOrder;
    }

}
