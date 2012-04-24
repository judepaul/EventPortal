<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>

<style type="text/css" >

.ea-manage-session-panel{
	background: white;
	margin: 0 auto;
	width: 900px;
}

.ea-session-col1{

width: 350px;
vertical-align: top;
}

.ea-session-col2{

width: 550px;
vertical-align: top;
}

.ea-session-input-col1{

width: 150px;

}

.ea-session-input-col2{

width: 200px;

}

.ea-session-list-panel-body-class{

height:350px;
width:480px;
overflow:auto;
background: white;

}


</style>

<script type="text/javascript">

function selectedSession(sessionId){
	    //alert(sessionId);
		document.getElementById('manageDataForm:selectedSessionId').value = sessionId;
		document.getElementById('manageDataForm:selectedSessionIdButton').click();
}

</script>
<a4j:commandButton id="selectedSessionIdButton"
	actionListener="#{maintenanceBean.sessionListener}"  style="display:none;" reRender="manage-session-panel"></a4j:commandButton>
<h:inputHidden id="selectedSessionId"
	value="#{maintenanceBean.selectedSessionId}" ></h:inputHidden>
<rich:panel id="manage-session-panel" styleClass="ea-manage-session-panel">

<h:panelGrid rendered="#{maintenanceBean.showSessionList}" id="sessionListGridId"  columnClasses="ea-event-col2" columns="1">

		<h:panelGroup>
			<div style="height:400px ; width:850px; overflow-x:auto; overflow-y:scroll">
				<rich:dataTable rowKeyVar="idx" id="sessionList" columnClasses="col"
					value="#{maintenanceBean.sessionList}" var="sessionValues"
					onRowDblClick="selectedSession('#{sessionValues.sessionId}')">
					<f:facet name="header">
						<rich:columnGroup>
							<h:column>
								<h:outputText value="S.No"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Session"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Event "></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Start Date [Time]"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="End Date [Time]"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="KeyNote"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Venue"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Tag"></h:outputText>
							</h:column>
							
						</rich:columnGroup>
					</f:facet>

					<h:column>
						<h:outputText value="#{idx+1}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{sessionValues.sessionName}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{sessionValues.sessionEventName}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText
							value="#{sessionValues.sessionStartDate} [#{sessionValues.sessionStartTime}]"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText
							value="#{sessionValues.sessionEndDate} [#{sessionValues.sessionEndTime}]"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{sessionValues.sessionKeyNote}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{sessionValues.sessionVenueName}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{sessionValues.sessionTag}"></h:outputText>
					</h:column>
				</rich:dataTable>
			</div>
			
			
		</h:panelGroup>
<h:panelGroup>
	<rich:spacer width="300px"></rich:spacer>				<a4j:commandButton value="Add Session" actionListener="#{maintenanceBean.showSessionAddModifyListener}" reRender="manage-session-panel"></a4j:commandButton>
</h:panelGroup>

	</h:panelGrid>


	<h:panelGrid id="addModifySessionGridId"  rendered="#{!maintenanceBean.showSessionList}"  columnClasses="ea-event-col1" columns="1">

		<h:panelGroup>

			<h:panelGrid columnClasses="ea-event-input-col1,ea-event-input-col2"
				columns="2">
				<h:panelGroup>
					<h:outputText styleClass="ea-content-class" value="Session"></h:outputText>
				</h:panelGroup>
				<h:panelGroup>
					<h:inputText value="#{maintenanceBean.sessionName}"></h:inputText>					
				</h:panelGroup>

				<h:panelGroup>
					<h:outputText styleClass="ea-content-class"
						value="Event"></h:outputText>
				</h:panelGroup>
				<h:panelGroup>
					<h:selectOneMenu id="eventListBox" value="#{maintenanceBean.sessionEventId}">
						<f:selectItems value="#{maintenanceBean.eventMap}"/> 
						</h:selectOneMenu>
				</h:panelGroup>
				<h:panelGroup>
                              <h:outputText styleClass="ea-content-class" value="Start Date"></h:outputText>
                        </h:panelGroup>
                        <h:panelGroup>
                              <a4j:outputPanel id="startDate"layout="block">
                    <rich:calendar showApplyButton="true" datePattern='dd/M/yyyy hh:mm a' value="#{maintenanceBean.sessionStartDate}" cellWidth="24px"cellHeight="22px" style="width:200px"/>
            </a4j:outputPanel>
                        </h:panelGroup>
                        <h:panelGroup>
                              <h:outputText styleClass="ea-content-class" value="End Date"></h:outputText>
                        </h:panelGroup>
                        <h:panelGroup>
                              <a4j:outputPanel id="endDate"layout="block">
                    <rich:calendar showApplyButton="true"  datePattern='dd/M/yyyy hh:mm a' value="#{maintenanceBean.sessionEndDate}" cellWidth="24px"cellHeight="22px" style="width:200px"/>
            </a4j:outputPanel>
                        </h:panelGroup>

				<h:panelGroup>
					<h:outputText styleClass="ea-content-class" value="KeyNote"></h:outputText>
				</h:panelGroup>
				<h:panelGroup>
			<h:inputTextarea value="#{maintenanceBean.sessionKeyNote}"></h:inputTextarea>
				</h:panelGroup>
				<h:panelGroup>
					<h:outputText styleClass="ea-content-class" value="Venue"></h:outputText>
				</h:panelGroup>
				<h:panelGroup>
					<h:inputText value="#{maintenanceBean.sessionVenueName}"></h:inputText>
				</h:panelGroup>			
				<h:panelGroup>
					<h:outputText styleClass="ea-content-class" value="Tag"></h:outputText>
				</h:panelGroup>
				<h:panelGroup>
					<h:inputText value="#{maintenanceBean.sessionTag}"></h:inputText>
				</h:panelGroup>			
				<h:panelGroup>
				</h:panelGroup>
				<h:panelGroup>
					<a4j:commandButton value="Add" reRender="manage-session-panel" rendered="#{maintenanceBean.addButtonEnable == true}"
						actionListener="#{maintenanceBean.addSessionListener}"></a4j:commandButton>
					<a4j:commandButton value="Modify" reRender="manage-session-panel" rendered="#{maintenanceBean.addButtonEnable == false}"
						actionListener="#{maintenanceBean.updateSessionListener}"></a4j:commandButton>				
				<a4j:commandButton id="sessionDeleteButton" value="Delete" rendered="#{maintenanceBean.addButtonEnable == false}" actionListener="#{maintenanceBean.deleteSessionConfirmListener}" reRender="session-delete-modal-panel-id"  oncomplete="#{rich:component('session-delete-modal-panel-id')}.show()" >
<a4j:actionparam name="eventSessionEvent" value="#{maintenanceBean.selectedSessionId}" assignTo="#{maintenanceBean.sessionId}"/>
</a4j:commandButton>
				
				<a4j:commandButton value="Cancel" actionListener="#{maintenanceBean.showSessionListListener}" reRender="manage-session-panel"></a4j:commandButton>
						
				
				
				</h:panelGroup>

			</h:panelGrid>

		</h:panelGroup>

	</h:panelGrid>

</rich:panel>

