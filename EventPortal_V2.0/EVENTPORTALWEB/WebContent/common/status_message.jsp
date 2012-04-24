<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<style type="text/css">

.info, .success, .warning, .error, .validation {
border: 1px solid;
margin: 10px 0px;
padding:8px 10px 8px 50px;
background-repeat: no-repeat;
background-position: 10px center;
}
.info {
color: #00529B;
background-color: #BDE5F8;
background-image: url('../images/info.png');
}
.success {
color: #4F8A10;
background-color: #DFF2BF;
background-image:url('../images/success.png');
}

.warning {
color: #9F6000;
background-color: #FEEFB3;
background-image: url('../images/warning.png');
}
.error {
color: #D8000C;
background-color: #FFBABA;
background-image: url('../images/error.png');
}

.validation {
color: #D63301;
background-color: #FFCCBA;
background-image: url('../images/validation.png');
}


</style>

<a4j:outputPanel style="z-index:1000; position: absolute; left: 15px; top: 120px;" id="message-panel"
 onclick="hideMessages({delay:0.5,duration:0.9});">
<rich:effect name="hideMessages" for="message-panel" type="Fade" />  
<rich:messages  errorClass="error" infoClass="success" fatalClass="validation" warnClass="warning" id="message-panel-show"></rich:messages>
</a4j:outputPanel>

 
                

