<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>

<style type="text/css">

.ea-social-profile-outer-grid-row1{

height: 10px;

}

.ea-social-profile-outer-grid-row2{
vertical-align:top;
height: 400px;

}
.ea-social-profile-outer-grid-row3{

height: 10px;
/*background:green;*/
}

.ea-social-profile-header-col1{
width:490px;
padding: 0;
margin: 0;
border: 0;

}
.ea-social-profile-header-col2{
width:10px;
padding: 0;
margin: 0;
border: 0;
text-align: right;

}

.ea-social-profile-footer-col1{
width:470px;
padding: 0;
margin: 0;
border: 0;

}

.ea-social-profile-footer-col2{
width:20px;
padding: 0;
margin: 0;
border: 0;

}

.ea-social-profile-body-col1{
/*background: blue;*/
width:5px;
padding: 0;
margin: 0;
border: 0;

}

.ea-social-profile-body-col2{
/*background: olive;*/
width:495px;
padding: 0;
margin: 0;
border: 0;

}
.ea-social-profile-body-panel-body-class{

height:380px;
overflow:auto;
background: white;

}

</style>

<script type="text/javascript">

function viewEventDetails(eventId){

	document.getElementById("modalEventDetailsForm:eventId").value = eventId;
	//document.getElementById("modalEventDetailsForm:submitEventIdforAgenda").click();
	
}

function popup(url) 
{
 var width  = 600;
 var height = 400;
 var left   = (screen.width  - width)/2;
 var top    = (screen.height - height)/2;
 var params = 'width='+width+', height='+height;
 params += ', top='+top+', left='+left;
 params += ', directories=no';
 params += ', location=no';
 params += ', menubar=no';
 params += ', resizable=no';
 params += ', scrollbars=yes';
 params += ', status=no';
 params += ', toolbar=no';
 newwin=window.open(url,'windowname5', params);
 if (window.focus) {newwin.focus();}
 return false;
}
</script>


<rich:modalPanel style="padding:0;margin:0;" width="535" height="500" id="ea-user-social-profile-modal-Panel">
<!-- hidden field values -->
<f:facet name="header">
            <h:panelGroup>
                <h:outputText value="Public Profile of #{socialMediaBean.twitterDTO.userName}"></h:outputText>
            </h:panelGroup>
</f:facet>
<f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/images/close.png" styleClass="hidelink" id="modalHidelink"/>
                <rich:componentControl for="ea-user-social-profile-modal-Panel" attachTo="modalHidelink" operation="hide" event="onclick"/>
            </h:panelGroup>
</f:facet>
<a4j:form id="modalUserSocialProfileForm">

<h:panelGrid  rowClasses="ea-social-profile-outer-grid-row2,ea-social-profile-outer-grid-row3" columns="1">

<h:panelGroup>
<!-- Body Part -->
			
			<h:panelGrid columnClasses="ea-social-profile-body-col1, ea-social-profile-body-col2" columns="2">
			<h:panelGroup>
			
			</h:panelGroup>
					
			<h:panelGroup>
<rich:panel bodyClass="ea-social-profile-body-panel-body-class">
			
			
				<h:panelGrid columnClasses="ea-col1" columns="1">
<h:panelGroup rendered="#{socialMediaBean.twitterDTO.userName!=null}">
			<h:graphicImage value="/images/twitter_logo.jpg" />
			<h:outputText styleClass="ea-heading-class" value="Twitter Details"></h:outputText>
			</h:panelGroup>
		<h:panelGroup rendered="#{socialMediaBean.twitterDTO.userName!=null}">			
		<h:outputText styleClass="ea-content-bold-class" value="Name :"/><a4j:commandLink value="#{socialMediaBean.twitterDTO.userName}" onclick="popup('#{socialMediaBean.twitterDTO.userURL}')"/>
		</h:panelGroup>
		<h:panelGroup rendered="#{socialMediaBean.twitterDTO.userImg!=null}">
		<h:graphicImage value="#{socialMediaBean.twitterDTO.userImg}" id="img1"  onmouseover="enlargePic(this, {pwidth:'100px'})" 
onmouseout="releasePic(this)" style="cursor:hand; border:1px solid grey">
<rich:toolTip id="tooltip1" for="img1" value="Zoom">
			
			</rich:toolTip>
</h:graphicImage>
		</h:panelGroup>
		<h:panelGroup rendered="#{socialMediaBean.twitterDTO.userDesc!=null}">			
		<h:outputText styleClass="ea-content-bold-class" value="Description :" /><h:outputText  styleClass="ea-content-class" value="#{socialMediaBean.twitterDTO.userDesc}" />
		</h:panelGroup>
		<h:panelGroup rendered="#{socialMediaBean.twitterDTO.personalUrl!=null}">			
		<h:outputText styleClass="ea-content-bold-class" value="Personal URL:" /><a4j:commandLink value="#{socialMediaBean.twitterDTO.personalUrl}" onclick="popup('#{socialMediaBean.twitterDTO.personalUrl}')"/>
		</h:panelGroup>
		
		<h:panelGroup rendered="#{socialMediaBean.twitterDTO.friendsCount!=null}">			
		<h:outputText styleClass="ea-content-bold-class" value="Following Count :"/><h:outputText styleClass="ea-content-class"  value="#{socialMediaBean.twitterDTO.friendsCount}" />
		</h:panelGroup>
		<h:panelGroup rendered="#{socialMediaBean.twitterDTO.followersCount!=null}">			
		<h:outputText styleClass="ea-content-bold-class" value="Followers Count :"/><h:outputText styleClass="ea-content-class" value="#{socialMediaBean.twitterDTO.followersCount}"/>
		</h:panelGroup>
		
	
		<h:panelGroup>
		<rich:separator  rendered="#{socialMediaBean.faceBookDTO.name!=null}"></rich:separator>
		</h:panelGroup>				
		<h:panelGroup  rendered="#{socialMediaBean.faceBookDTO.name!=null}" >
			<h:graphicImage value="/images/facebook_logo.jpg" />
			<h:outputText styleClass="ea-heading-class" value="FaceBook Details"></h:outputText>
			</h:panelGroup>
			<h:panelGroup rendered="#{socialMediaBean.faceBookDTO.profileImg!=null}">
			<h:graphicImage value="#{socialMediaBean.faceBookDTO.profileImg}" 
			id="img2"  onmouseover="enlargePic(this, {pwidth:'100px'})" 
