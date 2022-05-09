/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantproject;

import java.io.File;
import java.io.FileNotFoundException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author MIX
 */
//this class is for reading and writing data in the xml file
public class Xml {

    private User user;
    private Restaurant r;
    private int type;

    public Restaurant getR() throws JAXBException {
        read();
        return r;
    }

    public void read() throws JAXBException {

        JAXBContext jaxbcontext = JAXBContext.newInstance(Restaurant.class);
        Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();
        this.r = (Restaurant) unmarshaller.unmarshal(new File("input.xml"));
    }

    public boolean login(String username, String password) throws JAXBException, NumberFormatException, FileNotFoundException {

        read();

        for (User user : r.getUsers()) {

            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {

                if (user.getRole().equals("Client")) {
                    this.user = new Client();
                    this.user.setName(user.getName());
                    this.user.setRole(user.getRole());

                    type = 1;

                } else if (user.getRole().equals("Waiter")) {
                    this.user = new Waiter();
                    this.user.setName(user.getName());
                    this.user.setRole(user.getRole());

                    type = 2;

                } else if (user.getRole().equals("Cooker")) {
                    this.user = new Cooker();
                    this.user.setName(user.getName());
                    this.user.setRole(user.getRole());

                    type = 3;
                } else if (user.getRole().equals("Manager")) {
                    this.user = new Manager();
                    this.user.setName(user.getName());
                    this.user.setRole(user.getRole());

                    type = 4;

                }

                return true;
            }
        }
        return false;

    }

    public Client getClient() {
        return (Client) this.user;
    }

    public Waiter getWaiter() {
        return (Waiter) this.user;
    }

    public Cooker getCooker() {
        return (Cooker) this.user;
    }

    public Manager getManager() {
        return (Manager) this.user;
    }

    public User getUser() {
        return user;
    }

    public int getType() {
        return type;
    }

    public void write() throws JAXBException {
        JAXBContext jaxbcontext = JAXBContext.newInstance(Restaurant.class);
        Marshaller marshaller = jaxbcontext.createMarshaller();
        marshaller.marshal(this.r, new File("input.xml"));

    }

}
