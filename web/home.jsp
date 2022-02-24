<%@ page import="itemAm.model.User" %>
<%@ page import="itemAm.model.Item" %>
<%@ page import="java.util.List" %><%--
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
        padding: 80px;
        text-align: center;
        background: #1abc9c;
        color: white;
    }

    /* Increase the font size of the h1 element */
    .header h1 {
        font-size: 40px;
    }

    /* Style the top navigation bar */
    .navbar {
        overflow: hidden;
        background-color: #333;
    }

    /* Style the navigation bar links */
    .navbar a {
        float: left;
        display: block;
        color: white;
        text-align: center;
        padding: 14px 20px;
        text-decoration: none;
    }

    /* Right-aligned link */
    .navbar a.right {
        float: right;
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
    }

    /* Main column */
    .main {
        flex: 70%;
        background-color: white;
        padding: 20px;
    }

    /* Fake image, just for this example */
    .fakeImg {
        background-color: #aaa;
        width: 100%;
        padding: 20px;
    }
</style>
<body>
<%
    HttpSession currentSession = request.getSession();
    User user = (User) currentSession.getAttribute("user");
%>
<div class="header">
    <h1>ITEM.AM</h1>
    <h3>Բարև <%=user.getName()%> </h3>
</div>



<div class="navbar">
    <a href="${pageContext.request.contextPath}/">Գլխավոր</a>
    <a href="${pageContext.request.contextPath}/?catId=car">Ավտոմեքենաներ</a>
    <a href="${pageContext.request.contextPath}/?catId=house">Բնակարաններ/Տներ</a>
    <a href="${pageContext.request.contextPath}/?catId=commercial">Կոմերցիոն հայտարարություններ</a>
    <a href="${pageContext.request.contextPath}/?catId=furniture">Կահույք</a>
    <a href="${pageContext.request.contextPath}/logout" class="right">Ելք</a>
    <a href="${pageContext.request.contextPath}/currentUserAds?userId=<%=user.getId()%>" class="right">Իմ հայտարարությունները</a>
    <a href="${pageContext.request.contextPath}/createAd.jsp" class="right">Ավելացնել հայտարարություն</a>
</div>

<% List<Item> items = (List<Item>) request.getAttribute("items");
    if (items != null) {
        for (Item item : items) {%>
<div class="row">
    <div class="side">
        <h2><%=item.getTitle()%></h2>
        <h5><%=item.getDescription()%></h5>
        <h5><%=item.getPrice()%></h5>
        <h5><%=item.getUser().getEmail()%></h5>
        <%if(item.getPicUrl()!= null){%>
        <div class="fakeImg" style="height:200px;">
            <img src="${pageContext.request.contextPath}/image?path=<%=item.getPicUrl()%>" width="220px">
        </div>
        <%}%>
    </div>
</div>
<%}%>
<%}%>
</body>
</html>
