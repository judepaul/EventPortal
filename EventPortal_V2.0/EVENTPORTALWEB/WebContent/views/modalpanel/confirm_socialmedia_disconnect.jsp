<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>


<a4j:form id="socialMediaDisconnectConfirmForm">
<rich:modalPanel width="378" height="148"  id="social-media-disconnect-confirm-modal-panel-id">

<f:facet name="header">
            <h:panelGroup>
                <h:outputText value="#{socialMediaBean.currentMedia} Disconnect - Confirm"></h:outputText>
            </h:panelGroup>
</f:facet>
<f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/images/close.png" styleClass="hidelink" id="hidelink"/>
                <rich:componentControl for="social-media-disconnect-confirm-modal-panel-id" attachTo="hidelink" operation="hide" event="onclick"/>
            </h:panelGroup>
</f:facet>

<h:panelGrid id="contentPanel" columns="1" columnClasses="ea-outer-col-class">		
		<h:panelGroup>
		
		<h:outputText rendered="#{socialMediaBean.currentMedia == 'Twitter'}" styleClass="ea-content-class" 
		value="You are about to disconnect @#{socialMediaBean.twitterId}. You cannot able to share messages or follow friends in Twitter. Are you sure you want to disconnect?" />
		
		<h:outputText rendered="#{socialMediaBean.currentMedia == 'FaceBook'}" styleClass="ea-content-class" 
		value="You are about to disconnect FaceBook. You cannot able to post messages or make friends in FaceBook. Are you sure you want to disconnect?" />
		
				
		<h:outputText rendered="#{socialMediaBean.currentMedia == 'LinkedIn'}" styleClass="ea-content-class" 
		value="You are about to disconnect LinkedIn. You cannot able to post messages or connect friends in LinkedIn. Are you sure you want to disconnect?" />
		
		</h:panelGroup>
		
		<h:panelGroup>	
		<h:panelGrid columns="4" columnClasses="footer-col1,footer-col2,footer-col3,footer-col4">
		<h:panelGroup>
			</h:panelGroup>
			<h:panelGroup>
			
			<a4j:commandButton rendered="#{socialMediaBean.currentMedia == 'Twitter'}"  value="Yes" reRender="twitterConnectGroupId,picture-panel,message-panel" actionListener="#{socialMediaBean.disconnectTwitter}" onclick="#{rich:component('social-media-disconnect-confirm-modal-panel-id')}.hide()"></a4j:commandButton>
			
			<a4j:commandButton rendered="#{socialMediaBean.currentMedia == 'FaceBook'}"  value="Yes" reRender="faceBookConnectGroupId,picture-panel,message-panel" actionListener="#{socialMediaBean.disconnectFaceBook}" onclick="#{rich:component('social-media-disconnect-confirm-modal-panel-id')}.hide()"></a4j:commandButton>
			
			<a4j:commandButton rendered="#{socialMediaBean.currentMedia == 'LinkedIn'}"  value="Yes" reRender="linkedInConnectGroupId,picture-panel,message-panel" actionListener="#{socialMediaBean.disconnectLinkedIn}" onclick="#{rich:component('social-media-disconnect-confirm-modal-panel-id')}.hide()"></a4j:commandButton>
			
			<a4j:commandButton value="No" onclick="#{rich:component('social-media-disconnect-confirm-modal-panel-id')}.hide()"></a4j:commandButton>
			</h:panelGroup>
			<h:panelGroup>
			</h:panelGroup>	
			<h:panelGroup>
			</h:panelGroup>		
			</h:panelGrid>
		</h:panelGroup>
</h:panelGrid>
            <h:panelGroup>
 	<h:outputLink value="#" id="link">
        close 
     <rich:componentControl for="social-media-disconnect-confirm-modal-panel-id" attachTo="link" operation="hide" event="onclick"/>
    </h:outputLink>            
    </h:panelGroup>

</rich:modalPanel>
</a4j:form>