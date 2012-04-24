<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>

<script src="../scripts/CommonScript.js" language="javascript">
</script>


<style type="text/css" >
.ea-profile-social-nw-panel{	
	background: white;	
	width: 900px;
	height: 300px;
}
.ea-profile-social-nw-row1{
height: 90px;

}

.ea-profile-social-nw-row2{
height: 10px;
}

.ea-profile-social-nw-row3{
height: 90px;
}
.ea-profile-social-nw-row4{
height: 10px;
}
.ea-profile-social-nw-row5{
height: 90px;
}
.ea-profile-social-nw-row6{
height: 10px;
}
.ea-profile-twitter-col1{
width: 200px;
}
.ea-profile-twitter-col2{
width: 200px;
}
.ea-profile-twitter-col3{
width: 200px;
}
.ea-profile-twitter-col4{
width: 200px;
}
.tooltip {
    background:#ECFFFF;
   border: 2px solid #06266F;  
   padding:5px;    
   -moz-border-radius: 10px;    /* Rounded border mozilla browsers */
   -webkit-border-radius: 10px; /* Rounded border webkit browsers */
   border-radius: 10px; 
   
}

</style>

<script type="text/javascript" >
function openPopUp(url){
	  popup(url);	
}

function reload(){
	document.getElementById('profileForm:refreshSocialNetworkId').click(); 
}
</script>

<rich:panel styleClass="ea-profile-social-nw-panel" id="ea-profile-social-nw-panel-id">
<a4j:commandButton id="refreshSocialNetworkId" reRender="ea-profile-social-nw-panel-id,socialMediaImagePanel" value="" style="display:none;" actionListener="#{socialMediaBean.socialMediaConnectListener}"/>
<h:panelGrid columns="1" rowClasses="ea-profile-social-nw-row1,ea-profile-social-nw-row2,ea-profile-social-nw-row3,ea-profile-social-nw-row4,ea-profile-social-nw-row5,ea-profile-social-nw-row6">

<h:panelGroup id="twitterConnectGroupId">

<h:panelGrid columns="4" columnClasses="ea-profile-twitter-col1,ea-profile-twitter-col2,ea-profile-twitter-col3,ea-profile-twitter-col4">
<h:panelGroup  id="configTweet">
<a4j:commandButton  rendered="#{!socialMediaBean.twitterConnected}" actionListener="#{socialMediaBean.socialMediaConnectInitListener}" onclick="openPopUp('#{TWITTERAUTHURL}')"  image="/images/twitter_connect.jpg">
  			
			
<a4j:actionparam name="currentMedia" value="Twitter" assignTo="#{socialMediaBean.currentMedia}"/>
</a4j:commandButton>
<h:graphicImage rendered="#{socialMediaBean.twitterConnected}" value="/images/twitter_connect_disable.jpg"/>
<rich:toolTip styleClass="tooltip"  for="configTweet" value="#{socialMediaBean.twitterConnectedToolTip}"/>
<br/>
<a4j:commandLink  rendered="#{socialMediaBean.twitterPubUrl!=null}"   value="Public profile @#{socialMediaBean.twitterId}" onclick="popup('#{socialMediaBean.twitterPubUrl}')" />									            

</h:panelGroup>
<h:panelGroup>
<h:selectBooleanCheckbox rendered="#{socialMediaBean.twitterConnected}" value="#{userBean.twtShowTweets}"></h:selectBooleanCheckbox>
<h:outputText rendered="#{socialMediaBean.twitterConnected}" styleClass="ea-content-class" value="Share Tweets"></h:outputText>
</h:panelGroup>
<h:panelGroup>
<h:selectBooleanCheckbox rendered="#{socialMediaBean.twitterConnected}" value="#{userBean.twtAllowFriends}"></h:selectBooleanCheckbox>
<h:outputText rendered="#{socialMediaBean.twitterConnected}" styleClass="ea-content-class" value="Allow Friends to Follow"></h:outputText>
</h:panelGroup>

<h:panelGroup>
<a4j:commandLink value="Disconnect Twitter" rendered="#{socialMediaBean.twitterConnected}"  reRender="social-media-disconnect-confirm-modal-panel-id" actionListener="#{socialMediaBean.socialMediaDisConnectListener}" oncomplete="#{rich:component('social-media-disconnect-confirm-modal-panel-id')}.show()">
<a4j:actionparam name="currentMedia" value="Twitter" assignTo="#{socialMediaBean.currentMedia}"/>
</a4j:commandLink>

</h:panelGroup>
</h:panelGrid>

</h:panelGroup>

<h:panelGroup>
<rich:separator align="center" height="2" lineType="solid" width="700"></rich:separator>
</h:panelGroup>

<h:panelGroup id="faceBookConnectGroupId">

