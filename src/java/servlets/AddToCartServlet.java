/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import cart.CartDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import product.ProductDTO;

/**
 *
 * @author patho
 */
public class AddToCartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private int quant = 1;
    private String USER_PAGE = "user.jsp";
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = "";
        try {
            String productName = request.getParameter("txtProductName");
            float price = Float.parseFloat(request.getParameter("txtPrice"));     
            int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
            String description = request.getParameter("txtDescription");
            String lastSearchValue = request.getParameter("lastSearchValue");
            String imageLink = request.getParameter("txtImageLink");
            HttpSession session = request.getSession();
            if(quantity>0){
                if(session.getAttribute("CART") == null){
                CartDTO cart = new CartDTO();
                List<ProductDTO> listProducts = new ArrayList<ProductDTO>();
                ProductDTO product = new ProductDTO(productName, imageLink, price, quant, description);
                listProducts.add(product);
                cart.setProducts(listProducts);
                session.setAttribute("CART", cart);
                }else{
                    CartDTO cart = (CartDTO) session.getAttribute("CART");
                    List<ProductDTO> listProducts = cart.getProducts();
                    boolean check = false;
                    for(ProductDTO product: listProducts){
                        if(product.getProductName().equals(productName)){
                            if(product.getQuantity() + quant > quantity){
                                request.setAttribute("OUT_OF_STOCK", "out of stock");
                            }else{
                                product.setQuantity(product.getQuantity() + quant);  
                            }
                            check = true;
                        }
                    }
                    if(check == false){
                        ProductDTO product = new ProductDTO(productName, imageLink, price, quant, description);
                        listProducts.add(product);
                    }
                    cart.setProducts(listProducts);
                    session.setAttribute("CART", cart);
                }
            }else{
                request.setAttribute("OUT_OF_STOCK", "out of stock");
            }
            
            url = "MainController?btAction=Search&txtSearchValue=" + lastSearchValue;
        }finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
