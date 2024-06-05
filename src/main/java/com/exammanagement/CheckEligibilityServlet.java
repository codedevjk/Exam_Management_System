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
@WebServlet("/CheckEligibilityServlet")
public class CheckEligibilityServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String course = request.getParameter("course");
        int year = Integer.parseInt(request.getParameter("year"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ExaminationSystem", "root", "im@jk17JK");

            String sql = "SELECT s.name, s.roll_number, SUM(a.status) as attendance_count " +
                         "FROM students s " +
                         "JOIN attendance a ON s.id = a.student_id " +
                         "WHERE s.course = ? AND s.year = ? " +
                         "GROUP BY s.id";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, course);
            stmt.setInt(2, year);
            ResultSet rs = stmt.executeQuery();

            sql = "SELECT min_attendance FROM eligibility WHERE course=? AND year=?";
            PreparedStatement stmt2 = conn.prepareStatement(sql);
            stmt2.setString(1, course);
            stmt2.setInt(2, year);
            ResultSet rs2 = stmt2.executeQuery();
            int minAttendance = 0;
            if (rs2.next()) {
                minAttendance = rs2.getInt("min_attendance");
            }

            PrintWriter out = response.getWriter();
            out.println("<html><body><h1>Eligible Students</h1><table border='1'><tr><th>Name</th><th>Roll Number</th></tr>");
            while (rs.next()) {
                int attendanceCount = rs.getInt("attendance_count");
                if (attendanceCount >= minAttendance) {
                    out.println("<tr><td>" + rs.getString("name") + "</td><td>" + rs.getString("roll_number") + "</td></tr>");
                }
            }
            out.println("</table></body></html>");

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
