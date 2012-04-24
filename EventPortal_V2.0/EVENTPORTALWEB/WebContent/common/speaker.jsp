<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<h:panelGroup rendered="#{applicationBean.currentPage =='SESSIONDETAIL' && applicationBean.currentSession!=null && applicationBean.currentSpeaker!=null}">
<h:outputText rendered="#{applicationBean.currentSession!=null && applicationBean.currentSpeaker!=null}" id="sessionSpeakerInfoTip"   styleClass="ea-content-bold-class" value="Speaker of the session - #{applicationBean.currentSpeaker}">
   <rich:toolTip styleClass="tooltip"  id="sessionSpeakerInfoToolTip" for="sessionSpeakerInfoTip" layout="inline"  value="#{applicationBean.currentSpeakerToolTip}"/>

</h:outputText>
</h:panelGroup>