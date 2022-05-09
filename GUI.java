/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javax.xml.bind.JAXBException;

/**
 *
 * @author MIX
 */
public class GUI {

    Xml xml = new Xml();
    Logic logic = new Logic();
    Scene scene1;
    Scene scene;
    Stage error = new Stage();
//login form
    public void prepareScene() {
        //drawing the scene
        Text text = new Text("Login Form:");
        text.setFont(Font.font("ariel", FontWeight.BOLD, FontPosture.REGULAR, 15));
        text.setTextAlignment(TextAlignment.CENTER);
        Label label1 = new Label("Username:");
        label1.setFont(Font.font("ariel", 12));
        Label label2 = new Label("Password:");
        label2.setFont(Font.font("ariel", 12));
        Button button1 = new Button("Log in");
        button1.setFont(Font.font("ariel", 12));
        Label label3 = new Label("First time here?");
        label3.setFont(Font.font("ariel", 12));
        Button button2 = new Button("New Client");
        button2.setFont(Font.font("ariel", 12));
        TextField textfield1 = new TextField();
        PasswordField textfield2 = new PasswordField();
        GridPane grid = new GridPane();
        grid.add(text, 1, 0);
        grid.add(label1, 0, 1);
        grid.add(label2, 0, 2);
        grid.add(button1, 1, 3);
        grid.add(textfield1, 1, 1);
        grid.add(textfield2, 1, 2);
        grid.add(label3, 0, 4);
        grid.add(button2, 1, 4);
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        GridPane.setHalignment(button1, HPos.CENTER);
        GridPane.setHalignment(button2, HPos.CENTER);
        GridPane.setHalignment(text, HPos.CENTER);
        error.setTitle("Error");
        Text errormsg = new Text("Incorrect Username or Password");
        Button ok = new Button("OK");
        ok.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                error.close();
            }
        });
        GridPane grid2 = new GridPane();
        grid2.add(errormsg, 0, 0);
        grid2.add(ok, 0, 1);
        grid.setVgap(15);
        GridPane.setHalignment(ok, HPos.CENTER);
        grid2.setAlignment(Pos.CENTER);
        Scene sceneError = new Scene(grid2, 200, 100);
        error.setScene(sceneError);
        //buttons events
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                prepareNewUser();
            }
        });
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!xml.login(textfield1.getText(), textfield2.getText())) {
                        error.show();
                    } else {
                        //determining the which scene to call
                        switch (xml.getType()) {
                            case 1:
                                prepareClient();
                                break;
                            case 2:
                                prepareWaiter();
                                break;
                            case 3:
                                prepareCooker();
                                break;
                            case 4:
                                prepareManager();

                        }
                    }
                } catch (JAXBException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NumberFormatException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        scene1 = new Scene(grid, 300, 200);

    }

    public Scene getScene() {
        return this.scene1;
    }
//Client scene
    public void prepareClient() throws NumberFormatException, JAXBException, FileNotFoundException {
        //drawing the scene
        Image image = new Image(new FileInputStream("background.jpg"));
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundimage);
        Label welcomeLabel = new Label("Welcome " + xml.getUser().getName());
        welcomeLabel.setFont(Font.font("ariel", FontWeight.BOLD, 20));
        Label label1 = new Label("Please Select Number of Seats needed");
        label1.setFont(Font.font("ariel", FontWeight.BOLD, 18));
        ComboBox<String> numberOfSeats = new ComboBox<>();
        numberOfSeats.getItems().addAll("4", "5", "6", "7", "12");
        ComboBox<String> amPm = new ComboBox<>();
        amPm.getItems().addAll("AM", "PM");
        numberOfSeats.setPromptText("Choose Number of Seats");
        CheckBox smoking = new CheckBox();
        smoking.setMaxWidth(50);
        smoking.setMaxHeight(50);
        Button proceedButton = new Button("Proceed");
        proceedButton.setTextFill(Paint.valueOf("white"));
        proceedButton.setStyle("-fx-background-color: #000000	; ");
        Label smokinglabel = new Label("Please tick this box if you want to smoke");
        smokinglabel.setFont(Font.font("ariel", FontWeight.BOLD, 18));
        Label addTime = new Label("Please Enter the time of your reservation");
        Label label = new Label("Reservation are avaliable from 10AM to 9PM\nPlease enter the hour you please\nusing the integers from 1 to 12");
        label.setFont(Font.font("ariel", FontWeight.BOLD, 14));
        addTime.setFont(Font.font("ariel", FontWeight.BOLD, 18));
        TextField time = new TextField();
        GridPane grid = new GridPane();
        grid.add(welcomeLabel, 0, 0);
        grid.add(label1, 0, 2);
        grid.add(smokinglabel, 0, 4);
        grid.add(smoking, 1, 4);
        grid.add(numberOfSeats, 1, 2);
        grid.add(proceedButton, 2, 6);
        grid.add(addTime, 0, 5);
        grid.add(time, 1, 5);
        grid.add(label, 0, 6);
        grid.add(amPm, 2, 5);

        grid.setVgap(20);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setBackground(background);
