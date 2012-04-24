<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>

<style type="text/css">

.ea-session-comments-list-panel-body-class{

height:150px;
overflow:auto;
background-color: white;

}

.ea-comment-col1{

vertical-align: top;
width: 300px;
background-color: white;
}

.ea-comment-col2{

vertical-align: top;
width: 280px;
background-color: white;
}
.toolTipTFL {
	 background:#ECFFFF;
   border: 2px solid #06266F;  
   padding:5px;    
   -moz-border-radius: 10px;    /* Rounded border mozilla browsers */
   -webkit-border-radius: 10px; /* Rounded border webkit browsers */
   border-radius: 10px; 
   
} 
</style>


<h:panelGrid columns="1" columnClasses="ea-comment-col1">

<h:panelGroup>

<rich:panel >
                    <f:facet name="header">
                        <h:outputText value="Your Comments!"  />   
                    </f:facet>
                    <a4j:region id="updateCommentRegion">
                    <h:panelGrid columns="2" id="commentArea">  
                    <h:panelGroup>                  
                        <h:inputTextarea rows="3" cols="75" value="#{sessionBean.sessionCommentValue}"/> 
                     </h:panelGroup>
                     <h:panelGroup>
                          <h:panelGrid columns="1"> 
                     
                     <h:panelGroup  rendered="#{socialMediaBean.twitterConnected==true}">
                     <h:column>
                      <h:selectBooleanCheckbox  value="#{sessionBean.twitterCheck}"/>
                      </h:column>
                      <h:column>
                     <h:graphicImage id="tId"  value="/images/twitter_logo.jpg">
                   <rich:toolTip styleClass="toolTipTFL" id="tooltip1" for="tId" value="Post your comments in Twitter"/>
			
			
                     </h:graphicImage>
                     </h:column>
                      </h:panelGroup>
                      
                       <h:panelGroup rendered="#{socialMediaBean.faceBookConnected==true}">
                     <h:column>
                      <h:selectBooleanCheckbox value="#{sessionBean.facebookCheck}"/>
                      </h:column>
                      <h:column>
                    <h:graphicImage  id="fbId"  value="/images/facebook_logo.jpg" style=" width : 15px; height : 15px;">
                           <rich:toolTip styleClass="toolTipTFL"  id="tooltip2" for="fbId" value="Post your comments in FaceBook"/>
                    </h:graphicImage>
                     </h:column>
                      </h:panelGroup>
 
                       <h:panelGroup rendered="#{socialMediaBean.linkedInConnected==true}">
                     <h:column>
                        <h:selectBooleanCheckbox value="#{sessionBean.linkedinCheck}"/>
                      </h:column>
                      <h:column>
                    <h:graphicImage id="linId" value="/images/linkedin_logo.jpg">
                    <rich:toolTip id="tooltip3" styleClass="toolTipTFL"  for="linId" value="Post your comments in LinkedIn"/>
                    </h:graphicImage>
                     </h:column>
                      </h:panelGroup>
                     
                     </h:panelGrid>
                     </h:panelGroup>
                     
                     <h:panelGroup>
                         <a4j:status id="commentUpdateStatus" for="updateCommentRegion">
                <f:facet name="start">
                    <h:graphicImage  value="/images/updating-comments.gif" />
                </f:facet>
 				</a4j:status>
                     </h:panelGroup>
                     
                     <h:panelGroup>
                     
                          <a4j:commandButton reRender="sessionCommentList,commentArea,message-panel" value="Comment" actionListener="#{sessionBean.updateSessionComment}"></a4j:commandButton>
                     </h:panelGroup>
                       
                   </h:panelGrid>
                   </a4j:region>
</rich:panel>


</h:panelGroup>


<h:panelGroup>
<rich:panel id="ea-session-comments-list-panel" bodyClass="ea-session-comments-list-panel-body-class">
<f:facet name="header">
                <h:outputText value="Session Comments" />
</f:facet>
<a4j:region id="commentListRegion">
<a4j:status id="specificStatus" for="commentListRegion">
                <f:facet name="start">
                    
                </f:facet>
 </a4j:status>
 
 <h:inputHidden id="sessionCommentLikeId" value="#{attendeeBean.hiddenLikeId}"></h:inputHidden>
 <rich:dataTable width="500" id="sessionCommentList" columnClasses="col" 
						            			value="#{sessionBean.sessionCommentsList}" var="commentValues">
						            			
						            			   <h:column>
						    				            	<h:graphicImage width="30" id="prfId" height="30" rendered="#{commentValues.profileDTO.profImgFileName != '/images/null'}" value="#{commentValues.profileDTO.profImgFileName}">
											            	    <rich:toolTip styleClass="tooltip"  id="tooltip" for="prfId" value="#{commentValues.profileDTO.profileToolTip}"/>
											            	</h:graphicImage>
											          
											            	<h:graphicImage width="30" id="prfId1" height="30" rendered="#{commentValues.profileDTO.profImgFileName == '/images/null'}" value="/images/noPhoto.jpg">
											            	    <rich:toolTip styleClass="tooltip"  id="tooltip11" for="prfId1" value="#{commentValues.profileDTO.profileToolTip}"/>
											            	</h:graphicImage>
											             	 					            
									                   	</h:column>
						            					<h:column>	
						                					<h:outputText value="#{commentValues.scComment}" />
						                					<rich:spacer width="10"></rich:spacer>
						                					 </h:column>
						                					 <h:column>	
						                					 <h:outputText value="#{commentValues.sessionCommentTime}" />  
						                					<rich:spacer width="10"></rich:spacer>
						                					</h:column>
						                					 <h:column>							                					 
						                					<a4j:commandButton status="commentListRegion"  image="/images/like.jpg" onclick="setSessionCommentLikeId('#{commentValues.scId}')" reRender="sessionCommentList,ea-session-like-id"  actionListener="#{attendeeBean.sessionCommentLikeListener}">						                						
						                					</a4j:commandButton> 	
						                					<h:outputText value="#{commentValues.sessionLikeCount}"></h:outputText>					                					
											            </h:column>
						            			
						            			
						            	    </rich:dataTable>
						            	     
						            	    
	</a4j:region>					            	
</rich:panel>		
</h:panelGroup>




</h:panelGrid>
				            	    
