/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantproject;

/**
 *
 * @author MIX
 */
public class Client extends User {

    private Reservation reserve = new Reservation();

    public Reservation getReservation() {
        return reserve;
    }

    public void makeOrder(Dish dish) {

        OrderLine line = new OrderLine();

        line.setDish(dish);
        line.setQuantity(1);
        reserve.getOrder().addOrderLine(line);
        reserve.getOrder().calculateTotalPrice();
    }

    public boolean checkAvailableTable(int time, int seats, boolean smoking, Restaurant r) {
        int found;
        for (Table table : r.getTables()) {
            found = 0;
            if (table.getSeats() == seats && table.isSmoking() == smoking) {
                if (!r.getReservation().isEmpty()) {
                    for (Reservation r1 : r.getReservation()) {
                        if (r1.getTable() == table.getNumber() && r1.getTime() == time) {
                            found = 1;
                        }

                    }
                }
                if (found == 1) {
                    continue;
                }
                reserve.setTable(table.getNumber());
                reserve.setTime(time);
                return true;
            }

        }
        return false;

    }

}