//buttons functions
        proceedButton.setOnAction(e -> {
            try {
                if (!logic.checkTime(time.getText(), amPm.getValue())) {
                    errorMessage("Enter a correct time");
                } else {
                    try {
                        if (xml.getClient().checkAvailableTable(Integer.parseInt(time.getText()), Integer.parseInt(numberOfSeats.getValue()), smoking.isSelected(), xml.getR())) {
                            prepareMenu();
                        } else {
                            errorMessage("No Table Is Available");
                        }
                    } catch (Exception e1) {
                        errorMessage("Choose Number of Seats");
                    }
                }
            } catch (NullPointerException ex) {
                errorMessage("Please choose AM or PM");

            }
        });
        scene = new Scene(grid, 700, 300);

        Main.getWindow().setScene(scene);

    }
//error message function
    private void errorMessage(String message) {
        Stage errorStage = new Stage();
        Label errorMessage = new Label(message);
        Button errorButton = new Button("OK");
        errorButton.setOnAction(f -> errorStage.close());
        VBox errorLayout = new VBox(10);
        errorLayout.setAlignment(Pos.CENTER);
        errorLayout.getChildren().addAll(errorMessage, errorButton);
        Scene errorScene = new Scene(errorLayout, 150, 100);
        errorStage.setScene(errorScene);
        errorStage.show();
    }
