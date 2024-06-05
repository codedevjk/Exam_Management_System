<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Student Records</title>
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
            text-align: center; /* added to center the button */
        }

        input[type="submit"] {
            background-color: #007bff; /* blue background */
            color: #fff; /* white text */
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.2s ease-in-out; /* smooth background color transition */
        }

        input[type="submit"]:hover {
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
    </style>
</head>
<body>
    <div class="container">
        <h1>All Student Records</h1>
        <form action="PrintAllRecordsServlet" method="get">
            <input type="submit" value="Print All Records">
        </form>
        <%
            String error = request.getParameter("error");
            if (error!= null) {
                out.println("<p class='message error'>" + error + "</p>");
            }
        %>
    </div>
</body>
</html>