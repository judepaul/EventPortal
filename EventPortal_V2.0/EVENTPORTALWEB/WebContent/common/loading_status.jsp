<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<script type="text/javascript">

function disableScreen(){

	var freezeDiv = document.createElement("div");
   	freezeDiv.id = "freezeDiv";
   	freezeDiv.style.cssText = "position:absolute; top:0; right:0; width:" + screen.width + "px; height:" + screen.height + "px; background-color: #cccccc; opacity:0.5; filter:alpha(opacity=50)";    
   	document.getElementsByTagName("body")[0].appendChild(freezeDiv );
}

function enableScreen(){
	var freezeDiv = document.getElementById("freezeDiv");
	if(freezeDiv != null){
		document.getElementsByTagName("body")[0].removeChild(freezeDiv);		
	}
	
}

   </script>
   
<style type="text/css">
.mainStatus-start-classs{

	background-color: #ffA500;
   font-weight:bold;
   position: absolute; 
   right: 5px; 
   top: 1px; 
   width: 100px;
}

</style>
<a4j:status onstart="disableScreen();" onstop="enableScreen();">
                <f:facet name="start">
                    <h:graphicImage  value="/images/loading.gif" />
                </f:facet>
 </a4j:status>
 
 