//menu scene
    public void prepareMenu() throws FileNotFoundException {
        //drawing the scene
        xml.getClient().getReservation().setNameOfCLient(xml.getClient().getName());
        Label menu = new Label("Menu");
        menu.setFont(Font.font("ariel", FontWeight.BOLD, FontPosture.REGULAR, 30));
        Label maincourse = new Label("Main Course");
        maincourse.setFont(Font.font("ariel", FontWeight.BOLD, FontPosture.REGULAR, 25));
        Label appetizers = new Label("Appetizers");
        appetizers.setFont(Font.font("ariel", FontWeight.BOLD, FontPosture.REGULAR, 25));
        Label dessert = new Label("Dessert");
        dessert.setFont(Font.font("ariel", FontWeight.BOLD, FontPosture.REGULAR, 25));
        Label receipt = new Label("Receipt");
        receipt.setFont(Font.font("ariel", FontWeight.BOLD, FontPosture.REGULAR, 25));

        Image image = new Image(new FileInputStream("GrilledChicken.jpg"));
        Image image2 = new Image(new FileInputStream("Steak.jpg"));
        Image image3 = new Image(new FileInputStream("MushroomSoup.jpg"));
        Image image4 = new Image(new FileInputStream("FriedPotatoes.jpg"));
        Image image5 = new Image(new FileInputStream("GreekSalad.jpg"));
        Image image6 = new Image(new FileInputStream("MoltenCake.jpg"));
        Image image7 = new Image(new FileInputStream("ApplePie.jpg"));
        ImageView imageView = new ImageView(image);
        ImageView imageView2 = new ImageView(image2);
        ImageView imageView3 = new ImageView(image3);
        ImageView imageView4 = new ImageView(image4);
        ImageView imageView5 = new ImageView(image5);
        ImageView imageView6 = new ImageView(image6);
        ImageView imageView7 = new ImageView(image7);

        imageView.setFitHeight(170);
        imageView.setFitWidth(160);
        imageView2.setFitHeight(170);
        imageView2.setFitWidth(160);
        imageView3.setFitHeight(170);
        imageView3.setFitWidth(160);
        imageView4.setFitHeight(170);
        imageView4.setFitWidth(160);
        imageView5.setFitHeight(170);
        imageView5.setFitWidth(160);
        imageView6.setFitHeight(170);
        imageView6.setFitWidth(160);
        imageView7.setFitHeight(170);
        imageView7.setFitWidth(160);
        Label grilled = new Label("Grilled Chicken.....75LE");
        grilled.setFont(Font.font("ariel", 16));
        Label steak = new Label("Beef Steak.....80LE");
        steak.setFont(Font.font("ariel", 16));
        Label mushroom = new Label("Mushroom Soup...60LE");
        mushroom.setFont(Font.font("ariel", 16));
        Label fries = new Label("Fried Potatoes...30LE");
        fries.setFont(Font.font("ariel", 16));
        Label salad = new Label("Greek Salad...35LE");
        salad.setFont(Font.font("ariel", 16));
        Label molten = new Label("Molten Cake...60LE");
        molten.setFont(Font.font("ariel", 16));
        Label pie = new Label("Apple Pie...50LE");
        pie.setFont(Font.font("ariel", 16));
        Button[] plus = new Button[7];
        Button proceed = new Button("Proceed");
        Button logout = new Button("Log out");
        Button exit = new Button("Exit");
        for (int i = 0; i < 7; i++) {
            plus[i] = new Button("ADD");

        }
        TextArea list = new TextArea();
        list.setEditable(false);
        Label head = new Label("   Dish                  \t\t\t\t\t\t\t  Price   \t\t  Quantity");
        head.setFont(Font.font("ariel", FontWeight.BOLD, 14));

        GridPane grid = new GridPane();
        grid.add(menu, 1, 0);
        grid.add(maincourse, 0, 1);
        grid.add(imageView, 0, 2);
        grid.add(grilled, 0, 3);
        grid.add(plus[0], 0, 4);
        grid.add(imageView2, 1, 2);
        grid.add(steak, 1, 3);
        grid.add(plus[1], 1, 4);
        grid.add(imageView3, 2, 2);
        grid.add(mushroom, 2, 3);
        grid.add(plus[2], 2, 4);
        grid.add(appetizers, 0, 5);
        grid.add(imageView4, 0, 6);
        grid.add(fries, 0, 7);
        grid.add(plus[3], 0, 8);
        grid.add(imageView5, 1, 6);
        grid.add(salad, 1, 7);
        grid.add(plus[4], 1, 8);
        grid.add(dessert, 2, 5);
        grid.add(imageView6, 2, 6);
        grid.add(molten, 2, 7);
        grid.add(plus[5], 2, 8);
        grid.add(imageView7, 3, 6);
        grid.add(pie, 3, 7);
        grid.add(plus[6], 3, 8);
        grid.add(receipt, 4, 0);
        grid.add(head, 4, 1);
        grid.add(list, 4, 2);
        grid.add(proceed, 4, 3);
        grid.add(logout, 4, 4);
        grid.add(exit, 4, 5);
        list.setFont(Font.font("ariel", 14));
                GridPane.setHalignment(menu, HPos.CENTER);
        GridPane.setHalignment(plus[0], HPos.CENTER);
        GridPane.setHalignment(plus[1], HPos.CENTER);
        GridPane.setHalignment(plus[2], HPos.CENTER);
        GridPane.setHalignment(plus[3], HPos.CENTER);
        GridPane.setHalignment(plus[4], HPos.CENTER);
        GridPane.setHalignment(plus[5], HPos.CENTER);
        GridPane.setHalignment(plus[6], HPos.CENTER);
        GridPane.setHalignment(grilled, HPos.CENTER);
        GridPane.setHalignment(steak, HPos.CENTER);
        GridPane.setHalignment(mushroom, HPos.CENTER);
        GridPane.setHalignment(fries, HPos.CENTER);
        GridPane.setHalignment(salad, HPos.CENTER);
        GridPane.setHalignment(molten, HPos.CENTER);
        GridPane.setHalignment(pie, HPos.CENTER);
        GridPane.setHalignment(proceed, HPos.CENTER);
        grid.setHgap(15);
        scene = new Scene(grid, 1400, 1000);
//buttons events
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getWindow().setScene(scene1);

            }
        });
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getWindow().close();

            }
        });
        proceed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (xml.getClient().getReservation().getOrder().getTotalPrice() == 0) {
                    errorMessage("No Order Entered");
                } else {
                    try {
                        xml.getR().addResevation(xml.getClient().getReservation());
                    } catch (JAXBException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        xml.write();
                    } catch (JAXBException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        prepareThankYou();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });
        plus[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    xml.getClient().makeOrder(xml.getR().getDishes().get(0));
                    list.setText(logic.format(xml.getClient().getReservation().getOrder().printOrder()));

                } catch (JAXBException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        plus[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    xml.getClient().makeOrder(xml.getR().getDishes().get(6));
                    list.setText(logic.format(xml.getClient().getReservation().getOrder().printOrder()));

                } catch (JAXBException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        plus[2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    xml.getClient().makeOrder(xml.getR().getDishes().get(5));
                    list.setText(logic.format(xml.getClient().getReservation().getOrder().printOrder()));

                } catch (JAXBException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        plus[3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {

                    xml.getClient().makeOrder(xml.getR().getDishes().get(2));
                    list.setText(logic.format(xml.getClient().getReservation().getOrder().printOrder()));

                } catch (JAXBException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        plus[4].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    xml.getClient().makeOrder(xml.getR().getDishes().get(1));
                    list.setText(logic.format(xml.getClient().getReservation().getOrder().printOrder()));

                } catch (JAXBException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        plus[5].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    xml.getClient().makeOrder(xml.getR().getDishes().get(4));
                    list.setText(logic.format(xml.getClient().getReservation().getOrder().printOrder()));

                } catch (JAXBException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        plus[6].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    xml.getClient().makeOrder(xml.getR().getDishes().get(3));

                    list.setText(logic.format(xml.getClient().getReservation().getOrder().printOrder()));

                } catch (JAXBException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });



        Main.getWindow().setScene(scene);
        Main.getWindow().setFullScreen(true);

    }
//exiting scene for Client
    public void prepareThankYou() throws FileNotFoundException {
//drawing the scene
        Text text = new Text("Thank you for visiting\nBon Appetit");
        text.setFont(Font.font("ariel", FontWeight.BOLD, FontPosture.REGULAR, 30));
        Button logout = new Button("Log out");
        logout.setTextFill(Paint.valueOf("white"));
        logout.setStyle("-fx-background-color: #000000	; ");
        Button exit = new Button("Exit");
        exit.setTextFill(Paint.valueOf("white"));
        exit.setStyle("-fx-background-color: #000000	; ");
        text.setTextAlignment(TextAlignment.CENTER);
        GridPane grid = new GridPane();
        grid.add(text, 1, 0);
        grid.add(logout, 0, 1);
        grid.add(exit, 2, 1);
        Image image = new Image(new FileInputStream("background.jpg"));
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundimage);
        grid.setBackground(background);
        GridPane.setHalignment(logout, HPos.CENTER);
        GridPane.setHalignment(exit, HPos.CENTER);
        grid.setAlignment(Pos.CENTER);
        scene = new Scene(grid, 430, 400);
        //buttons events
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getWindow().setScene(scene1);

            }
        });

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getWindow().close();

            }
        });


        Main.getWindow().setScene(scene);

    }
    //Waiter Scene

    public void prepareWaiter() throws JAXBException, FileNotFoundException {

        // widgets
        Label label1 = new Label("Welcome " + xml.getWaiter().getName());
        label1.setFont(Font.font("ariel", FontWeight.BOLD, FontPosture.REGULAR, 22));
        Text text = new Text("Reseravtion List");
        text.setFont(Font.font("ariel", FontWeight.BOLD, FontPosture.REGULAR, 22));
        Button exit = new Button("Exit");
        exit.setTextFill(Paint.valueOf("white"));
        exit.setStyle("-fx-background-color: #000000	; ");
        Button logOut = new Button("Log Out");
        logOut.setTextFill(Paint.valueOf("white"));
        logOut.setStyle("-fx-background-color: #000000	; ");
        Button view = new Button("View Reservation");
        view.setTextFill(Paint.valueOf("white"));
        view.setStyle("-fx-background-color: #000000	; ");
        TextArea reservationList = new TextArea(xml.getWaiter().viewReservations(xml.getR())); // return formatted string
        reservationList.setPrefColumnCount(24);
        reservationList.setPrefRowCount(24);
        reservationList.setEditable(false);
        Button returnButton = new Button("Return");
        returnButton.setTextFill(Paint.valueOf("white"));
        returnButton.setStyle("-fx-background-color: #000000	; ");
        GridPane grid2 = new GridPane();
        GridPane grid = new GridPane();
        Image image = new Image(new FileInputStream("background.jpg"));
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        Background background = new Background(backgroundimage);

        // setting up layouts
        grid2.add(text, 0, 0);
        grid2.add(reservationList, 0, 1);
        grid2.add(returnButton, 0, 2);
        grid.add(label1, 1, 0);
        grid.add(view, 1, 2);
        grid.add(logOut, 0, 3);
        grid.add(exit, 2, 3);
        grid.setAlignment(Pos.CENTER);
        GridPane.setHalignment(label1, HPos.CENTER);
        GridPane.setHalignment(view, HPos.CENTER);
        GridPane.setHalignment(text, HPos.CENTER);
        GridPane.setHalignment(returnButton, HPos.CENTER);

        grid.setBackground(background);
        grid2.setBackground(background);
        grid.setVgap(10);
        grid2.setAlignment(Pos.CENTER);

        Scene scene2 = new Scene(grid2, 400, 400);
        Scene scene = new Scene(grid, 450, 300);

        // event handling
        logOut.setOnAction(e -> Main.getWindow().setScene(scene1));
        exit.setOnAction(e -> Main.getWindow().close());

        // switching between scenes
        returnButton.setOnAction(e -> Main.getWindow().setScene(scene));
        view.setOnAction(e -> Main.getWindow().setScene(scene2));
        Main.getWindow().setScene(scene);

    }
