package hw3.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hw3.model.Forum;


@WebServlet(urlPatterns = "/DisplayForums", loadOnStartup = 1)
public class DisplayForums extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DisplayForums() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        Connection c = null;
        List<Forum> forums = new ArrayList<>();
        try {
            String url = "*****";
            String username = "*****";
            String password = "*****";

            c = DriverManager.getConnection(url, username, password);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select f.id, f.name, count(t.id) as size " +
                            "from forums f left join topics t " +
                            "on t.forum_id = f.id group by f.id order by f.id");

            while (rs.next()) {
                forums.add(new Forum(rs.getInt("id"), rs.getString("name"), rs.getInt("size")));
            }
            getServletContext().setAttribute("forums", forums);

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection c = null;
        List<Forum> forums = new ArrayList<>();
        try {
            String url = "*****";
            String username = "*****";
            String password = "*****";

            c = DriverManager.getConnection(url, username, password);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select f.id, f.name, count(t.id) as size " +
                            "from forums f left join topics t " +
                            "on t.forum_id = f.id group by f.id order by f.id;");

            while (rs.next()) {
                forums.add(new Forum(rs.getInt("id"), rs.getString("name"), rs.getInt("size")));
            }
            getServletContext().setAttribute("forums", forums);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (c != null) c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/WEB-INF/view/DisplayForums.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
