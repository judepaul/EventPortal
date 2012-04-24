<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<script type="text/javascript">

function submitSessionIdForDelete(sessionId){
	
	document.getElementById("sessionDeleteForm:sessionId").value = sessionId;
	Richfaces.hideModalPanel('session-delete-modal-panel-id');
	
}
</script>

<rich:modalPanel width="378" height="130"  id="session-delete-modal-panel-id">

<f:facet name="header">
            <h:panelGroup>
                <h:outputText value="Delete - Confirm"></h:outputText>
            </h:panelGroup>
</f:facet>
<f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/images/close.png" styleClass="hidelink" id="sessionDeleteHidelink"/>
                <rich:componentControl for="session-delete-modal-panel-id" attachTo="sessionDeleteHidelink" operation="hide" event="onclick"/>
            </h:panelGroup>
</f:facet>
<a4j:form id="sessionDeleteForm">
<h:panelGrid id="contentPanel" columns="1" columnClasses="ea-outer-col-class">

<h:inputHidden id="sessionId" value="#{maintenanceBean.sessionId}"></h:inputHidden>	
		<h:panelGroup>
		<h:outputText styleClass="ea-content-class" 
		value="Are you sure you want to delete the Session?" />
		
		</h:panelGroup>
		
		<h:panelGroup>	
		<h:panelGrid columns="4" columnClasses="footer-col1,footer-col2,footer-col3,footer-col4">
		<h:panelGroup>
			</h:panelGroup>
			<h:panelGroup>
			<a4j:commandButton value="Yes" actionListener="#{maintenanceBean.deleteSessionListener}" reRender="manage-session-panel" onclick="submitSessionIdForDelete('#{maintenanceBean.selectedSessionId}')"></a4j:commandButton>
			<a4j:commandButton value="No" onclick="#{rich:component('session-delete-modal-panel-id')}.hide()"></a4j:commandButton>
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
     <rich:componentControl for="session-delete-modal-panel-id" attachTo="link" operation="hide" event="onclick"/>
    </h:outputLink>            
    </h:panelGroup>
</a4j:form>
</rich:modalPanel>
