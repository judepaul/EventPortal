<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:loadBundle basename="com.eventattend.portal.web.resources.ApplicationResources" var="msg"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event Attend - Login</title>
<% String currentURL="http://" + request.getServerName() + ":" + request.getServerPort() +  request.getContextPath();  %>
<script src="<%=currentURL%>/scripts/CommonScript.js" language="javascript">
</script>
<link href="<%=currentURL%>/styles/styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=currentURL%>/scripts/jquery.js"></script>
<script type="text/javascript" src="<%=currentURL%>/scripts/loginScipts.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	  
	  
		$("#login").click(function() {
	cvars = document.getElementById("loginvalue").value;
	cvars = parseInt(cvars);				   
	//alert(cvars);
	  if(cvars==0)
  		{
		$("#loginbox").show();
		document.getElementById("login").src = "<%=currentURL%>/images/login-enable.gif";
		document.getElementById("loginvalue").value = 1;
		}
		else
		{
		$("#loginbox").hide();
		document.getElementById("login").src = "<%=currentURL%>/images/login.gif";
		document.getElementById("loginvalue").value = 0;
		}
	}); 

});

function echeck(str) {
		//alert('in echeck');
		//alert(str);
	var at="@";
	var dot=".";
	var lat=str.indexOf(at);
	var lstr=str.length;
	var ldot=str.indexOf(dot);
	if (str.indexOf(at)==-1){
	   alert("Invalid E-mail ID");
	   return false;
	}

	if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
	   alert("Invalid E-mail ID");
	   return false;
	}

	if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
	    alert("Invalid E-mail ID");
	    return false;
	}

	 if (str.indexOf(at,(lat+1))!=-1){
	    alert("Invalid E-mail ID");
	    return false;
	 }

	 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
	    alert("Invalid E-mail ID");
	    return false;
	 }

	 if (str.indexOf(dot,(lat+2))==-1){
	    alert("Invalid E-mail ID");
	    return false;
	 }
	
	 if (str.indexOf(" ")!=-1){
	    alert("Invalid E-mail ID");
	    return false;
	 }
	return true;
}

function validateLogin(){	
	var chk = false;
	var emailID = document.getElementById('loginForm:Email').value;
	if(emailID.length==0 && document.getElementById('loginForm:Password').value.length==0){
		alert("Email and Password can't be blank");
		emailID.focus();
		return false;
	}else if(emailID.length==0 && document.getElementById('loginForm:Password').value.length!=0){
		alert("Email can't be blank");
		emailID.focus();
		return false;
	}else if(emailID.length!=0 && document.getElementById('loginForm:Password').value.length==0){
		chk = echeck(emailID);		
		alert("Password can't be blank");
		emailID.focus();
		return false;
	}else if(document.getElementById('loginForm:Password').value.length==0){
		alert("Password can't be blank");		
		emailID.focus();
		return false;
	}else if(emailID.length==0){
		alert("Email can't be blank");	
		emailID.focus();
		return false;
	}else{		
		chk = echeck(emailID);
		//alert(chk);
		if(chk==true && document.getElementById('loginForm:Password').value.length!=0){
			//alert('hi');
			//document.getElementById('loginForm:chkLogin').click();
			if(document.getElementById('loginForm:rememberId').checked){
				//alert('hi1');
				toMem();
				return true;
			}
			if(document.getElementById('loginForm:rememberId').value==true){
				//alert('hi2');
				toMem();
				return true;
			}
			
			
		}		
		//emailID.value="";
		//emailID.focus();		
	}	

}