//Cook Scene
    public void prepareCooker() throws JAXBException, FileNotFoundException {

        //widgets
        Label label1 = new Label("Welcome Chef " + xml.getCooker().getName());
        label1.setFont(Font.font("ariel", FontWeight.BOLD, FontPosture.REGULAR, 22));
        label1.setAlignment(Pos.CENTER);
        Button exit = new Button("Exit");
        exit.setTextFill(Paint.valueOf("white"));
        exit.setStyle("-fx-background-color: #000000	; ");
        Button logOut = new Button("Log Out");
        logOut.setTextFill(Paint.valueOf("white"));
        logOut.setStyle("-fx-background-color: #000000	; ");
        Button viewOrders = new Button("View Orders");
        viewOrders.setTextFill(Paint.valueOf("white"));
        viewOrders.setStyle("-fx-background-color: #000000	; ");
        Text text = new Text("Orders List");
        text.setFont(Font.font("ariel", FontWeight.BOLD, FontPosture.REGULAR, 22));
        TextArea ordersList = new TextArea(xml.getCooker().viewReservations(xml.getR()));
        ordersList.setEditable(false);
        ordersList.setPrefColumnCount(24);
        ordersList.setPrefRowCount(24);
        ordersList.setFont(new Font("Areial", 14));
        Button returnButton = new Button("Return");
        returnButton.setTextFill(Paint.valueOf("white"));
        returnButton.setStyle("-fx-background-color: #000000	; ");
        Image image = new Image(new FileInputStream("background.jpg"));
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundimage);
        GridPane grid = new GridPane();
        GridPane grid2 = new GridPane();
        GridPane.setHalignment(viewOrders, HPos.CENTER);
        GridPane.setHalignment(text, HPos.CENTER);
        GridPane.setHalignment(returnButton, HPos.CENTER);
        grid2.setAlignment(Pos.CENTER);
        grid.setBackground(background);
        grid2.setBackground(background);
        // setting up layouts
        grid2.add(text, 0, 0);
        grid2.add(ordersList, 0, 1);
        grid2.add(returnButton, 0, 2);
        grid.add(label1, 1, 0);
        grid.add(viewOrders, 1, 1);
        grid.add(logOut, 0, 2);
        grid.add(exit, 2, 2);
        grid.setAlignment(Pos.CENTER);
        Scene scene2 = new Scene(grid2, 500, 500);
        Scene scene = new Scene(grid, 450, 300);

        // event handling
        logOut.setOnAction(e -> Main.getWindow().setScene(scene1));
        exit.setOnAction(e -> Main.getWindow().close());

        // switching between scenes
        returnButton.setOnAction(e -> Main.getWindow().setScene(scene));
        viewOrders.setOnAction(e -> Main.getWindow().setScene(scene2));
        Main.getWindow().setScene(scene);

    }
