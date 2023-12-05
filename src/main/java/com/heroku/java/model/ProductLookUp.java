package com.heroku.java.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ProductLookUp")
public class ProductLookUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductLookupId")
    private int productLookupId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    public ProductLookUp() {
    }

    public int getProductLookupId() {
        return productLookupId;
    }

    public void setProductLookupId(int productLookupId) {
        this.productLookupId = productLookupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
