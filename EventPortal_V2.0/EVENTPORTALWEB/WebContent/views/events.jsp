<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="<%=session.getAttribute("CURRENTURL")%>/scripts/events_script.js"></script>

<style type="text/css">
.event-home-event-list{

  width:530px;
  height:420px;
  align:left;
  padding:0;
  vertical-align:top;
  background-color: white;
  
}
.event-home-tweet-list{
  width:420px;
  height:420px;
  align:right;
  padding:0;
  vertical-align:top;
  margin: 0 auto;
  background-color: white;	
}
.event-home-seperator{
width:30px;

}
.ea-eventlist-col{
vertical-align: top;
background: white;
width: 500px;
}
.ea-event-list-row1{
width: 100%;
}

.ea-event-list-row2{


}
.ea-event-list-row3{


}
.event-home-main-panel{
  width:980px;
  height:440px;
  align:left;
  padding:0px;
  vertical-align:middle;
  margin: 0 auto;
  border: 1px;    
  background-color: white;
 
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
<a4j:form id="eventsForm" style="margin:0px;padding:0px;">
<h:panelGrid columns="1" rowClasses="ea-header-info-row">
<h:panelGroup>
<h:panelGrid columns="4" columnClasses="ea-header-info-col1,ea-header-info-col2,ea-header-info-col3,ea-header-info-col4">
<h:panelGroup>
<a4j:commandLink value="Events" action="#{eventBean.populateEventsPage}"></a4j:commandLink>
</h:panelGroup>

<h:panelGroup>

</h:panelGroup>

<h:panelGroup>
<h:outputLabel styleClass="ea-content-class" value="#{applicationBean.totalEventLikeCount} people likes this"></h:outputLabel>
</h:panelGroup>

<h:panelGroup>
<jsp:include page="../common/loading_status.jsp" />
<jsp:include page="../common/status_message.jsp" />
</h:panelGroup>
</h:panelGrid>

</h:panelGroup>

</h:panelGrid>
<!-- hidden field values -->
		<h:inputHidden value="#{eventBean.eventId}" id="eventId" />
		<h:inputHidden value="#{eventBean.eventCategory}" id="eventCategoryId" />
	<h:panelGrid columnClasses="event-home-event-list,event-home-seperator,event-home-tweet-list" columns="3">
		<h:panelGroup>
		<h:panelGrid columns="1" columnClasses="ea-eventlist-col">
		<h:panelGroup>
		<jsp:include page="events_search.jsp" />
		</h:panelGroup>		
		<h:panelGroup>
		<jsp:include page="events_catalog.jsp" />
		</h:panelGroup>
		</h:panelGrid>
		</h:panelGroup>
		<h:panelGroup>		
			&nbsp;<a4j:commandButton id="submitEventIdforPopup" value="" reRender="modalEventDetailsPanel" style="display:none" actionListener="#{eventBean.eventDetails}" oncomplete="#{rich:component('modalEventDetailsPanel')}.show()" />
			<a4j:commandButton id="submitEventIdforAgenda" value="" style="display:none" action="#{eventBean.populateAgendaPage}" />
			<a4j:commandButton id="submitEventIdforJoin" value="" style="display:none" action="#{eventBean.joinEvent}" />
			<a4j:commandButton id="hiddenActionForPopulateEvent" value="" style="display:none" action="#{eventBean.populateEventPageAction}" />
			<a4j:commandButton id="submitEventIdforUnJoin" value="" reRender="eventList" style="display:none" actionListener="#{eventBean.unJoinEventListener}" />
			<a4j:commandButton id="loadTweets" value="" reRender="twitterSearchList,ea-all-event-tweet-panel" style="display:none" actionListener="#{socialMediaBean.allEventTweets}" />
		</h:panelGroup>
		<h:panelGroup>
				<jsp:include page="events_tweet.jsp" />
		</h:panelGroup>		
	</h:panelGrid>
</a4j:form>
<a4j:region>
        <h:form>
            <a4j:poll id="allEventTweetPoll" status="tweetStatus" actionListener="#{socialMediaBean.allEventTweets}" interval="1000" enabled="#{socialMediaBean.twitterPollEnabled}"
                reRender="twitterSearchList,ea-all-event-tweet-panel,allEventTweetPoll" />
        </h:form>
</a4j:region>


