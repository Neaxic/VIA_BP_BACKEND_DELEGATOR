package com.heroku.java.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductId")
    private int productId;


    @ManyToOne
    @JoinColumn(name = "batchNo")
    private BatchInfo batchInfo;

    @Column(name = "ProductLookupId")
    private Integer productLookupId;

    @Basic
    @Column(name = "TimeStamp")
    private LocalDateTime timeStamp;

    @Column(name = "IsFake")
    private boolean isFake;

    public Product() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Integer getProductLookupId() {
        return productLookupId;
    }

    public void setProductLookupId(Integer productLookupId) {
        this.productLookupId = productLookupId;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean isFake() {
        return isFake;
    }

    public void setFake(boolean fake) {
        isFake = fake;
    }

    public BatchInfo getBatchInfo() {
        return batchInfo;
    }

    public void setBatchInfo(BatchInfo batchInfo) {
        this.batchInfo = batchInfo;
    }
}
