<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modify Student</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0; /* light gray background */
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        h1 {
            color: #343a40; /* dark gray text */
            text-align: center;
            margin-bottom: 20px;
        }

       .container {
            background: #fff; /* white background */
            padding: 20px;
            border: 1px solid #ced4da; /* light gray border */
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* subtle shadow */
            max-width: 400px;
            width: 100%;
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input[type="number"],
        input[type="text"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ced4da; /* light gray border */
            border-radius: 5px;
            transition: border-color 0.2s ease-in-out; /* smooth border color transition */
        }

        input[type="number"]:focus,
        input[type="text"]:focus {
            border-color: #007bff; /* blue border on focus */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* subtle shadow on focus */
        }

        button[type="submit"] {
            background-color: #007bff; /* blue background */
            color: #fff; /* white text */
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.2s ease-in-out; /* smooth background color transition */
        }

        button[type="submit"]:hover {
            background-color: #0056b3; /* darker blue background on hover */
        }

       .message {
            margin-top: 20px;
        }

       .message.error {
            color: #dc3545; /* red text */
            background-color: #ffe6e6; /* light red background */
            padding: 10px;
            border-radius: 5px;
        }

       .message.success {
            color: #28a745; /* green text */
            background-color: #d4edda; /* light green background */
            padding: 10px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Modify Student</h1>
        <form action="ModifyStudentServlet" method="post">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name"><br>
            <label for="rollNumber">Roll Number:</label>
            <input type="text" id="rollNumber" name="rollNumber"><br>
            <label for="course">Course:</label>
            <input type="text" id="course" name="course"><br>
            <label for="year">Year:</label>
            <input type="number" id="year" name="year"><br>
             <label for="email">Email:</label>
            <input type="text" id="email" name="email"><br>
            <button type="submit">Modify Student</button>
        </form>
        <%
            String error = request.getParameter("error");
            String success = request.getParameter("success");
            if (error!= null) {
                out.println("<p class='message error'>" + error + "</p>");
            } else if (success!= null) {
                out.println("<p class='message success'>" + success + "</p>");
            }
        %>
    </div>
</body>
</html>