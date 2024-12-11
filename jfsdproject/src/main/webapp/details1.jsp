<%@ page import="java.sql.*" %> 
<%@ page import="java.sql.Statement" %> 
<%@ page import="java.io.*" %> 

<html>
<head>
<meta charset="UTF-8">
<title>Admin Portal</title>
<style>
    /* Styles for the navigation bar */
    ul.navbar {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        background-color: #333;
    }

    li.navitem {
        float: left;
    }

    li.navitem a {
        display: block;
        color: white;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
    }

    li.navitem a:hover {
        background-color: #111;
    }

    /* Styles for the content */
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
    }

    .content {
    max-width: 800px;
    margin: 0 auto;
}

.forms-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 20px;
    max-width: 800px; /* Set a maximum width for the forms container */
    margin: 0 auto; /* Center the forms container within the content area */
}

.form-container {
    /* ... previous CSS for form-container ... */
    width: 100%; /* Adjust the width of each form container to 100% */
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
    background-color: #fff;
    padding: 20px;
}

    h1 {
        text-align: center;
        margin-bottom: 20px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    th, td {
        text-align: left;
        padding: 8px;
        border-bottom: 1px solid #ddd;
    }

    th {
        background-color: #f2f2f2;
        color: #333; /* Header text color */
    }
    
    .logout-btn {
        float: right;
        margin-right: 20px;
        color: white;
        background-color: #333;
        padding: 10px 16px;
        text-decoration: none;
        border-radius: 5px;
    }

    .logout-btn:hover {
        background-color: #111;
    }
</style>
</head>
<body>
<ul class="navbar">
    <li class="navitem"><a href="votes.jsp">Home</a></li>
    
    
    <li class="navitem">
        <a href="volunteer.html" class="logout-btn">Go Back</a>
    </li>
</ul>

<div class="container">
    <div class="form-container">
        <h1>Voters</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Aadhar</th>
                <th>Pan</th>
                <th>Email</th>
                <th>Password</th>
            </tr>
            <% 
            String jdbcUrl = "jdbc:mysql://localhost:3306/ep";
            String username = "root";
            String password = "root";
            try {
                Connection connection = null; 
                Class.forName("com.mysql.jdbc.Driver").newInstance(); 
                connection = DriverManager.getConnection(jdbcUrl, username, password);

                if (!connection.isClosed()) {
                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM voter WHERE voter_id LIKE '2100031%'");  
                    while (rs.next()) {
            %>
                        <tr>
                            <td><%= rs.getString(1) %></td>
                            <td><%= rs.getString(2) %></td>
                            <td><%= rs.getString(3) %></td>
                            <td><%= rs.getString(4) %></td>
                            <td><%= rs.getString(5) %></td>
                        </tr>
            <% 
                    }
                    connection.close();
                }
            } catch (Exception ex) {
            %>
                <tr>
                    <td colspan="4" style="color: red;">Unable to connect to the database.</td>
                </tr>
            <%
            }
            %>
        </table>
    </div>
</div>
</body> 
</html>
