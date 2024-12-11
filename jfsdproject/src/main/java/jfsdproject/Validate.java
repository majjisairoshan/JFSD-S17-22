package jfsdproject;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Validate
 */
@WebServlet("/Validate")
public class Validate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
	    String u = request.getParameter("voterid");
	    //int i = Integer.parseInt(u);
	    
	    String password = request.getParameter("pwd");
	    System.out.println(u+" "+password);
	    System.out.println(u.length());
	    if(u.length()==10) {
	    	try {
		  	      Class.forName("com.mysql.jdbc.Driver");
		  	      Connection conn=DriverManager.getConnection(  
		  	          "jdbc:mysql://localhost:3306/ep","root","root");
		  	      
		  	      
		  	      String sql="select * from voter where voter_id=? and password=?";
		  	      PreparedStatement pstmt = conn.prepareStatement(sql);
		  	      
		  	      pstmt.setString(1, u);
		  	      pstmt.setString(2, password);
		  	      
		  	      ResultSet rs = pstmt.executeQuery();
		  	      if(rs.next()) {
		  	          RequestDispatcher rd = request.getRequestDispatcher("voting.html");
		  	          rd.forward(request, response);
		  	        } else {
		  	          RequestDispatcher rd = request.getRequestDispatcher("failure.html");
		  	          rd.forward(request, response);
		  	        }
		  	    }catch (Exception e) {
		  	         System.out.println(e);
		  	      }
	    	
	    	//System.out.println("inside try");
	    	//RequestDispatcher rd = request.getRequestDispatcher("voter.html");
   		// rd.forward(request, response);
	    }
	    else if(u.length()==5){
	    	try {
		  	      Class.forName("com.mysql.jdbc.Driver");
		  	      Connection conn=DriverManager.getConnection(  
		  	          "jdbc:mysql://localhost:3306/ep","root","root");
		  	      
		  	      
		  	      String sql="select * from volunteer where voter_id=? and password=?";
		  	      PreparedStatement pstmt = conn.prepareStatement(sql);
		  	      
		  	      pstmt.setString(1, u);
		  	      pstmt.setString(2, password);
		  	      
		  	      ResultSet rs = pstmt.executeQuery();
		  	      if(rs.next()) {
		  	    	  if(u.equals("75891")) {
		  	    		  RequestDispatcher rd = request.getRequestDispatcher("volunteer.html");
			  	          rd.forward(request, response); 
		  	    	  }
		  	    	  if(u.equals("38786")) {
		  	    		  RequestDispatcher rd = request.getRequestDispatcher("volunteer1.html");
			  	          rd.forward(request, response); 
		  	    	  }
		  	    	  if(u.equals("12345")) {
		  	    		  RequestDispatcher rd = request.getRequestDispatcher("volunteer2.html");
			  	          rd.forward(request, response); 
		  	    	  }
		  	        } else {
		  	          RequestDispatcher rd = request.getRequestDispatcher("failure.html");
		  	          rd.forward(request, response);
		  	        }
		  	    }catch (Exception e) {
		  	         System.out.println(e);
		  	      }
	    	
	    
	    }
	    
	    
	    else if(u.length()==4){
	    	try {
		  	      Class.forName("com.mysql.jdbc.Driver");
		  	      Connection conn=DriverManager.getConnection(  
		  	          "jdbc:mysql://localhost:3306/ep","root","root");
		  	      
		  	      
		  	      String sql="select * from voter where voter_id=? and password=?";
		  	      PreparedStatement pstmt = conn.prepareStatement(sql);
		  	      
		  	      pstmt.setString(1, u);
		  	      pstmt.setString(2, password);
		  	      
		  	      ResultSet rs = pstmt.executeQuery();
		  	      if(rs.next()) {
		  	          RequestDispatcher rd = request.getRequestDispatcher("admin.html");
		  	          rd.forward(request, response);
		  	        } else {
		  	          RequestDispatcher rd = request.getRequestDispatcher("failure.html");
		  	          rd.forward(request, response);
		  	        }
		  	    }catch (Exception e) {
		  	         System.out.println(e);
		  	      }
	    
	    }
	}

}