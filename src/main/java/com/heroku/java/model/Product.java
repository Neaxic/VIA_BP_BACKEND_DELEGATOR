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
    @JoinColumn(name = "BatchNo")
    private BatchInfo batchNo;

    @Column(name = "ProductLookupId")
    private Integer productLookupId;

    @Basic
    @Column(name = "TimeStamp")
    private LocalDateTime timeStamp;

    @Column(name = "IsFake", columnDefinition = "BIT")
    private Boolean isFake;

    public Product() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public BatchInfo getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(BatchInfo batchNo) {
        this.batchNo = batchNo;
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

    public Boolean getFake() {
        return isFake;
    }

    public void setFake(Boolean fake) {
        isFake = fake;
    }


    public UserDTO toDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(this.getUserId());
        userDTO.setUsername(this.getUsername());

       /* Set<String> roleNames = this.getRoles().stream()
                .map(UserRoles::getRoleName)
                .collect(Collectors.toSet());*/

        userDTO.setRoles(this.getRoles());
        return userDTO;
    }

}
