<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<script type="text/javascript">

function submitSessionIdForAttend(eventId,sessionId){
	//alert("Confirm eventId==>"+eventId);
	//alert("Confirm sessionId==>"+sessionId);
	document.getElementById("attendConfirmForm:eventId").value = eventId;
	document.getElementById("attendConfirmForm:sessionId").value = sessionId;
	Richfaces.hideModalPanel('attend-confirm-modal-panel-id');
}
</script>

<rich:modalPanel width="378" height="148"  id="attend-confirm-modal-panel-id">

<f:facet name="header">
            <h:panelGroup>
                <h:outputText value="Attend - Confirm"></h:outputText>
            </h:panelGroup>
</f:facet>
<f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/images/close.png" styleClass="hidelink" id="hidelink"/>
                <rich:componentControl for="attend-confirm-modal-panel-id" attachTo="hidelink" operation="hide" event="onclick"/>
            </h:panelGroup>
</f:facet>
<a4j:form id="attendConfirmForm">
<h:panelGrid id="contentPanel" columns="1" columnClasses="ea-outer-col-class">	

<h:inputHidden id="sessionId" value="#{sessionBean.sessionId}"></h:inputHidden>	
<h:inputHidden id="eventId" value="#{sessionBean.eventId}"></h:inputHidden>	
		<h:panelGroup>
		<h:outputText styleClass="ea-content-class" 
		value="You are about to attend the live session. If you have not joined in this event , you will get automatically joined. Your online status will be shown to the co-attendees. Using this, you can comment and discuss the session details with the co-attendees. Are you sure you want to attend?" />
		
		</h:panelGroup>
		
		<h:panelGroup>	
		<h:panelGrid columns="4" columnClasses="footer-col1,footer-col2,footer-col3,footer-col4">
		<h:panelGroup>
			</h:panelGroup>
			<h:panelGroup>
			<a4j:commandButton value="Ok" action="#{sessionBean.populateSessionDetailsPage}" onclick="submitSessionIdForAttend('#{sessionBean.eventId}','#{sessionBean.sessionId}')"></a4j:commandButton>
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
        Cancel
     <rich:componentControl for="attend-confirm-modal-panel-id" attachTo="link" operation="hide" event="onclick"/>
    </h:outputLink>            
    </h:panelGroup>
</a4j:form>
</rich:modalPanel>
