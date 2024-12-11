package jfsdproject;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Delete")
public class Delete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Update these values with your actual database credentials
    private final String DB_URL = "jdbc:mysql://localhost:3306/ep";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

      //  String name = request.getParameter("fname");
       String voterid = request.getParameter("uname");
       /*String aadhar = request.getParameter("aadhar");
        String pan = request.getParameter("pan");
        String email = request.getParameter("mail");
      //  String name = request.getParameter("fname");
        String password = request.getParameter("pwd");
        String passwordAgain = request.getParameter("confirmPwd");
	   // int i = Integer.parseInt(id);


        if (!password.equals(passwordAgain)) {
            out.println("<h2>Passwords do not match. Please try again.</h2>");
            return;
        }*/

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String insertQuery = "DELETE FROM volunteer WHERE voter_id=?";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);

            pstmt.setString(1, voterid);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
            	 out.println("<h2>Deletion successful! Deleted, " +voterid + "!</h2>");
            } else {
                out.println("<h2>Registration failed. Please try again later.</h2>");
            }

            pstmt.close();
            conn.close();
        } catch (Exception e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
            e.printStackTrace();
        }
    }
}
