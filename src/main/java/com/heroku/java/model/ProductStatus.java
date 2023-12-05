package com.heroku.java.model;

import jakarta.persistence.*;


@Entity
@Table(name = "productStatus")
public class ProductStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductStatusId")
    int productStatusId;


    /*@Column(name = "ProductLookupId")
    int productLookUpId;

    @Column(name = "ProductId")
    int productId;*/


    @ManyToOne
    @JoinColumn(name = "ProductLookupId")
    private ProductLookUp productLookUp;

    @ManyToOne
    @JoinColumn(name = "ProductId")
    private Product product;


    public int getProductStatusId() {
        return productStatusId;
    }

    public void setProductStatusId(int productStatusId) {
        this.productStatusId = productStatusId;
    }



    public ProductLookUp getProductLookUp() {
        return productLookUp;
    }

    public void setProductLookUp(ProductLookUp productLookUp) {
        this.productLookUp = productLookUp;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
