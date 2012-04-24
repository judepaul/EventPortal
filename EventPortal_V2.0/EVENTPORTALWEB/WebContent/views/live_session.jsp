<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="<%=session.getAttribute("CURRENTURL")%>/scripts/live_session_script.js"></script>

<script type="text/javascript">

</script>

<style type="text/css">

.session-detail-panel{
  width:980px;
  height:440px; 
  padding:0px;
  vertical-align:top;
  margin: 0 auto;
  border: 0px;    
  background-color: white;
}
.ea-session-detail-col1{
vertical-align:top;

width:580px;
background: white;

}
.ea-session-detail-col2{
vertical-align:top;
width:5px;
background: white;
}
.ea-session-detail-col3{

vertical-align:top;
width:390px;
background: white;
}
.white-panel{
vertical-align: top;
	border: 0;
	width:100%;
	height:100%;
	background-color: white;

}
.speaker-panel{
background-color: white;

}
.session-info-panel{
background-color: white;

}
.row1{
height: 20px;

}
.row2{


}

.ea-bot-col1{

width: 50px;
}
.ea-bot-col2{

width: 150px;
}
.ea-bot-col3{

width: 150px;
}
.ea-session-toggle-panel-class{

background-color: white;

}
.ea-session-panel-grid-class{
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


<a4j:form id="liveSessionForm">

<h:inputHidden id="likeId" value="#{attendeeBean.hiddenLikeId}"></h:inputHidden>
<h:panelGrid columns="1" rowClasses="ea-header-info-row">
<h:panelGroup>
<h:panelGrid columns="4" columnClasses="ea-header-info-col1,ea-header-info-col2,ea-header-info-col3,ea-header-info-col4">
<h:panelGroup>
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

<h:panelGroup>
<h:outputText rendered="#{applicationBean.currentSession!=null && applicationBean.currentSpeaker!=null}" id="sessionSpeakerInfoTip"   styleClass="ea-content-bold-class" value="Speaker of the session - #{applicationBean.currentSpeaker}">
   <rich:toolTip styleClass="tooltip"  id="sessionSpeakerInfoToolTip" for="sessionSpeakerInfoTip" layout="inline"  value="#{applicationBean.currentSpeakerToolTip}"/>

</h:outputText>
</h:panelGroup>
<a4j:region id="sessionLikeRegion">
<a4j:status id="sessionLikeStatus" for="sessionLikeRegion">
                <f:facet name="start">
                    
                </f:facet>
 </a4j:status>
<h:panelGroup id="ea-session-like-id">
<h:panelGrid columns="2">
<h:panelGroup >
<a4j:commandButton status="sessionLikeStatus" style="width:50px;height:20px;" reRender="ea-session-like-id" image="/images/like_box.jpg" onclick="setSessionLikeId('#{sessionBean.sessionId}')"  actionListener="#{attendeeBean.sessionLikeListener}"></a4j:commandButton>
</h:panelGroup>
<h:panelGroup id="sess_session_Like_Count" >
<h:outputLabel styleClass="ea-content-class" value="#{applicationBean.sessionLikeCount} people likes this"></h:outputLabel>
</h:panelGroup>
</h:panelGrid>
</h:panelGroup>
</a4j:region>
<h:panelGroup>
<jsp:include page="../common/loading_status.jsp" />
<jsp:include page="../common/status_message.jsp" />
</h:panelGroup>
</h:panelGrid>

</h:panelGroup>

</h:panelGrid>


<a4j:commandButton id="userSocialProfile" value=""  reRender="ea-user-social-profile-modal-Panel" style="display:none" actionListener="#{socialMediaBean.speakerSocialProfile}" oncomplete="#{rich:component('ea-user-social-profile-modal-Panel')}.show()" />

<a4j:commandButton id="resetLiveSessionId" value="" style="display:none" actionListener="#{sessionBean.leaveLiveSessionListener}" />
<!--<a4j:commandButton id="speakerInfo" value="" reRender="ea-speaker-info-modal-Panel" style="display:none" actionListener="#{sessionBean.sessionSpeakerInfoListener}" oncomplete="#{rich:component('ea-speaker-info-modal-Panel')}.show()" />-->
<!-- hidden field values -->
<h:inputHidden id="sessionId" value="#{sessionBean.sessionId}"></h:inputHidden>
<h:inputHidden id="liveSession" value="#{sessionBean.liveSession}"></h:inputHidden>
<h:inputHidden id="toName" value="#{attendeeBean.hiddenToName}"></h:inputHidden>
<h:inputHidden id="toProfileId" value="#{attendeeBean.hiddenProfileId}"></h:inputHidden>
<h:inputHidden id="profileIdToConnect" value="#{socialMediaBean.profileIdToConnect}"></h:inputHidden>
<h:inputHidden id="profileName" value="#{socialMediaBean.profileName}"></h:inputHidden>
<h:inputHidden id="twitterFollow" value="#{socialMediaBean.twitterFollow}"></h:inputHidden>
<h:inputHidden id="faceBookFriendsConnect" value="#{socialMediaBean.faceBookFriendsConnect}"></h:inputHidden>
<h:inputHidden id="linkedinFriendsConnect" value="#{socialMediaBean.linkedinFriendsConnect}"></h:inputHidden>
<h:inputHidden id="alreadyFollowingInTwitter" value="#{socialMediaBean.alreadyFollowingInTwitter}"></h:inputHidden>
<h:inputHidden id="alreadyFriendInFB" value="#{socialMediaBean.alreadyFriendInFB}"></h:inputHidden>
<h:inputHidden id="alreadyFriendInLin" value="#{socialMediaBean.alreadyFriendInLin}"></h:inputHidden>
<h:inputHidden id="currentUser" value="#{socialMediaBean.currentUser}"></h:inputHidden>
 


<rich:panel id="sessionDetail" styleClass="session-detail-panel">
<h:panelGrid  rowClasses="row1" columns="1">
<h:panelGroup>
	<h:panelGrid columns="3" columnClasses="ea-session-detail-col1,ea-session-detail-col2,ea-session-detail-col3" >
	<h:panelGroup>	
	<h:panelGrid columns="1">	
	<h:panelGroup>

<!--<h:graphicImage width="20" height="20" value="/images/finger_print.jpg"></h:graphicImage>--> 
<!--<a4j:htmlCommandLink  value="You are attending this Session. Leave Now?" onclick="submitSessionId('#{sessionBean.sessionId}','#{sessionBean.sessionId}')" action="#{sessionBean.leaveLiveSession}"></a4j:htmlCommandLink>-->
<h:outputText styleClass="ea-content-class" value="You are attending this Session."></h:outputText>
<a4j:commandLink  value="Leave Now?" reRender="leave-confirm-modal-panel-id" oncomplete="#{rich:component('leave-confirm-modal-panel-id')}.show()">
<a4j:actionparam name="currentLinkClicked" value="LIVESESSION" assignTo="#{applicationBean.linkClicked}"/>
</a4j:commandLink>			
</h:panelGroup>
<!-- <h:panelGroup>
<rich:simpleTogglePanel styleClass="ea-session-toggle-panel-class" switchType="client" label="Add AJAX capability to existing JSF applications">

                    <f:facet name="header">
                       <h:outputText value="Session Information - #{sessionBean.eventDTO.sessionName}   | Venue - #{sessionBean.eventDTO.sessionVenueName}"  />                          
                    </f:facet>
                    <h:panelGrid columns="1" styleClass="ea-session-panel-grid-class">
                        <h:outputText styleClass="ea-content-bold-class" value="Keynotes:" />
                        <rich:spacer width="12" height="5"/>
                        <h:outputText styleClass="ea-content-class" value="#{sessionBean.eventDTO.sessionKeyNote}"/>     
                        <h:outputText styleClass="ea-content-bold-class" value="Date:" />
                        <rich:spacer width="12" height="5"/>
                        <h:outputText styleClass="ea-content-class" value="#{sessionBean.eventDTO.sessionStartDate}"/>
                        <h:outputText styleClass="ea-content-bold-class" value="Timings:" />
                        <rich:spacer width="12" height="5"/>
                        <h:outputText styleClass="ea-content-class" value="#{sessionBean.eventDTO.sessionStartTime} - #{sessionBean.eventDTO.sessionEndTime}"/>     
                                  
                   </h:panelGrid>

                </rich:simpleTogglePanel>
</h:panelGroup>-->


	<h:panelGroup>
	<jsp:include page="live_session_comment.jsp" />
	</h:panelGroup>
	<h:panelGroup>
	<h:panelGrid width="100%" columns="2" columnClasses="ea-bot-col1,ea-bot-col2">
				<h:panelGroup>
				
							</h:panelGroup>
							<h:panelGroup >
				
				
				</h:panelGroup>
	</h:panelGrid>
	
	</h:panelGroup>
	</h:panelGrid>
	</h:panelGroup>
	<h:panelGroup>
	
	</h:panelGroup>
	<h:panelGroup>
	<h:panelGrid columns="1" >
	<h:panelGroup>
<jsp:include page="live_session_attendee.jsp" />
</h:panelGroup>
<!--<h:panelGroup>
<rich:panel  style="background:white;">

 <f:facet name="header">
                       <h:outputText value="Speaker Information:" />                          
                    </f:facet>
				
						<h:graphicImage width="40" height="40"  rendered="#{sessionBean.sessionSpeakerPicture!=null}"

value="#{sessionBean.sessionSpeakerPicture}"/>
	<h:outputText value="#{sessionBean.speakerName}"/>


						<h:panelGroup rendered="#{sessionBean.speakerName!=null}">
				         	
				         	<a4j:commandButton id="profileImg" image="/images/profile.jpg" size="15" onclick="showUserSocialProfile();" value="Social Profile">
				         	<rich:toolTip id="toolTip" for="profileImg" value="Public Profile of #{sessionBean.speakerName}">
			   			    </rich:toolTip>
				         	</a4j:commandButton>
				    
				         	<rich:spacer width="5" />				         							         	      	
						      <a4j:commandButton style="cursor:hand;"  image="/images/connect.jpg"   actionListener="#{socialMediaBean.connectViaSMListener}"  onclick="connectSocialMedia('#{sessionBean.sessionSpeakerSMPId}','#{sessionBean.sessionSpeakerFirstName}','#{sessionBean.sessionSpeakerTwitterFollow}','#{sessionBean.sessionSpeakerFacebookConnect}','#{ sessionBean.sessionSpeakerLinkedInConnect}');" rendered="#{(sessionBean.sessionSpeakerTwitterFollow == true || sessionBean.sessionSpeakerFacebookConnect == true || sessionBean.sessionSpeakerLinkedInConnect== true) && (sessionBean.currentUser== false)}"  reRender="ea-socialMedia-invite-modal-Panel"  oncomplete="#{rich:component('ea-socialMedia-invite-modal-Panel')}.show()"  /> 
							          <a4j:commandButton rendered="#{sessionBean.currentUser== false}" image="/images/mail_logo.jpg" actionListener="#{attendeeBean.initSendMail}" reRender="modalConnectMailPanel" onclick="connectViaEmail('#{sessionBean.sessionSpeakerSMPId}','#{sessionBean.sessionSpeakerFirstName}');"  oncomplete="#{rich:component('modalConnectMailPanel')}.show()" /> 
						              	<rich:spacer width="5" />
						              	</h:panelGroup>
				</rich:panel>
</h:panelGroup>-->
</h:panelGrid>
	</h:panelGroup>
		
		
	</h:panelGrid>
</h:panelGroup>
</h:panelGrid>
</rich:panel>
</a4j:form>

<a4j:region>
        <h:form>
            <a4j:poll id="attendeePoll" actionListener="#{sessionBean.liveAttendeeRefreshListener}" interval="45000" enabled="#{applicationBean.attendeeListAutoRefresh}"
                reRender="ea-live-session-attendee-panel" />
        </h:form>
        <h:form>
            <a4j:poll id="commentPoll" actionListener="#{sessionBean.sessionCommentRefreshListener}" interval="20000" enabled="#{applicationBean.sessionCommentListAutoRefresh}"
                reRender="ea-session-comments-list-panel" />
        </h:form>
        <h:form>
            <a4j:poll id="likePoll" actionListener="#{sessionBean.sessionLikeRefreshListener}" interval="60000" enabled="#{applicationBean.likeRefresh}"
                reRender="ea-session-like-id" />
        </h:form>
     
</a4j:region>