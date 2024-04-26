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
@WebServlet(name = "Home", urlPatterns = {"/home"})
public class Home extends HttpServlet {

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
            out.println("<title>Servlet Home</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Home at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Dao d = new Dao();
        if (request.getParameter("search") != null && session.getAttribute("acc") != null) {
            String s = request.getParameter("search");
            Information i = (Information) session.getAttribute("acc");
            request.setAttribute("tasks", d.getTasksByUserIdAndSearch(i.getA().getUserID(), s));
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else if (request.getParameter("sort") != null && session.getAttribute("acc") != null) {
            String sort = request.getParameter("sort");
            Information i = (Information) session.getAttribute("acc");
            switch (sort) {
                case "1":
                    test(request, response);
                    break;
                case "2":
                    request.setAttribute("sort", sort);
                    request.setAttribute("tasks", d.getDown(i.getA().getUserID()));
                    request.getRequestDispatcher("test.jsp").forward(request, response);
                    break;
                case "3":
                    request.setAttribute("sort", sort);
                    request.setAttribute("tasks", d.getTasksByUserIdAndSort(i.getA().getUserID(), "desc"));
                    request.getRequestDispatcher("home.jsp").forward(request, response);
                    break;
                case "4":
                    request.setAttribute("sort", sort);
                    request.setAttribute("tasks", d.getTasksByUserIdAndSort(i.getA().getUserID(), "asc"));
                    request.getRequestDispatcher("home.jsp").forward(request, response);
                    break;
                default:
                    response.sendRedirect("home");
            }
        } else {
            test(request, response);
        }
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

    private void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("acc") != null) {
            Dao d = new Dao();
            Information i = (Information) session.getAttribute("acc");
            request.setAttribute("tasks", d.getTasksByUserId(i.getA().getUserID()));
        }
        String suc = String.valueOf(session.getAttribute("suc"));
        String err = String.valueOf(session.getAttribute("err"));
        if (suc != null) {
            request.setAttribute("suc", suc);
            session.removeAttribute("suc");
        }
        if (err != null) {
            request.setAttribute("err", err);
            session.removeAttribute("err");
        }
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    private void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("acc") != null) {
            Dao d = new Dao();
            Information i = (Information) session.getAttribute("acc");
            request.setAttribute("tasks", d.get(i.getA().getUserID()));
        }
        String suc = String.valueOf(session.getAttribute("suc"));
        String err = String.valueOf(session.getAttribute("err"));
        if (suc != null) {
            request.setAttribute("suc", suc);
            session.removeAttribute("suc");
        }
        if (err != null) {
            request.setAttribute("err", err);
            session.removeAttribute("err");
        }
        request.getRequestDispatcher("test.jsp").forward(request, response);
    }

}
