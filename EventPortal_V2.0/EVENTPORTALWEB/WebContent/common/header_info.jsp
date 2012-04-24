<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<style type="text/css" >
.ea-header-info-col1{

width:350px;
background: white;

}

.ea-header-info-col2{

width:300px;
background: white;

}
.ea-header-info-col3{

width:230px;
background: white;

}
.ea-header-info-col4{

width:120px;
background: white;

}
.ea-header-info-row{
height:30px;
background: white;

}

</style>

<h:panelGrid columns="1" rowClasses="ea-header-info-row">
<h:panelGroup>
<h:panelGrid columns="4" columnClasses="ea-header-info-col1,ea-header-info-col2,ea-header-info-col3,ea-header-info-col4">
<h:panelGroup>
<jsp:include page="bread_crumb.jsp"/>
</h:panelGroup>

<h:panelGroup>
<jsp:include page="speaker.jsp" />
</h:panelGroup>

<h:panelGroup>
<jsp:include page="likes.jsp" />
</h:panelGroup>

<h:panelGroup>
<jsp:include page="loading_status.jsp" />
<jsp:include page="status_message.jsp" />
</h:panelGroup>
</h:panelGrid>

</h:panelGroup>

</h:panelGrid>

