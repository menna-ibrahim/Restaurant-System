/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantproject;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MIX
 */
@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {

    @XmlElement(name = "orderline")
    private List<OrderLine> orderLines = new ArrayList<>();

    public double getTotalPrice() {
        return totalPrice;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }
    private double totalPrice;

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public void calculateTotalPrice() {
        totalPrice = 0;
        for (OrderLine o : this.orderLines) {
            this.totalPrice += o.getDish().getPrice() * o.getQuantity();
        }
    }

    public void addOrderLine(OrderLine o) {
        int added = 0;
        for (OrderLine o1 : this.orderLines) {
            if (o1.getDish().getName().equals(o.getDish().getName())) {
                o1.setQuantity(o1.getQuantity() + 1);
                added = 1;
            }
        }
        if (added == 0) {
            this.orderLines.add(orderLines.size(), o);
        }

    }

    public double calculateWithTaxes() {
        totalPrice = 0;
        for (OrderLine o : this.orderLines) {
            if (o.getDish().getType().equals("main_course")) {
                totalPrice += o.getDish().getPrice() * o.getQuantity() * 1.15;
            }
            if (o.getDish().getType().equals("appetizer")) {
                totalPrice += o.getDish().getPrice() * o.getQuantity() * 1.1;
            }
            if (o.getDish().getType().equals("desert")) {
                totalPrice += o.getDish().getPrice() * o.getQuantity() * 1.2;
            }

        }
        return this.totalPrice;
    }

    public String printOrder() {
        return this.orderLines.toString() + String.format("%n----------------------------------------------------------------------------------------%nTotal Price----------------------------------------------------------------------%3.1f%nTotal Price With Taxes---------------------------------------------------------%3.1f", this.totalPrice, calculateWithTaxes());
    }

}
