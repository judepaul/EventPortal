<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>
<style type="text/css">

.ea-all-event-tweet-panel-body-class{

	height:300px;
	overflow:auto;

}
.tooltip {
    background:#ECFFFF;
   border: 2px solid #06266F;  
   padding:5px;    
   -moz-border-radius: 10px;    /* Rounded border mozilla browsers */
   -webkit-border-radius: 10px; /* Rounded border webkit browsers */
   border-radius: 10px; 
   
}
</style>
<script type="text/javascript">


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
<a4j:region id="eventsTwitterRegion">
 <div class="rich-panel-header" style="vertical-align:middle;padding:0;top-margin:0; height : 25px;">
 <table border="0" cellpadding="0" cellspacing="0" height="100%" width="100%">
 <tr>
 <td>&nbsp;</td>
 <td class="ea-heading-class">
 All Event Tweets
 </td>
 <td width="20">&nbsp;</td>
 <td> 
 <h:panelGroup id="ea-tweet-search-panel-group">
  <h:inputText value="#{socialMediaBean.eventTag}" ></h:inputText>
  <rich:spacer width="10"></rich:spacer><a4j:commandButton status="tweetStatus" reRender="ea-tweet-search-panel-group,twitterSearchList" actionListener="#{socialMediaBean.searchTweets}" value="Search"></a4j:commandButton>
</h:panelGroup> 
 </td>
 </tr>
 </table> 
 </div>
<rich:panel id="ea-all-event-tweet-panel" bodyClass="ea-all-event-tweet-panel-body-class">

<a4j:status id="tweetStatus">
                <f:facet name="start">
                    <h:graphicImage  value="/images/loading-tweets.gif" />
                </f:facet>
 </a4j:status>

<rich:dataTable id="twitterSearchList" columnClasses="col"
						            			value="#{socialMediaBean.allEventTwitterDTO.tweetList}" var="tweetValues">
													<h:column>
													
											            	<h:graphicImage  id="img-email1"   alt="#{tweetValues.userName}" onclick="popup('#{tweetValues.userScreeName}')" width="30px" height="30px" value="#{tweetValues.userImg}" style="cursor:hand; border:1px solid grey">
											            	<rich:toolTip styleClass="tooltip" id="tool-email1" for="img-email1" value="#{tweetValues.userName}">
			
			</rich:toolTip>
											            	
											            	</h:graphicImage>
											         				            
									                   	</h:column>
						            					<h:column>	
						                					<h:outputText value="#{tweetValues.tweet}"  escape="false"  />      
						                					<rich:spacer height="1px" width="10px" /><h:outputText value="#{tweetValues.tweetCreatedAt}"/>         
											            </h:column>
</rich:dataTable>
<h:panelGroup rendered="#{socialMediaBean.twitterProblem}">
<h:outputText styleClass="errormsg" value="#{socialMediaBean.twitterProblemMessage}"></h:outputText>
</h:panelGroup>
</rich:panel>
</a4j:region>