/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantproject;

import static java.lang.Character.isLetter;
import java.util.List;

/**
 *
 * @author MIX
 */
//this class is for the functions we needed in the gui for formatting or validation
public class Logic {

    public boolean checkTime(String s, String amPm) {
        int x;
        try {
            x = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        if (x < 1 || x > 12) {
            return false;
        }
        if ((x == 11 || x == 10) && amPm.equals("PM")) {
            return false;
        }
        if ((x == 12 || x < 9) && amPm.equals("AM")) {
            return false;
        }

        return true;

    }

    public String format(String s) {
        int i = 0, j = 0;
        char[] newS = new char[2000];
        char[] oldS = s.toCharArray();
        while (i < oldS.length) {
            if (oldS[i] != ']' && oldS[i] != '[') {
                if (oldS[i] == ',') {
                    i++;
                    i++;
                }
                newS[j] = oldS[i];
            }

            i++;
            j++;
        }
        s = String.valueOf(newS);
        return s;

    }

    public boolean checkUsername(List<User> users, String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return false;
            }
        }

        return true;

    }

    public boolean checkSpaces(String username) {
        if (username.contains(" ")) {
            return false;
        }
        return true;
    }

    public boolean checkName(String name) {
        int i = 0;
        while (i < name.length()) {
            if (isLetter(name.charAt(i)) || name.charAt(i) == ' ') {
                i++;
            } else {
                return false;
            }
        }
        return true;

    }

    public boolean checkPassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        return true;
    }

}
