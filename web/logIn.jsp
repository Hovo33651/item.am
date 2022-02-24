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
    input,
    .btn {
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

    input:hover,
    .btn:hover {
        opacity: 1;
    }

    /* add appropriate colors to fb, twitter and google buttons */
    .fb {
        background-color: #3B5998;
        color: white;
    }

    .twitter {
        background-color: #55ACEE;
        color: white;
    }

    .google {
        background-color: #dd4b39;
        color: white;
    }

    /* style the submit button */
    input[type=submit] {
        background-color: #04AA6D;
        color: white;
        cursor: pointer;
    }

    input[type=submit]:hover {
        background-color: #45a049;
    }

    /* Two-column layout */
    .col {
        float: left;
        width: 50%;
        margin: auto;
        padding: 0 50px;
        margin-top: 6px;
    }

    /* Clear floats after the columns */
    .row:after {
        content: "";
        display: table;
        clear: both;
    }



    /* bottom container */
    .bottom-container {
        text-align: center;
        background-color: #666;
    }


    /* Responsive layout - when the screen is less than 650px wide, make the two columns stack on top of each other instead of next to each other */
    @media screen and (max-width: 650px) {
        .col {
            width: 100%;
            margin-top: 0;
        }
        /* hide the vertical line */
        .vl {
            display: none;
        }
        /* show the hidden text on small screens */
        .hide-md-lg {
            display: block;
            text-align: center;
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
            <a href="${pageContext.request.contextPath}/register.jsp" style="color:white" class="btn">Գրանցում</a>
        </div>
    </div>
</div>

</body>
</html>
