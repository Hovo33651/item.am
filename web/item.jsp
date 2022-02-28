<%@ page import="itemAm.model.Category" %>
<%@ page import="itemAm.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="itemAm.model.Item" %>
<%@ page import="itemAm.model.Picture" %><%--
  Created by IntelliJ IDEA.
  User: Hovhanes Gevorgyan
  Date: 26.02.2022
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    * {
        box-sizing: border-box;
    }

    body {
        font-family: Arial, Helvetica, sans-serif;
        margin: 0;
    }

    /* Style the header */
    .header {
        padding-bottom: 15px;
        padding-top: 15px;
        text-align: center;
        background: #1abc9c;
        color: white;
        margin-bottom: 3px;
        height: 200px;
    }

    /* Increase the font size of the h1 element */
    .header h1 {
        font-size: 40px;
    }

    /* Style the top navigation bar */
    .navbar {
        overflow: hidden;
        background-color: #ffebaf;
    }

    /* Style the navigation bar links */
    .navbar a {
        float: left;
        display: block;
        color: #3f2c2c;
        text-align: center;
        padding: 14px 20px;
        text-decoration: none;
    }

    /* Right-aligned link */
    .navbar a.right {
        float: right;
        color: #c4c4c4;
    }

    /* Change color on hover */
    .navbar a:hover {
        background-color: #ddd;
        color: black;
    }

    /* Column container */
    .row {
        display: flex;
        flex-wrap: wrap;
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        padding: 100px;margin-top: 100px;
    }

    /* Create two unequal columns that sits next to each other */
    /* Sidebar/left column */
    .side {
        flex: 30%;
        background-color: #f1f1f1;
        padding: 20px;
        height: 350px;
    }
    .sidenav a {
        font-size: 15px;
        color: #f30707;

    }
    @media screen and (max-height: 450px) {
        .sidenav {
            padding-top: 15px;
        }

        #main {
            transition: margin-left .5s;
            padding: 16px;
            float: right;
        }
    }

        /* Fake image, just for this example */
    .fakeImg {
        width: 100%;
    }
    .sidenav {
        height: 100%;
        width: 0;
        position: fixed;
        z-index: 1;
        top: 0;
        left: 0;
        background-color: #2d2a2a;
        overflow-x: hidden;
        transition: 0.7s;
        padding-top: 60px;
        opacity: 0.8;
        text-align: center;
    }

    .sidenav a {
        padding: 25px 25px 0;
        text-decoration: none;
        font-size: 25px;
        color: #c7b2b2;
        display: block;
        transition: 0.3s;
        float: left;
    }

    .side {
        flex: 30%;
        background-color: #f1f1f1;
        padding: 20px;
        height: 350px;
    }
    .sidenav a:hover {
        color: #b93838;
    }

    .sidenav .closebtn {
        position: absolute;
        top: 0;
        right: 25px;
        font-size: 36px;
        margin-left: 50px;
    }
</style>
<body>
<%

    List<Category> categories = (List<Category>) session.getAttribute("categories");
    Item item = (Item) request.getAttribute("item");

%>
<a href="${pageContext.request.contextPath}/main" style="text-decoration: none">
    <div class="header">
        <h1>ITEM.AM</h1>
    </div>
</a>

<div class="navbar">
    <span class="right" style="font-size:22px;cursor:pointer;" onclick="openNav()">&#9776;</span>
    <a href="${pageContext.request.contextPath}/main" style="margin-right: 570px; font-size: 16px">Գլխավոր</a>
    <%for (Category category : categories) {%>
    <a href="${pageContext.request.contextPath}/main?catId=<%=category.getId()%>"><%=category.getName()%>
    </a>
    <%}%>
    <div id="mySidenav" class="sidenav">
        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
        <a href="${pageContext.request.contextPath}/currentUserAds" class="right">Իմ հայտարարությունները</a>
        <a href="${pageContext.request.contextPath}/createAd.jsp" class="right">Ավելացնել հայտարարություն</a>
        <a href="${pageContext.request.contextPath}/logout" class="right">Ելք</a>
    </div>
    <br>
</div>
<script>
    function openNav() {
        document.getElementById("mySidenav").style.width = "250px";
        document.getElementById("main").style.marginLeft = "250px";
    }

    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
        document.getElementById("main").style.marginLeft = "0";
    }
</script>
<div class="row" style="margin-top: 200px">
    <div class="side" style="width: 800px; height: 800px; margin-top: 400px">
        <h2 style="font-size: 18px; text-align: center"><%=item.getTitle()%>
        </h2>
        <div class="fakeImg" style="height:150px; text-align: center">
            <%
                List<Picture> pictures = item.getPictures();
            if(pictures != null){
                for (Picture pic : pictures) {%>
            <img src="/image?path=<%=pic.getPicUrl()%>" width="350px">
            <%}} else {%>
            <img src="/img/img.jpg" width="150px">
            <%}%>
        </div>
        <h5>Գինը՝ <%=item.getPrice() + " " + item.getCurrency()%>
        </h5><br>

        <hr class="solid" style="border-top: 1px solid #bbb; margin-top: 70px">
        <h5 style="color: #9f7b7b">Էլ․ հասցե՝ <%=item.getUser().getEmail()%>
        </h5>
        <h3>Նկարագրություն</h3>
        <p><%=item.getDescription()%>
        </p>
    </div>
</div>
</body>
</html>
