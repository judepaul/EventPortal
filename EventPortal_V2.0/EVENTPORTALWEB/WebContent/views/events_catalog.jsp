<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<link href="../styles/styles.css" rel="stylesheet"
	type="text/css" />
	
<style type="text/css">

.ea-event-list-class{

background: white;
border: 0;

}

</style>

<rich:panel id="eventList" styleClass="ea-event-list-class">

	<h:panelGrid columnClasses="" columns="1">	
<a4j:region id="listLiveEventRegion">	
<h:inputHidden value="#{eventBean.unjoinEventId}" id="liveEventId" />	
		<h:panelGroup>
			<h:outputText styleClass="ea-heading1-class" value="Live Events" />
			<a4j:status id="listLiveEventStatus" for="listLiveEventRegion">
                <f:facet name="start">
                    <h:graphicImage  value="/images/loading_refresh.gif" />
                </f:facet>
 </a4j:status>
		</h:panelGroup>
		<rich:dataList styleClass="event-data-list" id="liveEventList"
			value="#{eventBean.eventDTO.liveEventList}" var="liveEvents">
			<h:graphicImage width="20" height="20"
				rendered="#{liveEvents.joinedEvent == true}"
				value="/images/foot_print.jpg" />
			<rich:spacer width="5"></rich:spacer>
			<a4j:commandLink
				onclick="getEventDetails('#{liveEvents.eventId}','#{liveEvents.joinedEvent}')">
				<h:outputText value="#{liveEvents.eventName}" />
				<a4j:actionparam name="eventCategory" value="LIVE" assignTo="#{eventBean.eventCategory}"/>
			</a4j:commandLink>
			<rich:spacer width="10"></rich:spacer>
			<a4j:commandButton reRender="eventList" status="listLiveEventStatus" rendered="#{liveEvents.joinedEvent == true}"
				value="UnJoin" actionListener="#{eventBean.unJoinEventListener}" onclick="unJoinLiveEvent('#{liveEvents.eventId}')"></a4j:commandButton>

		</rich:dataList>
</a4j:region>
		
		<h:panelGroup>
			<rich:separator height="2" lineType="solid" />
		</h:panelGroup>
<a4j:region id="listFutureEventRegion">
<h:inputHidden value="#{eventBean.unjoinEventId}" id="futureEventId" />
		<h:panelGroup>
			<h:outputText styleClass="ea-heading1-class" value="Upcoming Events" />
			<a4j:status id="listFutureEventStatus" for="listFutureEventRegion">
                <f:facet name="start">
                    <h:graphicImage  value="/images/loading_refresh.gif" />
                </f:facet>
 </a4j:status>
		</h:panelGroup>


		<h:panelGroup>
			<rich:dataList styleClass="event-data-list" id="futureEventList"
				value="#{eventBean.eventDTO.futureEventList}" var="futureEvents">
				<h:graphicImage width="20" height="20"
					rendered="#{futureEvents.joinedEvent == true}"
					value="/images/foot_print.jpg" />
				<rich:spacer width="5"></rich:spacer>
				<a4j:commandLink
					onclick="getEventDetails('#{futureEvents.eventId}','#{futureEvents.joinedEvent}')">
					<h:outputText value="#{futureEvents.eventName}" />
					<a4j:actionparam name="eventCategory" value="FUTURE" assignTo="#{eventBean.eventCategory}"/>
				</a4j:commandLink>
				<rich:spacer width="10"></rich:spacer>
				<a4j:commandButton reRender="eventList" rendered="#{futureEvents.joinedEvent == true}"
					value="UnJoin" actionListener="#{eventBean.unJoinEventListener}" onclick="unJoinFutureEvent('#{futureEvents.eventId}')"></a4j:commandButton>

			</rich:dataList>

		</h:panelGroup>

	</a4j:region>
		<h:panelGroup>
			<rich:separator height="2" lineType="solid" />
		</h:panelGroup>

		<h:panelGroup>
			<h:outputText styleClass="ea-heading1-class" value="Past Events" />
		</h:panelGroup>

		<h:panelGroup>
			<rich:dataList styleClass="event-data-list" id="pastEventList"
				value="#{eventBean.eventDTO.pastEventList}" var="pastEvents">
				<h:graphicImage width="20" height="20"
					rendered="#{pastEvents.joinedEvent == true}"
					value="/images/foot_print.jpg" />
				<rich:spacer width="5"></rich:spacer>
				<a4j:commandLink
					onclick="getEventDetails('#{pastEvents.eventId}','past')">
					<h:outputText value="#{pastEvents.eventName}" />
					<a4j:actionparam name="eventCategory" value="PAST" assignTo="#{eventBean.eventCategory}"/>
				</a4j:commandLink>
				<rich:spacer width="10"></rich:spacer>


			</rich:dataList>
		</h:panelGroup>

		<h:panelGroup>

		</h:panelGroup>

	</h:panelGrid>

</rich:panel>
