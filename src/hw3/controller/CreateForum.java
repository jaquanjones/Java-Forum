package hw3.controller;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CreateForum")
public class CreateForum extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateForum() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if ((session.getAttribute("isLoggedIn") == null) || (!(boolean) session.getAttribute("isLoggedIn")))
            response.sendRedirect("Login");
        else {
            if ((boolean) session.getAttribute("isAdmin"))
                request.getRequestDispatcher("/WEB-INF/view/CreateForum.jsp").forward(request, response);
            else response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not authorized to add new forum.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        Connection c = null;
        try {
            String url = "*****";
            String username = "*****";
            String password = "*****";

            String insertForum = "insert into forums (name) values(?)";

            c = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = c.prepareStatement(insertForum);

            stmt.setString(1, name);
            stmt.executeUpdate();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (c != null) c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("DisplayForums");

    }

}
