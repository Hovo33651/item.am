<%@ page import="itemAm.model.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="itemAm.model.Category" %>
<%@ page import="itemAm.model.Picture" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
        padding-bottom: 20px;
        padding-top: 40px;
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
        color: #3f2c2c;
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


    /* Fake image, just for this example */
    .fakeImg {
        width: 100%;
    }
</style>


<body>
<a href="${pageContext.request.contextPath}/main" style="text-decoration: none">
    <div class="header">
        <h1>ITEM.AM</h1>
    </div>
</a>
<% List<Category> categories = (List<Category>) session.getAttribute("categories");%>
<div class="navbar">
    <a href="${pageContext.request.contextPath}/main">Գլխավոր</a>
    <%for (Category category : categories) {%>
    <a href="${pageContext.request.contextPath}/main?catId=<%=category.getId()%>"><%=category.getName()%>
    </a>
    <%}%>
    <a href="${pageContext.request.contextPath}/login.jsp" class="right">Մուտք</a>
    <a href="${pageContext.request.contextPath}/register.jsp" class="right">Գրանցվել</a>
</div>

<% List<Item> items = (List<Item>) request.getAttribute("items");
    if (items != null) {
        for (Item item : items) {%>
<div>
<a href="${pageContext.request.contextPath}/item?itemId=<%=item.getId()%>" style="text-decoration: none; font-family: Helvetica,serif; color: #333333">
    <div class="row" style="float:left; width: 270px; margin: 16px">
        <div class="side">
            <h2 style="font-size: 18px; text-align: center"><%=item.getTitle()%>
            </h2>
            <div class="fakeImg" style="height:150px; text-align: center">
                <%
                    List<Picture> pictures = item.getPictures();
                    if(pictures != null){
                        for (Picture pic : pictures) {%>
                <img src="${pageContext.request.contextPath}/image?path=<%=pic.getPicUrl()%>" width="220px" alt="<%=item.getTitle()%>">
                <%}break;} else {%>
                <img src="${pageContext.request.contextPath}/img/img.jpg" width="150px" alt="no image">
                <%}%>
            </div>
            <h5>Գինը՝ <%=item.getPrice() + " " + item.getCurrency()%>
            </h5>
            <hr class="solid" style="border-top: 1px solid #bbb;">
            <h5 style="color: #9f7b7b">Էլ․ հասցե՝ <%=item.getUser().getEmail()%>
            </h5>
        </div>
    </div>
</a>
<%}%>
<%}%>
</div>
</body>
</html>
