<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<link href="../styles/styles.css" rel="stylesheet" type="text/css" />

<style type="text/css">
.ea-manage-event-panel {
      background: white;
      margin: 0 auto;
      width: 900px;
}

.ea-event-col1 {
      
width: 400px;
vertical-align: top;
}

.ea-event-col2 {
      width: 850px;
vertical-align: top;
}

.ea-event-input-col1 {
      width: 150px;
}

.ea-event-input-col2 {
      width: 200px;
}

.ea-event-list-panel-body-class {
      height: 350px;
      width: 480px;
      overflow: auto;
      background: white;
}
</style>


<script type="text/javascript">
function selectedEvent(eventId){
	   // alert(eventId);
		document.getElementById('manageDataForm:selectedEventId').value = eventId;
		document.getElementById('manageDataForm:selectedEventIdButton').click();
}

</script>
<a4j:commandButton id="selectedEventIdButton"
	actionListener="#{maintenanceBean.eventListener}" reRender="manage-event-panel"  style="display:none;"></a4j:commandButton>
<h:inputHidden id="selectedEventId"
	value="#{maintenanceBean.selectedEventId}" ></h:inputHidden>

<rich:panel id="manage-event-panel" styleClass="ea-manage-event-panel">

<h:panelGrid rendered="#{maintenanceBean.showEventList}" id="eventListGridId"  columnClasses="ea-event-col2" columns="1">

		<h:panelGroup>
			<div style="height:400px ; width:850px; overflow-x:auto; overflow-y:scroll">
				<rich:dataTable rowKeyVar="idx" id="eventList" columnClasses="col"
					value="#{maintenanceBean.eventsList}" var="eventValues"
					onRowDblClick="selectedEvent('#{eventValues.eventId}')">
					<f:facet name="header">
						<rich:columnGroup>
							<h:column>
								<h:outputText value="S.No"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Event"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Description"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Start Date [Time]"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="End Date [Time]"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Time Zone"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Organized By"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Location"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Country"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="City"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="State"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Website"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="No Of Days"></h:outputText>
							</h:column>
						</rich:columnGroup>
					</f:facet>

					<h:column>
						<h:outputText value="#{idx+1}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{eventValues.eventName}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{eventValues.eventDescription}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText
							value="#{eventValues.eventStartDate} [#{eventValues.eventStartTime}]"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText
							value="#{eventValues.eventEndDate} [#{eventValues.eventEndTime}]"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{eventValues.eventTimeZone}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{eventValues.eventOrganizedBy}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{eventValues.eventLocation}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{eventValues.eventCountry}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{eventValues.eventCity}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{eventValues.eventState}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{eventValues.eventWebsite}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{eventValues.eventNoOfDays}"></h:outputText>
					</h:column>



				</rich:dataTable>
			</div>
			
			
		</h:panelGroup>
<h:panelGroup>
	<rich:spacer width="300px"></rich:spacer>				<a4j:commandButton value="Add Event" actionListener="#{maintenanceBean.showAddModifyListener}" reRender="manage-event-panel"></a4j:commandButton>
