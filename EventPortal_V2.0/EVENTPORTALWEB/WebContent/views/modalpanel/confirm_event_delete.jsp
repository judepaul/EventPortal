<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<script type="text/javascript">

function submitEventIdForDelete(eventId){
	
	document.getElementById("eventDeleteForm:eventId").value = eventId;
	Richfaces.hideModalPanel('event-delete-modal-panel-id');
	
}
</script>

<rich:modalPanel width="378" height="148"  id="event-delete-modal-panel-id">

<f:facet name="header">
            <h:panelGroup>
                <h:outputText value="Delete - Confirm"></h:outputText>
            </h:panelGroup>
</f:facet>
<f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/images/close.png" styleClass="hidelink" id="deleteHidelink"/>
                <rich:componentControl for="event-delete-modal-panel-id" attachTo="deleteHidelink" operation="hide" event="onclick"/>
            </h:panelGroup>
</f:facet>
<a4j:form id="eventDeleteForm">
<h:panelGrid id="contentPanel" columns="1" columnClasses="ea-outer-col-class">

<h:inputHidden id="eventId" value="#{maintenanceBean.eventId}"></h:inputHidden>	
		<h:panelGroup>
		<h:outputText styleClass="ea-content-class" 
		value="Are you sure you want to delete the Event?" />
		
		</h:panelGroup>
		
		<h:panelGroup>	
		<h:panelGrid columns="4" columnClasses="footer-col1,footer-col2,footer-col3,footer-col4">
		<h:panelGroup>
			</h:panelGroup>
			<h:panelGroup>
			<a4j:commandButton value="Yes" actionListener="#{maintenanceBean.deleteEventListener}" reRender="manage-event-panel" onclick="submitEventIdForDelete('#{maintenanceBean.selectedEventId}')"></a4j:commandButton>
			<a4j:commandButton value="No" onclick="#{rich:component('event-delete-modal-panel-id')}.hide()"></a4j:commandButton>
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
     <rich:componentControl for="event-delete-modal-panel-id" attachTo="link" operation="hide" event="onclick"/>
    </h:outputLink>            
    </h:panelGroup>
</a4j:form>
</rich:modalPanel>
