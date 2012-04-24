<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>

<script>
function enableSelect(){
	alert('Hi');	
    //var x=document.getElementById('profileForm:mailOption1');
    //x.disabled=true;
    //alert(document.getElementById('profileForm:mailOption1').value);	
    document.getElementById('profileForm:mailOption1').click();
}

</script>

<style type="text/css">

.ea-event-info-row-class{
 
 	height: 80px;
 
}
.ea-agenda-row-class{
   vertical-align: top;
	height: 320px;

}
.ea-header-info-col1{

width:350px;
background: white;

}

.ea-header-info-col2{

width:300px;
background: white;

}
.ea-header-info-col3{

width:230px;
background: white;

}
.ea-header-info-col4{

width:120px;
background: white;

}
.ea-header-info-row{
height:30px;
background: white;

}
.tooltip {
   border-width:0px;
   padding:0px;
   border-style:none;    
   -moz-border-radius: 10px;    /* Rounded border mozilla browsers */
   -webkit-border-radius: 10px; /* Rounded border webkit browsers */
   border-radius: 10px; 
}

.breadcrumb-active {
	font-size: 13px;
	line-height:18px;
	float:left;
	text-align:left;
	margin:0;
	color:#c14b59;
	font-weight: bold;
}
.breadcrumb-active a:link, a:visited, a:active {
	color:#c14b59;
	text-decoration:none;
}
.breadcrumb-active a:hover {
	color:#000000;
	text-decoration:none;
}
.breadcrumb-normal{

	font-size: 13px;
	line-height:18px;
	float:left;
	text-align:left;
	margin:0;
	color:#000000;
	font-weight: bold;
	
}
.default-white-panel{
	border: 0;
	width: 100%;
	height: 30px;
	/*background-color: green;*/
	padding: 0;
	margin:0;
}
.ea-breadcrumb-col{
border: 1px;
/*background-color: #014040;*/
}


</style>

<a4j:form id="profileForm" enctype="multipart/form-data">
<h:panelGrid columns="1" rowClasses="ea-header-info-row">
<h:panelGroup>
<h:panelGrid columns="4" columnClasses="ea-header-info-col1,ea-header-info-col2,ea-header-info-col3,ea-header-info-col4">
<h:panelGroup>
<h:panelGrid columns="3">
<h:panelGroup styleClass="breadcrumb-active">
<a4j:commandLink value="Events" action="#{eventBean.populateEventsPage}"></a4j:commandLink>
</h:panelGroup>
<h:panelGroup>
<h:graphicImage value="/images/breadcrumb_arrow.jpg" ></h:graphicImage>
</h:panelGroup>
<h:panelGroup>
<h:outputText styleClass="ea-content-bold-class" value="Profile">
</h:outputText>
</h:panelGroup>
</h:panelGrid>
</h:panelGroup>

<h:panelGroup>

</h:panelGroup>

<h:panelGroup>

</h:panelGroup>

<h:panelGroup>
<jsp:include page="../common/loading_status.jsp" />
<jsp:include page="../common/status_message.jsp" />
</h:panelGroup>
</h:panelGrid>

</h:panelGroup>

</h:panelGrid>

<rich:tabPanel id="mainTab" switchType="client" styleClass="profile-tab-panel" selectedTab="#{userBean.selectedTab}" >

<rich:tab name="accountSettings" label="Account Settings">
<rich:panel styleClass="tab-container">

<jsp:include page="profile_settings_account.jsp"></jsp:include>

</rich:panel>
</rich:tab>

<rich:tab name="profilePicture" label="Profile Picture">
<rich:panel styleClass="tab-container">

<jsp:include page="profile_settings_picture.jsp"></jsp:include>

</rich:panel>
</rich:tab>

<rich:tab name="contactInformation" label="Contact Information">
<rich:panel styleClass="tab-container">
<jsp:include page="profile_settings_contactinfo.jsp"></jsp:include>
</rich:panel>
</rich:tab>

<rich:tab id="socialNetworks" name="socialNetworks" label="Social Networks" >
<rich:panel styleClass="tab-container">

<jsp:include page="profile_settings_socialmedia.jsp"></jsp:include>

</rich:panel>
</rich:tab>
<rich:tab id="speakerSettings" name="speakerSettings" label="Speaker Settings" >
<rich:panel styleClass="tab-container">

<jsp:include page="profile_settings_speaker.jsp"></jsp:include>

</rich:panel>
</rich:tab>

</rich:tabPanel>
	<div class="save-changes-btn">
<a4j:commandButton id="saveChangesBtn"  value="Save Changes" reRender="mainTab,imgArea,header-image-area-id" actionListener="#{userBean.updateProfileSettings}"></a4j:commandButton>
</div>
</a4j:form>