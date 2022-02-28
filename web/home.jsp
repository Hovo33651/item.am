<%@ page import="itemAm.model.User" %>
<%@ page import="itemAm.model.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="itemAm.manager.CategoryManager" %>
<%@ page import="itemAm.model.Category" %>
<%@ page import="itemAm.model.Picture" %><%--
  Created by IntelliJ IDEA.
  User: Hovhanes Gevorgyan
  Date: 23.02.2022
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Item.am</title>
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
        text-align: center;
    }

    .navbar span {
        float: left;
        margin-left: 20px;
        margin-right: 15px;
        margin-top: 7px;
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
        font-size: 20px;
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
    }

    /* Create two unequal columns that sits next to each other */
    /* Sidebar/left column */
    .side {
        flex: 30%;
        background-color: #f1f1f1;
        padding: 20px;
        height: 350px;
    }


    /* Fake image, just for this example */
    .fakeImg {
        width: 100%;
    }

    body {
        font-family: "Lato", sans-serif;
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

    #main {
        transition: margin-left .5s;
        padding: 16px;
        float: right;
    }

    @media screen and (max-height: 450px) {
        .sidenav {
            padding-top: 15px;
        }

        .sidenav a {
            font-size: 15px;
            color: #f30707;

        }
    }
</style>
<body>
<%
    HttpSession currentSession = request.getSession();
    User user = (User) currentSession.getAttribute("user");
    List<Category> categories = (List<Category>) session.getAttribute("categories");
%>
<a href="${pageContext.request.contextPath}/main" style="text-decoration: none">
    <div class="header">
        <h1>ITEM.AM</h1>
        <h3>Բարև <%=user.getName()%>
        </h3>
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

<% List<Item> items = (List<Item>) request.getAttribute("items");
    if (items != null) {
        for (Item item : items) {%>
<div>
    <a href="/item?itemId=<%=item.getId()%>" style="text-decoration: none; font-family: Helvetica; color: #2a1f1f">
        <div class="row" style="float:left; width: 270px; margin: 10px 16px 50px;">
            <div class="side">
                <h2 style="font-size: 18px; text-align: center"><%=item.getTitle()%>
                </h2>
                <div class="fakeImg" style="height:150px; text-align: center">
                    <%List<Picture> pictures = item.getPictures();
                        if (pictures != null){ ;
                        for (Picture pic : pictures) {
                    %>
                    <img src="/image?path=<%=pic.getPicUrl()%>" width="220px">
                    <%}break;
                        } else{%>
                    <img src="/img/img.jpg" width="150px">
                    <%}%>
                </div>
                <div>
                    <h5>Գինը՝ <%=item.getPrice() + " " + item.getCurrency()%>
                    </h5>
                    <hr class="solid" style="border-top: 1px solid #bbb;">
                    <h5 style="color: #e19d9d">Էլ․ հասցե՝ <%=item.getUser().getEmail()%>
                    </h5>
                </div>
                <br>
                <%if (item.getUser().getId() == user.getId()) {%>
                <div style="text-align: right">
                    <a href="/deleteItem?itemId=<%=item.getId()%>"
                       style="text-decoration: none; color: #1ea9de; font-size: 15px">Չեղարկել</a>
                </div>
                <%}%>
            </div>
        </div>
    </a>
    <%}%>
    <%}%>
</div>
</body>
</html>
