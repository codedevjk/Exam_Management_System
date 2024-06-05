package com.exammanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/PrintAllRecordsServlet")
public class PrintAllRecordsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ExaminationSystem", "root", "im@jk17JK");

	        String sql = "SELECT * FROM students";
	        stmt = conn.prepareStatement(sql);
	        rs = stmt.executeQuery();

	        PrintWriter out = response.getWriter();
	        out.println("<html><body><h1>All Student Records</h1><table border='1'><tr><th>Name</th><th>Roll Number</th><th>Course</th><th>Year</th></tr>");
	        while (rs.next()) {
	            out.println( "</td><td>" + rs.getString("name") + "</td><td>" + rs.getString("roll_number") + "</td><td>" + rs.getString("course") + "</td><td>" + rs.getInt("year") + "</td></tr>");
	        }
	        out.println("</table></body></html>");
	    } catch (Exception e) {
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        response.setContentType("text/plain");
	        response.getWriter().println("Error: " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (stmt != null) {
	                stmt.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            // log the exception
	        }
	    }
	}
}