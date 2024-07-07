package ru.AlexZab.tgBot.entity;

import jakarta.persistence.*;

@Entity
public class OrderProduct {

    @Id
    @GeneratedValue
    private Long ID;

    @Column(nullable = false)
    private Integer countProduct;

    @ManyToOne
    private Product product;

    @ManyToOne
    private ClientOrder clientOrder;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
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
