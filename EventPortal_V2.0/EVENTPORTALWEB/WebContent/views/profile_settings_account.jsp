<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>

<style type="text/css" >

.ea-accnt-settings-row1{

 height: 150px;

}

.ea-accnt-settings-row2{

 height: 20px;
 
}

.ea-accnt-settings-row3{

  height: 150px;

}

.ea-accnt-settings-col1{
  width:400px;
  align:left;
  padding:0;
  vertical-align:top;  
}
.ea-accnt-settings-col2{
width:30px; 
}
.ea-accnt-settings-col3{
  width:400px;
  align:left;
  padding:0;
  vertical-align:top;  
}

</style>

<script type="text/javascript">

</script>

<h:panelGrid rowClasses="ea-accnt-settings-row1,ea-accnt-settings-row2" columns="1">

<h:panelGroup>
<h:panelGrid columnClasses="ea-accnt-settings-col1,ea-accnt-settings-col2,ea-accnt-settings-col3" columns="3">
<h:panelGroup>
<h:outputText styleClass="content-heading" value="User Info"></h:outputText>
<rich:panel styleClass="user-info-panel">

<h:panelGrid columnClasses="user-info-col1,user-info-col2" columns="2">

<h:panelGroup>
<h:outputText styleClass="content-text" value="First Name"></h:outputText>
<div class="vseperator2"></div>
<h:outputText styleClass="content-text" value="Last Name"></h:outputText>
<div class="vseperator2"></div>
<h:outputText styleClass="content-text" value="Email"></h:outputText>
</h:panelGroup>

<h:panelGroup>
<h:inputText styleClass="input-box" value="#{userBean.firstName}"> </h:inputText>
<div class="vseperator2"></div>
<h:inputText styleClass="input-box" value="#{userBean.lastName}"> </h:inputText>
<div class="vseperator2"></div>
<h:outputText styleClass="content-text" value="#{userBean.eMail}"></h:outputText>
</h:panelGroup>


</h:panelGrid>
</rich:panel>
</h:panelGroup>
<h:panelGroup>

</h:panelGroup>
<h:panelGroup>
<h:outputText styleClass="content-heading" value="Password Reset"></h:outputText>
<rich:panel styleClass="password-reset-panel">

<h:panelGrid columnClasses="password-reset-col1,password-reset-col2" columns="2">

<h:panelGroup>
<h:outputText styleClass="content-text" value="Old Password"></h:outputText>
<div class="vseperator2"></div>
<h:outputText styleClass="content-text" value="New Password"></h:outputText>
<div class="vseperator2"></div>
<h:outputText styleClass="content-text" value="Confirm Password"></h:outputText>
</h:panelGroup>

<h:panelGroup>
<h:inputSecret styleClass="input-box" value="#{userBean.password}"> </h:inputSecret>
<div class="vseperator2"></div>
<h:inputSecret styleClass="input-box" value="#{userBean.newPassword}"> </h:inputSecret>
<div class="vseperator2"></div>
<h:inputSecret  styleClass="input-box" value="#{userBean.confirmPassword}"> </h:inputSecret>
</h:panelGroup>
</h:panelGrid>
</rich:panel>
</h:panelGroup>
</h:panelGrid>
</h:panelGroup>
<h:panelGroup>

</h:panelGroup>

</h:panelGrid>
