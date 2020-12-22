package hw3.controller;

import java.io.IOException;
import java.sql.*;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hw3.model.Forum;

@WebServlet("/CreateTopic")
public class CreateTopic extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateTopic() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if ((session.getAttribute("isLoggedIn") == null) || (!(boolean) session.getAttribute("isLoggedIn")))
            response.sendRedirect("Login");
        else request.getRequestDispatcher("/WEB-INF/view/CreateTopic.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Forum currentForum = (Forum) getServletContext().getAttribute("currentForum");
        int forumIndex = currentForum.getId();

        String subject = request.getParameter("subject");
        String content = request.getParameter("content");

        HttpSession session = request.getSession();
        int authorId = (int) session.getAttribute("authorId");

        Connection c = null;
        try {
            String url = "*****";
            String username = "*****";
            String password = "*****";

            String insertTopic = "insert into topics (forum_id, author_id, title, reply_count, last_post) values(?,?,?,?,?)";
            String insertReply = "insert into posts (topic_id, author_id, content, timestamp) values (?,?,?,?)";

            c = DriverManager.getConnection(url, username, password);
            PreparedStatement topicStmt = c.prepareStatement(insertTopic);
            PreparedStatement replyStmt = c.prepareStatement(insertReply);

            topicStmt.setInt(1, forumIndex);
            topicStmt.setInt(2, authorId);
            topicStmt.setString(3, subject);
            topicStmt.setInt(4, currentForum.getSize());

            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            topicStmt.setTimestamp(5, timestamp);

            topicStmt.executeUpdate();

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select max(id) as current_topic_id, max(last_post) as topic_last_post from topics");

            if (rs.next()) {
                int currentTopicId = rs.getInt("current_topic_id");
                Timestamp lastPostTimeStamp = rs.getTimestamp("topic_last_post");
                replyStmt.setInt(1, currentTopicId);
                replyStmt.setInt(2, authorId);
                replyStmt.setString(3, content);
                replyStmt.setTimestamp(4, lastPostTimeStamp);
                replyStmt.executeUpdate();
            }

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
        response.sendRedirect("DisplayTopics?forumIndex=" + forumIndex);
    }
}
