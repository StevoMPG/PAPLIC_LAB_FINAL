<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="datatypes.DtActividadDeportivaExtra"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/home.css">
</head>
<body class="img" style="background-image: url(<%=request.getContextPath()%>/assets/images/misc/bg.jpg);">
	<jsp:include page="/template/header.jsp"/>
	
    
      <div class="row mx-3 mx-md-5">
        <div class="ins-cat col-2" style="margin-left: -18px;margin-right: 18px;">
          <jsp:include page="/template/stdLeftSection.jsp"/>
        </div>
        <div class="actdep-panel col-7 ms-2 ms-sm-1 ms-md-0">
        	<br>
       		<div class="border border-dark row ms-1 mb-4 p-5 text-white bg rounded-3 ">
				    	<h2 class="h2 ">Actividades destacadas:</h2>
			       		<%for (int i = 1; i < 4; i++) {
			       			DtActividadDeportivaExtra datosActividad = (DtActividadDeportivaExtra) request.getAttribute("actividad" + i);
			       			if (datosActividad != null) {%>
			       			
			       			<a href="<%=request.getContextPath()%>/actividades?actividad=<%=datosActividad.getNombre()%>" class="nav-link">
			       			
				        	<div class="actDep row mb-4">
					            <img src="<%=request.getContextPath()%>/api/content?c=act&id=<%=datosActividad.getNombre()%>" class="img-fluid d-inline col-5 col-md-7"></img>
					             <p class="d-inline col-12 col-sm-9 col-md-7 text-start" style="color:white;"><b><%=datosActividad.getNombre()%>.</b> <%=datosActividad.getDescripcion()%>
					            </div>
					            </a>
				    	<%  }
				    	}%>
				     </div>
      </div>
    <div class="col-3 ps-1 ps-sm-2">
  	<jsp:include page="/template/stdRightSection.jsp"/>
  	</div>
  	</div>

	<jsp:include page="/template/footer.jsp"/>
</body>
</html>