package it.vb.sample.demo.entities;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String buyerEmail;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> lines;

    public long getId() {
        return id;
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

    public List<OrderLine> getLines() {
        return lines;
    }

    public void setLines(List<OrderLine> lines) {
        this.lines = lines;
    }

    /**
     * NOTE: Not optimized, just for this sample
     * @return
     */
    public double computeTotal() {
        return lines.stream().collect(Collectors.summarizingDouble(l -> l.getPrice() * l.getQty())).getSum();
    }
}