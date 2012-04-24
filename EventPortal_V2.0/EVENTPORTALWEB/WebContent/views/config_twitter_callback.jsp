<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<html>
      <head>
            <title>Event Attend - Connect Twitter</title>
            <link href="../styles/styles_design.css" rel="stylesheet" type="text/css"/>
      </head>
<script type="text/javascript">

function closeWindow(){
   opener.reload();
   self.close();
}
	    	    
	  </script>
<style type="text/css">
.ea-social-network-panel{
vertical-align:middle;
width: 600px;
height: 400px;

}
.ea-social-network-col{
height:150px;

vertical-align:middle;
}
.ea-social-network-row1{
height:50px;
}
.ea-social-network-row2{
height:150px;

vertical-align:top;
}

</style>
 <body>
<f:view>
	<a4j:form id="linkedInForm">
<a4j:region id="twitterConnectRegion">
<a4j:poll id="twitterBeforeConnectPoll" reRender="ea-twitter-connect-panel-id" actionListener="#{socialMediaBean.configureTwitter}" interval="5000" enabled="#{socialMediaBean.socialMediaBeforeConnect}">

</a4j:poll>

<rich:panel styleClass="ea-social-network-panel" id="ea-twitter-connect-panel-id">
<f:facet name="header">
<h:outputText value="Twitter Connect"></h:outputText>
</f:facet>

<a4j:poll id="twitterAfterConnectPoll" reRender="message-panel,ea-profile-social-nw-panel-id,picture-panel,ea-twitter-connect-panel-id" actionListener="#{socialMediaBean.socialMediaConnectRefreshListener}" onsubmit="closeWindow();" interval="5000" enabled="#{socialMediaBean.socialMediaAfterConnect}">
</a4j:poll>

<h:panelGrid columns="1">
<h:panelGroup rendered="#{socialMediaBean.socialMediaBeforeConnect}"  id="ea-twitter-before-connect-panel-id">
<h:panelGrid columns="1" columnClasses="ea-social-network-row1,ea-social-network-row2">

<h:panelGroup>
<h:graphicImage value="/images/twitter_connect.jpg"></h:graphicImage>
<br/>
<br/>
<h:outputText styleClass="ea-content-class" value="Connecting Twitter... Please be patient!"></h:outputText>

</h:panelGroup>
<h:panelGroup>

<a4j:status id="twitterStatus" for="twitterConnectRegion">
                <f:facet name="start">
                    <h:graphicImage  value="/images/loading.gif" />
                </f:facet>
 </a4j:status>
 
</h:panelGroup>

</h:panelGrid>

</h:panelGroup>
<h:panelGroup rendered="#{socialMediaBean.socialMediaAfterConnect}" id="ea-twitter-after-connect-panel-id">
<h:panelGrid columns="1" columnClasses="ea-social-network-row1,ea-social-network-row2">

<h:panelGroup>
<h:graphicImage value="/images/twitter_connect.jpg"></h:graphicImage>
<br/>
<br/>
<h:outputText styleClass="ea-content-class" value="You have configured your Twitter account succesfully. You can now able to share messages or follow friends."></h:outputText>

</h:panelGroup>
<h:panelGroup>
<h:outputText styleClass="ea-content-class" value="You can safely "/>
<a4j:commandLink reRender="message-panel,ea-profile-social-nw-panel-id,picture-panel" actionListener="#{socialMediaBean.socialMediaConnectRefreshListener}" onclick="closeWindow();">Close</a4j:commandLink>

<h:outputText styleClass="ea-content-class" value=" this window if it is not closed automatically within 5 seconds."></h:outputText>
</h:panelGroup>

</h:panelGrid>

</h:panelGroup>

</h:panelGrid>


</rich:panel>
</a4j:region>
</a4j:form>	
	
</f:view>

</body>
</html>
