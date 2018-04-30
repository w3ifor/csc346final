import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class StockController {
    @FXML
    private TextField stockSymbol;
    @FXML
    private Button submitButton;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event){
        //  count++;

        Window owner = submitButton.getScene().getWindow();
        // String s = String.format("Button has been called %d times",count);
        //    System.out.println(s);
        if(stockSymbol.getText().isEmpty() ) {
            Model.showAlert(Alert.AlertType.ERROR, owner, "Form Error","Stock Symbol field cannot be empty");
            return;
        }

        Model.buildStockArticles(stockSymbol.getText(),Alert.AlertType.CONFIRMATION,owner);

    }

}
