<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>

<style type="text/css" >


.ea-speaker-settings-row{
  vertical-align: top;
  height: 250px;
  /*background-color: green;*/

}

.ea-speaker-settings-col{
  width:800px;
  align:left;
  padding:0;
  vertical-align:top;
  
}

.speker-settings-panel{
	margin-top: 15px;
	height: 120px;			
	width: 450px;
	background-color: white;

}

.speaker-list-panel{
	margin-top: 15px;
	height: 150px;			
	width: 350px;
	background-color: white;

}

.speaker-settings-col1{
	
width: 120px;
height: 30px

}

.speaker-settings-col2{
width: 330px;
height: 30px;
}

.speaker-panel-col1{
vertical-align: top;
}
.speaker-panel-col2{	
vertical-align: top;
}

.ea-speaker-settings-panel{
	background: white;
	margin: 0 auto;
	width: 900px;
}
.ea-speaker-row1{
height: 60px;
}
.ea-speaker-row2{
height: 250px;
vertical-align: top;
}
.ea-speaker-row1-col1{
width: 100px;
}
.ea-speaker-row1-col2{
width: 150px;
}
.ea-speaker-row1-col3{
width: 400px;
}

</style>

<script type="text/javascript">
function eventSelect(){
	document.getElementById('profileForm:eventSelectButtonId').click();
}

function speakerSelect(){

	document.getElementById('profileForm:speakerCheckHiddenButtonId').click();
   
}

function submitSession(){
	document.getElementById('profileForm:sessionSubmitHiddenButtonId').click();
}

function submitSessionId(sessionId){

	document.getElementById('profileForm:sessionId').value = sessionId;
}

</script>

<a4j:region id="speakerSettingsRegion">

<a4j:commandButton id="speakerCheckHiddenButtonId" status="speakerSettingsStatus" reRender="eventListBoxGroup,sessionListBoxGroup" actionListener="#{userBean.speakerCheckActionListener}" style="display:none;"></a4j:commandButton>

<a4j:commandButton id="eventSelectButtonId" status="speakerSettingsStatus" reRender="sessionListBoxGroup" actionListener="#{userBean.getSessionByEventIdActionListener}"  style="display:none;"></a4j:commandButton>

<a4j:commandButton id="sessionSubmitHiddenButtonId" status="speakerSettingsStatus" reRender="speaker-panel" actionListener="#{userBean.submitSessionIdForSpeakerActionListener}"  style="display:none;"></a4j:commandButton>

<h:inputHidden id="sessionId" value="#{userBean.sessionOption}" ></h:inputHidden>

<rich:panel id="speaker-panel" styleClass="ea-speaker-settings-panel">

<h:panelGrid columnClasses="ea-speaker-row1,ea-speaker-row2"  columns="1" >

<h:panelGroup>
<h:panelGrid columnClasses="ea-speaker-row1-col1,ea-speaker-row1-col2,ea-speaker-row1-col3"  columns="3" >

<h:panelGroup>
<a4j:status id="speakerSettingsStatus" for="speakerSettingsRegion">
                <f:facet name="start">
                   <h:graphicImage value="/images/updating.gif"></h:graphicImage> 
                </f:facet>
 </a4j:status>
<h:selectBooleanCheckbox id="selectCheckbox" onchange="speakerSelect();" immediate="true" value="#{userBean.speakerCheckBox}" />
<h:outputText styleClass="ea-content-class" value="I'm a Speaker"></h:outputText>
</h:panelGroup>

<h:panelGroup id="eventListBoxGroup">
<h:outputText  rendered="#{userBean.showEventList}"  styleClass="ea-heading-class" value="Select an Event"></h:outputText>
<br/>
<h:selectOneMenu id="eventListBox" onchange="eventSelect();" rendered="#{userBean.showEventList}"  value="#{userBean.eventOption}" label="#{userBean.eventOptionId}" >
<f:selectItems value="#{userBean.eventMap}"/> 
</h:selectOneMenu>
</h:panelGroup>


<h:panelGroup id="sessionListBoxGroup">
<h:outputText  rendered="#{userBean.showSessionList}"  styleClass="ea-heading-class" value="Unassigned Session"></h:outputText>
<br/>
<h:selectOneMenu id="sessionListBox" style="width:320px;" onchange="submitSession();" rendered="#{userBean.showSessionList}" value="#{userBean.sessionOption}">
<f:selectItems   value="#{userBean.sessionMap}"/> 
</h:selectOneMenu> 
<h:outputText  rendered="#{userBean.showSessionList==false && userBean.speakersAssigned==true}"   styleClass="ea-content-class" value="Speakers assigned for all the sessions"></h:outputText>

</h:panelGroup>


</h:panelGrid>


</h:panelGroup>

<h:panelGroup>
<rich:dataTable rowKeyVar="idx" id="speakerSessionList"  columnClasses="col" value="#{userBean.profileDTO.speakerDTO.sessionList}"
						            			 var="speakerSession">
<f:facet name="header">
<rich:columnGroup>
<h:column>
<h:outputText value="S.No"></h:outputText>
</h:column>
<h:column>
<h:outputText value="Event Name"></h:outputText>
</h:column>
<h:column>
<h:outputText value="Session Name"></h:outputText>
</h:column>
<h:column>
<h:outputText value="UnAssign Speaker"></h:outputText>
</h:column>
</rich:columnGroup>
</f:facet>
<h:column>
<h:outputText value="#{idx+1}"></h:outputText>
</h:column>
<h:column>
<h:outputText value="#{speakerSession.eventName}"></h:outputText>
</h:column>
<h:column>
<h:outputText value="#{speakerSession.sessionName}"></h:outputText>
</h:column>
<h:column>
<a4j:commandLink value="Remove" actionListener="#{userBean.removeSessionIdForSpeakerActionListener}" onclick="submitSessionId('#{speakerSession.sessionId}')"  reRender="speaker-panel">
</a4j:commandLink>
</h:column>
</rich:dataTable>

</h:panelGroup>


</h:panelGrid>

</rich:panel>

</a4j:region>