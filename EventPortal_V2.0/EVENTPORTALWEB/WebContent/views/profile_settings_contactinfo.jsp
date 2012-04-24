<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<style type="text/css">

.contact-info-col1{
width: 150px;
height: 30px;


	
}
.contact-info-col2{
width: 200px;
height: 30px;

	
}

.contact-info-panel{	
	 background:white;
	margin: 0 auto;
	width: 500px;
}

.ea-contact-info-row{

 height: 20px;

}
.ea-contact-outer-col1{
width: 150px;
}
.ea-contact-outer-col2{
width: 500px;
}
.ea-contact-outer-col3{
width: 150px;
}

</style>





<h:panelGrid columns="3" columnClasses="ea-contact-outer-col1,ea-contact-outer-col2,ea-contact-outer-col3">
<h:panelGroup>
</h:panelGroup>
<h:panelGroup>
<rich:panel styleClass="contact-info-panel">
<h:panelGrid  rowClasses="ea-contact-info-row" columnClasses="contact-info-col1,contact-info-col2" columns="2">
<h:panelGroup><h:outputText styleClass="ea-content-class" value="Email"></h:outputText></h:panelGroup>
<h:panelGroup><h:outputText styleClass="ea-content-class" value="#{userBean.eMail}"></h:outputText></h:panelGroup>


<h:panelGroup><h:outputText styleClass="ea-content-class" value="Phones"></h:outputText></h:panelGroup>
<h:panelGroup><h:inputText styleClass="input-box" value="#{userBean.mobile}" ></h:inputText></h:panelGroup>

<h:panelGroup><h:outputText styleClass="ea-content-class" value="Address"></h:outputText></h:panelGroup>
<h:panelGroup><h:inputText styleClass="input-box" value="#{userBean.address}"> </h:inputText></h:panelGroup>

<h:panelGroup><h:outputText styleClass="ea-content-class" value="City/Town"></h:outputText></h:panelGroup>
<h:panelGroup><h:inputText styleClass="input-box" value="#{userBean.city}"> </h:inputText></h:panelGroup>

<h:panelGroup><h:outputText styleClass="ea-content-class" value="Zip"></h:outputText></h:panelGroup>
<h:panelGroup><h:inputText styleClass="input-box" value="#{userBean.zip}"> </h:inputText></h:panelGroup>

<h:panelGroup><h:outputText styleClass="ea-content-class" value="State"></h:outputText></h:panelGroup>
<h:panelGroup><h:inputText styleClass="input-box" value="#{userBean.state}"> </h:inputText></h:panelGroup>

<h:panelGroup><h:outputText styleClass="ea-content-class" value="Country"></h:outputText></h:panelGroup>
<h:panelGroup><h:inputText styleClass="input-box" value="#{userBean.country}"> </h:inputText></h:panelGroup>

<h:panelGroup><h:outputText styleClass="ea-content-class" value="Education"></h:outputText></h:panelGroup>
<h:panelGroup>
<rich:comboBox  value="#{userBean.education}"  defaultLabel="Select" selectFirstOnUpdate="false">
 <f:selectItem itemValue="Under Graduate"/>
 <f:selectItem itemValue="Graduate"/>
 <f:selectItem itemValue="Post Graduate"/>
 <f:selectItem itemValue="Post Graduate - General"/>
 <f:selectItem itemValue="Post Graduate - Professional"/>
</rich:comboBox></h:panelGroup>

<h:panelGroup><h:outputText styleClass="ea-content-class" value="Occupation"></h:outputText></h:panelGroup>
<h:panelGroup>
<rich:comboBox value="#{userBean.occupation}"  defaultLabel="Select" selectFirstOnUpdate="false">
<f:selectItem itemValue="Officer/Executive - Senior"/>
 <f:selectItem itemValue="Officer/Executive - Middle"/>
 <f:selectItem itemValue="Officer/Executive - Junior"/>
 <f:selectItem itemValue="Software Professional"/>
 <f:selectItem itemValue="Self Employed Professional"/>
 <f:selectItem itemValue="Businessman"/>
 <f:selectItem itemValue="Student"/>
 <f:selectItem itemValue="Notworking/Retired"/> 
</rich:comboBox>
</h:panelGroup>

<h:panelGroup><h:outputText styleClass="ea-content-class" value="Website"></h:outputText></h:panelGroup>
<h:panelGroup><h:inputText styleClass="input-box" value="#{userBean.website}"> </h:inputText></h:panelGroup>

<h:panelGroup><h:outputText styleClass="ea-content-class" value="About Yourself"></h:outputText></h:panelGroup>
<h:panelGroup><h:inputTextarea rows="4" cols="30" value="#{userBean.speakerKeyNotes}"/></h:panelGroup>

</h:panelGrid>
</rich:panel>
</h:panelGroup>
<h:panelGroup>
</h:panelGroup>
</h:panelGrid>
