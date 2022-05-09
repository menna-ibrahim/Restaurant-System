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
public class Waiter extends User {

    public String viewReservations(Restaurant restaurant) {
        String reservation = "";
        if (restaurant.getReservation().isEmpty()) {
            reservation += "No Reservations made";
        } else {
            Collections.sort(restaurant.getReservation());
            for (Reservation r : restaurant.getReservation()) {
                if (r.getTime() == 10 || r.getTime() == 11) {
                    reservation += "Client Name: " + r.getNameOfCLient() + " " + "\nTable: " + r.getTable() + " "
                            + "\nTime: " + r.getTime() + " AM" + "\n\n";
                } else {
                    reservation += "Client Name: " + r.getNameOfCLient() + " " + "\nTable: " + r.getTable() + " "
                            + "\nTime: " + r.getTime() + " PM" + "\n\n";
                }
                reservation += "---------------------\n";
            }
        }

        return reservation;
    }
}
