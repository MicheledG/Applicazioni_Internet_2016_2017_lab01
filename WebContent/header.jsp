<%@page import="ai_esercitazione_01.controllers.LoginServlet" %>
<%@page import="ai_esercitazione_01.controllers.LogoutServlet" %>
<%@page import="ai_esercitazione_01.model.CartService" %>
<%@page import="ai_esercitazione_01.model.User" %>
<%@page import="ai_esercitazione_01.model.Item" %>
<%

    CartService cartService = (CartService) request.getSession().getAttribute(CartService.ATTRIBUTE_NAME);
    int itemCount = 0;
    if (cartService == null) {
        //error -> should not be here
    } else {
    	// updated
    	for (Item item : cartService.getItems()) {
    		itemCount += item.getQuantity();
    	}
    }

    //check if there is a user logged-in
    String method;
    String urlLoginLogout;
    String messageLoginLogout;
    User user = (User) request.getSession().getAttribute(LoginServlet.SESSION_ATTRIBUTE_USER);
    if (user == null) {
        //TODO
        method = "get";
        urlLoginLogout = "login.jsp";
        messageLoginLogout = "login";
    } else {
        method = "post";
        urlLoginLogout = LogoutServlet.URL;
        messageLoginLogout = "logout";
    }

%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>TGT Transports</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<nav class="navbar navbar-default navbar-inverse navbar-static-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}index.jsp">TGT Transports</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <ul class="nav navbar-nav navbar-right">
            <li><a href="${pageContext.request.contextPath}/index.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
            <li><a href="${pageContext.request.contextPath}/cart2.jsp"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> Cart
                (<%=itemCount %>)</a></li>
            <li>
                <form action="${pageContext.request.contextPath}/<%=urlLoginLogout %>" method="<%=method%>" style="padding-top: 8px;">
                    <button type="submit" class="btn btn-link">
                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                        <%=messageLoginLogout%>
                    </button>
                </form>
            </li>
        </ul>
    </div><!-- /.container-fluid -->
</nav>

<div class="container" role="main">