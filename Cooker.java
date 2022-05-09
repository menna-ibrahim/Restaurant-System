/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantproject;

import java.util.Collections;

/**
 *
 * @author MIX
 */
public class Cooker extends User {

    public String viewReservations(Restaurant restaurant) {

        String orders = "";
        if (restaurant.getReservation().isEmpty()) {
            orders += "No Reservations made";
        } else {

            Collections.sort(restaurant.getReservation());

            for (Reservation r : restaurant.getReservation()) {
                orders += "Table " + r.getTable() + "\n";
                orders += r.getTime();

                if (r.getTime() == 10 || r.getTime() == 11) {
                    orders += " AM" + "\nOrder:\n";
                } else {
                    orders += " PM" + "\nOrder:\n";
                }
                for (OrderLine orderLine : r.getOrder().getOrderLines()) {
                    orders += orderLine.getQuantity() + "  " + orderLine.getDish().getName() + "\n";
                }
                orders += "-------------------------\n";
            }
        }
        return orders;

    }

}
