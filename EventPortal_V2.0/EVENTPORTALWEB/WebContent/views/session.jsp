<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>

<style type="text/css">

.ea-session-summary-panel{
  width:980px;
  height:440px; 
  padding:0px;
  vertical-align:top;
  margin: 0 auto;
  border: 0px;    
  background-color: white;
}
.ea-session-summary-col1{
width:675px;

}
.ea-session-summary-col2{
width:5px;
}
.ea-session-summary-col3{
vertical-align:top;
width:300px;
}
.toolTipP {
	 background:#ECFFFF;
   border: 2px solid #06266F;  
   padding:5px;    
   -moz-border-radius: 10px;    /* Rounded border mozilla browsers */
   -webkit-border-radius: 10px; /* Rounded border webkit browsers */
   border-radius: 10px; 
   
} 
.col1{
width:675px;

}
.col2{
width:5px;
}
.col3{
vertical-align:top;
width:300px;
}
.white-panel{

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

.event-heading {
	font-size:15px;
	color:#c0595a;
	line-height:25px;
	font-weight: bold;
}
.event-row1{
height: 30px;
}
.event-row2{
height: 30px;
text-align: right;
}

.ea-session-summary-row1{
vertical-align:top;
 height: 95px;

}

.ea-session-summary-row2{
vertical-align:top;

 height: 120px;
 
}

.ea-session-summary-row3{
vertical-align:top;

 height: 150px;
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

<script type="text/javascript">

function submitSessionIdForDetail(sessionId){

	//alert('ID==>'+sessionId);
	document.getElementById("sessionSummaryForm:sessionId").value = sessionId;
	//document.getElementById("sessionSummaryForm:submitSessionIdforDetail").click();
	
}


function openWindow(mediaId){
	  popup(mediaId);   
	//window.open(mediaId);
	//window.open(mediaId,'windowname2','width=500,height=300,directories=no,location=no, menubar=no,resizable=no,scrollbars=1,status=no,toolbar=no');
}

function popup(url) 
{
var width  = 600;
var height = 400;
var left   = (screen.width  - width)/2;
var top    = (screen.height - height)/2;
var params = 'width='+width+', height='+height;
params += ', top='+top+', left='+left;
params += ', directories=no';
params += ', location=no';
params += ', menubar=no';
params += ', resizable=no';
params += ', scrollbars=yes';
params += ', status=no';
params += ', toolbar=no';
newwin=window.open(url,'windowname5', params);
if (window.focus) {newwin.focus();}
return false;
}

function showUserSocialProfile(){	
	document.getElementById("sessionSummaryForm:userSocialProfile").click();	
}

function connectViaEmail(mailId,toName){
	//alert(mailId);
	document.getElementById('sessionSummaryForm:toName').value = toName;
	document.getElementById('sessionSummaryForm:toProfileId').value = mailId;	
			
}
function connectSocialMedia(profileId,profileName,twitterFollow,faceBookFriendsConnect,linkedinFriendsConnect){
	//alert(profileId+" "+profileName+" "+twitterFollow+" "+faceBookFriendsConnect+" "+linkedinFriendsConnect);
	document.getElementById('sessionSummaryForm:profileIdToConnect').value = profileId;
	document.getElementById('sessionSummaryForm:profileName').value = profileName;
	document.getElementById('sessionSummaryForm:twitterFollow').value = twitterFollow;
	document.getElementById('sessionSummaryForm:faceBookFriendsConnect').value = faceBookFriendsConnect;
	document.getElementById('sessionSummaryForm:linkedinFriendsConnect').value = linkedinFriendsConnect;
	document.getElementById('sessionSummaryForm:alreadyFollowingInTwitter').value = false;
	document.getElementById('sessionSummaryForm:alreadyFriendInFB').value = false;
	document.getElementById('sessionSummaryForm:alreadyFriendInLin').value = false;
}

function setSessionLikeId(id){

	document.getElementById("sessionSummaryForm:likeId").value = id;
	
}
</script>

<a4j:form id="sessionSummaryForm">

<h:inputHidden id="likeId" value="#{attendeeBean.hiddenLikeId}"></h:inputHidden>
<h:panelGrid columns="1" rowClasses="ea-header-info-row">
<h:panelGroup>
<h:panelGrid columns="4" columnClasses="ea-header-info-col1,ea-header-info-col2,ea-header-info-col3,ea-header-info-col4">
<h:panelGroup>
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

<h:panelGroup>
<h:outputText rendered="#{applicationBean.currentSession!=null && applicationBean.currentSpeaker!=null}" id="sessionSpeakerInfoTip"   styleClass="ea-content-bold-class" value="Speaker of the session - #{applicationBean.currentSpeaker}">
   <rich:toolTip styleClass="tooltip"  id="sessionSpeakerInfoToolTip" for="sessionSpeakerInfoTip" layout="inline"  value="#{applicationBean.currentSpeakerToolTip}"/>

</h:outputText>
</h:panelGroup>

<h:panelGroup id="ea-session-like-id">
<h:panelGrid columns="2">
<h:panelGroup>
<a4j:commandButton style="width:50px;height:20px;" reRender="ea-session-like-id" image="/images/like_box.jpg" onclick="setSessionLikeId('#{sessionBean.sessionId}')"  actionListener="#{attendeeBean.sessionLikeListener}"></a4j:commandButton>
</h:panelGroup>
<h:panelGroup id="sess_session_Like_Count" >
<h:outputLabel styleClass="ea-content-class" value="#{applicationBean.sessionLikeCount} people likes this"></h:outputLabel>
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



<!-- hidden field values -->

<h:inputHidden id="sessionId" value="#{sessionBean.sessionId}"></h:inputHidden>
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
		

<rich:panel id="sessionSummary" styleClass="ea-session-summary-panel">
		<h:panelGrid columns="3" columnClasses="ea-session-summary-col1,ea-session-summary-col2,ea-session-summary-col3">
	<h:panelGroup>
<h:panelGrid columns="1" rowClasses="ea-session-summary-row1,ea-session-summary-row2,ea-session-summary-row3">
<h:panelGroup>
<jsp:include page="event_information_main.jsp"></jsp:include>
</h:panelGroup>

<h:panelGroup>
<rich:panel styleClass="session-info-panel">
                    <f:facet name="header">
                       <h:outputText value="Session Information - #{sessionBean.eventDTO.sessionName}   | Venue - #{sessionBean.eventDTO.sessionVenueName}"  />                          
                    </f:facet>
                    <h:panelGrid columns="1">
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
                </rich:panel>
</h:panelGroup>

<h:panelGroup>
<rich:panel styleClass="speaker-panel">
				 <f:facet name="header">
         			<h:outputText value="Speaker Information:" />
      			  </f:facet>				
				<table>
				<tr>
					<td>			
					<table>
					<tr>
					<td>			
						<table>
						<tr>
						<td>

						<h:graphicImage id="speakerproftip" width="40" height="40"  rendered="#{sessionBean.sessionSpeakerPicture!= null}"

value="#{sessionBean.sessionSpeakerPicture}">
     	                <rich:toolTip styleClass="tooltip" id="speakerproftooltip" for="speakerproftip" layout="inline"  value="#{sessionBean.profileDTO.profileToolTip}"/>
						      
						       
</h:graphicImage>
	<h:graphicImage id="speakerproftip1" width="40" height="40"  rendered="#{sessionBean.sessionSpeakerPicture== null && sessionBean.speakerName!=null}"

value="/images/noPhoto.jpg">
     	                <rich:toolTip styleClass="tooltip" id="speakerproftooltip1" for="speakerproftip1" layout="inline"  value="#{sessionBean.profileDTO.profileToolTip}"/>
						      
						       
</h:graphicImage>
						</td>
						</tr>
						<tr>
						<td style="color:green;font-weight: 

bold;font-size: 12pt;">
						<h:outputText value="#{sessionBean.speakerName}"/>
						</td>						
						</tr>
						<tr>
						<td>
						<h:outputText styleClass="ea-content-class" value="#{sessionBean.speakerBio}"/>
						</td>
						</tr>
						
						</table>			
					</td>
					</tr>
					</table>
					</td>
				<td>
				</td>
				<td>
					<table>
						<tr>
						<td style="font-weight: bold;font-size: 

10pt;">
							
						</td>						
						</tr>
						<tr>
						<td>					
			               <!--  <rich:spacer rendered="#{sessionBean.sessionSpeakerSMPTwitterProfileUrl != null}" width="5" />
			                <h:graphicImage style="cursor:hand;"  width="15" height="15" onclick="openWindow('#{sessionBean.sessionSpeakerSMPTwitterProfileUrl}')" rendered="#{sessionBean.sessionSpeakerSMPTwitterProfileUrl != null}" value="/images/twitter_logo.jpg"/>
			         		<rich:spacer rendered="#{sessionBean.sessionSpeakerSMPFaceBookProfileUrl != null}" width="5" />
			         		<h:graphicImage style="cursor:hand;" width="15" height="15" onclick="openWindow('#{sessionBean.sessionSpeakerSMPFaceBookProfileUrl}')" rendered="#{sessionBean.sessionSpeakerSMPFaceBookProfileUrl != null}" value="/images/facebook_logo.jpg"/>
			                <rich:spacer rendered="#{sessionBean.sessionSpeakerSMPLinkedInProfileUrl != null}" width="5" />
			                <h:graphicImage style="cursor:hand;" width="15" height="15" onclick="openWindow('#{sessionBean.sessionSpeakerSMPLinkedInProfileUrl}')" rendered="#{sessionBean.sessionSpeakerSMPLinkedInProfileUrl != null}" value="/images/linkedin_logo.jpg"/>
			                	-->					                
				         	<rich:spacer width="5" />
				         	<h:panelGroup rendered="#{sessionBean.speakerName!=null}">
				         	
				         	<a4j:commandButton id="profileImg" image="/images/profile.jpg" size="15" rendered="#{sessionBean.profileDTO.faceBookId != null || sessionBean.profileDTO.twitterId != null || sessionBean.profileDTO.linkedInId != null}"  onclick="showUserSocialProfile();" value="Social Profile">
				         	<rich:toolTip  styleClass="toolTipP" id="toolTipProfile" for="profileImg" value="Public Profile of #{sessionBean.speakerName}">
			   			    </rich:toolTip>
				         	</a4j:commandButton>
				         	
				         	<h:graphicImage value="/images/gray_profile.jpg" rendered="#{sessionBean.profileDTO.faceBookId == null && sessionBean.profileDTO.twitterId == null && sessionBean.profileDTO.linkedInId == null}" width="15" height="15" />
				    
				         	<rich:spacer width="5" />				         							         	      	
						      <a4j:commandButton style="cursor:hand;"  image="/images/connect.jpg"   actionListener="#{socialMediaBean.connectViaSMListener}"  onclick="connectSocialMedia('#{sessionBean.sessionSpeakerSMPId}','#{sessionBean.sessionSpeakerFirstName} #{sessionBean.sessionSpeakerLastName}','#{sessionBean.sessionSpeakerTwitterFollow}','#{sessionBean.sessionSpeakerFacebookConnect}','#{ sessionBean.sessionSpeakerLinkedInConnect}');" rendered="#{(sessionBean.sessionSpeakerTwitterFollow == true || sessionBean.sessionSpeakerFacebookConnect == true || sessionBean.sessionSpeakerLinkedInConnect== true) && (sessionBean.currentUser== false)}"  reRender="ea-socialMedia-invite-modal-Panel"  oncomplete="#{rich:component('ea-socialMedia-invite-modal-Panel')}.show()"  />
						      <h:graphicImage value="/images/gray_connect.jpg" rendered="#{(sessionBean.sessionSpeakerTwitterFollow == false && sessionBean.sessionSpeakerFacebookConnect == false && sessionBean.sessionSpeakerLinkedInConnect== false) && (sessionBean.currentUser== false)}" /> 
						 
							          <a4j:commandButton rendered="#{sessionBean.currentUser== false}" image="/images/mail_logo.jpg" actionListener="#{attendeeBean.initSendMail}" reRender="modalConnectMailPanel" onclick="connectViaEmail('#{sessionBean.sessionSpeakerSMPId}','#{sessionBean.sessionSpeakerFirstName} #{sessionBean.sessionSpeakerLastName}');"  oncomplete="#{rich:component('modalConnectMailPanel')}.show()" /> 
						              	<rich:spacer width="5" />		
							
				         	</h:panelGroup>
						</td>
						</tr>
					</table>			
				</td>
				</tr>
			</table>			
			</rich:panel>		
</h:panelGroup>

</h:panelGrid>


	</h:panelGroup>
	
	<h:panelGroup>
	
	</h:panelGroup>
		
		<h:panelGroup>
				<!-- Attendee List -->
				<jsp:include page="session_attendee.jsp" />
		</h:panelGroup>		
	</h:panelGrid>
</rich:panel>
<a4j:commandButton id="submitSessionIdforDetail" value="" style="display:none" action="#{sessionBean.populateSessionDetailsPage}" />
<a4j:commandButton id="userSocialProfile" value=""  reRender="ea-user-social-profile-modal-Panel" style="display:none" actionListener="#{socialMediaBean.speakerSocialProfile}" oncomplete="#{rich:component('ea-user-social-profile-modal-Panel')}.show()" />
</a4j:form>
<a4j:region>
        <h:form>
            <a4j:poll id="sessionAttendeePoll" actionListener="#{sessionBean.sessionAttendeeRefreshListener}" interval="90000" enabled="#{applicationBean.sessionAttendeeListAutoRefresh}"
                reRender="ea-session-attendee-panel" />
        </h:form>
       
     
</a4j:region>