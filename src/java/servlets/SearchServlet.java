/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import product.ProductDAO;
import product.ProductDTO;
import registration.RegistrationDAO;
import registration.RegistrationDTO;

/**
 *
 * @author patho
 */
public class SearchServlet extends HttpServlet {

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
    private String USER_PAGE = "user.jsp";
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = SEARCH_PAGE;
        try  {
            String searchValue = request.getParameter("txtSearchValue");
            ProductDAO dao = new ProductDAO();
            //RegistrationDTO acc = (RegistrationDTO) request.getAttribute("lastACC");
            List<ProductDTO> accs = dao.search(searchValue);
            Cookie[] cookies =request.getCookies();
            boolean isAdmin = true;
            for(Cookie cookie:cookies){
                    String k = cookie.getName();
                    String v = cookie.getValue();
                    if(k.equals("IS_ADMIN")){
                        isAdmin = Boolean.parseBoolean(v);
                    }
                }
            if(!isAdmin){
                url = USER_PAGE;
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            //request.setAttribute("ACC", acc);
            request.setAttribute("ACCS", accs);
            rd.forward(request,response);
        }catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally {
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
