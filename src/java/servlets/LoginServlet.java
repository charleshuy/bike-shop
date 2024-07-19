/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import registration.RegistrationDAO;
import registration.RegistrationDTO;

/**
 *
 * @author patho
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String SEARCH_PAGE = "search.jsp";
    private String LOGIN_PAGE = "login.jsp";
    private String PURCHASE_PAGE = "purchase.jsp";
    private String USER_PAGE = "user.jsp";
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = LOGIN_PAGE;
        try  {
            String userName = request.getParameter("txtUsername");
            String passWord = request.getParameter("txtPassword");
            RegistrationDAO dao = new RegistrationDAO();
            RegistrationDTO acc = dao.checkLogin(userName, passWord);
            // Create cookies for user data
            request.setAttribute("ACC", acc);
            Cookie userNameCookie = new Cookie("USER_NAME", acc != null ? acc.getUsername() : "");
            Cookie passwordCookie = new Cookie("PASSWORD", acc != null ? acc.getPassword() : "");
            Cookie lastNameCookie = new Cookie("LAST_NAME", acc != null ? acc.getLastName() : "");
            Cookie isAdminCookie = new Cookie("IS_ADMIN", acc != null ? acc.isIsAdmin() + "" : "false");
            response.addCookie(userNameCookie);
            response.addCookie(passwordCookie);
            response.addCookie(lastNameCookie);
            response.addCookie(isAdminCookie);
            if (acc != null && acc.isIsAdmin()) {
                url = SEARCH_PAGE;   
            }else if(acc != null && !acc.isIsAdmin()){         
                url = USER_PAGE;
            }else if(acc == null){
                request.setAttribute("INVALID_LOGIN", "Invalid");
            }
                
                
        }catch (SQLException | ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request,response);
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
