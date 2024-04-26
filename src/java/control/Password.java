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
import model.Information;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Password", urlPatterns = {"/password"})
public class Password extends HttpServlet {

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
            out.println("<title>Servlet Password</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Password at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("password.jsp").forward(request, response);
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
        String old = request.getParameter("old");
        String new1 = request.getParameter("new");
        String again = request.getParameter("again");
        String err;
        HttpSession session = request.getSession();
        Information i = (Information) session.getAttribute("acc");
        if (!new1.equals(again)) {
            err = "New password and confirm password not match!!!";
        } else if (!old.equals(i.getA().getPass())) {
            err = "Old password not exactly!!!";
        } else if (new1.length() < 3 || new1.length() > 10) {
            err = "Password must >=3 and <= 10 characters!!!";
        } else {
            Dao d = new Dao();
            boolean check = d.changePassword(i.getA().getUserID(), new1);
            if (check) {
                i.getA().setPass(new1);
                session.removeAttribute("acc");
                session.setAttribute("acc", i);
                session.setAttribute("suc", "Change password successfully!");
                response.sendRedirect("home");
                return;
            } else {
                err = "Change password fail!!!";
            }
        }
        request.setAttribute("err", err);
        request.getRequestDispatcher("password.jsp").forward(request, response);

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
