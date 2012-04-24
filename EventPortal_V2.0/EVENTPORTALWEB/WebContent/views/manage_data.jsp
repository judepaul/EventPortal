<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>

<script>


</script>

<style type="text/css">

.ea-event-info-row-class{
 
 	height: 80px;
 
}
.ea-agenda-row-class{
   vertical-align: top;
	height: 320px;

}
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
.tooltip {
   border-width:0px;
   padding:0px;
   border-style:none;    
   -moz-border-radius: 10px;    /* Rounded border mozilla browsers */
   -webkit-border-radius: 10px; /* Rounded border webkit browsers */
   border-radius: 10px; 
}

.breadcrumb-active {
	font-size: 13px;
	line-height:18px;
	float:left;
	text-align:left;
	margin:0;
	color:#c14b59;
	font-weight: bold;
}
.breadcrumb-active a:link, a:visited, a:active {
	color:#c14b59;
	text-decoration:none;
}
.breadcrumb-active a:hover {
	color:#000000;
	text-decoration:none;
}
.breadcrumb-normal{

	font-size: 13px;
	line-height:18px;
	float:left;
	text-align:left;
	margin:0;
	color:#000000;
	font-weight: bold;
	
}
.default-white-panel{
	border: 0;
	width: 100%;
	height: 30px;
	/*background-color: green;*/
	padding: 0;
	margin:0;
}
.ea-breadcrumb-col{
border: 1px;
/*background-color: #014040;*/
}


</style>

<a4j:form id="manageDataForm">
<h:panelGrid columns="1" rowClasses="ea-header-info-row">
<h:panelGroup>
<h:panelGrid columns="4" columnClasses="ea-header-info-col1,ea-header-info-col2,ea-header-info-col3,ea-header-info-col4">
<h:panelGroup>
<h:panelGrid columns="3">
<h:panelGroup styleClass="breadcrumb-active">
<a4j:commandLink value="Events" action="#{eventBean.populateEventsPage}"></a4j:commandLink>
</h:panelGroup>
<h:panelGroup>
<h:graphicImage value="/images/breadcrumb_arrow.jpg" ></h:graphicImage>
</h:panelGroup>
<h:panelGroup>
<h:outputText styleClass="ea-content-bold-class" value="Manage Data">
</h:outputText>
</h:panelGroup>
</h:panelGrid>
</h:panelGroup>

<h:panelGroup>

</h:panelGroup>

<h:panelGroup>

</h:panelGroup>

<h:panelGroup>
<jsp:include page="../common/loading_status.jsp" />
<jsp:include page="../common/status_message.jsp" />
</h:panelGroup>
</h:panelGrid>

</h:panelGroup>

</h:panelGrid>

<rich:tabPanel id="manageDataTab" switchType="ajax" styleClass="profile-tab-panel">

<rich:tab name="manageEvents" label="Manage Event"  actionListener="#{maintenanceBean.manageEventsListener}" >
<rich:panel styleClass="tab-container">

<jsp:include page="manage_data_event.jsp"></jsp:include>

</rich:panel>
</rich:tab>

<rich:tab name="manageSessions" label="Manage Session"  actionListener="#{maintenanceBean.manageSessionsListener}" >
<rich:panel styleClass="tab-container">

<jsp:include page="manage_data_session.jsp"></jsp:include>

</rich:panel>
</rich:tab>

<rich:tab name="manageUsers" label="Manage Attendee"  actionListener="#{maintenanceBean.manageAttendeeListener}" >
<rich:panel styleClass="tab-container">

<jsp:include page="manage_data_user.jsp"></jsp:include>

</rich:panel>
</rich:tab>
</rich:tabPanel>
	
</a4j:form>