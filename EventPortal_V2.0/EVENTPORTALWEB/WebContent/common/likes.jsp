<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<script type="text/javascript">
function setSessionLikeId(id){

	document.getElementById("headerForm:likeId").value = id;
	
}
function setEventLikeId(id){

	document.getElementById("headerForm:likeId").value = id;
	
}
</script>

<style type="text/css" >

.ea-like-outer-col1{
width:23px;

}
.ea-like-outer-col1{



}
</style>

<a4j:region id="likeRegion">

<h:inputHidden id="likeId" value="#{attendeeBean.hiddenLikeId}"></h:inputHidden>
<h:panelGrid columns="2" columnClasses="ea-like-outer-col1,ea-like-outer-col2">
<h:panelGroup>
<a4j:status id="likeStatus" for="likeRegion">
                <f:facet name="start">
                    <h:graphicImage  value="/images/loading_refresh.gif" />
                </f:facet>
 </a4j:status>
</h:panelGroup>
<h:panelGroup>
<h:panelGrid columns="1">
<!-- Session Like -->
<h:panelGroup id="ea-session-like-id" rendered="#{applicationBean.like == 'Session'}">
<h:panelGrid columns="2">
<h:panelGroup>
<a4j:commandButton status="likeStatus" style="width:50px;height:20px;" reRender="ea-session-like-id" image="/images/like_box.jpg" onclick="setSessionLikeId('#{sessionBean.sessionId}')"  actionListener="#{attendeeBean.sessionLikeListener}"></a4j:commandButton>
</h:panelGroup>
<h:panelGroup id="sess_session_Like_Count" >
<h:outputLabel styleClass="ea-content-class" value="#{applicationBean.sessionLikeCount} people likes this"></h:outputLabel>
</h:panelGroup>
</h:panelGrid>
</h:panelGroup>
<!-- Event Like -->
<h:panelGroup id="ea-event-like-id" rendered="#{applicationBean.like == 'Event'}">
<h:panelGrid columns="2">
<h:panelGroup>
<a4j:commandButton style="width:50px;height:20px;" reRender="ea-event-like-id" image="/images/like_box.jpg" onclick="setEventLikeId('#{eventBean.eventId}')"  actionListener="#{attendeeBean.eventLikeListener}"></a4j:commandButton>
</h:panelGroup>
<h:panelGroup>
<h:outputLabel styleClass="ea-content-class" value="#{applicationBean.eventLikeCount} people likes this"></h:outputLabel>
</h:panelGroup>
</h:panelGrid>
</h:panelGroup>
<!-- Event Like -->
<h:panelGroup id="ea-total-event-like-id" rendered="#{applicationBean.like == 'EventSum'}">
<h:panelGrid columns="2">
<!--<h:panelGroup>
<!--<a4j:commandButton reRender="ea-total-event-like-id" image="/images/like_box.jpg" actionListener="#{attendeeBean.eventSumLikeListener}"></a4j:commandButton>
</h:panelGroup>-->
<h:panelGroup>
<h:outputLabel styleClass="ea-content-class" value="#{applicationBean.totalEventLikeCount} people likes this"></h:outputLabel>
</h:panelGroup>
</h:panelGrid>
</h:panelGroup>
</h:panelGrid>
</h:panelGroup>
</h:panelGrid>
</a4j:region>