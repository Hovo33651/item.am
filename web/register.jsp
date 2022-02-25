<%--
  Created by IntelliJ IDEA.
  User: Hovhanes Gevorgyan
  Date: 23.02.2022
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    body {
        font-family: Arial, Helvetica, sans-serif;
        background-color: black;
    }

    * {
        box-sizing: border-box;
    }

    /* Add padding to containers */
    .container {
        padding: 16px;
        background-color: white;
    }

    /* Full-width input fields */
    input[type=text], input[type=password] {
        width: 100%;
        padding: 15px;
        margin: 5px 0 22px 0;
        display: inline-block;
        border: none;
        background: #f1f1f1;
    }

    input[type=text]:focus, input[type=password]:focus {
        background-color: #ddd;
        outline: none;
    }

    /* Overwrite default styles of hr */
    hr {
        border: 1px solid #f1f1f1;
        margin-bottom: 25px;
    }

    /* Set a style for the submit button */
    .registerBtn {
        background-color: #04AA6D;
        color: white;
        padding: 16px 20px;
        margin: 8px 0;
        border: none;
        cursor: pointer;
        width: 100%;
        opacity: 0.9;
    }

    .registerBtn:hover {
        opacity: 1;
    }

    /* Add a blue text color to links */
    a {
        color: dodgerblue;
    }

    /* Set a grey background color and center the text of the "sign in" section */
    .signIn {
        background-color: #f63737;
        text-align: center;
    }
</style>
<body>
<form action="${pageContext.request.contextPath}/register" method="post">
    <div class="container">
        <h1>Գրանցում</h1>
        <p>Գրանցվելու համար լրացրեք բոլոր տողերը</p>
        <hr>

        <label for="name"><b>Անուն*</b></label>
        <input type="text" placeholder="Ձեր անունը" name="name" id="name" required>

        <label for="surname"><b>Ազգանուն*</b></label>
        <input type="text" placeholder="Ձեր ազգանունը" name="surname" id="surname" required>

        <label for="email"><b>Էլ․ հասցե*</b></label>
        <input type="text" placeholder="Ձեր էլ․ հասցեն" name="email" id="email" required>

        <label for="psw"><b>Գաղտնաբառ*</b></label>
        <input type="password" placeholder="Ձեր գաղտնաբառը" name="password" id="psw" required>

        <hr>
        <button type="submit" class="registerBtn">Գրանցվել</button>
    </div>

    <div class="container signIn">
        <p>Արդեն գրանցվա՞ծ ես <a href="${pageContext.request.contextPath}/login.jsp">Մուտք</a>.</p>
    </div>
</form>
</body>
</html>
