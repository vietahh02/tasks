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

/**
 *
 * @author Admin
 */
@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {

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
            out.println("<title>Servlet Register</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("register.jsp").forward(request, response);
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
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String cpass = request.getParameter("cpass");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String err;
        LocalDate k = LocalDate.parse(dob);
        if (!pass.equals(cpass)) {
            err = "Password and Confirm password don't match!!";
        } else {
            Account a = new Account(user, user, pass);
            Information i = new Information("", name, email, phone, "", gender, dob, a);
            Dao d = new Dao();
            if (user.trim().isBlank() || email.trim().isBlank() || phone.trim().isBlank()) {
                err = "Enter data into the fields";
            } else if (user.trim().length() < 4 || user.trim().length() > 10) {
                err = "User must >=4 and <= 10 characters!!!";
            } else if (d.checkUser(user.trim())) {
                err = "UserName already exist!!!";
            } else if (d.checkEmail(email.trim())) {
                err = "Email already exist!!!";
            } else if (phone.substring(0, 1).equals("0")) {
                err = "Phone number Unavailable";
            } else if (d.checkPhone(phone.trim())) {
                err = "Phone number already exist!!!";
            } else if (calculateAge(k) < 6) {
                err = "Age must be greater than 6!!!";
            } else if (pass.length() < 3 || pass.length() > 10) {
                err = "Password must >=3 and <= 10 characters!!!";
            } else {
                boolean check = d.register(a, i);
                if (check) {
                    HttpSession session = request.getSession();
                    session.setAttribute("suc", "Register Successfully!");
                    response.sendRedirect("login");
                    return;
                } else {
                    err = "Register fail!!!";
                }
            }
        }
        request.setAttribute("user", user);
        request.setAttribute("email", email);
        request.setAttribute("name", name);
        request.setAttribute("phone", phone);
        request.setAttribute("gender", gender);
        request.setAttribute("dob", dob);
        request.setAttribute("err", err);
        request.getRequestDispatcher("register.jsp").forward(request, response);
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

    public int calculateAge(LocalDate birthDate) {
        LocalDate dateNow = LocalDate.now();
        // validate inputs ...
        return Period.between(birthDate, dateNow).getYears();
    }
}
