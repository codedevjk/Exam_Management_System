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

@WebServlet("/ModifyStudentServlet")
public class ModifyStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String rollNumber = request.getParameter("rollNumber");
        String course = request.getParameter("course");
        int year = request.getParameter("year").isEmpty() ? -1 : Integer.parseInt(request.getParameter("year"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ExaminationSystem", "root", "im@jk17JK");

            StringBuilder sqlBuilder = new StringBuilder("UPDATE students SET ");
            boolean addComma = false;

            if (name != null && !name.isEmpty()) {
                sqlBuilder.append("name = ?");
                addComma = true;
            }

            if (rollNumber != null && !rollNumber.isEmpty()) {
                if (addComma) sqlBuilder.append(", ");
                sqlBuilder.append("roll_number = ?");
                addComma = true;
            }

            if (course != null && !course.isEmpty()) {
                if (addComma) sqlBuilder.append(", ");
                sqlBuilder.append("course = ?");
                addComma = true;
            }

            if (year != -1) {
                if (addComma) sqlBuilder.append(", ");
                sqlBuilder.append("year = ?");
            }

            sqlBuilder.append(" WHERE id = ?");

            PreparedStatement stmt = conn.prepareStatement(sqlBuilder.toString());

            int parameterIndex = 1;

            if (name != null && !name.isEmpty()) {
                stmt.setString(parameterIndex++, name);
            }

            if (rollNumber != null && !rollNumber.isEmpty()) {
                stmt.setString(parameterIndex++, rollNumber);
            }

            if (course != null && !course.isEmpty()) {
                stmt.setString(parameterIndex++, course);
            }

            if (year != -1) {
                stmt.setInt(parameterIndex++, year);
            }

            stmt.setInt(parameterIndex, id);
            stmt.executeUpdate();

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("modifyStudent.jsp");
    }
}
