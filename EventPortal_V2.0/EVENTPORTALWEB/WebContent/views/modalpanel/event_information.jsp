<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>

<style type="text/css">

.footer-col1{

width: 130px;

}

.footer-col2{

width: 150px;

}

.footer-col3{

width: 100px;

}
.footer-col4{



}

.ea-outer-col-class{


}
.ea-event-detail-mid1-content-col1{
  width:350px;

  
}
.ea-event-detail-mid1-content-col2{

}

.ea-event-detail-mid2-content-col1{
  width:100px;

  
}
.ea-event-detail-mid2-content-col2{

}

</style>

<script type="text/javascript">

function submitEventIdForEvent(eventId){
	
		document.getElementById("modalEventDetailsForm:eventId").value = eventId;
		Richfaces.hideModalPanel('modalEventDetailsPanel');
			
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


<a4j:form id="modalEventDetailsForm">	
<rich:modalPanel width="500" height="250"  id="modalEventDetailsPanel">

<!-- hidden field values -->
	<h:inputHidden value="#{eventBean.eventId}" id="eventId" />


<f:facet name="header">
            <h:panelGroup>
                <h:outputText value="#{eventBean.eventDTO.eventName}"></h:outputText>
            </h:panelGroup>
</f:facet>
<f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/images/close.png" styleClass="hidelink" id="hidelink"/>
                <rich:componentControl for="modalEventDetailsPanel" attachTo="hidelink" operation="hide" event="onclick"/>
            </h:panelGroup>
</f:facet>

<h:panelGrid id="eventDetailPanel" columns="1" columnClasses="ea-outer-col-class">		
		<h:panelGroup>
		<h:column>
		<h:outputLabel styleClass="ea-content-bold-class" value=" - "></h:outputLabel>
		<h:outputText styleClass="ea-content-bold-class" value="#{eventBean.eventDTO.eventStartDate} to #{eventBean.eventDTO.eventEndDate}, "/>
		<h:outputText styleClass="ea-content-bold-class" value=" #{eventBean.eventDTO.eventStartTime} - #{eventBean.eventDTO.eventEndTime}, "/>
		</h:column>
		<h:column><h:outputText styleClass="ea-content-bold-class" value=" #{eventBean.eventDTO.eventLocation}"/></h:column>
		</h:panelGroup>
		<h:panelGroup>
		<h:outputText styleClass="ea-content-class" value="#{eventBean.eventDTO.eventDescription}"/>
		</h:panelGroup>
		<h:panelGroup>	
		
		<h:panelGrid columnClasses="ea-event-detail-mid1-content-col1,ea-event-detail-mid1-content-col2" columns="2">
		
		<h:panelGroup>
					<h:panelGrid columnClasses="ea-event-detail-mid2-content-col1,ea-event-detail-mid2-content-col2" columns="2">
			
			<h:panelGroup>
				<h:outputLabel styleClass="ea-content-bold-class">Website:</h:outputLabel>			
			</h:panelGroup>
			
			<h:panelGroup>
						<a4j:commandLink value="#{eventBean.eventDTO.eventWebsite}" onclick="popup('#{eventBean.eventDTO.eventWebsite}')" />
			</h:panelGroup>
			
			<h:panelGroup>
			<h:outputLabel styleClass="ea-content-bold-class">Organized By :</h:outputLabel>
			</h:panelGroup>
			<h:panelGroup>				
			<h:outputText styleClass="ea-content-class" value="#{eventBean.eventDTO.eventOrganizedBy}"/>
			</h:panelGroup>
			</h:panelGrid>
		
		</h:panelGroup>
		<h:panelGroup>
		
		</h:panelGroup>
		
		</h:panelGrid>		
		</h:panelGroup>
		<h:panelGroup rendered="#{applicationBean.currentPage == 'EVENTLIST'}">	
		<h:panelGrid columns="4" columnClasses="footer-col1,footer-col2,footer-col3,footer-col4">
			<h:panelGroup>
			</h:panelGroup>
			<h:panelGroup>
			<a4j:commandButton value="View Event Details" id="viewAgenda" onclick="submitEventIdForEvent('#{eventBean.eventDTO.eventId}')"
			action="#{eventBean.populateAgendaPage}"  oncomplete="Richfaces.hideModalPanel('modalEventDetailsPanel')" />
			
			</h:panelGroup>
			<h:panelGroup>
			<a4j:commandButton value="Join Event" id="joinEvent" onclick="submitEventIdForEvent('#{eventBean.eventDTO.eventId}')"
			action="#{eventBean.joinEvent}" oncomplete="Richfaces.hideModalPanel('modalEventDetailsPanel')" />	
			
			</h:panelGroup>	
			<h:panelGroup>
			</h:panelGroup>		
			</h:panelGrid>
		</h:panelGroup>
</h:panelGrid>
            <h:panelGroup>
 	<h:outputLink value="#" id="link">
        close 
     <rich:componentControl for="modalEventDetailsPanel" attachTo="link" operation="hide" event="onclick"/>
    </h:outputLink>            
    </h:panelGroup>

</rich:modalPanel>

</a4j:form>
