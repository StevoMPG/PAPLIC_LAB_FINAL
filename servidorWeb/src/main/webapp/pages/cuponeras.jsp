<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="datatypes.DtCuponera"%>
<%@ page import="datatypes.DtUsuarioExtra"%>
<%@ page import="datatypes.DtFechaHora"%>
<%@ page import="datatypes.DtSocioExtra"%>
<%@ page import="datatypes.DtClasesCuponeras"%>
<%@ page import="servlets.Login"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<%@ page import="servlets.Cuponeras"%>


<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/home.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/cup.css">

    <title>Entrenamos.uy</title>
  </head>
  <body>
    <jsp:include page="/template/header.jsp"/>

    <!--CUERPO DE LA PAGINA WEB-->
    <div class="container-fluid mt-4">
	  <div class="row mx-3 mx-md-5">
        <div class="ins-cat col-2">
          <jsp:include page="/template/stdLeftSection.jsp"/>
        <div class= "row"><br> </div>   
          <jsp:include page="/template/stdRightSection.jsp"/>
        </div>
        
        <div id="user-general" class="col-sm-7">
        <%
          Boolean comprada = false;
          DtUsuarioExtra usrLogged = (DtUsuarioExtra) request.getSession().getAttribute("loggedUser");
          DtCuponera c = (DtCuponera) request.getAttribute("cuponera");
          if (usrLogged != null && usrLogged instanceof DtSocioExtra){
        	  DtSocioExtra usr = (DtSocioExtra)usrLogged;
        	  Set<String> cups = usr.getCuponerasCompradas();
              for (String x: cups) {
      			if (x == c.getNombre()){
              		comprada= true;
              	}  
         	 }
          }
        %>
 	       <div id="user-superior" class = "row" >
 	       		<div class="col-3 py-3">
		    		<div id="mainImgDiv" class="">
			    		<img alt="imagenCuponera" id="cupImg" src="<%=request.getContextPath()%>/api/content?c=cup&id=<%=c.getNombre()%>">
		    		</div>
        		</div>
           		<div class="col-9 py-3" style="background-color: rgba(255, 251, 251, 0.78); border-radius: 10% / 50%; margin-bottom: 15px;padding-left: 39px;">
			      	<div id="user-info" class="row">
               			<p><strong id="user-nickname"> <%= c.getNombre() %> </strong></p>
			      	</div>
			      	<div id="creatorDiv" class="row">
	    	    		<div class="col-auto">
	    	    	<%  Boolean vot = (usrLogged != null) && (usrLogged instanceof DtSocioExtra) && (comprada == false);
	    	    	if (vot == true){ %>
						<button type="button" class="btn" style="border-color: black; color:white; background-color: rgba(0, 0, 0, 0.79);" id="boto" onclick="location.href='<%=request.getContextPath()%>/ComprarCuponera?cuponera=<%=c.getNombre()%>'">
				            	Comprar
				        </button>	
				      <%}%>
				      <%if ((usrLogged != null) && (usrLogged instanceof DtSocioExtra) && (comprada == true)){ %>
				    	<button type="button" disabled class="btn" style="border-color: black; color:white; background-color: rgba(0, 0, 0, 0.79);" id="boto" onclick="location.href='<%=request.getContextPath()%>/ComprarCuponera?cuponera=<%=c.getNombre()%>'">
				            	Comprado
				        </button>  
				      <%}%>
				      	</div>
	    	    	</div>
    	    	</div>
          </div>
          <div id="user-inferior" class= "row card-body mb-3" style="background-color: rgba(255, 251, 251, 0.78); border-radius: 10% / 50%; margin-bottom: 15px;padding-left: 39px;">
      		<div class="row">
       			<div class="col-sm-3">
           			<h6 class="mb-0"><strong>Descripción:</strong></h6>
       			</div>
       			<div class="col-sm-9 text-secondary">
                        <%= c.getDescripcion() %>
       			</div>
      		</div>
           	<div class="row">
              	<div class="col-sm-3">
                  	<h6 class="mb-0"><strong>Período de validez:</strong></h6>
              	</div>
              	<div class="col-sm-9 text-secondary">
                        <%= c.getFechaInicio().toFecha() %> - <%= c.getFechaFin().toFecha() %>
              	</div>
            </div>
            <div class="row">
              	<div class="col-sm-3">
                  	<h6 class="mb-0"><strong>Precio:</strong></h6>
              	</div>
              	<div class="col-sm-9 text-secondary">
                  	$<%= c.getCosto() %>
              	</div>
            </div>
            <div class="row">
              	<div class="col-sm-3">
                  	<h6 class="mb-0"><strong>Descuento aplicado:</strong></h6>
              	</div>
              	<div class="col-sm-9 text-secondary">
                      <%= c.getDescuento() %>%
              	</div>
            </div>
           	<div class="row">
           		<div class="col-sm-3">
               		<h6 class="mb-0"><strong>Fecha de registro:</strong></h6>
           		</div>
           		<div class="col-sm-9 text-secondary">
                        <%= c.getFechaAlta().toFecha() %>
           		</div>
           	</div>
		</div>
       </div>
       <div class="col-sm-3 ps-1 ps-sm-3" >
       	<div id= "user-consultaInscriptos" class="extraInfoDiv row" >
      		<h5 style="color: white;">Actividades Deportivas</h5>
      		<ul id="listaInscriptos" class="py-3" style="background-color: rgba(0, 0, 0, 0); border-radius: 10% / 50%;"> >
      			<% List<DtClasesCuponeras> acts = c.getContenido();
                   for (DtClasesCuponeras x :acts){ %>
   				<li class="container border card-body elementoLista" style="background-color: rgb(255, 255, 255); border-radius: 10% / 50%; margin-bottom: 15px;padding-left: 39px;"> 
	           		<img alt="imagenActividad"  src="<%=request.getContextPath()%>/api/content?c=act&id=<%=x.getNombreActividad()%>" class="vertical-align-middle imagenSeleccionable">
	           		<a class="clase color-blue" href="<%=request.getContextPath()%>/actividades?actividad=<%= x.getNombreActividad()%>"> <%= x.getNombreActividad() %> / <%=x.getCantidadClases() %><%if (x.getCantidadClases()>1){ %> clases<%}else{ %> clase<%} %>  </a>
	        	</li>
      			<%  } %>
      		</ul>
    	</div>
    	<div id= "user-consultaInscriptos" class="extraInfoDiv row">
      		<h5 style="color: white;">Categorías</h5>
      		<ul id="listaInscriptos" class="py-3" style="background-color: rgba(0, 0, 0, 0); border-radius: 10% / 50%;">
      			<% List<String> cats = c.getCategorias();
            		for (String x :cats){  %>
   				<li class="container border card-body elementoLista" style="background-color: rgb(255, 255, 255); border-radius: 10% / 50%; margin-bottom: 15px;padding-left: 39px;">
	           		<a class="clase color-blue" href="<%=request.getContextPath()%>/search?actividades=yes&cuponeras=yes&fltrC1=<%=x%>"> <%=x%>  </a>
	        	</li>
      			<%  } %>
      		</ul>
    	</div>
       </div>
     </div>
  </div>
  <jsp:include page="/template/footer.jsp"/>
</body>
</html>
