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

@XmlRootElement(name = "reservation")
@XmlAccessorType(XmlAccessType.FIELD)
public class Reservation implements Comparable<Reservation> {

    @XmlElement(name = "tableNumber")
    private int tableNumber;
    @XmlElement(name = "Order")
    private Order order = new Order();
    @XmlElement(name = "nameOfClient")
    private String nameOfCLient;
    @XmlElement(name = "time")
    private int time;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setNameOfCLient(String nameOfCLient) {
        this.nameOfCLient = nameOfCLient;
    }

    public String getNameOfCLient() {
        return nameOfCLient;
    }

    public int getTable() {
        return tableNumber;
    }

    public void setTable(int table) {
        this.tableNumber = table;
    }

    @Override
    public int compareTo(Reservation o) {
        if (time == 10 || time == 11 || time == 12) {
            if (time < o.time) {
                return 1;
            } else if (time > o.time) {
                return -1;
            }

        } else {
            if (time < o.time) {
                return -1;
            } else if (time > o.time) {
                return 1;
            }

        }

        return 0;
    }

}
