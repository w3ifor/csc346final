import javafx.scene.control.Alert;
import javafx.stage.Window;
//import com.mysql.jdbc.Statement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.*;

public class Model {

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static void buildStockArticles(String stockSymbol, Alert.AlertType alertType, Window owner){
        String symbol = stockSymbol.toUpperCase();
        Connection conn;
        java.sql.Statement stmt;
        ResultSet rs;

        String dbFile = "jdbc:sqlite:data.db";
        System.out.println("Tryin to connect");

        String queryString = "SELECT shortName, fullName FROM company WHERE shortName like'"+symbol+"'";

        try{
            conn = DriverManager.getConnection(dbFile);
            if(conn == null){
//                Alert alert = new Alert(alertType);
//                alert.setTitle("Failure to connect");
//                alert.setHeaderText(null);
//                alert.setContentText("Failed to connect to database");
//                alert.initOwner(owner);
//                alert.show();
                System.out.println("test connection failed");

            }

            stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);
            ResultSetMetaData rsMetaData = rs.getMetaData();
            String symbolFromDatabase=null;
            String fullName;
            while(rs.next()){
                symbolFromDatabase = rs.getString("shortName");
                fullName = rs.getString("fullName");
                System.out.println("Test: "+symbolFromDatabase+" and full name "+fullName);

            }
//            if(symbolFromDatabase != null) {
//                String url = "http://articlefeeds.nasdaq.com/nasdaq/symbols?symbol=" + symbolFromDatabase;
//                String xml;
//                xml = readFromURL(url);
//                Document doc = Jsoup.parse(xml,"", Parser.xmlParser());
//
//            }else{}
            conn.close();
        } catch (SQLException e) {
           // e.printStackTrace();
            System.err.println("Oops. Failed to connect to "+dbFile);
            System.err.println(e.getMessage());
            System.exit(1);
        }


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
