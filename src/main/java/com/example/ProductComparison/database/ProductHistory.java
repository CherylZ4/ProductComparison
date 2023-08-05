package com.example.ProductComparison.database;
import jakarta.persistence.*;


@Entity // This tells Hibernate to make a table out of this class
@Table(name = "producthistory")
public class ProductHistory {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    private Long id;

    @Column(name = "user_email", nullable = false)
    private String userEmail;
    private Double percsil;

    private String product1;

    private String product2;

    private String ingr_common;

    private String p1ingr;

    private String p2ingr;


    public String getUserEmail() {
        return userEmail;
    }

    public ProductHistory(String userEmail, Double percsil, String product1, String product2,
                          String ingr_common,
                          String p1ingr,
                          String p2ingr ) {

        this.userEmail = userEmail;
        this.percsil = percsil;
        this.product1 = product1;
        this.product2 = product2;
        this.ingr_common = ingr_common;
        this.p1ingr = p1ingr;
        this.p2ingr = p2ingr;
    }

    public ProductHistory() {
    }


    public Double getPercSil() {
        return percsil;
    }

    public String getProduct1() {
        return product1;
    }


    public String getProduct2() {
        return product2;
    }


    public String getIngrCommon() {
        return ingr_common;
    }


    public String getP1ingr() {
        return p1ingr;
    }


    public String getP2ingr() {
        return p2ingr;
    }





}