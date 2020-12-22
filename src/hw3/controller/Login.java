package hw3.controller;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection c = null;
        try {
            String url = "*****";
            String username = "*****";
            String password = "*****";

            String user = request.getParameter("username");
            String pw = request.getParameter("password");

            c = DriverManager.getConnection(url, username, password);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select * from users where username = '" + user + "' and password='" + pw + "'");
            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("authorId", rs.getInt("id"));
                session.setAttribute("fName", rs.getString("first_name"));
                session.setAttribute("isAdmin", rs.getBoolean("is_admin"));
                session.setAttribute("isLoggedIn", true);
            }
            c.close();
            response.sendRedirect("DisplayForums");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (c != null) c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
