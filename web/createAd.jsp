<%@ page import="itemAm.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Hovhanes Gevorgyan
  Date: 24.02.2022
  Time: 01:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    body, textarea {
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
    input[type=text], input[type=text], select, textarea,input[type=file]{
        width: 100%;
        padding: 15px;
        margin: 5px 0 22px 0;
        display: inline-block;
        border: none;
        background: #f1f1f1;
    }


    input[type=text]:focus ,input[type=text]:focus,input[type=file] ,textarea:focus, select:focus{
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

    a {
        color: dodgerblue;
    }
</style>
<body>
<%
    HttpSession currentSession = request.getSession();
    User user = (User) currentSession.getAttribute("user");
%>
<form action="/createAd" method="post" enctype="multipart/form-data">
    <div class="container">
        <h1>Գրանցել հայտարարություն</h1>
        <hr>

        <label><b>Անունը</b></label>
        <input type="text" placeholder="Enter Title" name="title" id="title" required>

        <label><b>Հակիրճ նկարագիր</b></label><br>
        <textarea name="description" placeholder="Item Description"></textarea><br>

        <label><b>Ընտրիր կատեգորիա</b></label><br>
        <select name="category">
            <option value="car">Ավտոմեքենաներ</option>
            <option value="house">Բնակարան/Տուն</option>
            <option value="furniture">Կահույք</option>
            <option value="commercial">Կոմերցիոն</option>
        </select>


        <label><b>Գինը</b></label>
        <input type="text" placeholder="Input the price" name="price" id="psw-repeat" required>
        <label><b>Ընտրիր արժույթ</b></label>
        <select name="currency">
            <option value="USD">USD</option>
            <option value="AMD">AMD</option>
            <option value="RUB">RUB</option>
            <option value="EUR">EUR</option>
        </select>

        <label>Ընտրիր նկար</label><br>
        <input type="file" name="image"><br>
        <input type="hidden" name="userId" value="<%=user.getId()%>">
        <hr>
        <button type="submit" class="registerBtn">Ավելացնել</button>
    </div>
</form>
</body>
</html>
