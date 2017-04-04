/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PageRankDemo;

import java.math.BigDecimal;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DreamingTraveler
 */
public class MyDatabase {
    private Connection conn;
    private Statement stmt;
    
    public MyDatabase(){
        String driver = "com.mysql.jdbc.Driver";
        String mysql = "jdbc:mysql://localhost:3306/webcrawlerdb?useUnicode=true&characterEncoding=Big5";
        String username = "webtest";
        String password = "webtest";
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(mysql, username, password);
            stmt = conn.createStatement();
            System.out.printf("DataBase is %s%n", stmt.isClosed()?"closed":"opened");
        } catch(ClassNotFoundException ex){
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public Connection getConn() {
        return conn;
    }

    public Statement getStmt() {
        return stmt;
    }
    
    public void updateData(String newUrl, String title) throws SQLException{
        String sql = "UPDATE page SET url = ?, title = ? WHERE url = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setString(1, newUrl);
        ps.setString(2, title);
        ps.setString(3, newUrl);
        ps.executeUpdate();
    }
    
    public void insertData(String url, String title, double pageRank) throws SQLException{
        String sql = "INSERT INTO page values(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql); 
        
        ps.setString(1, url);
        ps.setString(2, title);
        ps.setDouble(3, pageRank);
        ps.executeUpdate();
        //System.out.println("Add data Success");
    }
    
    public void addPageRank(String url, double pageRank) throws SQLException{
        String sql = "UPDATE page SET PageRank = ? WHERE url = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setDouble(1, pageRank);
        ps.setString(2, url);
        ps.executeUpdate();
    }
}
