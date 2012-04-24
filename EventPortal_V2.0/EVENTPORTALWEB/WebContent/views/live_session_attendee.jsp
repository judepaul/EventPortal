<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<link href="../styles/styles.css" rel="stylesheet" type="text/css" />

<style type="text/css">
.ea-live-session-attendee-panel-body-class {
	height: 150px;
	overflow: auto;
}

.toolTipProfile1 {
	 background:#ECFFFF;
   border: 2px solid #06266F;  
   padding:5px;    
   -moz-border-radius: 10px;    /* Rounded border mozilla browsers */
   -webkit-border-radius: 10px; /* Rounded border webkit browsers */
   border-radius: 10px; 
   
}

.toolTipProfile2 {
 background:#ECFFFF;
   border: 2px solid #06266F;  
   padding:5px;    
   -moz-border-radius: 10px;    /* Rounded border mozilla browsers */
   -webkit-border-radius: 10px; /* Rounded border webkit browsers */
   border-radius: 10px; 
   
}

.ea-live-session-attendee-panel-class {
	height: 300px;
}

.ea-live-session-speaker-panel-class {
	background: #FFEC8B;
}
</style>

<rich:panel id="ea-live-session-attendee-panel"
	styleClass="ea-live-session-attendee-panel-class"
	bodyClass="ea-live-session-attendee-panel-body-class">


	<f:facet name="header">
		<h:outputText
			value="Live Session Attendees : #{attendeeBean.attendeeDTO.attendeeCount}" />
	</f:facet>

	<h:inputHidden id="faceBookId" value="#{socialMediaBean.faceBookId}" />
	<h:inputHidden id="twitterId" value="#{socialMediaBean.twitterId}" />
	<h:inputHidden id="linkedInId" value="#{socialMediaBean.linkedInId}" />

	<rich:dataTable width="350" id="attendeeListTable" border="0"
		columnClasses="col" value="#{attendeeBean.attendeeDTO.attendeeList}"
		var="attendeeListVar">

		<rich:column rendered="#{attendeeListVar.currentSessionSpeaker==true}"
			styleClass="ea-live-session-speaker-panel-class">
			<h:graphicImage width="30" height="30" id="sessionProfiletip"
				rendered="#{attendeeListVar.profImgFileName != '/images/null'}"
				value="#{attendeeListVar.profImgFileName}">
				<rich:toolTip styleClass="tooltip" id="sessionProfiletooltip"
					for="sessionProfiletip" layout="inline"
					value="#{attendeeListVar.profileToolTip}" />
			</h:graphicImage>
			<h:graphicImage width="30" height="30" id="sessionProfiletip1"
				rendered="#{attendeeListVar.profImgFileName == '/images/null'}"
				value="/images/noPhoto.jpg">
				<rich:toolTip styleClass="tooltip" id="sessionProfiletooltip1"
					for="sessionProfiletip1" layout="inline"
					value="#{attendeeListVar.profileToolTip}" />
			</h:graphicImage>


			<rich:spacer rendered="#{attendeeListVar.speakerCheck}" width="5" />
			<h:graphicImage id="tooltipGStar" width="15" height="15"
				rendered="#{attendeeListVar.speakerCheck}"
				value="/images/greenstar.jpg" />
			<rich:spacer width="5" />
			<h:outputText
				value="#{attendeeListVar.firstName} #{attendeeListVar.lastName}"></h:outputText>
			<rich:spacer width="5" />
			<a4j:commandButton
				rendered="#{attendeeListVar.socialMediaDTO.faceBookId != null || attendeeListVar.socialMediaDTO.twitterId != null || attendeeListVar.socialMediaDTO.linkedInId != null}"
				id="userSocialProfileID"
				onclick="showUserSocialProfile('#{attendeeListVar.socialMediaDTO.faceBookId}','#{attendeeListVar.socialMediaDTO.twitterId}','#{attendeeListVar.socialMediaDTO.linkedInProfileUrl}');"
				image="/images/profile.jpg"
				reRender="ea-user-social-profile-modal-Panel"
				actionListener="#{socialMediaBean.userSocialProfile}"
				oncomplete="#{rich:component('ea-user-social-profile-modal-Panel')}.show()">
				<rich:toolTip   styleClass="toolTipProfile1"  id="toolTipProfile1" for="userSocialProfileID"
					value="Public Profile of #{attendeeListVar.firstName} #{attendeeListVar.lastName}">
				</rich:toolTip>
			</a4j:commandButton>
			<h:graphicImage value="/images/gray_profile.jpg"
				rendered="#{attendeeListVar.socialMediaDTO.faceBookId == null && attendeeListVar.socialMediaDTO.twitterId == null && attendeeListVar.socialMediaDTO.linkedInId == null}" />
			<rich:spacer width="5" />
			<a4j:commandButton image="/images/connect.jpg"
				actionListener="#{socialMediaBean.connectViaSMListener}"
				onclick="connectSocialMedia('#{attendeeListVar.profileId}','#{attendeeListVar.firstName} #{attendeeListVar.lastName}','#{attendeeListVar.socialMediaDTO.twitterFollow}','#{attendeeListVar.socialMediaDTO.faceBookFriendsConnect}','#{ attendeeListVar.socialMediaDTO.linkedInFriendsConnect}');"
				rendered="#{(attendeeListVar.socialMediaDTO.twitterFollow == true || attendeeListVar.socialMediaDTO.faceBookFriendsConnect == true || attendeeListVar.socialMediaDTO.linkedInFriendsConnect == true) && (attendeeListVar.currentUser== false)}"
				reRender="ea-socialMedia-invite-modal-Panel"
				oncomplete="#{rich:component('ea-socialMedia-invite-modal-Panel')}.show()" />
			<h:graphicImage value="/images/gray_connect.jpg"
				rendered="#{(attendeeListVar.socialMediaDTO.twitterFollow == false && attendeeListVar.socialMediaDTO.faceBookFriendsConnect == false && attendeeListVar.socialMediaDTO.linkedInFriendsConnect == false) && (attendeeListVar.currentUser== false)}" />

		</rich:column>
		<rich:column
			rendered="#{attendeeListVar.currentSessionSpeaker==false}">
			<h:graphicImage id="livesessionProfiletip" width="30" height="30"
				value="#{attendeeListVar.profImgFileName }"
				rendered="#{attendeeListVar.profImgFileName != '/images/null'}">
				<rich:toolTip styleClass="tooltip" id="livesessionProfiletooltip"
					for="livesessionProfiletip" layout="inline"
					value="#{attendeeListVar.profileToolTip}" />

			</h:graphicImage>
			<h:graphicImage id="livesessionProfiletip1" width="30" height="30"
				rendered="#{attendeeListVar.profImgFileName == '/images/null'}"
				value="/images/noPhoto.jpg">
				<rich:toolTip styleClass="tooltip" id="livesessionProfiletooltip1"
					for="livesessionProfiletip1" layout="inline"
					value="#{attendeeListVar.profileToolTip}" />

			</h:graphicImage>

			<rich:spacer rendered="#{attendeeListVar.speakerCheck}" width="5" />
			<h:graphicImage id="tooltipStar" width="15" height="15"
				rendered="#{attendeeListVar.speakerCheck}" value="/images/star.jpg">
				<!--   <rich:toolTip id="tooltipS1" for="tooltipStar"  value="SPEAKER - #{attendeeListVar.speakerFor}"/>-->
			</h:graphicImage>
			<rich:spacer width="5" />
			<h:outputText
				value="#{attendeeListVar.firstName} #{attendeeListVar.lastName}"></h:outputText>
			<rich:spacer width="5" />
			<a4j:commandButton
				rendered="#{attendeeListVar.socialMediaDTO.faceBookId != null || attendeeListVar.socialMediaDTO.twitterId != null || attendeeListVar.socialMediaDTO.linkedInId != null}"
				id="userSocialProfileID1"
				onclick="showUserSocialProfile('#{attendeeListVar.socialMediaDTO.faceBookId}','#{attendeeListVar.socialMediaDTO.twitterId}','#{attendeeListVar.socialMediaDTO.linkedInProfileUrl}');"
				image="/images/profile.jpg"
				reRender="ea-user-social-profile-modal-Panel"
				actionListener="#{socialMediaBean.userSocialProfile}"
				oncomplete="#{rich:component('ea-user-social-profile-modal-Panel')}.show()">
				<rich:toolTip   styleClass="toolTipProfile2"  id="toolTipProfile2" for="userSocialProfileID1"
					value="Public Profile of #{attendeeListVar.firstName} #{attendeeListVar.lastName}">
				</rich:toolTip>
			</a4j:commandButton>
			<h:graphicImage value="/images/gray_profile.jpg"
				rendered="#{attendeeListVar.socialMediaDTO.faceBookId == null && attendeeListVar.socialMediaDTO.twitterId == null && attendeeListVar.socialMediaDTO.linkedInId == null}" />
			<rich:spacer width="5" />
			<a4j:commandButton style="cursor:hand;" image="/images/connect.jpg"
				actionListener="#{socialMediaBean.connectViaSMListener}"
				onclick="connectSocialMedia('#{attendeeListVar.profileId}','#{attendeeListVar.firstName} #{attendeeListVar.lastName}','#{attendeeListVar.socialMediaDTO.twitterFollow}','#{attendeeListVar.socialMediaDTO.faceBookFriendsConnect}','#{ attendeeListVar.socialMediaDTO.linkedInFriendsConnect}');"
				rendered="#{(attendeeListVar.socialMediaDTO.twitterFollow == true || attendeeListVar.socialMediaDTO.faceBookFriendsConnect == true || attendeeListVar.socialMediaDTO.linkedInFriendsConnect == true) && (attendeeListVar.currentUser== false)}"
				reRender="ea-socialMedia-invite-modal-Panel"
				oncomplete="#{rich:component('ea-socialMedia-invite-modal-Panel')}.show()" />

			<h:graphicImage value="/images/gray_connect.jpg"
				rendered="#{(attendeeListVar.socialMediaDTO.twitterFollow == false && attendeeListVar.socialMediaDTO.faceBookFriendsConnect == false && attendeeListVar.socialMediaDTO.linkedInFriendsConnect == false) && (attendeeListVar.currentUser== false)}" />
			<rich:spacer width="5" />
			<a4j:commandButton rendered="#{attendeeListVar.currentUser== false}"
				image="/images/mail_logo.jpg"
				actionListener="#{attendeeBean.initSendMail}"
				reRender="modalConnectMailPanel"
				onclick="connectViaEmail('#{attendeeListVar.profileId}','#{attendeeListVar.firstName} #{attendeeListVar.lastName}');"
				oncomplete="#{rich:component('modalConnectMailPanel')}.show()" />
		</rich:column>

	</rich:dataTable>

</rich:panel>
