<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<style type="text/css">
.ea-foot-col1{
width: 300px;

}
.ea-foot-col2{
width: 304px;

}
.ea-foot-col3{ 
width: 400px;
background: white;
text-align: right;
}

.ea-foot-outer-col{

width: 1004px;
vertical-align: top;
background: white;

}

.ea-footer-panel-class{
background: white;
border: 0;
}

</style>

<rich:panel id="footerPanel" styleClass="ea-footer-panel-class">
<h:panelGrid columns="1" columnClasses="ea-foot-outer-col">
<h:panelGroup>
<rich:separator height="2" lineType="solid"/>
</h:panelGroup>
<h:panelGroup>
<h:panelGrid columns="3" columnClasses="ea-foot-col1,ea-foot-col2,ea-foot-col3">
<h:panelGroup>
</h:panelGroup>
<h:panelGroup>
</h:panelGroup>
<h:panelGroup>
<span class="ea-footer-class">&copy;</span><h:outputText styleClass="ea-footer-class" value="2011 eventattend"></h:outputText>
</h:panelGroup>
</h:panelGrid>
</h:panelGroup>

</h:panelGrid>
</rich:panel>