<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:loadBundle basename="com.eventattend.portal.web.resources.ApplicationResources" var="msg"/>

<html>
      <head>
            <title>Event Attend - Manage Data</title>           
            <link href="../styles/styles_design.css" rel="stylesheet" type="text/css"/>
      </head>
<script type="text/javascript">
   
</script>

 <body>
<f:view>

		<!-- hidden field values -->
	<rich:page styleClass="outer-layout" sidebarPosition="left" sidebarWidth="300">
<f:facet name="header">
         <jsp:include page="../common/header_main.jsp" />
         </f:facet>    	
   <h:panelGrid styleClass="table-layout">   	
      <rich:layout>         
	  <rich:layoutPanel position="left" width="5">	 	 
	     </rich:layoutPanel>
	     <rich:layoutPanel position="center" width="100%">
	        <h:panelGrid styleClass="content-layout">
		    <!--  Body Content Starts here -->
		   
		   <jsp:include page="manage_data.jsp" />
		   
		    <!--  Body Content Ends here -->
		</h:panelGrid>
	     </rich:layoutPanel>
	     <rich:layoutPanel position="right" width="5">   
	     </rich:layoutPanel> 
	 </rich:layout>
      </h:panelGrid>
      <f:facet name="footer">
           <jsp:include page="../common/footer_main.jsp" />
      </f:facet> 
</rich:page>
<jsp:include page="../views/modalpanel/modalpanel_main.jsp" />
</f:view>

</body>
</html>
