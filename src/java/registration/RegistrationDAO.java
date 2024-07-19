/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtil;

/**
 *
 * @author patho
 */
public class RegistrationDAO {
    public RegistrationDTO checkLogin (String username, String password) throws SQLException,ClassNotFoundException {
        // Target: Check xem Username & Password exist?
        //in DB
        //return true if found otherwise FALSE
        //Connection: Ket noi db
        Connection con = null;
        // Query
        PreparedStatement stm = null;
        //ResultSet luu dulieu ve
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            if (con != null){
                String query = "SELECT * FROM registration WHERE username = ? AND password = ?"; //"?" la PlaceOver, dat cho.
                stm = con.prepareStatement(query); //Khoi tao cau lenh query
                stm.setString(1, username); //Nap gia tri cho vi tri ? dau tien
                stm.setString(2, password); //Nap gia tri cho vi tri ? thu 2 
                rs = stm.executeQuery(); //Chay lenh query
                RegistrationDTO dto = null;
                if (rs.next()) {
                   String name = rs.getString("username");
                   String pass = rs.getString("password");       //Kiem tra xem co ton tai bat cu hang nao thoa cau lenh nay khong
                   String lastName = rs.getString("lastname");
                   boolean isAdmin = rs.getBoolean("isAdmin");
                   dto = new RegistrationDTO(name,pass,lastName,isAdmin);
            }
                return dto;
        } 
            }
        finally {
            //VIEC TRUY XUAT DU LIEU SE RAT TON TAI NGUYEN
            //VI VAY TOT NHAT NEN DONG CONNECTION ASAP
            //DONG NHUNG CONNECTION DUOC MO RA CUOI CUNG TRUOC.
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
        return null; //Neu bi exception, thi return false
    }
    public List<RegistrationDTO> search(String searchValue) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<RegistrationDTO> accs = new ArrayList<>();
        try {
            con = DBUtil.getConnection();
            if (con != null)
            {
                String query = "SELECT * FROM registration WHERE lastname LIKE ?";
                stm = con.prepareStatement(query);
                stm.setString(1,"%"+searchValue+"%");
                rs = stm.executeQuery();
                //rs next se bat dau tu 0.
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastName = rs.getString("lastname");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    accs.add(new RegistrationDTO(username,
                            password,
                            lastName,
                            isAdmin));
                }
            }
        }
        finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
        return accs;
    }
    public boolean CheckAccountExisted(String userName) 
            throws SQLException, ClassNotFoundException {
    List<RegistrationDTO> accs = new ArrayList<>();
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    try {
        con = DBUtil.getConnection();
        if (con != null) {
            String query = "select * from registration where userName = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, userName);
            rs = stm.executeQuery(); // Execute the query to get results
            while (rs.next()) {
                String username = rs.getString("userName");
                String password = rs.getString("password");
                String lastName = rs.getString("lastName");
                boolean isAdmin = rs.getBoolean("isAdmin");
                accs.add(new RegistrationDTO(username, password, lastName, isAdmin));
            }
        }
    } finally {
        if (rs != null) rs.close();
        if (stm != null) stm.close();
        if (con != null) con.close();
    }
    // If there are accounts in the list, return true (account exists), otherwise false
    return !accs.isEmpty();
}

    public boolean insert(String userName, String password, String lastName)
            throws SQLException, ClassNotFoundException{
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBUtil.getConnection();
            if(con!=null){
                String query = "INSERT INTO registration (userName, password, lastName, isAdmin) "
                        + " values (?, ?, ?, 0)";
                stm = con.prepareStatement(query);
                stm.setString(1, userName);
                stm.setString(2, password);
                stm.setString(3, lastName);
                check = stm.executeUpdate() > 0 ? true : false;
            }
        }
        finally{
            if (stm != null) stm.close();
            if (con != null) con.close();
        }return check;
    }
    public boolean delete(String userName)
            throws SQLException, ClassNotFoundException
    {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBUtil.getConnection();
            if(con != null){
                String querry = "DELETE FROM registration WHERE userName = ?";
                stm = con.prepareStatement(querry);
                stm.setString(1, userName);
                check = stm.executeUpdate() > 0 ? true : false;
            }
        }finally{
            if (stm != null) stm.close();
            if (con != null) con.close();
        }return check;
    }
    public boolean update(String userName, String password, String lastName, boolean isAdmin)
            throws SQLException, ClassNotFoundException
    {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBUtil.getConnection();
            if(con != null){
                String querry = "UPDATE registration SET password = ?, lastName = ?, isAdmin = ? WHERE userName = ? ";
                stm = con.prepareStatement(querry);
                stm.setString(1, password);
                stm.setString(2, lastName);
                stm.setBoolean(3, isAdmin);
                stm.setString(4, userName);
                check = stm.executeUpdate() > 0 ? true : false;
            }
        }finally{
            if (stm != null) stm.close();
            if (con != null) con.close();
        }return check;
    }
}
