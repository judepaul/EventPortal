<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>
		
			<rich:tabPanel styleClass="agenda-tab-panel" height="320" switchType="client">
<!--			<c:forEach items="#{eventBean.agendaSessionTabList}" var="cap">			-->
			<rich:tab name="Day1" label="Day1">
				<rich:dataTable width="100%" id="sessionList1" rows="30" columnClasses="col" value="#{eventBean.agendaSessionList}"
						            			 var="portalSession">
						            			<f:facet name="header">
								                		<rich:columnGroup>
								                    		<h:column>
								                        		<h:outputText styleClass="headerText" value="Time" />
								                    		</h:column>
								                    		<h:column>
								                        		<h:outputText styleClass="headerText" value="Session" />
								                    		</h:column>
								                    		<h:column >
								                        		<h:outputText styleClass="headerText" value="Location" />
								                    		</h:column>  
								                    		<h:column>
								                        		<h:outputText styleClass="headerText" value="View/Attend" />
								                    		</h:column>                 
								                		</rich:columnGroup>
								      			</f:facet>
											            <h:column >
										                <h:outputText  value="#{portalSession.sessionTime}"  />              
						                              	</h:column>
						            					<h:column>	
						                					<h:outputText   value="#{portalSession.sessionName}"  />          
											            </h:column>
						            					<h:column>
						                					<h:outputText  value="#{portalSession.sessionVenueName}" />
						                			       </h:column>
											            <h:column>
						                					<a4j:commandButton  value="View" action="#{sessionBean.populateSessionPage}" onclick="setLiveSessionId('#{portalSession.sessionId}','#{portalSession.liveSession}');"/>
<a4j:commandButton value="Attend" rendered="#{portalSession.liveSession == true}" actionListener="#{sessionBean.attendConfirmListener}" reRender="attend-confirm-modal-panel-id"  onclick="sessionAttend('#{eventBean.eventId}','#{portalSession.sessionId}')" oncomplete="#{rich:component('attend-confirm-modal-panel-id')}.show()" >
<a4j:actionparam name="sessionEventId" value="#{eventBean.eventId}" assignTo="#{sessionBean.eventId}"/>
</a4j:commandButton>
											            </h:column>
								       		 </rich:dataTable>
			
			
			</rich:tab>	
			<rich:tab name="Day2" label="Day2">
				<rich:dataTable width="100%" id="sessionList2" rows="30" columnClasses="col" value="#{eventBean.agendaSessionList1}"
						            			 var="portalSession1">
						            			<f:facet name="header">
								                		<rich:columnGroup>
								                    		<h:column>
								                        		<h:outputText styleClass="headerText" value="Time" />
								                    		</h:column>
								                    		<h:column>
								                        		<h:outputText styleClass="headerText" value="Session" />
								                    		</h:column>
								                    		<h:column >
								                        		<h:outputText styleClass="headerText" value="Location" />
								                    		</h:column>  
								                    		<h:column>
								                        		<h:outputText styleClass="headerText" value="View/Attend" />
								                    		</h:column>                 
								                		</rich:columnGroup>
								      			</f:facet>
											            <h:column >
										                <h:outputText  value="#{portalSession1.sessionTime}"  />              
						                              	</h:column>
						            					<h:column>	
						                					<h:outputText   value="#{portalSession1.sessionName}"  />          
											            </h:column>
						            					<h:column>
						                					<h:outputText  value="#{portalSession1.sessionVenueName}" />
											            </h:column>
											            <h:column>
						                					<a4j:commandButton value="View"   action="#{sessionBean.populateSessionPage}" onclick="setLiveSessionId('#{portalSession1.sessionId}','#{portalSession1.liveSession}')"/>
															<a4j:commandButton value="Attend" rendered="#{portalSession1.liveSession == true}" actionListener="#{sessionBean.attendConfirmListener}" reRender="attend-confirm-modal-panel-id" onclick="sessionAttend('#{eventBean.eventId}','#{portalSession1.sessionId}')" oncomplete="#{rich:component('attend-confirm-modal-panel-id')}.show()" >
											            <a4j:actionparam name="sessionEventId1" value="#{eventBean.eventId}" assignTo="#{sessionBean.eventId}"/>
											            </a4j:commandButton>
											            </h:column>
								       		 </rich:dataTable>
			</rich:tab>
<!--			</c:forEach>					-->
			</rich:tabPanel>
