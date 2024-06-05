<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Attendance</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        h1 {
            color: #343a40;
            text-align: center;
            margin-bottom: 20px;
        }

        .container {
            background: #fff;
            padding: 20px;
            border: 1px solid #ced4da;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }

        label {
            display: block;
            margin-bottom: 10px;
            color: #666;
        }

        input[type="number"],
        input[type="date"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ced4da;
            border-radius: 5px;
            transition: border-color 0.2s ease-in-out;
        }

        input[type="number"]:focus,
        input[type="date"]:focus {
            border-color: #007bff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        select {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ced4da;
            border-radius: 5px;
            transition: border-color 0.2s ease-in-out;
        }

        select:focus {
            border-color: #007bff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        button[type="submit"] {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.2s ease-in-out;
        }

        button[type="submit"]:hover {
            background-color: #0056b3;
        }

        .message {
            margin-top: 20px;
        }

        .message.error {
            color: #dc3545;
        }

        .message.success {
            color: #28a745;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Mark Attendance</h1>
        <form action="AddAttendanceServlet" method="post">
            <label for="roll_no">Roll no:</label>
            <input type="number" id="student_id" name="student_id" required><br>
            <label for="date">Date:</label>
            <input type="date" id="date" name="date" required><br>
            <label for="status">Status:</label>
            <select id="status" name="status" required>
                <option value="present">Present</option>
                <option value="absent">Absent</option>
            </select><br>
            <button type="submit">Mark Attendance</button>
        </form>
        <%
    String error = request.getParameter("error");
    String success = request.getParameter("success");
    if (error!= null) {
        out.println("<p class='message error'>Error marking attendance!</p>");
    } else if (success!= null) {
        out.println("<p class='message success'>Attendance marked successfully!</p>");
    }
%>
    </div>
</body>
</html>