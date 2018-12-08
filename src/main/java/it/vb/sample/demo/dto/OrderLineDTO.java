package it.vb.sample.demo.dto;

public class OrderLineDTO {
    private String sku;
    private double qty;

    /**
     * @return the sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @return the qty
     */
    public double getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(double qty) {
        this.qty = qty;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(String sku) {
        this.sku = sku;
    }
}