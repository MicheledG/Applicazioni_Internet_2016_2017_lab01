<%@page import="ai_esercitazione_01.model.CartService"%>
<%
	CartService cartService = (CartService) request.getSession().getAttribute(CartService.ATTRIBUTE_NAME);
	int itemCount = 0;
	if(cartService != null){
		itemCount = cartService.getItemCount();	
	}

%>

<nav class="navbar navbar-default navbar-inverse navbar-static-top">
<div class="container-fluid">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
    		<span class="sr-only">Toggle navigation</span>
	    	<span class="icon-bar"></span>
	    	<span class="icon-bar"></span>
	    	<span class="icon-bar"></span>
	  	</button>
		<a class="navbar-brand" href="#">TGT Transports</a>
	</div>

	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav navbar-right">
	  		<li><a href="index2.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
	  		<li><a href="cart.jsp"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> Cart (<%=itemCount %>)</a></li>
			<li><a href="login.jsp"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Login</a></li>
			<!-- <li><a href="#">Logout</a></li> -->
	  	</ul>
	</div><!-- /.navbar-collapse -->
</div><!-- /.container-fluid -->
</nav>
    