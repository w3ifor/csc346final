import javafx.scene.control.Alert;
import javafx.stage.Window;
//import com.mysql.jdbc.Statement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model {

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static List<Article> buildStockArticles(String stockSymbol, Alert.AlertType alertType, Window owner){
        String symbol = stockSymbol.toUpperCase();
        Connection conn;
        java.sql.Statement stmt;
        ResultSet rs;
        List<Article> articles = new ArrayList<Article>();
        String dbFile = "jdbc:sqlite:data.db";
        System.out.println("Tryin to connect");
        int count=0;

        String queryString = "SELECT shortName, fullName FROM company WHERE shortName like'"+symbol+"'";

        try{//check to make sure stock symbol inputed is a valid stock symbol
            conn = DriverManager.getConnection(dbFile);
            if(conn == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failure to connect");
                alert.setHeaderText(null);
                alert.setContentText("Failed to connect to database");
                alert.initOwner(owner);
                alert.show();
                System.out.println("test connection failed");

            }

            stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);
            ResultSetMetaData rsMetaData = rs.getMetaData();
            String symbolFromDatabase=null;
            String fullName;
            System.out.println("Going int to loop");
            while(rs.next()){
                symbolFromDatabase = rs.getString("shortName");
                fullName = rs.getString("fullName");
                System.out.println("Test: "+symbolFromDatabase+" and full name "+fullName);

            }
            if(symbolFromDatabase != null) {
                String url = "http://articlefeeds.nasdaq.com/nasdaq/symbols?symbol=" + symbolFromDatabase;
                String xml;
                xml = readFromURL(url);
                Document doc = Jsoup.parse(xml,"", Parser.xmlParser());
                for(Element item : doc.select("item")){
                    Article newArticle = new Article();
//                   Elements children = item.children();
//                   for(Element child : children){
//                       System.out.println(child.text());
//                   }
                    Elements links = item.select("link");
                    Element link =  links.first();
                    String linkURL = link.text();
                    newArticle.setLink(linkURL);
              //      System.out.println(linkURL);

                    Elements titles = item.select("title");
                    Element title = titles.first();
                    String titleName = title.text();
                    newArticle.setTitle(titleName);
            //        System.out.println(titleName);

                    Elements descs = item.select("description");
                    Element desc = descs.first();
                    String description = desc.text();
                    String [] parts = description.split("<");
                    description = parts[0];
                    newArticle.setDescription(description);
             //       System.out.println(description);

                    Elements dates = item.select("pubDate");
                    Element date = dates.first();
                    String pubDate = date.text();
                    newArticle.setPubDate(pubDate);
           //         System.out.println(pubDate);

                    articles.add(newArticle);
                    count++;

                    if(count >= 3){
                        break;
                    }
                }//end of for

          //      StockArticlesController cntrl = new StockArticlesController();
          //      cntrl.setArticles(articles, stockSymbol);

            }else{}
            for(Article art : articles){
            //    System.out.println("END");
            }

            conn.close();
        } catch (SQLException e) {
           // e.printStackTrace();
            System.err.println("Oops. Failed to connect to "+dbFile);
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return articles;


    }//end of method

    public static String readFromURL(String urlString) {
        String data ="";
        try{
            URL url = new URL(urlString);
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String s;
            while((s = input.readLine()) !=null){
                data += s;
            }

            input.close();
        }catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(2);
        }

        return data;

    }


    }
