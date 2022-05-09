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
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MIX
 */
@XmlRootElement(name = "restaurant")
@XmlAccessorType(XmlAccessType.FIELD)
public class Restaurant {

    @XmlElementWrapper(name = "users")
    @XmlElement(name = "user")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @XmlElementWrapper(name = "dishes")
    @XmlElement(name = "dish")
    private List<Dish> dishes;

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @XmlElementWrapper(name = "tables")
    @XmlElement(name = "table")
    private List<Table> tables;

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
    @XmlElementWrapper(name = "reservations")
    @XmlElement(name = "reservation")
    private List<Reservation> reservations = new ArrayList<>();

    public List<Reservation> getReservation() {
        return reservations;
    }

    public void setReservation(List<Reservation> reservation) {
        this.reservations = reservation;
    }

    public void addResevation(Reservation r) {
        this.reservations.add(r);

    }

    public void addClient(String name, String username, String password) {
        User u = new User(name, "Client", username, password);
        this.users.add(u);
    }

}