<h:panelGrid columns="4" columnClasses="ea-profile-twitter-col1,ea-profile-twitter-col2,ea-profile-twitter-col3,ea-profile-twitter-col4">
<h:panelGroup id="configFB">
<a4j:commandButton rendered="#{!socialMediaBean.faceBookConnected}" actionListener="#{socialMediaBean.socialMediaConnectInitListener}" onclick="openPopUp('#{FACEBOOKAUTHURL}')"  image="/images/facebook_connect.jpg">
<a4j:actionparam name="currentMedia" value="FaceBook" assignTo="#{socialMediaBean.currentMedia}"/>
</a4j:commandButton>
<h:graphicImage rendered="#{socialMediaBean.faceBookConnected}" value="/images/facebook_connect_disable.jpg"/>
<rich:toolTip styleClass="tooltip"  for="configFB" value="#{socialMediaBean.faceBookConnectedToolTip}"/>
<br/>
<a4j:commandLink rendered="#{socialMediaBean.facebookPubUrl!=null}" value="Public profile" onclick="popup('#{socialMediaBean.facebookPubUrl}')" />									            

</h:panelGroup>
<h:panelGroup>
<h:selectBooleanCheckbox rendered="#{socialMediaBean.faceBookConnected}" value="#{userBean.fbAllowFriendsToPost}"></h:selectBooleanCheckbox>
<h:outputText rendered="#{socialMediaBean.faceBookConnected}" styleClass="ea-content-class" value="Allow Friends to Post"></h:outputText>
</h:panelGroup>
<h:panelGroup>
<h:selectBooleanCheckbox rendered="#{socialMediaBean.faceBookConnected}" value="#{userBean.fbAllowFriendsToConnect}"></h:selectBooleanCheckbox>
<h:outputText rendered="#{socialMediaBean.faceBookConnected}" styleClass="ea-content-class" value="Allow Friends to Connect"></h:outputText>
</h:panelGroup>

<h:panelGroup>

<a4j:commandLink value="Disconnect FaceBook" rendered="#{socialMediaBean.faceBookConnected}"  reRender="social-media-disconnect-confirm-modal-panel-id" actionListener="#{socialMediaBean.socialMediaDisConnectListener}" oncomplete="#{rich:component('social-media-disconnect-confirm-modal-panel-id')}.show()">
<a4j:actionparam name="currentMedia" value="FaceBook" assignTo="#{socialMediaBean.currentMedia}"/>
</a4j:commandLink>

</h:panelGroup>
</h:panelGrid>

</h:panelGroup>

<h:panelGroup>
<rich:separator align="center" height="2" lineType="solid" width="700"></rich:separator>
</h:panelGroup>

<h:panelGroup id="linkedInConnectGroupId">

<h:panelGrid columns="4" columnClasses="ea-profile-twitter-col1,ea-profile-twitter-col2,ea-profile-twitter-col3,ea-profile-twitter-col4">
<h:panelGroup id="configLin">
<a4j:commandButton rendered="#{!socialMediaBean.linkedInConnected}" actionListener="#{socialMediaBean.socialMediaConnectInitListener}" onclick="openPopUp('#{LINKEDINAUTHURL}')"  image="/images/linkedin_connect.jpg">
<a4j:actionparam name="currentMedia" value="LinkedIn" assignTo="#{socialMediaBean.currentMedia}"/>
</a4j:commandButton>
<h:graphicImage rendered="#{socialMediaBean.linkedInConnected}" value="/images/linkedin_connect_disable.jpg"/>
<rich:toolTip styleClass="tooltip"  for="configLin" value="#{socialMediaBean.linkedInConnectedToolTip}"/>
<br/>
<a4j:commandLink rendered="#{socialMediaBean.linkedInPubUrl!=null}" value="Public profile" onclick="popup('#{socialMediaBean.linkedInPubUrl}')" />									            

</h:panelGroup>
<h:panelGroup>
<h:selectBooleanCheckbox rendered="#{socialMediaBean.linkedInConnected}" value="#{userBean.liAllowFriendsToPost}"></h:selectBooleanCheckbox>
<h:outputText rendered="#{socialMediaBean.linkedInConnected}" styleClass="ea-content-class" value="Allow Friends to Post"></h:outputText>
</h:panelGroup>
<h:panelGroup>
<h:selectBooleanCheckbox rendered="#{socialMediaBean.linkedInConnected}" value="#{userBean.liAllowFriendsToFollow}"></h:selectBooleanCheckbox>
<h:outputText rendered="#{socialMediaBean.linkedInConnected}" styleClass="ea-content-class" value="Allow Friends to Follow"></h:outputText>
</h:panelGroup>

<h:panelGroup>
<a4j:commandLink value="Disconnect LinkedIn" rendered="#{socialMediaBean.linkedInConnected}"  reRender="social-media-disconnect-confirm-modal-panel-id" actionListener="#{socialMediaBean.socialMediaDisConnectListener}" oncomplete="#{rich:component('social-media-disconnect-confirm-modal-panel-id')}.show()">
<a4j:actionparam name="currentMedia" value="LinkedIn" assignTo="#{socialMediaBean.currentMedia}"/>
</a4j:commandLink>
</h:panelGroup>
</h:panelGrid>

</h:panelGroup>

<h:panelGroup>

</h:panelGroup>

</h:panelGrid>
</rich:panel>


								
