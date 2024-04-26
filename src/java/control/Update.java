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
import model.Account;
import model.Information;
import model.Tasks;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Update", urlPatterns = {"/update"})
public class Update extends HttpServlet {

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
            out.println("<title>Servlet Update</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Update at " + request.getContextPath() + "</h1>");
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
        String mode = request.getParameter("mode");
        String id = request.getParameter("id");
        Dao d = new Dao();
        HttpSession session = request.getSession();
        if (mode != null && "1".equals(mode)) {
            boolean check = d.deleteTask(id);
            if (check) {
                session.setAttribute("suc", "Update task successfully!");
            } else {
                session.setAttribute("err", "Error!!!");
            }
        }
        if (mode != null && "2".equals(mode)) {
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
            request.setAttribute("task", d.getTasksByTaskId(id));
            request.getRequestDispatcher("update.jsp").forward(request, response);
        }
        response.sendRedirect("home");
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
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String des = request.getParameter("des");
        String prioritize = request.getParameter("rating");
        HttpSession session = request.getSession();
        Tasks t = new Tasks();
        t.setTaskID(id);
        t.setTaskName(name);
        t.setTaskDate(date);

        if (!checkDate(date)) {
            session.setAttribute("err", "Cannot update past jobs");
            response.sendRedirect("update?mode=2&id=" + id);
            return;
        }
        
        if(name.trim().isBlank() || des.trim().isBlank()) {
            session.setAttribute("err", "Enter data into the fields");
            response.sendRedirect("update?mode=2&id=" + id);
            return;
        }
        if (time.length() > 5) {
            t.setTaskTime(time);
        } else {
            t.setTaskTime(time + ":00");
        }
        t.setTaskDes(des);
        t.setPrioritize(prioritize);
        Dao d = new Dao();
        boolean check = d.updateTask(t);
        if (check) {
            session.setAttribute("suc", "Update task successfully!");
            response.sendRedirect("home");
        } else {
            session.setAttribute("err", "Error!!!");
            response.sendRedirect("update?mode=2&id=" + id);
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
