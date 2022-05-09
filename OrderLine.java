/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantproject;

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
public class OrderLine {

    @XmlElement(name = "dish")
    private Dish dish;
    @XmlElement(name = "quantity")
    private int quantity;

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("%-50s\t\t\t%-4.1f\t\t\t\t%-3d\n", dish.getName(), dish.getPrice(), quantity);
    }

}
