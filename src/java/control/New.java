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
import java.time.LocalDate;
import java.time.Period;
import model.Account;
import model.Information;
import model.Tasks;

/**
 *
 * @author Admin
 */
@WebServlet(name = "New", urlPatterns = {"/new"})
public class New extends HttpServlet {

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
            out.println("<title>Servlet New</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet New at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("new.jsp").forward(request, response);
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
        String name = request.getParameter("name");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String des = request.getParameter("des");
        String prioritize = request.getParameter("rating");
        String err;
        HttpSession session = request.getSession();

        if (!checkDate(date)) {
            request.setAttribute("name", name);
            request.setAttribute("des", des);
            request.setAttribute("err", "Cannot create past jobs");
            doGet(request, response);
            return;
        }

        if (name.trim().isBlank() || date.trim().isBlank() || time.trim().isBlank() || des.trim().isBlank() || prioritize.trim().isBlank()) {
            err = "Must not be empty!!!";
            request.setAttribute("err", err);
            doGet(request, response);
        } else {
            Tasks t = new Tasks();
            Information i = (Information) session.getAttribute("acc");
            Account a = new Account();
            t.setTaskName(name);
            t.setTaskDate(date);
            t.setTaskTime(time + ":00");
            t.setTaskDes(des);
            t.setPrioritize(prioritize);
            a.setUserID(i.getA().getUserID());
            t.setA(a);
            Dao d = new Dao();
            boolean check = d.newTask(t);

            if (check) {
                session.setAttribute("suc", "Create task successfully!");
                response.sendRedirect("home");
            } else {
                request.setAttribute("err", "Error!!!");
                doGet(request, response);
            }
        }

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

    private boolean checkDate(String date) {
        LocalDate dateNow = LocalDate.now();
        LocalDate k = LocalDate.parse(date);
        int r = k.compareTo(dateNow);
        if (r > 0) {
            return true;
        }
        return false;
    }

}
