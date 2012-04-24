<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<style type="text/css">


.ea-event-info-row-classs{

	height:40px;
	background: white;
	text-align: right;
	vertical-align: top;

}
.ea-event-info-col1-class{

	width: 400px;
	background: white;
	vertical-align: top;	
	
}
.ea-event-info-col2-class{
	width: 200px;
	background: white;
	text-align: right;
	vertical-align: middle;
}
.ea-map-class{

width: 30px;
height: 30px;
}
.ea-event-info-right-col{
 vertical-align:middle;
 text-align: left;
 background: white;

}
</style>

<h:panelGrid columns="2" columnClasses="ea-event-info-col1-class,ea-event-info-col2-class">
<h:panelGroup>
<h:panelGrid columns="1">
<h:panelGroup>
<h:outputText styleClass="ea-heading1-class" value="#{eventBean.eventDetailDTO.eventName}"></h:outputText>
<h:outputText styleClass="ea-content-class" value=" - #{eventBean.eventDetailDTO.eventCountry} #{eventBean.eventDetailDTO.eventState} #{eventBean.eventDetailDTO.eventCity} #{eventBean.eventDetailDTO.eventLocation}"/>

</h:panelGroup>
<h:panelGroup>

<h:outputText styleClass="ea-content-class" value="#{eventBean.eventDetailDTO.eventShortDesc}"/>

<a4j:commandLink styleClass="ea-hyper-link-more" value="...More" actionListener="#{eventBean.eventDetails}" reRender="modalEventDetailsPanel" oncomplete="#{rich:component('modalEventDetailsPanel')}.show()"></a4j:commandLink>
</h:panelGroup>

<h:panelGroup rendered="#{applicationBean.currentPage == 'EVENTHOME'}">

<h:panelGroup id="joinEventId">
<h:panelGroup rendered="#{eventBean.eventDetailDTO.joinedEvent == true}">
			<h:panelGrid columns="2" columnClasses="ea-event-info-right-col">
			<h:panelGroup>
			<h:graphicImage width="18" height="18" value="/images/finger_print.jpg"></h:graphicImage>
			</h:panelGroup>
			<h:panelGroup>
			<h:outputLabel styleClass="ea-content-class" value="You have joined this Event"></h:outputLabel>
			</h:panelGroup>
			</h:panelGrid>
			
</h:panelGroup>
<h:panelGroup rendered="#{eventBean.eventDetailDTO.joinedEvent == false}">
<a4j:region id="joinRegion">
<h:inputHidden value="#{eventBean.eventId}" id="eventIdForJoin" />

			<a4j:commandLink rendered="#{eventBean.eventCategory != 'PAST'}" status="joinStatus" reRender="joinEventId,ea-event-attendee-list-id"   value="Join Event" onclick="submitEventIdForJoin('#{eventBean.eventId}')" actionListener="#{eventBean.joinEventListener}"></a4j:commandLink>
</a4j:region>
<a4j:status id="joinStatus" for="joinRegion">
                <f:facet name="start">
                    <h:graphicImage  value="/images/updating.gif" />
                </f:facet>
 </a4j:status>

			</h:panelGroup>
</h:panelGroup>

</h:panelGroup>

<h:panelGroup rendered="#{applicationBean.currentPage == 'SESSIONSUMMARY'}" >
<a4j:commandLink rendered="#{sessionBean.liveSession == true}" value="Click to Attend Session" actionListener="#{sessionBean.attendConfirmListener}" oncomplete="#{rich:component('attend-confirm-modal-panel-id')}.show()"></a4j:commandLink>
</h:panelGroup>

</h:panelGrid>
</h:panelGroup>


<h:panelGroup>
<h:panelGrid columns="1" rowClasses="ea-event-info-row-classs">
<h:panelGroup>
<rich:gmap gmapVar="map"           
         gmapKey="#{eventBean.eventDetailDTO.mapDTO.gmapKey}"
           style="width:250px;height:100px" 
           lng="#{eventBean.eventDetailDTO.mapDTO.longitude}" 
           lat="#{eventBean.eventDetailDTO.mapDTO.latitude}"              
           id="gmap"           
          zoom="15"
           mapType="G_NORMAL_MAP"  
			 >
                                     
                                        <script type="text/javascript">
                                            /*<![CDATA[*/
                                                  function showAddress(address) {
                                                    new GClientGeocoder().getLatLng(
                                                      address,
                                                      function(point) {
                                                        if (!point) {
                                                          alert(address + " not found");
                                                        } else {
                                                          map.setCenter(point, 15);
                                                          var marker = new GMarker(point);
                                                          map.addOverlay(marker);     
                                                          marker.openInfoWindowHtml(address,{maxWidth:20}); 
                                                                                               
                                                        //create the tooltip content
                                                           
                                                        }//Fine else
                                                      }//Fine function
                                                    );//Fine getLangLat
                                                  }//fine showaddress
                                            /*]]>*/
                                        </script>     
                                        </rich:gmap>        
</h:panelGroup>

</h:panelGrid>
</h:panelGroup>

</h:panelGrid>
