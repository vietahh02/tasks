/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dal.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.tribes.io.ChannelData;

/**
 *
 * @author Admin
 */
@WebServlet(name = "forget", urlPatterns = {"/forget"})
public class forget extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet forget</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet forget at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        if (request.getAttribute("email") != null && request.getAttribute("phone") != null) {
//            String email = (String) request.getAttribute("email");
//            String phone = (String) request.getAttribute("phone");

//        } else {
            request.getRequestDispatcher("forget.jsp").forward(request, response);
//        }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Dao d = new Dao();
        String err = "";
        if (request.getParameter("pass") != null) {
            String pass = request.getParameter("pass");
            String cpass = request.getParameter("cpass");
            if (!pass.equals(cpass)) {
                err = "Password and confirm password not match!!!";
            } else if (pass.length() < 3 || pass.length() > 10) {
                err = "Password must >=3 and <= 10 characters!!!";
            } else {
                boolean check = d.changePasswordForget(pass, email, phone);
                if (!check) {
                    err = "Change password fail!!!";
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("suc", "Change password successfully.");
                    response.sendRedirect("login");
                    return;
                }
            }
        } else {
            if (!d.checkEmail(email) && !d.checkPhone(phone)) {
                request.setAttribute("err", "Email and Phone number not exactly!!!");
                doGet(request, response);
                return;
            }
        }

        request.setAttribute("err", err);
        request.setAttribute("email", email);
        request.setAttribute("phone", phone);
        doGet(request, response);
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
