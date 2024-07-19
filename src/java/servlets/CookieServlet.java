/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import registration.RegistrationDAO;
import registration.RegistrationDTO;

/**
 *
 * @author patho
 */
public class CookieServlet extends HttpServlet {

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
    private String SEARCH_PAGE = "search.jsp";
    private String USER_PAGE = "user.jsp";
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = LOGIN_PAGE;
        try  {
            Cookie[] cookies = request.getCookies();
            String userName = null;
            String password = null;
            if(cookies != null){
                for(Cookie cookie:cookies){
                    String k = cookie.getName();//lay key
                    String v = cookie.getValue();//lay value
                    if(k.equals("USER_NAME")){
                        userName = v;
                    }
                    if(k.equals("PASSWORD")){
                        password = v;
                    }
                }
            }
            if(userName != null && password != null){
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO acc = dao.checkLogin(userName, password);
                if(acc != null && acc.isIsAdmin()){
                    url = SEARCH_PAGE;
                }else if(acc != null && !acc.isIsAdmin()){
                    url = USER_PAGE;
                }
            }
        }catch(SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }finally{
            response.sendRedirect(url);
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
