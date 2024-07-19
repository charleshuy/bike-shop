/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

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
public class ProductDAO {
    public List<ProductDTO> getProductList()
         throws SQLException, ClassNotFoundException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList();
        try{
            con = DBUtil.getConnection();
            if(con!=null){
                String querry = "SELECT * FROM product";
                stm = con.prepareStatement(querry);
                rs = stm.executeQuery();
                while(rs.next()){
                    String productName = rs.getString("productName");
                    String imageLink = rs.getString("imageUrl");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    String description = rs.getString("description");
                    
                    list.add(new ProductDTO(productName,imageLink, price, quantity, description));     
            }
        }
        }finally{
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
        return list;
    }
    public List<ProductDTO> search(String searchValue) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> accs = new ArrayList<>();
        try {
            con = DBUtil.getConnection();
            if (con != null)
            {
                String query = "SELECT * FROM product WHERE productName LIKE ?";
                stm = con.prepareStatement(query);
                stm.setString(1,"%"+searchValue+"%");
                rs = stm.executeQuery();
                //rs next se bat dau tu 0.
                while (rs.next()) {
                    String productName = rs.getString("productName");
                    String imageLink = rs.getString("imageUrl");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    String description = rs.getString("description");
                    accs.add(new ProductDTO(productName, imageLink,
                            price,
                            quantity,
                            description));
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
    public boolean delete(String productName)
            throws SQLException, ClassNotFoundException
    {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBUtil.getConnection();
            if(con != null){
                String querry = "DELETE FROM product WHERE productName = ?";
                stm = con.prepareStatement(querry);
                stm.setString(1, productName);
                check = stm.executeUpdate() > 0 ? true : false;
            }
        }finally{
            if (stm != null) stm.close();
            if (con != null) con.close();
        }return check;
    }
    public boolean insert(String productName, String imageUrl, float price, int quantity,
            String description)
            throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String query = "INSERT INTO product (productName, imageUrl, price, quantity, description) "
                        + " values (?, ?, ?, ?, ?)";
                stm = con.prepareStatement(query);
                stm.setString(1, productName);
                stm.setString(2,imageUrl);
                stm.setFloat(3, price);
                stm.setInt(4, quantity);
                stm.setString(5, description);
                check = stm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }
    public boolean update(String productName, String imageUrl, float price, int quantity, String description)
            throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String querry = "UPDATE product SET price = ?, imageUrl= ?, quantity = ?, description = ? WHERE productName = ? ";
                stm = con.prepareStatement(querry);
                stm.setFloat(1, price);
                stm.setString(2,imageUrl);
                stm.setInt(3, quantity);
                stm.setString(4, description);
                stm.setString(5, productName);
                check = stm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }
    public boolean CheckModeltExisted(String productName) 
            throws SQLException, ClassNotFoundException {
    List<ProductDTO> products = new ArrayList<>();
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    try {
        con = DBUtil.getConnection();
        if (con != null) {
            String query = "select * from product where productName = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, productName);
            rs = stm.executeQuery(); // Execute the query to get results
            while (rs.next()) {
                float price = rs.getFloat("password");
                int quantity = rs.getInt("lastName");
                String description = rs.getString("isAdmin");
                String imageLink = rs.getString("imageUrl"); 
                products.add(new ProductDTO(productName,imageLink, price, quantity, description));
            }
        }
    } finally {
        if (rs != null) rs.close();
        if (stm != null) stm.close();
        if (con != null) con.close();
    }
    return !products.isEmpty();
}
    public boolean updateQuant(String productName, int quantity)
            throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String querry = "UPDATE product SET quantity = quantity - ? WHERE productName = ? ";
                stm = con.prepareStatement(querry);
                stm.setInt(1, quantity);
                stm.setString(2, productName);
                check = stm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }
}
