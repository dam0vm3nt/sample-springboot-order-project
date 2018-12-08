package it.vb.sample.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double qty;

    private double price;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    /**
     * @return the qty
     */
    public double getQty() {
        return qty;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(double qty) {
        this.qty = qty;
    }

    public long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}