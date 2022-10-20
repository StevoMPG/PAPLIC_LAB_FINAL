<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<nav class="extraInfoDiv nav-listados nav-info nav flex-column">
	 
	 <h1 class="fs-5" style="color: grey;">Información</h1>
	 <a class="nav-link" style="color: white;" href="<%=request.getContextPath()%>/search?usuarios=yes">Usuarios</a>
	 <a class="nav-link" style="color: white;" href="<%=request.getContextPath()%>/search?actividades=yes">Actividades</a>
	 <a class="nav-link" style="color: white;" href="<%=request.getContextPath()%>/search?clases=yes">Clases</a>
	 <a class="nav-link" style="color: white;" href="<%=request.getContextPath()%>/search?cuponeras=yes">Cuponeras</a>
</nav>
<%  String x = "";
	String q = (String) request.getParameter("e");
	if (q!=null)
		x=q;
%>
<div id="contenedor3" class="contenedor3-container position-relative bottom-0 end-0 p-3" data-tcode="<%=x%>">

</div>
