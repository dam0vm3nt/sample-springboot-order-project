package it.vb.sample.demo.dto;

public class OrderLineDTO {
    private String productName;
    private double qty;

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
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
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
}