package ru.AlexZab.tgBot.entity;

import jakarta.persistence.*;
import org.jetbrains.annotations.Contract;

import java.math.BigDecimal;

@Entity
@Table(name = "ClientOrder")
public class ClientOrder {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false,precision = 15,scale = 2)
    private BigDecimal total;

    @ManyToOne
    private Client client;

    @Contract(pure = true)
    public ClientOrder(Integer status, BigDecimal total, Client client) {
        this.status = status;
        this.total = total;
        this.client = client;
    }

    @Contract(pure = true)
    public ClientOrder(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
