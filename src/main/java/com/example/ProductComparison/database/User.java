package com.example.ProductComparison.database;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity // This tells Hibernate to make a table out of this class
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    private int id;
    private double percSil;

    private String product1;

    private String product2;

    private String ingrCommon;

    private String p1ingr;

    private String p2ingr;


    public User(int id, double percSil, String product1,  String product2, String ingrCommon, String p1ingr, String p2ingr ) {
        this.id = id;
        this.percSil = percSil;
        this.product1 = product1;
        this.product2 = product2;
        this.ingrCommon = ingrCommon;
        this.p1ingr = p1ingr;
        this.p2ingr = p2ingr;
    }


    public double getPercSil() {
        return percSil;
    }

    public String getProduct1() {
        return product1;
    }


    public String getProduct2() {
        return product2;
    }


    public String getIngrCommon() {
        return ingrCommon;
    }


    public String getP1ingr() {
        return p1ingr;
    }


    public String getP2ingr() {
        return p2ingr;
    }


    public int getId() {
        return id;
    }


}