/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author patho
 */
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String LOGIN_PAGE = "login.jsp";
    private String LOGIN_SERVLET = "LoginServlet";
    private String SEARCH_SERVLET = "SearchServlet";
    private String SIGNUP_SERVLET = "SignupServlet";
    private String DELETE_SERVLET = "DeleteServlet";
    private String UPDATE_SERVLET = "UpdateServlet";
    private String COOKIE_SERVLET = "CookieServlet";
    private String INSERT_SERVLET = "InsertServlet";
    private String LOGOUT_SERVLET = "LogoutServlet";
    private String ADDTOCART_SERVLET = "AddToCartServlet";
    private String REMOVECART_SERVLET = "RemoveCartServlet";
    private String SEARCHACC_SERVLET = "SearchAccountServlet";
    private String DELETEACC_SERVLET = "DeleteAccountServlet";
    private String UPDATEACC_SERVLET = "UpdateAccountServlet";
    private String CHECKOUT_SERVLET = "CheckOutServlet";
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = LOGIN_PAGE;
        try  {
            String value = request.getParameter("btAction");
            if(value == null){
                url = COOKIE_SERVLET;
            }else if(value.equals("Login")){
                url = LOGIN_SERVLET;
            }else if(value.equals("Search")){
                url = SEARCH_SERVLET;
            }else if(value.equals("Create")){
                url = SIGNUP_SERVLET;
            }else if(value.equals("Delete")){
                url = DELETE_SERVLET;
            }else if(value.equals("Update")){
                url = UPDATE_SERVLET;
            }else if(value.equals("Add")){
                url = INSERT_SERVLET;
            }else if(value.equals("Logout")){
                url = LOGOUT_SERVLET;
            }else if(value.equals("Add To Cart")){
                url = ADDTOCART_SERVLET;
            }else if(value.equals("Remove")){
                url = REMOVECART_SERVLET;
            }else if(value.equals("Search Acc")){
                url = SEARCHACC_SERVLET;
            }else if(value.equals("DeleteAcc")){
                url = DELETEACC_SERVLET;
            }else if(value.equals("Update Account")){
                url = UPDATEACC_SERVLET;
            }else if(value.equals("Check Out")){
                url = CHECKOUT_SERVLET;
            }
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
