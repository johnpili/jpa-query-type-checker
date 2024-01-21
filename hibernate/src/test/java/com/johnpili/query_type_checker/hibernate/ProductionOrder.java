package com.johnpili.query_type_checker.hibernate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQuery(name = "get-production-order", query = "SELECT o FROM ProductionOrder o")
public class ProductionOrder {

    @Id
    private long id;

    private String code;
}
