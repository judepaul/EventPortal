<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript">
function closePopup(){
	//alert("Hi");	
	Richfaces.hideModalPanel('modalConnectMailPanel');
}

function test(option){
	//alert('Hi');
	document.getElementById('connectMailForm:hiddenMailOption').value=document.getElementById('connectMailForm:mailOption').value;
	
	document.getElementById('connectMailForm:CheckHiddenButtonId').click();
	
}   
</script>

<style type="text/css">

.connect-mail-panel{

	height: 250px;
	  width:400px;
	  background: white;
	}

.connect-mail-column{
	background: white;

}

.mailcol1{

width:70px;
padding: 0;
margin: 0;
border: 0;

}
.mailcol2{

width:250px;
padding: 0;
margin: 0;
border: 0;

}

.mailcol3{

padding: 0;
margin: 0;
border: 0;


}

.mail-connect-content-panel{

height: 250px;

}

.ea-outer-col-class{


}

</style>

<a4j:form id="connectMailForm">

<!-- hidden field values -->
<a4j:commandButton id="CheckHiddenButtonId" reRender="attendeeListBoxGroup1" actionListener="#{attendeeBean.getMailContent}" style="display:none;"></a4j:commandButton>
<h:inputHidden id="hiddenMailOption" value="#{attendeeBean.hiddenMailOption}" />
<rich:modalPanel width="380" height="230" id="modalConnectMailPanel">

<f:facet name="header">
            <h:panelGroup>
                <h:outputText value="Mail Connect - #{USERNAME}"></h:outputText>
            </h:panelGroup>
</f:facet>
<f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/images/close.png" styleClass="hidelink" id="hidelink"/>
                <rich:componentControl for="modalConnectMailPanel" attachTo="hidelink" operation="hide" event="onclick"/>
            </h:panelGroup>
</f:facet>

<h:panelGrid id="contentPanel" columns="1" columnClasses="ea-outer-col-class">		
		<h:panelGroup>
		
		<h:outputText styleClass="ea-content-bold-class" value="To:"/>
		&nbsp;&nbsp; 
		<h:outputText styleClass="ea-content-class" value="#{attendeeBean.mailDTO.toMailFirstName} #{attendeeBean.mailDTO.toMailLastName}" />
		</h:panelGroup>
		
		<h:panelGroup rendered="#{attendeeBean.mailCount==0}">		
		</h:panelGroup><!--

		<h:panelGroup rendered="#{attendeeBean.mailCount!=0}">		
		<rich:comboBox id="mailOption" value="#{attendeeBean.mailOption}" onselect="test()" defaultLabel="Select" directInputSuggestions="true" width="100">
			<f:selectItems value="#{attendeeBean.profileMailOption}"/>	
					
		</rich:comboBox>
		</h:panelGroup>
		
		--><h:panelGroup rendered="#{attendeeBean.mailDTO.firstMail}">
		<h:inputTextarea rows="5" cols="60" value="#{attendeeBean.profileMailContent}"></h:inputTextarea>
		</h:panelGroup>
		
		<h:panelGroup id="attendeeListBoxGroup1"  rendered="#{!attendeeBean.mailDTO.firstMail}">
		<h:inputTextarea rows="5" cols="60" value="#{attendeeBean.profileMailContent}"></h:inputTextarea>
		</h:panelGroup>
		
		<h:panelGroup>	
		<h:panelGrid columns="4" columnClasses="footer-col1,footer-col2,footer-col3,footer-col4">
		<h:panelGroup>
			</h:panelGroup>
			<h:panelGroup>
			<a4j:commandButton value="Send" onclick="closePopup()" actionListener="#{attendeeBean.sendMailToAttendees}" oncomplete="Richfaces.hideModalPanel('modalConnectMailPanel')"></a4j:commandButton>
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
     <rich:componentControl for="modalConnectMailPanel" attachTo="link" operation="hide" event="onclick"/>
    </h:outputLink>            
    </h:panelGroup>

</rich:modalPanel>

</a4j:form>

