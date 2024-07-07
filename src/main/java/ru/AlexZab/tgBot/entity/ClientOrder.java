package ru.AlexZab.tgBot.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ClientOrder")
public class ClientOrder {

    @Id
    @GeneratedValue
    private Long ID;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false,precision = 15,scale = 2)
    private BigDecimal total;

    @ManyToOne
    private Client client;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
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
