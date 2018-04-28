import java.sql.*;

public class Sqlite {
    static Connection conn;
    public boolean makeConnection(String fileName) {//this connects to the database
        boolean successfulOpen = false;
        String connectString = "jdbc:sqlite:" + fileName;
        try {
            conn = DriverManager.getConnection(connectString);
            if (conn != null) {
                successfulOpen = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return successfulOpen;
    }
    void createTable(String tableName, String column1Name, String column2Name) {
        String query = "create table if not exists " + tableName + "(" + column1Name + ", " + column2Name + ")";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    void insert(String tableName, String abbrev, String fullName) {//inserts data into table tableName
        String query = "INSERT INTO " + tableName + "(abbreviation,fullName) VALUES(?,?)";
        try {
            PreparedStatement pstmst = conn.prepareStatement(query);
            pstmst.setString(1, abbrev);
            pstmst.setString(2, fullName);
            pstmst.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void deleteTable(String tableName) {//deletes a table in scraping.db with name tableName
        String query = "DROP TABLE " + tableName;
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void close() {//closes database
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}