package jfsdproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateVote")
public class UpdateVoteServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ep";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String participantId = request.getParameter("participantId");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if (participantId != null) {
            int id = Integer.parseInt(participantId);
            updateVoteCount(id);
            RequestDispatcher rd = request.getRequestDispatcher("login.html");
	          rd.forward(request, response);
            out.println("<h2>Vote casted successfully</h2>");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
        	RequestDispatcher rd = request.getRequestDispatcher("voting.html");
	          rd.forward(request, response);
        	out.println("<h2>Vote not casted.</h2>");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void updateVoteCount(int participantId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("UPDATE Votes SET votes = votes + 1 WHERE participant_id = ?")) {
            statement.setInt(1, participantId);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
            	System.out.println("Success to record vote for participant: " + participantId);
            } else {
                System.out.println("Failed to record vote for participant: " + participantId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

