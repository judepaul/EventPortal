<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>
<script src="../scripts/CommonScript.js" language="javascript">
</script>
<style type="text/css" >

.ea-manage-user-panel{
	background: white;
	margin: 0 auto;
	width: 900px;
}

.ea-user-col1{

width: 350px;
vertical-align: top;
}

.ea-user-col2{

width: 550px;
vertical-align: top;
}

.ea-user-input-col1{

width: 150px;

}

.ea-user-input-col2{

width: 200px;

}

.ea-user-list-panel-body-class{

height:350px;
width:480px;
overflow:auto;
background: white;

}


</style>



<script type="text/javascript" >
function openPopUp(url){
	  popup(url);	
}

</script>


<rich:panel id="manage-user-panel" styleClass="ea-manage-user-panel">

<h:panelGrid id="userListGridId"  columnClasses="ea-event-col2" columns="1">

		<h:panelGroup>
			<div style="height:400px ; width:850px; overflow-x:auto; overflow-y:scroll">
				<rich:dataTable rowKeyVar="idx" id="userList" columnClasses="col"
					value="#{maintenanceBean.attendeeList}" var="userValues">
					<f:facet name="header">
						<rich:columnGroup>
							<h:column>
								<h:outputText value="S.No"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Name"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Picture"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Primary E-mail"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Alternative E-mail"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Address"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Country"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="State"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="City"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Zip"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="TimeZone"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Education"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Occupation"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Website"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="LandHome Phone"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="LandOffice Phone"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Mobile"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Twitter Link"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Facebook Link"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="LinkedIn Link"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Events Joined"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Sessions Attended"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value="Speaker For"></h:outputText>
							</h:column>
						</rich:columnGroup>
					</f:facet>

					<h:column>
						<h:outputText value="#{idx+1}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{userValues.firstName} #{userValues.lastName}"></h:outputText>
					</h:column>
					<h:column>					
						<h:graphicImage width="30" height="30" id="profileImg"  value="#{userValues.profImgFileName}"/>	
					</h:column>
					<h:column>
						<h:outputText
							value="#{userValues.profileEmail}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText
							value="#{userValues.alternativeEmail}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{userValues.address}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{userValues.country}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{userValues.state}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{userValues.city}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{userValues.zip}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{userValues.timeZone}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{userValues.education}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{userValues.occupation}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{userValues.website}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{userValues.landHome}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{userValues.landOffice}"></h:outputText>
					</h:column>
					<h:column>
						<h:outputText value="#{userValues.mobile}"></h:outputText>
					</h:column>
						<h:column>						
						<a4j:commandLink  rendered="#{userValues.twitterProfileUrl != null}"   value="#{userValues.twitterProfileUrl}" onclick="popup('#{userValues.twitterProfileUrl}')" />	
					</h:column>
						<h:column>						
						<a4j:commandLink  rendered="#{userValues.faceBookProfileUrl != null}"   value="#{userValues.faceBookProfileUrl}" onclick="popup('#{userValues.faceBookProfileUrl}')" />						
					</h:column>
						<h:column>						
						<a4j:commandLink  rendered="#{userValues.linkedInProfileUrl != null}"   value="#{userValues.linkedInProfileUrl}" onclick="popup('#{userValues.linkedInProfileUrl}')" />
					</h:column>
					
					<h:column>	
						<h:outputText value="#{userValues.joinedEventsName}"></h:outputText>								
					</h:column>
					<h:column>	
						<h:outputText value="#{userValues.attendedSessionsName}"></h:outputText>								
					</h:column>
					<h:column>	
						<h:outputText value="#{userValues.speakerFor}"></h:outputText>								
					</h:column>
					
				</rich:dataTable>
			</div>
			
			
		</h:panelGroup>


	</h:panelGrid>

</rich:panel>

