<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>


<style type="text/css">

.ea-event-attendee-panel-body-class{

height:150px;
overflow:auto;

}
.tooltip {

           
            border-width:0px;
            padding:0px;
            border-style:none;    
     -moz-border-radius: 10px;    /* Rounded border mozilla browsers */
    -webkit-border-radius: 10px; /* Rounded border webkit browsers */
    border-radius: 10px; 
        }
</style>
<script type="text/javascript">


function openWindow(mediaId){
	  popup(mediaId); 
	//Richfaces.showModalPanel('url-open-modal-panel-id'); 
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

<rich:panel id="ea-event-attendee-panel" bodyClass="ea-event-attendee-panel-body-class">
<f:facet name="header">
               <h:outputText value="Event Attendees : #{attendeeBean.attendeeDTO.attendeeCount}" />
            </f:facet>
<rich:dataTable width="337" id="attendeeListTable" border="0"  columnClasses="col"
						            value="#{attendeeBean.attendeeDTO.attendeeList}" var="attendeeListVar">
						            <h:column>							            					            
						            	<h:graphicImage id="profiletip"  width="30" rendered="#{attendeeListVar.profImgFileName != '/images/null'}" height="30" value="#{attendeeListVar.profImgFileName}">
						            	                <rich:toolTip styleClass="tooltip" id="profiletooltip" for="profiletip" layout="inline"  value="#{attendeeListVar.profileToolTip}"/>
						      
						            	</h:graphicImage>			
						            	<h:graphicImage id="profiletip1"  rendered="#{attendeeListVar.profImgFileName == '/images/null'}"  width="30" height="30" value="/images/noPhoto.jpg">
						            	                <rich:toolTip styleClass="tooltip" id="profiletooltip1" for="profiletip1" layout="inline"  value="#{attendeeListVar.profileToolTip}"/>
						      
						            	</h:graphicImage>			            
						                <rich:spacer  rendered="#{attendeeListVar.speakerCheck}" width="5" />
						                <h:graphicImage id="tooltipStar1" width="15" height="15" rendered="#{attendeeListVar.speakerCheck}"  value="/images/star.jpg" >
						             <!--          <rich:toolTip id="tooltip1" for="tooltipStar1" layout="inline"  value="<h1>SPEAKER</h1> - #{attendeeListVar.speakerFor}"/>-->
						          
						                </h:graphicImage>
						                <rich:spacer width="5" />
						                <h:outputText  value="#{attendeeListVar.firstName} #{attendeeListVar.lastName} " />
						                <rich:spacer rendered="#{attendeeListVar.twitterProfileUrl != null}" width="5" />
						                <h:graphicImage style="cursor:hand;"  width="15" height="15" onclick="openWindow('#{attendeeListVar.socialMediaDTO.twitterProfileUrl}')" rendered="#{attendeeListVar.socialMediaDTO.twitterProfileUrl != null}" value="/images/twitter_logo.jpg"/>
						                <h:graphicImage  width="15" height="15" rendered="#{attendeeListVar.socialMediaDTO.twitterProfileUrl == null}" value="/images/gray_twitter_logo.jpg"/>
						         	
						         		<rich:spacer rendered="#{attendeeListVar.socialMediaDTO.faceBookProfileUrl != null}" width="5" />
						         		<h:graphicImage style="cursor:hand;" width="15" height="15" onclick="openWindow('#{attendeeListVar.socialMediaDTO.faceBookProfileUrl}')" rendered="#{attendeeListVar.socialMediaDTO.faceBookProfileUrl != null}" value="/images/facebook_logo.jpg"/>
						         		<h:graphicImage width="15" height="15" rendered="#{attendeeListVar.socialMediaDTO.faceBookProfileUrl == null}" value="/images/gray_facebook_logo.jpg"/>
						                <rich:spacer rendered="#{attendeeListVar.socialMediaDTO.linkedInProfileUrl != null}" width="5" />
						                <h:graphicImage style="cursor:hand;" width="15" height="15" onclick="openWindow('#{attendeeListVar.socialMediaDTO.linkedInProfileUrl}')" rendered="#{attendeeListVar.socialMediaDTO.linkedInProfileUrl != null}" value="/images/linkedin_logo.jpg"/>
						                <h:graphicImage width="15" height="15" rendered="#{attendeeListVar.socialMediaDTO.linkedInProfileUrl == null}" value="/images/gray_linkedin_logo.jpg"/>						                
							         						                
							         	<rich:spacer width="5" />							         	      	
						                <a4j:commandButton style="cursor:hand;"  image="/images/connect.jpg"   actionListener="#{socialMediaBean.connectViaSMListener}"  onclick="connectSocialMedia('#{attendeeListVar.profileId}','#{attendeeListVar.firstName} #{attendeeListVar.lastName}','#{attendeeListVar.socialMediaDTO.twitterFollow}','#{attendeeListVar.socialMediaDTO.faceBookFriendsConnect}','#{ attendeeListVar.socialMediaDTO.linkedInFriendsConnect}');" rendered="#{(attendeeListVar.socialMediaDTO.twitterFollow == true || attendeeListVar.socialMediaDTO.faceBookFriendsConnect == true || attendeeListVar.socialMediaDTO.linkedInFriendsConnect == true) && (attendeeListVar.currentUser== false)}"  reRender="ea-socialMedia-invite-modal-Panel"  oncomplete="#{rich:component('ea-socialMedia-invite-modal-Panel')}.show()"  />
						                <h:graphicImage  value="/images/gray_connect.jpg"  rendered="#{(attendeeListVar.socialMediaDTO.twitterFollow == false && attendeeListVar.socialMediaDTO.faceBookFriendsConnect == false && attendeeListVar.socialMediaDTO.linkedInFriendsConnect == false) && (attendeeListVar.currentUser== false)}" /> 
							         	<rich:spacer width="5" />							         	
						                <a4j:commandButton rendered="#{attendeeListVar.currentUser== false}" image="/images/mail_logo.jpg" actionListener="#{attendeeBean.initSendMail}" reRender="modalConnectMailPanel" onclick="connectViaEmail('#{attendeeListVar.profileId}','#{attendeeListVar.firstName} #{attendeeListVar.lastName}');"  oncomplete="#{rich:component('modalConnectMailPanel')}.show()" /> 
						            </h:column>	
						            					
 </rich:dataTable>
 </rich:panel>
 

