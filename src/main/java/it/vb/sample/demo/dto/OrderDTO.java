package it.vb.sample.demo.dto;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private long id;
    private String buyerEmail;
    private Date date;
    private List<OrderLineDTO> lines;

    /**
     * @return the lines
     */
    public List<OrderLineDTO> getLines() {
        return lines;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the buyerEmail
     */
    public String getBuyerEmail() {
        return buyerEmail;
    }

    /**
     * @param buyerEmail the buyerEmail to set
     */
    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @param lines the lines to set
     */
    public void setLines(List<OrderLineDTO> lines) {
        this.lines = lines;
    }
}