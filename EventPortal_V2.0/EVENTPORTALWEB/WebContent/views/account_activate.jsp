<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>


<%

String accessID = null;
accessID = request.getParameter("activation_id");

System.out.println("User Email Confirmation.......!" +accessID);

session.setAttribute("ACTIVATION_ID",accessID);

%>

<html>
      <head>
            <title>Event Attend - User Activation</title>
            <link href="../styles/styles.css" rel="stylesheet" type="text/css"/>
      </head>

<script type="text/javascript">

function submitUserActivation(accessID){
			document.getElementById('activationForm:access_ID').value = accessID; 
			
			//document.getElementById('activationForm:confirmNewUserMailId').click();
 }

</script>

<style type="text/css">
.ea-user-activate-panel{
vertical-align:middle;
background: white;

}
.ea-user-activate-network-col{
height:150px;

vertical-align:middle;
}
.ea-social-network-row1{
height:50px;
}
.ea-social-network-row2{
height:150px;

vertical-align:top;
}

</style>


 <body>
<f:view>
	<a4j:form id="activationForm">

<a4j:region id="activateRegion">

		<!-- hidden field values -->
		<h:inputHidden value="#{userBean.activationID}" id="access_ID" />   		
<rich:panel styleClass="ea-user-activate-panel" id="ea-user-activation-panel-id">
		
		<a4j:poll id="userActivationPoll" reRender="ea-user-activation-panel-id" actionListener="#{userBean.confirmNewUserMailIdListener}" interval="3000" enabled="#{userBean.beforeUserActivation}">

</a4j:poll>
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
<tr>
<td colspan="3">
<h:graphicImage value="../images/eventattend.png" />
</td>
</tr>
			<tr>
					<td colspan="3" height="450" valign="top">
					
<h:panelGrid columns="1">
<h:panelGroup rendered="#{userBean.beforeUserActivation}"  id="ea-before-user-activate-panel-id">
<h:panelGrid columns="1" columnClasses="ea-social-network-row1,ea-social-network-row2">

<h:panelGroup>

<h:outputText styleClass="ea-content-class" value="Activation in progress... Please be patient!"></h:outputText>

</h:panelGroup>
<h:panelGroup>

<a4j:status id="activateStatus" for="activateRegion" >
                <f:facet name="start">
                    <h:graphicImage  value="/images/loading.gif" />
                </f:facet>
 </a4j:status>

</h:panelGroup>

</h:panelGrid>

</h:panelGroup>
<h:panelGroup rendered="#{userBean.afterUserActivation}" id="ea-after-user-activate-panel-id">
<h:panelGrid columns="1" columnClasses="ea-social-network-row1,ea-social-network-row2">

<h:panelGroup>

<h:outputText styleClass="ea-content-class" value="Your Account has been Activated. You can ">
</h:outputText>
<h:commandLink action="#{loginBean.loginHome}">Login</h:commandLink>
<h:outputText styleClass="ea-content-class" value=" now!">
</h:outputText>

</h:panelGroup>
<h:panelGroup>
</h:panelGroup>

</h:panelGrid>

</h:panelGroup>

</h:panelGrid>					
					
					</td>
				</tr>
				<tr>
<td colspan="3">
<a4j:commandButton id="confirmNewUserMailId" value="" style="display:none" action="#{userBean.confirmNewUserMailId}"/>

</td>
</tr>
<tr>
<td colspan="3">
<jsp:include page="../common/footer_main.jsp" />
</td>
</tr>
			</table>
		</rich:panel>
		</a4j:region>
	</a4j:form>
</f:view>	


</body>
</html>
