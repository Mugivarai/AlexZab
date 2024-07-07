package ru.AlexZab.tgBot.entity;

import jakarta.persistence.*;
import org.jetbrains.annotations.Contract;

@Entity
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue
    private Long ID;

    @Column(nullable = false, unique = true,length = 50)
    private String name;

    @ManyToOne
    private Category parent;

    @Contract(pure = true)
    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
    }

    @Contract(pure = true)
    public Category(){}

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

}