</h:panelGroup>

	</h:panelGrid>


	<h:panelGrid id="addModifyGridId"  rendered="#{!maintenanceBean.showEventList}"  columnClasses="ea-event-col1" columns="1">

		<h:panelGroup>

			<h:panelGrid columnClasses="ea-event-input-col1,ea-event-input-col2"
				columns="2">
				<h:panelGroup>
					<h:outputText styleClass="ea-content-class" value="Event Name"></h:outputText>
				</h:panelGroup>
				<h:panelGroup>
					<h:inputText value="#{maintenanceBean.eventName}"></h:inputText>
				</h:panelGroup>

				<h:panelGroup>
					<h:outputText styleClass="ea-content-class"
						value="Event Description"></h:outputText>
				</h:panelGroup>
				<h:panelGroup>
					<h:inputTextarea value="#{maintenanceBean.eventDescription}"></h:inputTextarea>
				</h:panelGroup>
				<h:panelGroup>
                              <h:outputText styleClass="ea-content-class" value="Start Date"></h:outputText>
                        </h:panelGroup>
                        <h:panelGroup>
                              <a4j:outputPanel id="sessionStartDate"layout="block">
                    <rich:calendar showApplyButton="true"  datePattern='dd/M/yyyy hh:mm a' value="#{maintenanceBean.eventStartDate}" cellWidth="24px"cellHeight="22px" style="width:200px"/>
            </a4j:outputPanel>
                        </h:panelGroup>
                        <h:panelGroup>
                              <h:outputText styleClass="ea-content-class" value="End Date"></h:outputText>
                        </h:panelGroup>
                        <h:panelGroup>
                              <a4j:outputPanel id="sessionEndDate"layout="block">
                    <rich:calendar showApplyButton="true"  datePattern='dd/M/yyyy hh:mm a' value="#{maintenanceBean.eventEndDate}" cellWidth="24px"cellHeight="22px" style="width:200px"/>
            </a4j:outputPanel>
                        </h:panelGroup>

				<h:panelGroup>
					<h:outputText styleClass="ea-content-class" value="Location"></h:outputText>
				</h:panelGroup>
				<h:panelGroup>
					<h:inputText value="#{maintenanceBean.eventLocation}"></h:inputText>
				</h:panelGroup>
				<h:panelGroup>
					<h:outputText styleClass="ea-content-class" value="OrganizedBy"></h:outputText>
				</h:panelGroup>
				<h:panelGroup>
					<h:inputText value="#{maintenanceBean.eventOrganizedBy}"></h:inputText>
				</h:panelGroup>
				<h:panelGroup>
					<h:outputText styleClass="ea-content-class" value="Website"></h:outputText>
				</h:panelGroup>
				<h:panelGroup>
					<h:inputText value="#{maintenanceBean.eventWebsite}"></h:inputText>
				</h:panelGroup>
				<h:panelGroup>
					<h:outputText styleClass="ea-content-class" value="Tag"></h:outputText>
				</h:panelGroup>
				<h:panelGroup>
					<h:inputText value="#{maintenanceBean.eventTag}"></h:inputText>
				</h:panelGroup>
				<h:panelGroup>
					<h:outputText styleClass="ea-content-class" value="Country"></h:outputText>
				</h:panelGroup>
				<h:panelGroup>
					<h:inputText value="#{maintenanceBean.eventCountry}"></h:inputText>
				</h:panelGroup>
				<h:panelGroup>
					<h:outputText styleClass="ea-content-class" value="City"></h:outputText>
				</h:panelGroup>
				<h:panelGroup>
					<h:inputText value="#{maintenanceBean.eventCity}"></h:inputText>
				</h:panelGroup>
				<h:panelGroup>
					<h:outputText styleClass="ea-content-class" value="State"></h:outputText>
				</h:panelGroup>
				<h:panelGroup>
					<h:inputText value="#{maintenanceBean.eventState}"></h:inputText>
				</h:panelGroup>
				<h:panelGroup>
					<h:outputText styleClass="ea-content-class" value="Timezone"></h:outputText>
				</h:panelGroup>

				<h:panelGroup>
<!--					<h:inputText value="#{maintenanceBean.eventTimezone}"></h:inputText>-->
					<h:selectOneMenu id="timeZoneSelectId" value="#{maintenanceBean.eventTimezone}">
<f:selectItems value="#{applicationBean.timeZoneCategoryMap}"/>
</h:selectOneMenu>
				</h:panelGroup>
				<h:panelGroup>
				</h:panelGroup>
				<h:panelGroup>
					<a4j:commandButton value="Add" reRender="manage-event-panel" rendered="#{maintenanceBean.addButtonEnable == true}"
						actionListener="#{maintenanceBean.addEventListener}"></a4j:commandButton>
					<a4j:commandButton value="Modify" reRender="manage-event-panel" rendered="#{maintenanceBean.addButtonEnable == false}"
						actionListener="#{maintenanceBean.updateEventListener}"></a4j:commandButton>				
				<a4j:commandButton value="Delete" rendered="#{maintenanceBean.addButtonEnable == false}" actionListener="#{maintenanceBean.deleteConfirmListener}" reRender="event-delete-modal-panel-id"  oncomplete="#{rich:component('event-delete-modal-panel-id')}.show()" >
<a4j:actionparam name="sessionEventId" value="#{maintenanceBean.selectedEventId}" assignTo="#{maintenanceBean.eventId}"/>
</a4j:commandButton>
				
				<a4j:commandButton value="Cancel" actionListener="#{maintenanceBean.showEventListListener}" reRender="manage-event-panel"></a4j:commandButton>
						
				
				
				</h:panelGroup>

			</h:panelGrid>

		</h:panelGroup>

	</h:panelGrid>
	
	
</rich:panel>