function newCookie(name,value) {
	 //alert("newCookie()");
	 var days = 10;   // the number at the left reflects the number of days for the cookie to last
	                 // modify it according to your needs
	 if (days) {
	   var date = new Date();
	   date.setTime(date.getTime()+(days*24*60*60*1000));
	   var expires = "; expires="+date.toGMTString(); }
	   else var expires = "";
	   document.cookie = name+"="+value+expires+"; path=/"; }

	function readCookie(name) {
	   var nameSG = name + "=";
	   var nuller = '';
	  if (document.cookie.indexOf(nameSG) == -1)
	    return nuller;

	   var ca = document.cookie.split(';');
	  for(var i=0; i<ca.length; i++) {
	    var c = ca[i];
	    while (c.charAt(0)==' ') c = c.substring(1,c.length);
	  if (c.indexOf(nameSG) == 0) return c.substring(nameSG.length,c.length); }
	    return null; }

	function eraseCookie(name) {
	  newCookie(name,"",1); }

	function toMem() {
		//alert("inside toMem()");
	    newCookie('theName', document.getElementById('loginForm:Email').value);     // add a new cookie as shown at left for every
	    newCookie('theEmail',document.getElementById('loginForm:Password').value);   // field you wish to have the script remember
	}

	function delMem() {
		//alert("inside delMem()");
	  eraseCookie('theName');   // make sure to add the eraseCookie function for every field
	  eraseCookie('theEmail');

	   document.getElementById('loginForm:Email').value = '';   // add a line for every field
	   document.getElementById('loginForm:Password').value = ''; }


	function remCookie() {
	document.getElementById('loginForm:Email').value = readCookie("theName");
	document.getElementById('loginForm:Password').value = readCookie("theEmail");
	}

	// Multiple onload function created by: Simon Willison
	// http://simon.incutio.com/archive/2004/05/26/addLoadEvent
	function addLoadEvent(func) {
	  var oldonload = window.onload;
	  if (typeof window.onload != 'function') {
	    window.onload = func;
	  } else {
	    window.onload = function() {
	      if (oldonload) {
	        oldonload();
	      }
	      func();
	    }
	  }
	}

	addLoadEvent(function() {
	  remCookie();
	});

</script>
</head>
<body id="homebg">
<f:view >
<a4j:region id="loginRegion">
    <div class="main-container">
    	<div class="header-container">
        	<div class="header-logo">
            	<img src="<%=currentURL%>/images/eventattend.png" alt="eventattend" title="eventattend"/>
            </div>
            <div class="login">
           		<img src="<%=currentURL%>/images/login.gif" alt="Login" title="Login" id="login" /></div>
            
            <input type="hidden" id="loginvalue" value="0"/>
            
  <div style="display:none" id="loginbox">

                      <a4j:form id="loginForm" onsubmit="return validateLogin();">
                      
                      <h:commandButton id="chkLogin" action="#{loginBean.checkLogin}"  style="display:none;"></h:commandButton>
                      	
                      <div class="vseperator5"></div>
                       <div class="vseperator2"></div>
                        <ul class="loginul logincontent">
                        	<li id="left"><strong>Email</strong></li>
                            <li id="spr">:</li>
                            <li id="right">
                            <h:inputText id="Email" styleClass="logininputbox" value="#{loginBean.eMail}" size="27" required="false">
                            </h:inputText>
                           
                            </li>
                        </ul>
                       <div class="vseperator2"></div>
                         <ul class="loginul logincontent">
                        	<li id="left"><strong>Password</strong></li>
                            <li id="spr">:</li>
                            <li id="right">
                            <h:inputSecret id="Password" styleClass="logininputbox" value="#{loginBean.password}" size="27" required="false"/>
                            
                            </li>
                        </ul>
                      <div class="vseperator1"></div>
                       <ul class="loginul logincontent">
                        	<li id="left">&nbsp;</li>
                            <li id="spr">&nbsp;</li>
                            <li id="right">
                            	<div class="floatleft">
                            	<h:selectBooleanCheckbox id="rememberId" value="" /></div>
                                <div class="floatleft">Remember Me</div>
                            </li>
                      </ul>
                      <div class="clear"></div>
                      <h:messages styleClass="errormsg"/>
                      <a4j:status id="loginStatus" for="loginRegion">
                			<f:facet name="start">
                   			 <h:graphicImage  value="/images/loading_login.gif" />
                				</f:facet>
 						</a4j:status>
                      <div class="clear"></div>
                     
                        	<div class="logincontent"><h:commandLink action="#{loginBean.userRegister}"> Create an Account / Sign Up</h:commandLink></div>
                            <div class="floatright">
                            <a4j:commandButton status="loginStatus" value="Sign in" styleClass="loginbtn" action="#{loginBean.checkLogin}" />

                            </div>
                       
                       <div class="vseperator3"></div>
                         
	  </a4j:form>
            </div>
    

        </div>
        <div class="body-container">
        	<div class="body-left-container">
            	<div class="vseperator5"> </div>
                <div class="motto-container">
                	<img src="<%=currentURL%>/images/connecting_events_across_the_globe.png" />
                </div>
                <div class="vseperator5"></div>
                <div class="floatleft"><img src="<%=currentURL%>/images/features.jpg" /></div>
                	<div class="clear"> </div>
                <div class="box1-top"> </div>
                <div class="box1-corners floatright"><img src="<%=currentURL%>/images/box1_right_top.gif" /></div>
                	<div class="clear"> </div>
                <div class="box1-middle">
                	<ul class="features">
                		<li>Focuses on managing the events</li>
                        <li>Help the attendees of the event to make contacts with all the co-attendees</li>
                        <li>Helps to make friendship through social Media's like Twitter, LinkedIn and Facebook</li>
                        <li>share the information with one another about the likes and dislikes of the event</li>
                	</ul>
                </div>
				 <div class="box1-corners floatleft"><img src="<%=currentURL%>/images/box1_left_bottom.gif" /></div>
                 <div class="box1-bottom"> </div>
                 <div class="box1-corners floatright"><img src="<%=currentURL%>/images/box1_right_bottom.gif" /></div>
                 <div class="clear"> </div>
                 <div class="vseperator5"> </div>
                 <div class="floatleft">
                 	<div class="why-eventsphere-container">
                    	<div class="floatleft"> 
                        	<div class="box2-corners floatleft"><img src="<%=currentURL%>/images/box2_left.gif" /></div>
                            <div class="box2-header"><img src="<%=currentURL%>/images/why_eventattend.jpg" /></div>
                            <div class="box2-corners floatleft"><img src="<%=currentURL%>/images/box2_right.gif" /></div>
                        </div>
                        <div class="vseperator2"> </div>
                        <div class="content1 padding5">
                        We are taking a huge step towards realizing our goal of
