/*
Andrea Koch and Wei Zhang
Final Project Spring 2018 CSC 346
This program allows user to input stock symbol. It then displays 3 most recent articles pertain to that company.
When user inputs stock symbol, it checks for it in the database to make sure it is a valid stock symbol.
After validating stock symbol, it creates URL for that stock and scrapes the XML for the 3 most recent articles.
Then it displays it in a new scene for the user.
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("stock.fxml"));
        primaryStage.setTitle("Stock News");

        //primaryStage.setScene(new Scene(root, 300, 275));
        Scene StockScene = new Scene(root, 600, 550);
        primaryStage.setScene(StockScene);
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }
}
