<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>


<style type="text/css">

.search-panel{
width:100%;
height:100%; 
 border: 0;

}
</style>	
	
<rich:panel id="searchBox">
<a4j:region id="searchEventsRegion">
			<h:inputText value="#{eventBean.searchKey}"></h:inputText>
			<rich:spacer width="10"></rich:spacer>			
			<a4j:commandButton status="searchEventsStatus" reRender="eventList,searchBox" actionListener="#{eventBean.searchEventsListener}" value="Search"></a4j:commandButton>
			<a4j:status id="searchEventsStatus" for="searchEventsRegion">
                <f:facet name="start">
                    <h:graphicImage  value="/images/searching-events.gif" />
                </f:facet>               
 </a4j:status>
 <h:panelGroup rendered="#{eventBean.searchResult}">
 <h:outputText styleClass="ea-content-class" value="Search Results for "></h:outputText>
 <h:outputText styleClass="ea-heading-class" value="#{eventBean.searchValue}"></h:outputText>
 </h:panelGroup>	
  </a4j:region>		 
</rich:panel>