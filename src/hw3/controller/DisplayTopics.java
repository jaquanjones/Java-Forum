package hw3.controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hw3.model.Forum;
import hw3.model.Topic;

@WebServlet("/DisplayTopics")
public class DisplayTopics extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DisplayTopics() {
        super();
    }

    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int forumIndex = Integer.parseInt(request.getParameter("forumIndex").trim());
        for (Forum forum : (List<Forum>) getServletContext().getAttribute("forums")) {
            if (forumIndex == forum.getId()) {
                request.getServletContext().setAttribute("currentForum", forum);
                request.getServletContext().setAttribute("currentForumIndex", forum.getId());
            }
        }
        List<Topic> topics = new ArrayList<>();

        Connection c = null;
        try {
            String url = "*****";
            String username = "*****";
            String password = "*****";

            c = DriverManager.getConnection(url, username, password);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select t.id, t.forum_id, t.title, t.last_post, count(r.id) as `size`, u.first_name " +
                            "from topics t left join users u on t.author_id = u.id " +
                            "left join replies r  on r.topic_id = t.id " +
                            "where  t.forum_id = " + forumIndex + " group by t.id order by t.id");

            while (rs.next()) {
                topics.add(new Topic(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("first_name"),
                        rs.getInt("size"),
                        rs.getTimestamp("last_post")));
            }
            getServletContext().setAttribute("topics", topics);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (c != null) c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/WEB-INF/view/DisplayTopics.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
