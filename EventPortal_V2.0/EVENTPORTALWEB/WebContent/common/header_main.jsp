<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<link href="../styles/styles_design.css" rel="stylesheet" type="text/css"/>
<% 
String currentURL="http://" + request.getServerName() + ":" + request.getServerPort() +  request.getContextPath();
session.setAttribute("CURRENTURL",currentURL);
%>
<!--<table width="100%" cellpadding="2" cellspacing="2" border="0" bgcolor="#E0B21B">-->
<style type="text/css">
.ea-head-outer-col{

width: 1004px;
vertical-align: top;
background: white;
}
.ea-head-col1{

 width:30%  
vertical-align: top;
}
.ea-head-col2{
width:40%  
}
.ea-head-col3{
width:30%
  
}

 .rich-inplace-select-control-set {
    margin-top: 4px;
}
.tooltip3 {
   background:#ECFFFF;
   border: 2px solid #06266F;  
   padding:5px;    
   -moz-border-radius: 10px;    /* Rounded border mozilla browsers */
   -webkit-border-radius: 10px; /* Rounded border webkit browsers */
   border-radius: 10px; 
   
}
</style>
<script type="text/javascript">
<!--

function selectedTimeZone(){
	//alert('TimeZOne');	
	document.getElementById("headerForm:timeZoneButtonId").click();
	document.getElementById('headerForm:zoneSelectId').style.display = "none";
	document.getElementById('headerForm:hideTimeZoneId').style.display = "none";
}
function sessionLeaveConfirm(){
	//alert('In Session Detail');
	Richfaces.showModalPanel('leave-confirm-modal-panel-id');

	return false;

}

function showTimeZone(){

	document.getElementById('headerForm:zoneLinkId').style.display = "none";
	document.getElementById('headerForm:zoneSelectId').style.display = "inline";
	document.getElementById('headerForm:hideTimeZoneId').style.display = "inline";
	
}

function hideTimeZone(){

	document.getElementById('headerForm:zoneLinkId').style.display = "inline";
	document.getElementById('headerForm:zoneSelectId').style.display = "none";
	document.getElementById('headerForm:hideTimeZoneId').style.display = "none";
	
}

//-->
</script>


<h:panelGrid columns="1" columnClasses="ea-head-outer-col">
<a4j:region>
        <h:form>
            <a4j:poll id="poll" interval="1000"   actionListener="#{applicationBean.currentServerTimeTZListener}" enabled="true"
                reRender="poll,timeZoneClockId" />
        </h:form>
</a4j:region>

<a4j:form id="headerForm" style="margin:0px;padding:0px;">
<h:panelGroup>
<h:panelGrid columns="3" id="imgArea" columnClasses="ea-head-col1,ea-head-col2,ea-head-col3">
<h:panelGroup>
<h:graphicImage value="../images/eventattend.png" />
</h:panelGroup>

<h:panelGroup>
<h:panelGrid columns="1" id="grid" >
<h:panelGroup id="timeZoneClockId">
<h:outputLabel style="color:gray;font-size: x-small" value="#{applicationBean.currentServerTimeTZ}" />
</h:panelGroup>
<h:panelGroup>
<a4j:region id="timeZoneRegion">
<a4j:status id="timeZoneStatus" for="timeZoneRegion">
                <f:facet name="start">
                    
                </f:facet>
 </a4j:status>
<h:graphicImage  width="30" value="/images/timeZoneIcon.gif"  id="zoneLinkId"  onclick="showTimeZone();">
	<rich:toolTip styleClass="tooltip3" id="tool-zone" for="zoneLinkId" value="Choose your time zone">
			
			</rich:toolTip>
											            
</h:graphicImage>           
                   
<h:selectOneMenu style="display:none;" id="zoneSelectId" onchange="selectedTimeZone();" value="#{applicationBean.selectedTimeZone}">
<f:selectItems value="#{applicationBean.timeZoneCategoryMap}"/>
</h:selectOneMenu><a4j:commandLink id="hideTimeZoneId" style="display:none;" value="Cancel" onclick="hideTimeZone();"></a4j:commandLink>
<a4j:commandButton status="timeZoneStatus" reRender="grid" id="timeZoneButtonId" style="display:none;" actionListener="#{userBean.onTimeZoneSelected}" ></a4j:commandButton>
</a4j:region>


</h:panelGroup>
</h:panelGrid>
</h:panelGroup>

<h:panelGroup id="header-image-area-id">
<table cellpadding="0" cellspacing="0" width="100%" border="0">
<tr>
<td align="right">
<a4j:commandLink rendered="#{applicationBean.currentPage !='SESSIONDETAIL'}"  value="Logout" action="#{logoutBean.logOut}"/>
<a4j:commandLink rendered="#{applicationBean.currentPage =='SESSIONDETAIL'}" reRender="leave-confirm-modal-panel-id"  value="Logout" actionListener="#{sessionBean.leaveLiveSessionListener}" oncomplete="#{rich:component('leave-confirm-modal-panel-id')}.show()">
<a4j:actionparam name="currentLinkClicked" value="LOGOUT" assignTo="#{applicationBean.linkClicked}"/>
</a4j:commandLink>

</td>
<td width="80" align="center" valign="middle">

<h:graphicImage id="ea-header-image-id" rendered="#{applicationBean.userPic != null}" value="#{applicationBean.userPic}" width="75" height="75"></h:graphicImage>
<h:graphicImage id="ea-header-image-id1" rendered="#{applicationBean.userPic == null}" value="/images/noPhoto.jpg" width="75" height="75"></h:graphicImage>
</td>
</tr>
<tr>
<td>
</td>
</tr>
<tr>
<td colspan="2" align="right">
<a4j:commandLink rendered="#{applicationBean.userType =='admin'}"  value="Manage Data" action="#{maintenanceBean.dataMaintenanceAction}"></a4j:commandLink>
&nbsp;
<span class="ea-content-small-class">Logged in as </span>
<a4j:commandLink rendered="#{applicationBean.currentPage !='SESSIONDETAIL'}"  value="#{USERNAME}" action="#{userBean.userProfilePopulate}"></a4j:commandLink>
<a4j:commandLink rendered="#{applicationBean.currentPage =='SESSIONDETAIL'}" reRender="leave-confirm-modal-panel-id"  value="#{USERNAME}" actionListener="#{sessionBean.leaveLiveSessionListener}" oncomplete="#{rich:component('leave-confirm-modal-panel-id')}.show()">
<a4j:actionparam name="currentLinkClicked" value="PROFILE" assignTo="#{applicationBean.linkClicked}"/>
</a4j:commandLink>

</td>
</tr>
</table>
</h:panelGroup>
</h:panelGrid>
</h:panelGroup>
<h:panelGroup>
<rich:separator lineType="solid" height="2"></rich:separator>
</h:panelGroup>
<h:panelGroup>

</h:panelGroup>


</a4j:form>
</h:panelGrid>