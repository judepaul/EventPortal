<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<style type="text/css">

.ea-social-network-panel{
vertical-align:middle;
width: 600px;
height: 400px;

}
.ea-social-network-col{
height:150px;

vertical-align:middle;
}

</style>
<script type="text/javascript">

function closeWindow(){
window.parent.reload();
	window.close();
}


</script>
<a4j:form id="socialNetworkConnectForm">
<rich:panel styleClass="ea-social-network-panel">

<h:panelGrid columns="1" columnClasses="ea-social-network-col" >

<h:panelGroup>
<h:outputText styleClass="ea-content-class" value="You have configured your #{socialMediaBean.currentMedia} account succesfully. You can now able to post messages or connect friends. "></h:outputText>

</h:panelGroup>
<h:panelGroup>
<h:outputText styleClass="ea-content-class" value="You can now safely close this window."></h:outputText>
<a4j:commandLink onclick="closeWindow();">close</a4j:commandLink>
</h:panelGroup>

</h:panelGrid>


</rich:panel>
</a4j:form>