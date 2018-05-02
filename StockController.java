import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//This is the controller for the first scene to input stock symbol
public class StockController {
    @FXML
    private TextField stockSymbol;
    @FXML
    private Button submitButton;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
        //  count++;

        List<Article> articles = new ArrayList<Article>();

        Window owner = submitButton.getScene().getWindow();
        // String s = String.format("Button has been called %d times",count);
        //    System.out.println(s);
        if(stockSymbol.getText().isEmpty() ) {
            Model.showAlert(Alert.AlertType.ERROR, owner, "Form Error","Stock Symbol field cannot be empty");
            return;
        }
        articles= Model.buildStockArticles(stockSymbol.getText(),Alert.AlertType.CONFIRMATION,owner);

        //now change scene after articles are found
        if(articles.size() >= 2){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("stockArticles.fxml"));
            Parent articlesViewParent = loader.load();
            Scene articlesViewScene = new Scene(articlesViewParent);

            StockArticlesController controller = loader.<StockArticlesController>getController();
            //controller.setArticles(articles);
            controller.initData(articles);

            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(articlesViewScene);
            window.show();

        }


    }

}
