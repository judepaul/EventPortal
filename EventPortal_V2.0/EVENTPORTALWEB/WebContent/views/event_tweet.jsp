<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.tweet-search-panel{

width:100%;


height: 25px;

}
.col1{

width:200px;

}

.col2{
width:110px;


}
.tweet-heading {
	font-size:15px;
	color:#c0595a;
	line-height:25px;
	font-weight: bold;
}
.default-black-content-right {
	font-size: 12px;
	line-height:18px;
	float:left;
	text-align:right;
	margin:0;
}

.ea-event-tweet-panel-body-class{

height:150px;
overflow:auto;

}
.tooltip2 {
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

<rich:panel id="ea-event-tweet-panel" bodyClass="ea-event-tweet-panel-body-class">

<f:facet name="header">
               <h:outputText value="Event Tweets: #{socialMediaBean.eventTag}" />
</f:facet>

<a4j:region> 
<a4j:status id="tweetStatus">
                <f:facet name="start">
                    <h:graphicImage  value="/images/loading-tweets.gif" />
                </f:facet>
 </a4j:status>													
				
<rich:dataTable id="twitterSearchList" columnClasses="col"
						            			value="#{socialMediaBean.eventTwitterDTO.tweetList}" var="tweetValues">
									<h:column>
													
											            	<h:graphicImage  id="img-email1"   alt="#{tweetValues.userName}" onclick="popup('#{tweetValues.userScreeName}')" width="30px" height="30px" value="#{tweetValues.userImg}" style="cursor:hand; border:1px solid grey">
											            	<rich:toolTip styleClass="tooltip2" id="tool-email1" for="img-email1" value="#{tweetValues.userName}">
			
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
</a4j:region>
 </rich:panel>
