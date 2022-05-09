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
public class Manager extends User {

    public String viewReservations(Restaurant restaurant) {

        String reservations = "";
        if (restaurant.getReservation().isEmpty()) {
            reservations += "No Reservations made";
        } else {

            Collections.sort(restaurant.getReservation());

            for (Reservation r : restaurant.getReservation()) {

                reservations += "Client name:  " + r.getNameOfCLient() + "\nTable Number: " + r.getTable() + "\n";

                reservations += "Time of reservation: " + r.getTime();
                if (r.getTime() == 10 || r.getTime() == 11) {
                    reservations += " AM" + "\n" + "\n";
                } else {
                    reservations += " PM" + "\nOrder:\n";
                }
                for (OrderLine orderLine : r.getOrder().getOrderLines()) {
                    reservations += orderLine.getQuantity() + "\t" + orderLine.getDish().getName() + "\n";
                }
                reservations += "Total Amount Paid:" + r.getOrder().calculateWithTaxes() + "\n\n--------------------\n\n";

            }
        }
        return reservations;

    }

    public String viewTotalIncome(Restaurant restaurant) {
        String income = "Total income made today:\n";
        int totalIncome = 0;
        for (Reservation r : restaurant.getReservation()) {
            totalIncome += r.getOrder().calculateWithTaxes();
        }
        return income + totalIncome + " LE";
    }
}
