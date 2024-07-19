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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import registration.RegistrationDAO;
import registration.RegistrationError;

/**
 *
 * @author patho
 */
public class SignupServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String SIGNUP_PAGE = "signup.jsp";
    private String LOGIN_PAGE = "login.jsp";
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = LOGIN_PAGE;
        try  {
            String userName = request.getParameter("txtUserName");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            String lastName = request.getParameter("txtLastName");
            RegistrationError errors = new RegistrationError();
            RegistrationDAO dao = new RegistrationDAO();
            boolean result = errors.checkUserNameLen(userName, 4, 8) & 
                    errors.checkPasswordLen(password, 4, 8) &
                    errors.checkConfirm(confirm, password) &
                    errors.checkLastNameLen(lastName, 4, 8);
            
            if(result) result = errors.checkAccountExist(userName);
            if(result){
                result = dao.insert(userName, password, lastName);
            }
            if(!result){
                request.setAttribute("ERROR", errors);
                url = SIGNUP_PAGE;
            }else {
                url = LOGIN_PAGE;
            }
        }catch(SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
        finally{
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
