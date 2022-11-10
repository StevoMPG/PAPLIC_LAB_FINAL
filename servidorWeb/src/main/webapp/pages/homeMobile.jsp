<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="datatypes.DtUsuarioExtra"%>
<%@ page import="datatypes.DtSocioExtra"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/home.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/homeMobile.css">
</head>
<body>
	<jsp:include page="/template/headerMobile.jsp"/>
	<%DtUsuarioExtra loggedUser = (DtUsuarioExtra) request.getSession().getAttribute("loggedUser"); 
	  if ((loggedUser != null) && (loggedUser instanceof DtSocioExtra)) {
		String f = "default.png";
	  	if (loggedUser.getImagen()!=null)
	  		f = new String(loggedUser.getImagen(), "UTF-8");
	  	%>
	<div id="section-socio" class="row mt-4 mb-3">
		  <img id="img-perfil" onerror="this.onerror=null; this.src='<%=request.getContextPath()%>/assets/images/misc/loading.gif'" alt="<%=loggedUser.getNickname()%>" id="img-perfil" src="<%=request.getContextPath()%>/api/content?c=usu&id=<%=loggedUser.getNickname()%>">
	    <p class="bienvenida-socio mt-3">Bienvenido <b><%=loggedUser.getNickname()%></b> a la plataforma movil de Entrenamos.uy</p>
	</div>
	
	<div id="section-titular" class="row" style="background-color: rgba(0, 0, 0, 0.79);" >
	    <h1>En esta plataforma podras encontrar</h1>
	</div>
	
	<div id="section-actDeps" class="row" style="background-color: rgba(0, 0, 0, 0.79);">
	    <div class="col-5 ms-1 ps-3">
	        <img src="<%=request.getContextPath()%>/assets/images/misc/actDepInfo.png" alt="">
	    </div>
	    <div id="texto-actDeps" class="col-6" >
	        <p>Información de las actividades deportivas</p>
	    </div>
	</div>
	
	  <div id="section-clases" class="row pb-4" style="background-color: rgba(0, 0, 0, 0.79);">
	      <div id="texto-clases" class="col-6 ms-3 ps-4">
	          <p>Informacion de las clases</p>
	      </div>
	      <div class="col-5 ms-3 pe-5">
	          <img src="<%=request.getContextPath()%>/assets/images/misc/claseInfo.png" alt="">
	      </div>
	  </div>
	
	  <div id="section-institucion" class="row pb-4" style="background-color: rgba(0, 0, 0, 0.79);">
	    <div class="col-5 ms-2 pe-5">
	        <img src="<%=request.getContextPath()%>/assets/images/misc/institucionInfo.png" alt="">
	    </div>
	    <div id="texto-institucion" class="col-6 ms-3 ps-4">
	      <p>Las actividades deportivas de tu institución favorita</p>
	  </div>
	</div>
	
	<div id="section-sponsors" class="row pt-5 pb-5" style="background-color: rgba(255, 255, 255, 0.79);">
	    <div class="row text-center">
	        <h1>Sponsors</h1>
	    </div>
	    <div class="row mt-4">
	        <div class="col-6 text-center">
	            <img src="<%=request.getContextPath()%>/assets/images/misc/logoUTEC.jpeg" alt="">
	        </div>
	        <div class="col-6 text-center">
	            <img src="<%=request.getContextPath()%>/assets/images/misc/logoDorado.png" alt="">
	        </div>
	    </div>
	    <div class="row mt-4">
	        <div class="col-6 text-center">
	            <img src="<%=request.getContextPath()%>/assets/images/misc/logoUruguay.png" alt="">
	        </div>
	        <div class="col-6 text-center">
	            <img src="<%=request.getContextPath()%>/assets/images/misc/logoPuma.png" alt="">
	        </div>
	    </div>
	</div>
	<jsp:include page="/template/footerMobile.jsp"/>
	<% } %>
</body>
</html>
