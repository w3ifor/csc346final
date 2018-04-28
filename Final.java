import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Final {
    public static void main(String[]args){
            String web="http://eoddata.com/stocklist/NASDAQ/A.htm";
        try {
            getData(web);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Sqlite db=new Sqlite();
//        db.makeConnection("data.db");
//        if (db.makeConnection("data.db")) {//if connected add data to database.
//            System.out.println("Database opened!");
//            System.out.println("Data inserted into departments table");
//        } else {
//            System.out.println("Database failed to open!");
//        }

    }
    public static java.sql.Connection getConnection() throws Exception {
        try {
            String dbFile = "jdbc:sqlite:data.db";
            java.sql.Connection conn = DriverManager.getConnection(dbFile);

            return conn;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    public static void getData(String website)throws Exception{
        ArrayList<Company> company=new ArrayList<Company>();
        Company comp=new Company();
        java.sql.Connection con = getConnection();
        Document doc = Jsoup.connect(website).get();
        Elements names=doc.select("tr.ro");
        for (Element name:names) {
            comp = new Company();
            String shortName = name.select("a").first().text();
            comp.shortName = shortName;
            String fullName = name.select("td").get(1).text();
            comp.fullName = fullName;
            PreparedStatement posted = con.prepareStatement("INSERT INTO company(fullName,shortname)" +
                    "VALUES (?,?)");
            posted.setString(1,comp.fullName);
            posted.setString(2,comp.shortName);
            posted.executeUpdate();
            company.add(comp);
        }
con.close();
    }
}
class Company{
    String fullName;
    String shortName;
//    public Company(){
//        fullName="";
//        shortName="NULL";
//    }
}