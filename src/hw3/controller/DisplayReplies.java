package hw3.controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hw3.model.Topic;
import hw3.model.Reply;


@WebServlet("/DisplayReplies")
public class DisplayReplies extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DisplayReplies() {
        super();
    }

    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int topicIndex = Integer.parseInt(request.getParameter("topicIndex").trim());
        for (Topic topic : (List<Topic>) getServletContext().getAttribute("topics")) {
            if (topicIndex == topic.getId()) {
                request.getServletContext().setAttribute("currentTopic", topic);
                request.getServletContext().setAttribute("currentTopicIndex", topic.getId());
            }
        }

        List<Reply> replies = new ArrayList<>();
        Connection c = null;
        try {
            int currentTopicIndex = (int) getServletContext().getAttribute("currentTopicIndex");

            String url = "*****";
            String username = "*****";
            String password = "*****";

            c = DriverManager.getConnection(url, username, password);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select u.first_name,r.id, r.content, r.timeStamp " +
                            "from replies r, users u " +
                            "where u.id = r.author_id and r.topic_id = " + currentTopicIndex);

            while (rs.next()) {
                replies.add(new Reply(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("content"),
                        rs.getTimestamp("timeStamp")));
            }

            getServletContext().setAttribute("replies", replies);
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
        request.getRequestDispatcher("/WEB-INF/view/DisplayReplies.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if ((session.getAttribute("isLoggedIn") == null) || (!(boolean) session.getAttribute("isLoggedIn")))
            response.sendRedirect("Login");
        else {
            int topicIndex = (int) getServletContext().getAttribute("currentTopicIndex");
            int authorId = (int) (session.getAttribute("authorId"));

            String content = request.getParameter("content");

            Connection c = null;
            try {
                String url = "*****";
                String username = "*****";
                String password = "*****";

                String insertReply = "insert into replies (topic_id, author_id, content, timestamp) values (?,?,?,?)";

                c = DriverManager.getConnection(url, username, password);
                PreparedStatement replyStmt = c.prepareStatement(insertReply);

                replyStmt.setInt(1, topicIndex);
                replyStmt.setInt(2, authorId);
                replyStmt.setString(3, content);

                Date date = new Date();
                Timestamp timestamp = new Timestamp(date.getTime());

                Statement stmt = c.createStatement();
                stmt.executeUpdate("update topics set last_post = '" + timestamp + "' where id ='" + topicIndex + "'");

                replyStmt.setTimestamp(4, timestamp);
                replyStmt.executeUpdate();
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
            response.sendRedirect("DisplayReplies?topicIndex=" + topicIndex);
        }
    }
}