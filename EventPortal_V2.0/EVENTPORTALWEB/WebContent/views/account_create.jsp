<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:loadBundle basename="com.eventattend.portal.web.resources.ApplicationResources" var="msg"/>
<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>

<style type="text/css">
.register-panel{
  width:420px;
  height:320px;
  align:center;
  padding:0;
  vertical-align:top;
  margin: 0 auto;  

}

.col1{

width:120px;

}

.col2{

width:300px;

}

.row{

height:30px;

}
.register-header-panel{

	height:35px;
	border:0;

}

.register-footer-panel{

	height:80px;
	border:0;

}
.register-body-panel{

	height:160px;
	border:0;

}

.header-heading {
	font-family:Arial, Helvetica, sans-serif;
	font-size:16px;
	font-weight:bold;
	color:#c0595a;
	text-align:left;
}
.body-content {
	font-family:Arial, Helvetica, sans-serif;
	font-size:13px;
	font-weight:normal;
	color:#000000;
	text-align:left;
}

</style>


<a4j:form id="registerForm">

<rich:panel styleClass="register-panel">

	<rich:panel styleClass="register-header-panel">
	<jsp:include page="../common/status_message.jsp"></jsp:include>
	<h:panelGrid columns="2">
	<h:panelGroup>
	<h:outputLabel styleClass="header-heading">Create an EventAttend Account</h:outputLabel>
	</h:panelGroup>
	<h:panelGroup>
	<rich:spacer width="70"></rich:spacer>
		<a4j:htmlCommandLink value="Login" action="#{logoutBean.logOut}"></a4j:htmlCommandLink>
	</h:panelGroup>
	
	</h:panelGrid>
	
	</rich:panel>
	
	<rich:panel styleClass="register-body-panel">
	<h:panelGrid columns="2" rowClasses="row" columnClasses="col1,col2">
	<h:panelGroup>
	<h:outputLabel styleClass="default-black-content" value="First Name"/>
	</h:panelGroup>
	<h:panelGroup>
	<h:inputText id="UserName" value="#{userBean.firstName}" size="27" required="false">
							<rich:beanValidator summary="Invalid UserName"/>
							</h:inputText>
							<rich:message styleClass="errormsg" for="UserName" />		
	</h:panelGroup>
	
	<h:panelGroup>
	<h:outputLabel styleClass="default-black-content" value="Last Name"/>
	</h:panelGroup>
	<h:panelGroup>
	<h:inputText id="LastName" value="#{userBean.lastName}" size="27" required="false">
							<rich:beanValidator summary="Invalid LastName"/>
							</h:inputText>
							<rich:message styleClass="errormsg" for="LastName" />	
	</h:panelGroup>
	
	<h:panelGroup>
	<h:outputLabel styleClass="default-black-content" value="Email"/>
	</h:panelGroup>
	<h:panelGroup>
	<h:inputText id="Email" value="#{userBean.eMail}" size="27" required="false">
							<rich:beanValidator summary="Invalid email"/>
							</h:inputText>
							<rich:message styleClass="errormsg" for="Email" />	
	</h:panelGroup>
	
	<h:panelGroup>
	<h:outputLabel styleClass="default-black-content" value="Password"/>
	</h:panelGroup>
	<h:panelGroup>
	<h:inputSecret id="Password" value="#{userBean.password}" size="27">
							<rich:beanValidator summary="Invalid password"/>
							</h:inputSecret>
							<rich:message styleClass="errormsg" for="Password" />	
	</h:panelGroup>
<!--	<h:panelGroup>-->
<!--	<h:outputLabel styleClass="default-black-content" value="Gender"/>-->
<!--	</h:panelGroup>-->
<!--	<h:panelGroup>-->
<!--	<rich:comboBox id="gender" value="#{userBean.gender}" defaultLabel="Select" directInputSuggestions="true" width="100">-->
<!--								-->
<!--								<f:selectItems value="#{userBean.genderCategory}"/>-->
<!--							</rich:comboBox>-->
<!--	</h:panelGroup>-->
	
	</h:panelGrid>
	
	</rich:panel>
	
	<rich:panel styleClass="register-footer-panel">
	
	<h:panelGrid columns="1">
	<h:panelGroup>
	<h:panelGrid columns="2">
	<h:panelGroup>
	<rich:spacer width="50"></rich:spacer>
	<h:selectBooleanCheckbox id="selectCheckbox" value="#{userBean.chkBoxChecked}">
	<a4j:support event="onclick" ajaxSingle="true" reRender="btnSubmit"/>
	</h:selectBooleanCheckbox>
	</h:panelGroup>
	<h:panelGroup>
	<h:outputLabel styleClass="default-black-content">I agree to the eventattend Terms of Services</h:outputLabel>
	</h:panelGroup>
	</h:panelGrid>
	</h:panelGroup>
	<h:panelGroup>
	<rich:spacer width="280" height="35"></rich:spacer>
	<h:commandButton id="btnSubmit"  value="Submit" action="#{userBean.createAccount}" disabled="#{userBean.btnDisabled}"/><br/>	

	</h:panelGroup>
	
	</h:panelGrid>
	
	</rich:panel>
	<h:panelGroup>
	
	</h:panelGroup>	
	
	</rich:panel>
	
</a4j:form>