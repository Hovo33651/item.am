<%--
  Created by IntelliJ IDEA.
  User: Hovhanes Gevorgyan
  Date: 23.02.2022
  Time: 23:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Մուտք</title>
</head>
<style>
    body {
        font-family: Arial, Helvetica, sans-serif;
    }

    * {
        box-sizing: border-box;
    }

    /* style the container */
    .container {
        position: relative;
        border-radius: 5px;
        background-color: #f2f2f2;
        padding: 20px 0 30px 0;
    }

    /* style inputs and link buttons */
    input{
        width: 100%;
        padding: 12px;
        border: none;
        border-radius: 4px;
        margin: 5px 0;
        opacity: 0.85;
        display: inline-block;
        font-size: 17px;
        line-height: 20px;
        text-decoration: none; /* remove underline from anchors */
    }

    .btn{
        width: 100px;
        border: none;
        border-radius: 4px;
        margin: 5px 0;
        opacity: 0.85;
        display: inline-block;
        font-size: 17px;
        line-height: 20px;
        text-decoration: none;
    }

    input:hover,
    .btn:hover {
        opacity: 1;
    }
    input[type=submit] {
        background-color: #04AA6D;
        color: white;
        cursor: pointer;
    }

    input[type=submit]:hover {
        background-color: #45a049;
    }

    .bottom-container input[type=submit]{
        background-color: #c24a4a;
        color: white;
        cursor: pointer;
    }

    /* Two-column layout */
    .col {
        float: left;
        width: 50%;
        padding: 0 50px;
        margin: 6px auto auto;
        text-align: center;
    }

    .row:after {
        content: "";
        display: table;
        clear: both;
    }

    @media screen and (max-width: 650px) {
        .col {
            width: 100%;
            margin-top: 0;
        }
    }
</style>
<body>
<div class="container">
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="row">
            <h2 style="text-align:left; margin-left: 50px">Մուտք</h2>
            <div class="col">
                <input type="email" name="email" placeholder="Email" required>
                <input type="password" name="password" placeholder="Password" required>
                <input type="hidden" name="catId" value=" ">
                <input type="submit" value="Մուտք">
            </div>
        </div>
    </form>
</div>

<div class="bottom-container">
    <div class="row" >
        <div class="col" >
            <form action="/register.jsp">
                <input type="submit" value="Գրանցում">
            </form>
        </div>
    </div>
</div>

</body>
</html>
