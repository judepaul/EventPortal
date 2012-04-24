<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="<%=session.getAttribute("CURRENTURL")%>/scripts/event_script.js"></script>

<style type="text/css">

.white-panel{
	border: 0;
	width:100%;	
	background-color: white;

}
.ea-attendee-list-row-class{
height: 190px;
background: blue;
}

.ea-row-seperator-class{
height: 8px;
}

.ea-tweet-list-row-class{
height: 200px;
background: white;
}

.attendee-list-coulmn{

  width:320px;
  height:420px;
  align:left;
  padding:0;
  vertical-align:top;
}

.agenda-list-column{

  width:650px;
  height:400px;
  align:left;
  padding:0;
  vertical-align:top;

}

.ea-agenda-list-col-class{

  width:600px;  
  align:left;
  padding:0;
  vertical-align:top;
  background-color: white;
  
}
.ea-agenda-tweet-list-col-class{
  width:360px;
  height:422px;
  align:right;
  padding:0;
  vertical-align:top;
  margin: 0 auto;
  background-color: white;	
}
.ea-agenda-seperator-class{
width:20px;

}

.ea-event-content-main-panel{
  width:980px;
  height:440px;
  align:center;
  padding:0;
  vertical-align:top;
  margin: 0 auto;
  border: 0;    
  background-color: white;
 
}
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
<a4j:form id="eventForm">
<h:inputHidden id="likeId" value="#{attendeeBean.hiddenLikeId}"></h:inputHidden>
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
<h:outputText id="eventInfoTip1" styleClass="ea-content-bold-class" value="#{applicationBean.currentEvent}">
 <rich:toolTip styleClass="tooltip"  id="eventInfoToolTip1" for="eventInfoTip1" layout="inline"  value="#{applicationBean.eventTooptip}"/>

</h:outputText>
</h:panelGroup>
</h:panelGrid>
</h:panelGroup>

<h:panelGroup>

</h:panelGroup>

<h:panelGroup id="ea-event-like-id">
<h:panelGrid columns="2">
<h:panelGroup>
<a4j:commandButton style="width:50px;height:20px;" reRender="ea-event-like-id" image="/images/like_box.jpg" onclick="setEventLikeId('#{eventBean.eventId}')"  actionListener="#{attendeeBean.eventLikeListener}"></a4j:commandButton>
</h:panelGroup>
<h:panelGroup>
<h:outputLabel styleClass="ea-content-class" value="#{applicationBean.eventLikeCount} people likes this"></h:outputLabel>
</h:panelGroup>
</h:panelGrid>
</h:panelGroup>

<h:panelGroup>
<jsp:include page="../common/loading_status.jsp" />
<jsp:include page="../common/status_message.jsp" />
</h:panelGroup>
</h:panelGrid>

</h:panelGroup>

</h:panelGrid>

<a4j:commandButton id="loadTweets" value="" reRender="ea-event-tweet-panel,twitterSearchList" style="display:none" onclick="submitSocialMediaEventId('#{eventBean.eventId}')" actionListener="#{socialMediaBean.eventTweets}" />
<!-- hidden field values -->

<h:inputHidden value="#{sessionBean.eventId}" id="sessionEventId" />
<h:inputHidden value="#{sessionBean.sessionId}" id="sessionId" />
<h:inputHidden value="#{eventBean.eventId}" id="eventId" />
<h:inputHidden value="#{socialMediaBean.eventId}" id="socialMediaEventId" />
<h:inputHidden value="#{sessionBean.liveSession}" id="liveSessionId" />
<h:inputHidden id="urlToOpenId" value="#{socialMediaBean.urlToOpen}"></h:inputHidden>
<h:inputHidden id="profileIdToConnect" value="#{socialMediaBean.profileIdToConnect}"></h:inputHidden>
<h:inputHidden id="toName" value="#{attendeeBean.hiddenToName}"></h:inputHidden>
<h:inputHidden id="toProfileId" value="#{attendeeBean.hiddenProfileId}"></h:inputHidden>
<h:inputHidden id="profileName" value="#{attendeeBean.profileName}"></h:inputHidden>
<h:inputHidden id="profName" value="#{socialMediaBean.profileName}"></h:inputHidden>
<h:inputHidden id="twitterFollow" value="#{socialMediaBean.twitterFollow}"></h:inputHidden>
<h:inputHidden id="faceBookFriendsConnect" value="#{socialMediaBean.faceBookFriendsConnect}"></h:inputHidden>
<h:inputHidden id="linkedinFriendsConnect" value="#{socialMediaBean.linkedinFriendsConnect}"></h:inputHidden>
<h:inputHidden id="alreadyFollowingInTwitter" value="#{socialMediaBean.alreadyFollowingInTwitter}"></h:inputHidden>
<h:inputHidden id="alreadyFriendInFB" value="#{socialMediaBean.alreadyFriendInFB}"></h:inputHidden>
<h:inputHidden id="alreadyFriendInLin" value="#{socialMediaBean.alreadyFriendInLin}"></h:inputHidden>

<rich:panel id="agenda" styleClass="ea-event-content-main-panel">
	
	<h:panelGrid columnClasses="ea-agenda-list-col-class,ea-agenda-seperator-class,ea-agenda-tweet-list-col-class" columns="3">
	
	<h:panelGroup>			
			<h:panelGrid columns="1" rowClasses="ea-event-info-row-class,ea-agenda-row-class">			
			<h:panelGroup>
			<jsp:include page="event_information_main.jsp"></jsp:include>
			</h:panelGroup>
			<h:panelGroup>			
			<jsp:include page="event_agenda.jsp"></jsp:include>
			</h:panelGroup>
			</h:panelGrid>			
	</h:panelGroup>
	
	<h:panelGroup>
	
	</h:panelGroup>
	<h:panelGroup>
			<h:panelGrid rowClasses="ea-tweet-list-row-class,ea-row-seperator-class,ea-tweet-list-row-class" columns="1">
			<h:panelGroup id="ea-event-attendee-list-id">
			<jsp:include page="event_attendee.jsp" />
			</h:panelGroup>
			<h:panelGroup>
			
			</h:panelGroup>			
			<h:panelGroup>
			<jsp:include page="event_tweet.jsp" />
			</h:panelGroup>
			</h:panelGrid>
			
	</h:panelGroup>
	
	
	</h:panelGrid>
	
</rich:panel>

</a4j:form>
<a4j:region>
        <h:form>
            <a4j:poll id="eventTweetPoll" status="tweetStatus" onsubmit="submitSocialMediaEventId('#{eventBean.eventId}')" actionListener="#{socialMediaBean.eventTweets}" interval="1000" enabled="#{socialMediaBean.twitterPollEnabled}"
                reRender="ea-event-tweet-panel,twitterSearchList,eventTweetPoll" />
        </h:form>
         <h:form>
            <a4j:poll id="eventAttendeePoll"   actionListener="#{eventBean.eventAttendeeRefreshListener}" interval="60000" enabled="#{applicationBean.eventattendeeAutoRefresh}"
                reRender="ea-event-attendee-panel" />
        </h:form>
</a4j:region>

