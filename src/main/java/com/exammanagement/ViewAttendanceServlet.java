package com.exammanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/ViewAttendanceServlet")
public class ViewAttendanceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ExaminationSystem", "root", "im@jk17JK");

            String sql = "SELECT * FROM attendance WHERE student_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            PrintWriter out = response.getWriter();
            out.println("<html><body><h1>Attendance Records</h1><table border='1'><tr><th>Date</th><th>Status</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getDate("date") + "</td><td>" + rs.getBoolean("status") + "</td></tr>");
            }
            out.println("</table></body></html>");

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
