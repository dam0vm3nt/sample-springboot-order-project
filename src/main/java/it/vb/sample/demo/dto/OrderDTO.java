package it.vb.sample.demo.dto;

import java.util.List;

public class OrderDTO {
    private List<OrderLineDTO> lines;

    /**
     * @return the lines
     */
    public List<OrderLineDTO> getLines() {
        return lines;
    }

    /**
     * @param lines the lines to set
     */
    public void setLines(List<OrderLineDTO> lines) {
        this.lines = lines;
    }
}