onmouseout="releasePic(this)" style="cursor:hand; border:1px solid grey">
<rich:toolTip id="tooltip2" for="img2" value="Zoom">
			
			</rich:toolTip>
			</h:graphicImage>
			</h:panelGroup>
			<h:panelGroup rendered="#{socialMediaBean.faceBookDTO.name!=null}">
				<h:outputText styleClass="ea-content-bold-class"  value="Name :"/><a4j:commandLink  value="#{socialMediaBean.faceBookDTO.name}" onclick="popup('#{socialMediaBean.faceBookDTO.profileUrl}')" />
			</h:panelGroup>			
		<h:panelGroup>
		<rich:separator rendered="#{socialMediaBean.linkedInDTO.name!=null}"></rich:separator>
		</h:panelGroup>	
			<h:panelGroup rendered="#{socialMediaBean.linkedInDTO.name!=null}">
			<h:graphicImage value="/images/linkedin_logo.jpg" />
			<h:outputText styleClass="ea-heading-class" value="LinkedIn Details"></h:outputText>
			</h:panelGroup>
			<h:panelGroup rendered="#{socialMediaBean.linkedInDTO.name!=null}">
			<h:outputText styleClass="ea-content-bold-class" value="Name :"/><a4j:commandLink value="#{socialMediaBean.linkedInDTO.name}" onclick="popup('#{socialMediaBean.linkedInDTO.pubProfielUrl}')" />			
			</h:panelGroup>
			<h:panelGroup rendered="#{socialMediaBean.linkedInDTO.profileImg!=null}">
			<h:graphicImage value="#{socialMediaBean.linkedInDTO.profileImg}"
			id="img3"  onmouseover="enlargePic(this, {pwidth:'100px'})" 
onmouseout="releasePic(this)" style="cursor:hand; border:1px solid grey">
<rich:toolTip id="tooltip3" for="img3" value="Zoom">
			
			</rich:toolTip> 
			</h:graphicImage>
			</h:panelGroup>
			<h:panelGroup rendered="#{socialMediaBean.linkedInDTO.headLine!=null}">
			<h:outputText styleClass="ea-content-bold-class" value="HeadLine :"/><h:outputText  styleClass="ea-content-class" value="#{socialMediaBean.linkedInDTO.headLine}"/>
			</h:panelGroup>
			<h:panelGroup rendered="#{socialMediaBean.linkedInDTO.industry!=null}">
			<h:outputText styleClass="ea-content-bold-class" value="Industry :" /><h:outputText  styleClass="ea-content-class" value="#{socialMediaBean.linkedInDTO.industry}"/>
			</h:panelGroup>
			<h:panelGroup rendered="#{socialMediaBean.linkedInDTO.specialities!=null}">
			<h:outputText styleClass="ea-content-bold-class" value="Specialities :" /><h:outputText  styleClass="ea-content-class" value="#{socialMediaBean.linkedInDTO.specialities}"/>
			</h:panelGroup>
			<h:panelGroup  rendered="#{socialMediaBean.linkedInDTO.summary!=null}">
			<h:outputText styleClass="ea-content-bold-class" value="Summary :"/><h:outputText  styleClass="ea-content-class" value="#{socialMediaBean.linkedInDTO.summary}"/>
			</h:panelGroup>
			<h:panelGroup rendered="#{socialMediaBean.linkedInDTO.locationName!=null}">
			<h:outputText styleClass="ea-content-bold-class" value="Location :" /><h:outputText  styleClass="ea-content-class" value="#{socialMediaBean.linkedInDTO.locationName}"/>
			</h:panelGroup>
			<h:panelGroup rendered="#{socialMediaBean.linkedInDTO.connectionTotal!=null}">
			<h:outputText styleClass="ea-content-bold-class" value="Connections :" /><h:outputText  styleClass="ea-content-class" value="#{socialMediaBean.linkedInDTO.connectionTotal}"/>
			</h:panelGroup>
<rich:jQuery name="enlargePic" timing="onJScall" query="animate({width:param.pwidth})" />
<rich:jQuery name="releasePic" timing="onJScall" query="animate({width:'50px'})"/>

</h:panelGrid>
</rich:panel>
			
			
			</h:panelGroup>
			
			
			</h:panelGrid>
			  
			
			
			<!-- End of body part -->
</h:panelGroup>

<h:panelGroup>
 	<h:outputLink value="#" id="link">
        close 
     <rich:componentControl for="ea-user-social-profile-modal-Panel" attachTo="link" operation="hide" event="onclick"/>
    </h:outputLink>            
    </h:panelGroup>


</h:panelGrid>
</a4j:form>
</rich:modalPanel>
