<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<style type="text/css">
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


<!-- Profile page -->
<h:panelGroup rendered="#{applicationBean.currentPage =='PROFILE'}">
<h:panelGrid columns="3">
<h:panelGroup styleClass="breadcrumb-active">
<a4j:commandLink value="Events" action="#{eventBean.populateEventsPage}"></a4j:commandLink>
</h:panelGroup>
<h:panelGroup>
<h:graphicImage value="/images/breadcrumb_arrow.jpg" ></h:graphicImage>
</h:panelGroup>
<h:panelGroup styleClass="ea-content-bold-class">
Profile
</h:panelGroup>
</h:panelGrid>
</h:panelGroup>

<!-- Event List page -->
<h:panelGroup rendered="#{applicationBean.currentPage =='EVENTLIST'}">
<h:panelGrid columns="3">
<h:panelGroup styleClass="breadcrumb-active">
<a4j:commandLink value="Events" action="#{eventBean.populateEventsPage}"></a4j:commandLink>
</h:panelGroup>
</h:panelGrid>
</h:panelGroup>

<!-- Event Home page -->
<h:panelGroup rendered="#{applicationBean.currentPage =='EVENTHOME'}">
<h:panelGrid columns="3">
<h:panelGroup styleClass="breadcrumb-active">
<a4j:commandLink value="Events" action="#{eventBean.populateEventsPage}"></a4j:commandLink>
</h:panelGroup>
<h:panelGroup>
<h:graphicImage value="/images/breadcrumb_arrow.jpg" ></h:graphicImage>
</h:panelGroup>
<h:panelGroup>
<h:outputText id="eventInfoTip1" styleClass="ea-content-bold-class" value="#{applicationBean.currentEvent}">
 <rich:toolTip styleClass="tooltip"  id="eventInfoToolTip1" for="eventInfoTip1" layout="inline"  value="#{applicationBean.eventTooptip}"/>

</h:outputText>
</h:panelGroup>
</h:panelGrid>
</h:panelGroup>

<!-- Session Summary page -->
<h:panelGroup rendered="#{applicationBean.currentPage =='SESSIONSUMMARY'}">
<h:panelGrid columns="5">
<h:panelGroup styleClass="breadcrumb-active">
<a4j:commandLink value="Events" action="#{eventBean.populateEventsPage}"></a4j:commandLink>
</h:panelGroup>
<h:panelGroup>
<h:graphicImage value="/images/breadcrumb_arrow.jpg" ></h:graphicImage>
</h:panelGroup>
<h:panelGroup styleClass="breadcrumb-active">
<a4j:commandLink id="eventInfoTip2" value="#{applicationBean.currentEvent}" action="#{eventBean.populateAgendaPage}">
<rich:toolTip styleClass="tooltip"  id="eventInfoToolTip2" for="eventInfoTip2" layout="inline"  value="#{applicationBean.eventTooptip}"/>

</a4j:commandLink>
</h:panelGroup>
<h:panelGroup>
<h:graphicImage value="/images/breadcrumb_arrow.jpg" ></h:graphicImage>
</h:panelGroup>
<h:panelGroup>
<h:outputText  id="sessionViewInfoTip" styleClass="ea-content-bold-class" value="#{applicationBean.currentSession}">
				   <rich:toolTip styleClass="tooltip"  id="sessionViewToolTip" for="sessionViewInfoTip" layout="inline"  value="#{applicationBean.sessionTooptip}"/>
				
</h:outputText>
</h:panelGroup>

</h:panelGrid>
</h:panelGroup>


<!-- Session Detail page -->
<h:panelGroup rendered="#{applicationBean.currentPage =='SESSIONDETAIL'}">
<h:panelGrid columns="5">
<h:panelGroup styleClass="breadcrumb-active">
<a4j:commandLink value="Events" reRender="leave-confirm-modal-panel-id"   actionListener="#{sessionBean.leaveLiveSessionListener}" oncomplete="#{rich:component('leave-confirm-modal-panel-id')}.show()">
<a4j:actionparam name="currentLinkClicked" value="EVENTS" assignTo="#{applicationBean.linkClicked}"/>
</a4j:commandLink>
</h:panelGroup>
<h:panelGroup>
<h:graphicImage value="/images/breadcrumb_arrow.jpg" ></h:graphicImage>
</h:panelGroup>
<h:panelGroup styleClass="breadcrumb-active">

<a4j:commandLink id="eventInfoTip" value="#{applicationBean.currentEvent}" reRender="leave-confirm-modal-panel-id"   actionListener="#{sessionBean.leaveLiveSessionListener}" oncomplete="#{rich:component('leave-confirm-modal-panel-id')}.show()">
 <rich:toolTip styleClass="tooltip"  id="eventInfoToolTip" for="eventInfoTip" layout="inline"  value="#{applicationBean.eventTooptip}"/>

<a4j:actionparam name="currentLinkClicked" value="EVENT" assignTo="#{applicationBean.linkClicked}"/>
</a4j:commandLink>

</h:panelGroup>
<h:panelGroup>
<h:graphicImage value="/images/breadcrumb_arrow.jpg" ></h:graphicImage>
</h:panelGroup>
<h:panelGroup>
<h:outputText id="sessionInfoTip" styleClass="ea-content-bold-class" value="#{applicationBean.currentSession}">
   <rich:toolTip styleClass="tooltip"  id="sessionInfoToolTip" for="sessionInfoTip" layout="inline"  value="#{applicationBean.sessionTooptip}"/>

</h:outputText>
</h:panelGroup>

</h:panelGrid>
</h:panelGroup>


