
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
