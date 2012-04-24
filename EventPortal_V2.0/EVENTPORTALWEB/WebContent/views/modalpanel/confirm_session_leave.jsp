<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>


<script type="text/javascript">

function submitSessionIdForLeave(sessionId){
	document.getElementById("leaveConfirmForm:sessionId").value = sessionId;
	Richfaces.hideModalPanel('leave-confirm-modal-panel-id');
}
</script>


<rich:modalPanel width="378" height="148"  id="leave-confirm-modal-panel-id">

<f:facet name="header">
            <h:panelGroup>
                <h:outputText value="Leave Session - Confirm"></h:outputText>
            </h:panelGroup>
</f:facet>
<f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/images/close.png" styleClass="hidelink" id="leaveConfirmhidelink"/>
                <rich:componentControl for="leave-confirm-modal-panel-id" attachTo="leaveConfirmhidelink" operation="hide" event="onclick"/>
            </h:panelGroup>
</f:facet>
<a4j:form id="leaveConfirmForm">
<h:panelGrid id="contentPanel" columns="1" columnClasses="ea-outer-col-class">	
<h:inputHidden id="sessionId" value="#{sessionBean.sessionId}"></h:inputHidden>		
		<h:panelGroup>
		<h:outputText styleClass="ea-content-class" 
		value="You are about to leave the live session. Your status will be changed to offline and you cannot comment and discuss the session details with the co-attendees. Are you sure you want to leave?" />
		
		</h:panelGroup>
		
		<h:panelGroup>	
		<h:panelGrid columns="4" columnClasses="footer-col1,footer-col2,footer-col3,footer-col4">
		<h:panelGroup>
			</h:panelGroup>
			<h:panelGroup>
			<a4j:commandButton rendered="#{applicationBean.linkClicked =='LOGOUT'}" value="Ok" action="#{sessionBean.leaveLiveSessionForLogOut}" onclick="submitSessionIdForLeave('#{sessionBean.sessionId}')"></a4j:commandButton>
			<a4j:commandButton rendered="#{applicationBean.linkClicked =='PROFILE'}" value="Ok" action="#{sessionBean.leaveLiveSessionForProfile}"  onclick="submitSessionIdForLeave('#{sessionBean.sessionId}')"></a4j:commandButton>
			<a4j:commandButton rendered="#{applicationBean.linkClicked =='LIVESESSION'}" value="Ok" action="#{sessionBean.leaveLiveSession}"  onclick="submitSessionIdForLeave('#{sessionBean.sessionId}')"></a4j:commandButton>
			<a4j:commandButton rendered="#{applicationBean.linkClicked =='EVENTS'}" value="Ok" action="#{sessionBean.leaveLiveSessionForEvents}" onclick="submitSessionIdForLeave('#{sessionBean.sessionId}')"></a4j:commandButton>
			<a4j:commandButton rendered="#{applicationBean.linkClicked =='EVENT'}" value="Ok" action="#{sessionBean.leaveLiveSession}"  onclick="submitSessionIdForLeave('#{sessionBean.sessionId}')"></a4j:commandButton>
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
     <rich:componentControl for="leave-confirm-modal-panel-id" attachTo="link" operation="hide" event="onclick"/>
    </h:outputLink>            
    </h:panelGroup>
</a4j:form>
</rich:modalPanel>
