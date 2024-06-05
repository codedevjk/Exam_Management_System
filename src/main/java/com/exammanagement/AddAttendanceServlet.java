package com.exammanagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/AddAttendanceServlet")
public class AddAttendanceServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String roll_no = request.getParameter("roll_no");
	    String date = request.getParameter("date");
	    String statusStr = request.getParameter("status");
	    boolean status = statusStr != null && statusStr.equals("present");

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ExaminationSystem", "root", "im@jk17JK");

	        String sql = "INSERT INTO attendance (roll_no, date, status) VALUES (?,?,?)";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setString(1, roll_no);
	        stmt.setString(2, date);
	        stmt.setBoolean(3, status);
	        stmt.executeUpdate();

	        conn.close();
	        response.sendRedirect("addAttendance.jsp?success=true");
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("addAttendance.jsp?error=true");
	    }
	}
}