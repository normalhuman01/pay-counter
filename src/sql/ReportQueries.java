package sql;

import Classes.Report;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportQueries {
    private static final String URL = "jdbc:mysql://remotemysql.com:3306/obsZ7jMTsl?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "obsZ7jMTsl";
    private static final String PASSWORD = "U7CiN59KBE";
    
    private Connection connection;
    private PreparedStatement addEntry;   
    private Statement modifyEntry;
    private Statement lastMonth;
    private Statement paymentList;
    private Statement reportList;
     
    public ReportQueries(){
        
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
            modifyEntry = connection.createStatement();
            lastMonth = connection.createStatement();
            
            addEntry = connection.prepareStatement(
                    "INSERT INTO Report "
                    + "(idUser, Hour, Date, LM, Payment) "
                    + "VALUES (?, ?, ?, ?, ?)");
            
            paymentList = connection.createStatement();
            reportList = connection.createStatement();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReportQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int modifyEntry(String username){
        try{
            return modifyEntry.executeUpdate("DELETE FROM Report "
                    + "WHERE idUser IN "
                    + "(SELECT InfoUser.idUser "
                    + "FROM InfoUser WHERE Username = '" + username + "') "
                    + "AND Date = '" + LocalDate.now().toString() + "' ");           
        } catch (SQLException ex) {
            Logger.getLogger(ReportQueries.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public double lastMonth(String username){
        try {
            ResultSet rs = lastMonth.executeQuery("SELECT LM "
                    + "FROM Report WHERE idUser IN "
                    + "(SELECT idUser FROM InfoUser "
                    + "WHERE Username = '" + username + "') "
                    + "ORDER BY idReport DESC");
            rs.first();
            return rs.getDouble("LM");
        } catch (SQLException ex) {
            return 0;
        }
        
    }
    
    public int AddEntry(Report report){
        try{
            
            addEntry.setInt(1, report.getidUser());
            addEntry.setTime(2, Time.valueOf(report.getTime()));
            addEntry.setDate(3, Date.valueOf(report.getDate()));
            addEntry.setDouble(4, report.getLecturaMes());
            addEntry.setDouble(5, report.getPayment());
            
            return addEntry.executeUpdate();
        }catch(SQLException sql){
            return 0;
        }
    }
    
    public List<Report> PaymentList(){
        try {
            List<Report> plist = new ArrayList<>();
            
            ResultSet rs = paymentList.executeQuery("SELECT iu.Username, r.Payment "
                    + "FROM Report AS r INNER JOIN InfoUser AS iu "
                    + "ON iu.idUser = r.idUser "
                    + "WHERE r.Date = '" + LocalDate.now().toString() + "' "
                    + "ORDER BY idReport DESC ");
            
            while(rs.next()){
                plist.add(new Report(rs.getString(1), rs.getDouble(2)));
            }

            return plist;
        } catch (SQLException ex) {
            Logger.getLogger(ReportQueries.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Report> ReportList(String username){
        
        try {
            List<Report> list = new ArrayList<>();
            
            ResultSet rs = reportList.executeQuery(
                    "SELECT iu.Username, r.Hour, r.Date, r.LM, r.Payment "
                    + "FROM Report AS r INNER JOIN InfoUser AS iu "
                    + "ON iu.idUser = r.idUser "
                    + "WHERE iu.Username = '" + username +"' "
                    + "ORDER BY idReport DESC ");
            
            while(rs.next()){
                list.add(new Report(rs.getString(1), rs.getTime(2).toLocalTime(),
                        rs.getDate(3).toLocalDate(), rs.getDouble(4), rs.getDouble(5)));
            }
            
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ReportQueries.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }       
    }
}
