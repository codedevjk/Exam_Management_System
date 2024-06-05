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
@WebServlet("/EditEligibilityServlet")
public class EditEligibilityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String course = request.getParameter("course");
        int year = Integer.parseInt(request.getParameter("year"));
        int minAttendance = Integer.parseInt(request.getParameter("minAttendance"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ExaminationSystem", "root", "im@jk17JK");

            String sql = "UPDATE eligibility SET min_attendance=? WHERE course=? AND year=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, minAttendance);
            stmt.setString(2, course);
            stmt.setInt(3, year);
            stmt.executeUpdate();

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("editEligibility.jsp");
    }
}
