<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="servlets.Login"%>
<%@ page import="datatypes.DtUsuarioExtra"%>
<%@ page import="datatypes.DtProfesorExtra"%>
<%@ page import="java.util.Set"%>
<%! @SuppressWarnings("unchecked") %>
<% 
DtUsuarioExtra u = (DtUsuarioExtra)request.getSession().getAttribute("loggedUser");
if (u!=null){
	%>
	<nav style="margin-bottom: 3em;" class="extraInfoDiv ins-cat-section nav flex-column">
	<h1 class="fs-5">Acciones</h1>
       <button type="button" id="btn-myUser" class="btn btn-link" onclick="location.href='<%=request.getContextPath()%>/usuarios?nickname=<%=u.getNickname()%>'">
        	Ir a mi perfil
    	</button>
	<% if (u instanceof DtProfesorExtra){ %>
	   <button type="button" id="btn-altaActDep" class="btn btn-link" data-bs-toggle="modal" data-bs-target="#altaActModal">
            Alta Actividad Deportiva
      </button>
      <% } %>
      
	  </nav>
<%}%>

<nav class="extraInfoDiv ins-cat-section nav flex-column">
  <h1 class="fs-5" style="color: grey">Instituciones</h1>
  <%
  Set<String> s = (Set<String>)request.getAttribute("stdInstituciones");
  for (String x: s){ %>
  	<a class="nav-link" style="color: white;" href="<%=request.getContextPath()%>/search?actividades=yes&cuponeras=yes&fltrI1=<%=x%>"><%=x%></a>
  <%} %>
</nav>
<nav class="extraInfoDiv ins-cat-section nav flex-column mt-5">
  <h1 class="fs-5" style="color: grey">Categorías</h1>
  <%
  Set<String> s2 = (Set<String>)request.getAttribute("stdCategorias");
  for (String x: s2){ %>
  	<a class="nav-link" style="color: white" href="<%=request.getContextPath()%>/search?actividades=yes&cuponeras=yes&fltrC1=<%=x%>"><%=x%></a>
  <%} %>
</nav>