//Manager Scene
    public void prepareManager() throws JAXBException, FileNotFoundException {
        //drawing the scene
        Image image = new Image(new FileInputStream("background.jpg"));
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundimage);
        Label label1 = new Label("Welcome Mr " + xml.getManager().getName());
        label1.setFont(Font.font("ariel", FontWeight.BOLD, FontPosture.REGULAR, 22));
        Button view1 = new Button("View Reservations");
        Button view2 = new Button("View total income");
        Button exit = new Button("Exit");
        Button logout = new Button("Log Out");
        Button back = new Button("Back");
        Button back2 = new Button("Back");
        logout.setTextFill(Paint.valueOf("white"));
        logout.setStyle("-fx-background-color: #000000	; ");
        view1.setTextFill(Paint.valueOf("white"));
        view1.setStyle("-fx-background-color: #000000	; ");
        view2.setTextFill(Paint.valueOf("white"));
        view2.setStyle("-fx-background-color: #000000	; ");
        exit.setTextFill(Paint.valueOf("white"));
        exit.setStyle("-fx-background-color: #000000	; ");
        back.setTextFill(Paint.valueOf("white"));
        back.setStyle("-fx-background-color: #000000	; ");
        back2.setTextFill(Paint.valueOf("white"));
        back2.setStyle("-fx-background-color: #000000	; ");
        TextArea reservations = new TextArea();
        reservations.setPrefColumnCount(24);
        reservations.setPrefRowCount(24);
        reservations.setEditable(false);
        reservations.setText(xml.getManager().viewReservations(xml.getR()));
        Label label2 = new Label(xml.getManager().viewTotalIncome(xml.getR()));
        label2.setFont(Font.font("ariel", FontWeight.BOLD, FontPosture.REGULAR, 22));
        label2.setTextAlignment(TextAlignment.CENTER);
        GridPane grid = new GridPane();
        GridPane grid2 = new GridPane();
        GridPane grid3 = new GridPane();
        grid.add(label1, 1, 0);
        grid.add(view1, 1, 1);
        grid.add(view2, 1, 2);
        grid.add(logout, 0, 3);
        grid.add(exit, 2, 3);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(15);
        GridPane.setHalignment(view1, HPos.CENTER);
        GridPane.setHalignment(label1, HPos.CENTER);
        GridPane.setHalignment(view2, HPos.CENTER);
        
        grid2.add(back, 0, 1);
        grid2.add(reservations, 0, 0);
        GridPane.setHalignment(back, HPos.CENTER);
        grid2.setAlignment(Pos.CENTER);

        grid3.add(label2, 0, 0);
        grid3.add(back2, 0, 1);
        grid3.setAlignment(Pos.CENTER);
        GridPane.setHalignment(back2, HPos.CENTER);

        grid.setBackground(background);
        grid2.setBackground(background);
        grid3.setBackground(background);
        Scene scene = new Scene(grid, 400, 300);
        Scene scene2 = new Scene(grid2, 400, 400);
        Scene scene3 = new Scene(grid3, 400, 400);

        Main.getWindow().setScene(scene);
        //button events
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getWindow().setScene(scene);

            }
        });
        back2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getWindow().setScene(scene);

            }
        });

        view1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getWindow().setScene(scene2);

            }
        });
        view2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getWindow().setScene(scene3);

            }
        });
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getWindow().setScene(scene1);

            }
        });
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getWindow().close();

            }
        });
    }

    public void prepareNewUser() {
        //scene 1
        Text text = new Text("Client Sign up Form:");
        text.setFont(Font.font("ariel", FontWeight.BOLD, FontPosture.REGULAR, 15));
        text.setTextAlignment(TextAlignment.CENTER);
        Label label1 = new Label("Name:");
        label1.setFont(Font.font("ariel", 12));
        Label error = new Label();
        error.setTextAlignment(TextAlignment.CENTER);
        error.setVisible(false);
        label1.setFont(Font.font("ariel", 12));
        Label label2 = new Label("Username:");
        label2.setFont(Font.font("ariel", 12));
        Label label3 = new Label("Password:");
        label3.setFont(Font.font("ariel", 12));
        Button button1 = new Button("Sign up");
        Button back = new Button("Back");
        button1.setFont(Font.font("ariel", 12));
        TextField textfield1 = new TextField();
        TextField textfield2 = new TextField();
        PasswordField textfield3 = new PasswordField();
        GridPane grid = new GridPane();
        grid.add(text, 1, 0);
        grid.add(label1, 0, 1);
        grid.add(label2, 0, 2);
        grid.add(label3, 0, 3);
        grid.add(textfield1, 1, 1);
        grid.add(textfield2, 1, 2);
        grid.add(textfield3, 1, 3);
        grid.add(button1, 1, 4);
        grid.add(error, 1, 5);
        grid.add(back, 1, 8);
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        GridPane.setHalignment(text, HPos.CENTER);
        GridPane.setHalignment(button1, HPos.CENTER);
        GridPane.setHalignment(error, HPos.CENTER);
        GridPane.setHalignment(back, HPos.CENTER);
        Scene scene = new Scene(grid, 300, 300);
        Main.getWindow().setScene(scene);

        //scene 2
        Text text2 = new Text("Thank you for registering");
        text2.setFont(Font.font("ariel", FontWeight.BOLD, FontPosture.REGULAR, 24));
        text2.setTextAlignment(TextAlignment.CENTER);
        Button backTo = new Button("Log in");
        Button exit = new Button("Exit");
        GridPane grid2 = new GridPane();
        grid2.add(text2, 1, 0);
        grid2.add(backTo, 1, 1);
        grid2.add(exit, 1, 1);
        GridPane.setHalignment(backTo, HPos.LEFT);
        GridPane.setHalignment(backTo, HPos.RIGHT);
        grid2.setAlignment(Pos.CENTER);
        Scene scene2 = new Scene(grid2, 400, 300);
        //buttons events
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getWindow().setScene(getScene());

            }
        });
        backTo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getWindow().setScene(getScene());

            }
        });
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getWindow().close();
            }
        });

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {//validation
                    if (textfield1.getText().length() == 0 || textfield2.getText().length() == 0 || textfield3.getText().length() == 0) {
                        errorMessage("Enter Required Fields");

                    } else if (!logic.checkName(textfield1.getText())) {
                        error.setText("This is not a valid name\nNames don't contain numbers\n or special characters");
                        error.setVisible(true);
                    } else if (!logic.checkSpaces(textfield2.getText())) {
                        error.setText("Username don't contain spaces");
                        error.setVisible(true);
                    } else if (!logic.checkUsername(xml.getR().getUsers(), textfield2.getText())) {
                        error.setText("Username already taken");
                        error.setVisible(true);

                    } else if (!logic.checkPassword(textfield3.getText())) {
                        error.setText("Password must be more than\n8 characters");
                        error.setVisible(true);

                    } else {
                        xml.getR().addClient(textfield1.getText(), textfield2.getText(), textfield3.getText());
                        xml.write();
                        Main.getWindow().setScene(scene2);

                    }
                } catch (JAXBException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }
}
