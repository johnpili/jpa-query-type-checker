package com.johnpili.query_type_checker.hibernate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Material {
    @Id
    private long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