providing business professionals with the best social
business profiles for all of their contacts.
                        </div>
                        <div class="clear"></div>
                        <div class="read-more1 floatright">
                        <a href="#">More...</a>
                        </div>
                    </div>
	                <div class="hseperator1">&nbsp;</div>
                    <div class="blog">
                    	<div class="floatleft"> 
                        	<div class="box2-corners floatleft"><img src="<%=currentURL%>/images/box2_left.gif" /></div>
                            <div class="box2-header"><img src="<%=currentURL%>/images/blog.jpg" /></div>
                            <div class="box2-corners floatleft"><img src="<%=currentURL%>/images/box2_right.gif" /></div>
                        </div>
                        <div class="vseperator2"> </div>
                        <div class="content1 padding5">
                        We are taking a huge step towards realizing our goal of
providing business professionals with the best social
business profiles for all of their contacts.
                        </div>
                        <div class="clear"></div>
                        <div class="read-more1 floatright">
                        <a href="#">More...</a>
                        </div>
                    </div>
                 </div>
            </div>
            
            
            <div class="body-center-gap">&nbsp;
            </div>
            
            
            <div class="body-right-container">
            	<div class="vseperator3"> </div>
            	<div class="floatleft"><img src="<%=currentURL%>/images/live_events.gif" alt="Live Events" title="Live Events" /></div><br />
				<div class="clear"> </div>
                <div class="live-events-container">
               	  <div class="vseperator2"> </div>
               	  <div class="live-events-items">
                  	<strong><u>Enterprising India 2011</u></strong><br />
                    Action and Inspiration galore at #EI11!! Become a part of it with the EI Treasure Hunt!! <a href="#">Read More...</a>                  </div>
                  <div class="vseperator2"> </div>
                  <div class="live-events-items">
                  	<strong><u>Java Conference 2011</u></strong><br />
                    Action and Inspiration galore at #EI11!! Become a part of it with the EI Treasure Hunt!! .
                    <a href="#">Read More...</a>                  </div>
                  <div class="vseperator2"> </div>
                <div class="floatcenter"><img src="<%=currentURL%>/images/video_tour.jpg" alt="Video Tour" title="Video Tour" /></div>
                <div class="floatcenter"><img src="<%=currentURL%>/images/video_img.jpg" /></div>
                </div>
                <div class="floatleft"><img src="<%=currentURL%>/images/eventattend_demo_bottom.gif" /></div>
          </div>
         <div class="vseperator3"> </div>
         <div class="footer-container"> </div>
         <div class="footer">
         	© 2011 eventattend
         </div>
        </div>
    </div>
    </a4j:region>
    </f:view>
</body>

</html>