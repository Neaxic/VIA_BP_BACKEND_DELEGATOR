package com.heroku.java.model;

import jakarta.persistence.*;


    @Entity
    @Table(name = "productStatus")
    public class ProductStatus {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ProductStatusId")
        int productStatusId;


        @Column(name = "ProductLookupId")
        int productLookUpId;

        @Column(name = "ProductId")
        int productId;


    @Transient
    @JoinTable(name = "ProductLookup",joinColumns = @JoinColumn(name = "ProductLookupId")) // Specify the name of the foreign key column
    private ProductLookUp productLookUp;

    @Transient
    @JoinTable(name = "Product",joinColumns = @JoinColumn(name = "ProductId"))
    private Product product;


        public int getProductStatusId() {
            return productStatusId;
        }

        public void setProductStatusId(int productStatusId) {
            this.productStatusId = productStatusId;
        }

        public int getProductLookUpId() {
            return productLookUpId;
        }

        public void setProductLookUpId(int productLookUpId) {
            this.productLookUpId = productLookUpId;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
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
