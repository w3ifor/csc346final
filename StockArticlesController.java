import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//this is for the 2nd scene after articles are fetched
//will display 3 most recent articles
public class StockArticlesController {
    @FXML
    private Label title1;
    @FXML
    private Label pubDate1;
    @FXML
    private Label desc1;
    @FXML
    private Hyperlink link1;
    @FXML
    private Label title2;
    @FXML
    private Label pubDate2;
    @FXML
    private Label desc2;
    @FXML
    private Hyperlink link2;
    @FXML
    private Label title3;
    @FXML
    private Label pubDate3;
    @FXML
    private Label desc3;
    @FXML
    private Hyperlink link3;

    List<Article> articles = new ArrayList<Article>();
    String stock;

    public void setArticles(List<Article> arts) {
        for(Article art : arts) {
            articles.add(art);

        }
    }

    public void initData(List<Article> articles){

      //  System.out.println(articles.size());
/*        int count =0;
        for(int i=0;i<articles.size();i++){
            count++;
            System.out.println(count+": " +articles.get(i));
        }*/

//set the label text
        Article art1 = articles.get(0);
        Article art2 = articles.get(1);
        Article art3= articles.get(2);

        title1.setText(art1.getTitle());
      //  title1.setMaxWidth(art1.getTitle().length());
       // title1.setWrapText(true);
  //      title1.setPrefWidth(100);
        title1.setTextAlignment(TextAlignment.JUSTIFY);
        pubDate1.setText(art1.getPubDate());
        pubDate1.setWrapText(true);
        desc1.setText(art1.getDescription());
        desc1.setWrapText(true);
        link1.setText(art1.getLink());
        link1.setWrapText(true);

        title2.setText(art2.getTitle());
        pubDate2.setText(art2.getPubDate());
        desc2.setText(art2.getDescription());
        desc2.setWrapText(true);
        link2.setText(art2.getLink());
        link2.setWrapText(true);

        title3.setText(art3.getTitle());
        pubDate3.setText(art3.getPubDate());
        desc3.setText(art3.getDescription());
        desc3.setWrapText(true);
        link3.setText(art3.getLink());
        link3.setWrapText(true);


    }

}
