<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>

<style type="text/css">

.ea-speaker-info-outer-grid-row1{

height: 10px;

}

.ea-speaker-info-outer-grid-row2{
vertical-align:top;
height: 250px;

}
.ea-speaker-info-outer-grid-row3{

height: 10px;
/*background:green;*/
}

.ea-speaker-info-header-col1{
width:490px;
padding: 0;
margin: 0;
border: 0;

}
.ea-speaker-info-header-col2{
width:10px;
padding: 0;
margin: 0;
border: 0;
text-align: right;

}

.ea-speaker-info-footer-col1{
width:470px;
padding: 0;
margin: 0;
border: 0;

}

.ea-speaker-info-footer-col2{
width:20px;
padding: 0;
margin: 0;
border: 0;

}

.ea-speaker-info-body-col1{
/*background: blue;*/
width:5px;
padding: 0;
margin: 0;
border: 0;

}

.ea-speaker-info-body-col2{
/*background: olive;*/
width:495px;
padding: 0;
margin: 0;
border: 0;

}
.ea-speaker-info-body-panel-body-class{

height:230px;
overflow:auto;

}

</style>
<script type="text/javascript">

function inviteInSocialMedia(profileIdToConnect,twitterCheck,faceBookCheck,linkedInCheck){
	//alert(twitterCheck+"  "+faceBookCheck+" "+linkedInCheck);
		document.getElementById('socialMediaInviteForm:profileIdToConnect').value = profileIdToConnect;
	document.getElementById('socialMediaInviteForm:twitterCheck').value = twitterCheck;
	document.getElementById('socialMediaInviteForm:faceBookCheck').value = faceBookCheck;
	document.getElementById('socialMediaInviteForm:linkedInCheck').value = linkedInCheck;
	
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

</script>

<rich:modalPanel style="padding:0;margin:0;" width="520" height="330" id="ea-socialMedia-invite-modal-Panel">
<f:facet name="header">
            <h:panelGroup>
                <h:outputText value="Get Connected in Social Media with #{socialMediaBean.profileName}"></h:outputText>
            </h:panelGroup>
</f:facet>

<f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/images/close.png" styleClass="hidelink" id="hidelink-invite"/>
                <rich:componentControl for="ea-socialMedia-invite-modal-Panel" attachTo="hidelink-invite" operation="hide" event="onclick"/>
            </h:panelGroup>
</f:facet>
<a4j:form id="socialMediaInviteForm">
<h:inputHidden id="twitterCheck" value="#{socialMediaBean.twitterCheck}"></h:inputHidden>
<h:inputHidden id="faceBookCheck" value="#{socialMediaBean.faceBookCheck}"></h:inputHidden>
<h:inputHidden id="linkedInCheck" value="#{socialMediaBean.linkedInCheck}"></h:inputHidden>
<h:inputHidden id="profileIdToConnect" value="#{socialMediaBean.profileIdToConnect}"></h:inputHidden>

<!-- Body Part -->
			<h:panelGroup>
			<h:panelGrid columnClasses="ea-speaker-info-body-col1, ea-speaker-info-body-col2" columns="2">
			<h:panelGroup>
			
			</h:panelGroup>
					
		

			
				<h:panelGroup>
							
			<rich:panel  bodyClass="ea-session-info-body-panel-body-class">
				  <h:panelGrid columns="1"> 
				    <h:panelGroup>
                    <h:outputLabel styleClass="ea-content-class" 
                     rendered="#{socialMediaBean.alreadyFriendInFB==true}" value=" #{socialMediaBean.profileName} friend in "></h:outputLabel>
                    <h:column rendered="#{socialMediaBean.faceBookFriendsConnect==true}">
              <!-- <h:selectBooleanCheckbox  rendered="#{socialMediaBean.userFaceBookConnect==true && socialMediaBean.alreadyFriendInFB==false && socialMediaBean.faceBookDTO.friendAlreadyReqYou==false}" value="#{socialMediaBean.faceBookCheck}" />-->    
          			</h:column>	
                    <h:column  rendered="#{socialMediaBean.faceBookFriendsConnect==true}">
                    <h:graphicImage id="fbId"  value="/images/facebook_logo.jpg">
                    <a4j:commandLink rendered="#{socialMediaBean.faceBookDTO.friendAlreadyReqYou==false && socialMediaBean.alreadyFriendInFB==false}" value="Add #{socialMediaBean.profileName} as Friend in" onclick="popup('http://www.facebook.com/addfriend.php?id=#{socialMediaBean.faceBookDTO.profileId}')" />
    	             <h:outputLabel styleClass="ea-content-class" 
    	              rendered="#{socialMediaBean.faceBookDTO.friendAlreadyReqYou==true}" value="#{socialMediaBean.profileName} request pending."></h:outputLabel>
    	            <a4j:commandLink rendered="#{socialMediaBean.faceBookDTO.friendAlreadyReqYou==true}" value=" Approve it" onclick="popup('http://www.facebook.com')" />
    	              
    	         
                    </h:graphicImage>
                    </h:column>
                    <h:column rendered="#{socialMediaBean.faceBookFriendsConnect==true}">
					<h:outputLabel styleClass="ea-content-class" 
					 rendered="#{socialMediaBean.userFaceBookConnect==false}" value="To make #{socialMediaBean.profileName} as your friend, connect your's facebook account in EventAttend"></h:outputLabel>                      
                    </h:column>
                    </h:panelGroup>
 
				    <h:panelGroup>
				    <h:outputLabel styleClass="ea-content-class" 
				     rendered="#{socialMediaBean.alreadyFollowingInTwitter==true}" value="#{socialMediaBean.twitterDTO.alreadyFollowingInTwitterMsg}"></h:outputLabel>                      
                    <h:column rendered="#{socialMediaBean.twitterCheckBoxRenderer}">
                    <h:selectBooleanCheckbox  onselect="#{socialMediaBean.userTwitterConnected==true}" value="#{socialMediaBean.twitterCheck}"/>                    
                    </h:column>
                    <h:column rendered="#{socialMediaBean.twitterFollow==true}">
 
                    <h:graphicImage  id="tId"  value="/images/twitter_logo.jpg">
                     </h:graphicImage>
                    </h:column>
                    <h:column rendered="#{socialMediaBean.twitterFollow==true}">
					<h:outputLabel styleClass="ea-content-class" 
					rendered="#{socialMediaBean.userTwitterConnected==false}" value="To follow  #{socialMediaBean.profileName} in twitter, connect your's twitter account in EventAttend"></h:outputLabel>                      
                    </h:column>
                     <h:column rendered="#{socialMediaBean.twitterDTO.friendIsFollowing==true && socialMediaBean.twitterFollow==true}">
					<h:outputLabel styleClass="ea-content-class" 
					 rendered="#{socialMediaBean.twitterDTO.friendIsFollowing==true && socialMediaBean.twitterFollow==true}" value=" [ #{socialMediaBean.profileName} is following you ] "></h:outputLabel>                      
                    </h:column>
                    </h:panelGroup>
                     
                    <h:panelGroup >
                    <h:outputLabel styleClass="ea-content-class" 
                    rendered="#{socialMediaBean.alreadyFriendInLin==true}" value=" #{socialMediaBean.profileName} connected with you in "></h:outputLabel>
                    <h:column  rendered="#{socialMediaBean.linkedinFriendsConnect==true}"> 
                    <h:selectBooleanCheckbox rendered="#{socialMediaBean.linkedInCheckBoxRenderer}" value="#{socialMediaBean.linkedInCheck}" />
                    </h:column>
                    <h:column rendered="#{socialMediaBean.linkedinFriendsConnect==true}">
                    <h:outputLabel styleClass="ea-content-class" 
                      rendered="#{socialMediaBean.alreadyInviteInLin==true}" value="Your request pending in "></h:outputLabel>
                    <h:outputLabel styleClass="ea-content-class" 
                     rendered="#{socialMediaBean.requestPending==true}" value="#{socialMediaBean.profileName} requested you."></h:outputLabel>
                    <a4j:commandLink rendered="#{socialMediaBean.requestPending==true}" value="Approve it" onclick="popup('https://www.linkedin.com/secure/login')" />
    	      
                    
                    <h:graphicImage  id="linId" value="/images/linkedin_logo.jpg">
                 
                    </h:graphicImage>
                    </h:column>
                    <h:column  rendered="#{socialMediaBean.linkedinFriendsConnect==true}">
					<h:outputLabel styleClass="ea-content-class" 
					  rendered="#{socialMediaBean.userLinedInFriendsConnect==false}" value="To make connection with #{socialMediaBean.profileName}, connect your's linkedIn account in EventAttend"></h:outputLabel>                      
                    </h:column>
                    </h:panelGroup>
                     </h:panelGrid>
 					  	<a4j:commandButton value="Invite" id="invite"  onclick="inviteInSocialMedia('#{socialMediaBean.profileIdToConnect}','#{socialMediaBean.twitterCheck}','#{socialMediaBean.faceBookCheck}','#{socialMediaBean.linkedInCheck}')" rendered="#{socialMediaBean.inviteButtonRenderer}"  actionListener="#{socialMediaBean.inviteFriends}"  oncomplete="Richfaces.hideModalPanel('ea-socialMedia-invite-modal-Panel')">
 	     			<!--<rich:toolTip id="inviteToolTip" for="invite" value="To follow in twitter and to send connection request in LinkedIn"/>-->
   					</a4j:commandButton>
			         </rich:panel>
                     </h:panelGroup>   
                      <h:panelGroup>
                      
                      
                      </h:panelGroup>
                   </h:panelGrid>		
			
			</h:panelGroup>
			<h:panelGroup>
 	<h:outputLink value="#" id="link">
        close 
     <rich:componentControl for="ea-socialMedia-invite-modal-Panel" attachTo="link" operation="hide" event="onclick"/>
    </h:outputLink>            
    </h:panelGroup>
			
			
					</a4j:form>
			</rich:modalPanel>
	
			
	