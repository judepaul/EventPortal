<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<link href="../styles/styles.css" rel="stylesheet" type="text/css"/>

<SCRIPT LANGUAGE="JavaScript">


function test(){	
	var f1 = document.getElementById("profileForm:fileid").value;	
	//alert(f1);
	document.getElementById("profileForm:hiddenId").value = document.getElementById("profileForm:fileid").value;
	//alert(document.getElementById("profileForm:hiddenId").value);
	}

function checkUploadImgCheck(){	
	if(document.getElementById('profileForm:uploadImgCheckBox').checked == true){		
		document.getElementById('profileForm:extImgCheckBox').disabled = true;
		document.getElementById('profileForm:smImgCheckBox').disabled = true;
		document.getElementById('profileForm:twitterCheckBox').disabled = true;
		document.getElementById('profileForm:faceBookCheckBox').disabled = true;
		document.getElementById('profileForm:lnCheckBox').disabled = true;
		document.getElementById('profileForm:localImgPanel').style.display = "inline";
				
	}else {		
		document.getElementById('profileForm:uploadImgCheckBox').disabled = false;
		document.getElementById('profileForm:extImgCheckBox').disabled = false;		
		document.getElementById('profileForm:smImgCheckBox').disabled = false;		
		document.getElementById('profileForm:twitterCheckBox').disabled = false;
		document.getElementById('profileForm:faceBookCheckBox').disabled = false;
		document.getElementById('profileForm:lnCheckBox').disabled = false;
		document.getElementById('profileForm:localImgPanel').style.display = "none";
	}
}

function checkExtImgCheck(){	
	if(document.getElementById('profileForm:extImgCheckBox').checked == true){		
		document.getElementById('profileForm:uploadImgCheckBox').disabled = true;
		document.getElementById('profileForm:smImgCheckBox').disabled = true;
		document.getElementById('profileForm:twitterCheckBox').disabled = true;
		document.getElementById('profileForm:faceBookCheckBox').disabled = true;
		document.getElementById('profileForm:lnCheckBox').disabled = true;
		document.getElementById('profileForm:extImgPanel').style.display = "inline";	
					
	}else {		
		document.getElementById('profileForm:uploadImgCheckBox').disabled = false;
		document.getElementById('profileForm:extImgCheckBox').disabled = false;		
		document.getElementById('profileForm:smImgCheckBox').disabled = false;		
		document.getElementById('profileForm:twitterCheckBox').disabled = false;
		document.getElementById('profileForm:faceBookCheckBox').disabled = false;
		document.getElementById('profileForm:lnCheckBox').disabled = false;
		document.getElementById('profileForm:extImgPanel').style.display = "none";	
	}	
}

function checkSmImgCheck(imgUrl){	
	if(document.getElementById('profileForm:smImgCheckBox').checked == true){		
		document.getElementById('profileForm:uploadImgCheckBox').disabled = true;
		document.getElementById('profileForm:extImgCheckBox').disabled = true;
		document.getElementById('profileForm:twitterCheckBox').disabled = false;
		document.getElementById('profileForm:faceBookCheckBox').disabled = false;
		document.getElementById('profileForm:lnCheckBox').disabled = false;	
		document.getElementById('profileForm:socialMediaImagePanel').style.display = "inline";	
		if(document.getElementById('profileForm:twitterCheckBox').checked == true){
			document.getElementById('profileForm:faceBookCheckBox').disabled = true;
			document.getElementById('profileForm:lnCheckBox').disabled = true;
			refreshImg(imgUrl);	
		}
		if(document.getElementById('profileForm:faceBookCheckBox').checked == true){
			document.getElementById('profileForm:twitterCheckBox').disabled = true;
			document.getElementById('profileForm:lnCheckBox').disabled = true;
			refreshImg(imgUrl);	
		}
		if(document.getElementById('profileForm:lnCheckBox').checked == true){
			document.getElementById('profileForm:twitterCheckBox').disabled = true;
			document.getElementById('profileForm:faceBookCheckBox').disabled = true;
			refreshImg(imgUrl);	
		}
					
	}else {		
		document.getElementById('profileForm:uploadImgCheckBox').disabled = false;
		document.getElementById('profileForm:extImgCheckBox').disabled = false;		
		document.getElementById('profileForm:smImgCheckBox').disabled = false;	

		document.getElementById('profileForm:twitterCheckBox').checked = false;
		document.getElementById('profileForm:faceBookCheckBox').checked = false;
		document.getElementById('profileForm:lnCheckBox').checked = false;
		
		document.getElementById('profileForm:twitterCheckBox').disabled = false;
		document.getElementById('profileForm:faceBookCheckBox').disabled = false;
		document.getElementById('profileForm:lnCheckBox').disabled = false;
		document.getElementById('profileForm:socialMediaImagePanel').style.display = "none";
	}
		
}

function refreshImg(imgUrl){

	document.getElementById("profileForm:hiddenImgId").value = imgUrl;
	document.getElementById("profileForm:hiddenImgButton").click();
	document.getElementById('profileForm:socialMediaImagePanel').style.display = "inline";
	
}


function imgFromLocalCheck(){

	if(document.getElementById('profileForm:uploadImgCheckBox').checked == true){		

		document.getElementById('profileForm:extImgCheckBox').disabled = true;
		document.getElementById('profileForm:extImgCheckBox').checked = false;
		
		document.getElementById('profileForm:smImgCheckBox').disabled = true;
		document.getElementById('profileForm:smImgCheckBox').checked = false;	
			
		document.getElementById('profileForm:localImgPanel').style.display = "inline";
		document.getElementById('profileForm:extImgPanel').style.display = "none";	
		document.getElementById('profileForm:socialMediaImagePanel').style.display = "none";
				
	}else{
		document.getElementById('profileForm:extImgCheckBox').disabled = false;
		document.getElementById('profileForm:smImgCheckBox').disabled = false;
		
		document.getElementById('profileForm:localImgPanel').style.display = "none";
		document.getElementById('profileForm:extImgPanel').style.display = "none";	
		document.getElementById('profileForm:socialMediaImagePanel').style.display = "none";
		
	}

	
}

function imgFromURLCheck(){
	
	if(document.getElementById('profileForm:extImgCheckBox').checked == true){		

		document.getElementById('profileForm:uploadImgCheckBox').disabled = true;
		document.getElementById('profileForm:uploadImgCheckBox').checked = false;
		
		document.getElementById('profileForm:smImgCheckBox').disabled = true;
		document.getElementById('profileForm:smImgCheckBox').checked = false;	
			
		document.getElementById('profileForm:localImgPanel').style.display = "none";
		document.getElementById('profileForm:extImgPanel').style.display = "inline";
		document.getElementById('profileForm:socialMediaImagePanel').style.display = "none";
					
	}else{
		document.getElementById('profileForm:uploadImgCheckBox').disabled = false;
		document.getElementById('profileForm:smImgCheckBox').disabled = false;
		
		document.getElementById('profileForm:localImgPanel').style.display = "none";
		document.getElementById('profileForm:extImgPanel').style.display = "none";	
		document.getElementById('profileForm:socialMediaImagePanel').style.display = "none";
		
	}
	
	
}

function imgFromSocialMediaCheck(){
	
	if(document.getElementById('profileForm:smImgCheckBox').checked == true){

		document.getElementById('profileForm:uploadImgCheckBox').disabled = true;
		document.getElementById('profileForm:uploadImgCheckBox').checked = false;
		
		document.getElementById('profileForm:extImgCheckBox').disabled = true;
		document.getElementById('profileForm:extImgCheckBox').checked = false;	
			
		document.getElementById('profileForm:localImgPanel').style.display = "none";
		document.getElementById('profileForm:extImgPanel').style.display = "none";
		document.getElementById('profileForm:socialMediaImagePanel').style.display = "inline";

		document.getElementById("profileForm:socialMediaImgRefreshButtonId").click();
		
				
	}else{

		document.getElementById('profileForm:uploadImgCheckBox').disabled = false;
		document.getElementById('profileForm:extImgCheckBox').disabled = false;
		
		document.getElementById('profileForm:localImgPanel').style.display = "none";
		document.getElementById('profileForm:extImgPanel').style.display = "none";	
		document.getElementById('profileForm:socialMediaImagePanel').style.display = "none";		
	}
	
}

function twitterImgRefresh(imgUrl){	
	
	var twitterConnected = document.getElementById('profileForm:twitterCheckBox');
	var faceBookConnected = document.getElementById('profileForm:faceBookCheckBox');
	var linkedInConnected = document.getElementById('profileForm:lnCheckBox');

	if(twitterConnected != null){
		
		if(twitterConnected.checked == true){

			if(faceBookConnected != null){
				faceBookConnected.checked = false;
				faceBookConnected.disabled = true;
			}
			if(linkedInConnected != null){
				linkedInConnected.checked = false;
				linkedInConnected.disabled = true;
			}

			refreshImg(imgUrl);
		 }else{

			 if(faceBookConnected != null){					
					faceBookConnected.disabled = false;
				}
				if(linkedInConnected != null){
					
					linkedInConnected.disabled = false;
				}
			 
		} 
	}

}

function faceBookImgRefresh(imgUrl){	
	var twitterConnected = document.getElementById('profileForm:twitterCheckBox');
	var faceBookConnected = document.getElementById('profileForm:faceBookCheckBox');
	var linkedInConnected = document.getElementById('profileForm:lnCheckBox');

if(faceBookConnected != null){
		
		if(faceBookConnected.checked == true){

			if(twitterConnected != null){
				twitterConnected.checked = false;
				twitterConnected.disabled = true;
			}
			if(linkedInConnected != null){
				linkedInConnected.checked = false;
				linkedInConnected.disabled = true;
			}

			refreshImg(imgUrl);
		 }else{

			 if(twitterConnected != null){					
				 twitterConnected.disabled = false;
				}
				if(linkedInConnected != null){
					
					linkedInConnected.disabled = false;
				}
			 
		} 	 
	}
}

function linkedInImgRefresh(imgUrl){	
	var twitterConnected = document.getElementById('profileForm:twitterCheckBox');
	var faceBookConnected = document.getElementById('profileForm:faceBookCheckBox');
	var linkedInConnected = document.getElementById('profileForm:lnCheckBox');

	if(linkedInConnected != null){
		
		if(linkedInConnected.checked == true){

			if(twitterConnected != null){
				twitterConnected.checked = false;
				twitterConnected.disabled = true;
			}
			if(faceBookConnected != null){
				faceBookConnected.checked = false;
				faceBookConnected.disabled = true;
			}

			refreshImg(imgUrl);
		 }else{

			 if(faceBookConnected != null){					
					faceBookConnected.disabled = false;
				}
				if(twitterConnected != null){
					
					twitterConnected.disabled = false;
				}
			 
		} 	 
	}
	
}

function checkEmptyURL(){

	var url = document.getElementById('profileForm:imageURLId').value;

	if(url!= null){

		if(url == ''){
			alert("Please enter the Picture URL");
			return false;
		}else{
            return true;
		}
	}
}

</SCRIPT>

<style type="text/css">
.profile-pic-row1{
vertical-align: top;
 height: 100px;
 

}

.profile-pic-row2{
vertical-align: top;

 height: 100px;

}

.profile-pic-row3{
vertical-align: top;
 height: 100px;

}
.image-panel{

height: 350px;
background-color: white;
border: 0;
vertical-align: middle;

}
.picture-options-panel{
height: 350px;
background-color: white;
border: 0;
vertical-align: middle;
background: white;
width:450px;


}

.ea-profile-picture-panel{	
	background: white;
	margin: 0 auto;
	width: 900px;
}
.profile-pic-col1{

  width:500px;
  height: 320px
  align:left;
  padding:0;
  vertical-align:top;
	
}

.profile-pic-col2{

  width:400px;
  height: 320px
  align:center;
  vertical-align: middle;
  padding:0;
 
	
}

</style>
<a4j:region id="profilePicRegion">
<h:inputHidden id="hiddenImgId" value = "#{userBean.inputImageVal}"></h:inputHidden>
<a4j:commandButton status="profilePicStatus" reRender="image-panel-id" id="hiddenImgButton" style="display:none;" actionListener="#{userBean.imgRefreshListener}" ></a4j:commandButton>
<a4j:commandButton reRender="image-panel-id" id="socialMediaImgRefreshButtonId" style="display:none;" actionListener="#{socialMediaBean.socialMediaImgRefreshListener}" ></a4j:commandButton>

<rich:panel id="picture-panel" styleClass="ea-profile-picture-panel">

<h:panelGrid columns="2" columnClasses="profile-pic-col1,profile-pic-col2" id ="info">
<h:panelGroup>
<rich:panel styleClass="picture-options-panel">

<h:panelGrid columns="1" rowClasses="profile-pic-row1,profile-pic-row2,profile-pic-row3">
<h:panelGroup>
<h:panelGrid columns="1">
<h:panelGroup>
<h:selectBooleanCheckbox id ="uploadImgCheckBox" value = "#{userBean.uploadImgCheckBox}" onclick="imgFromLocalCheck();"></h:selectBooleanCheckbox>
<h:outputText styleClass="ea-heading-class" value="Upload an image from system"></h:outputText>
</h:panelGroup>
<h:panelGroup style="display:none;" id="localImgPanel">
<rich:fileUpload status="profilePicStatus" listHeight="70" fileUploadListener="#{userBean.listener}"
		maxFilesQuantity="#{userBean.uploadsAvailable}"
		id="upload" 
		immediateUpload="#{userBean.autoUpload}"
		acceptedTypes="jpg, gif, png, bmp" allowFlash="#{userBean.useFlash}">
		<a4j:support status="profilePicStatus" event="onuploadcomplete" reRender="image-panel-id" />
</rich:fileUpload>
</h:panelGroup>
</h:panelGrid>
</h:panelGroup>

<h:panelGroup>

<h:panelGrid columns="1">

<h:panelGroup>
<h:selectBooleanCheckbox id ="extImgCheckBox" value = "#{userBean.externalImgCheckBox}" onclick="imgFromURLCheck();"></h:selectBooleanCheckbox>
<h:outputText styleClass="ea-heading-class" value="Select Picture From URL"></h:outputText>
</h:panelGroup>

<h:panelGroup id="extImgPanel"  style="display:none;">
<h:inputText id="imageURLId" style="width:300px;" value="#{userBean.extProfileImgLocation}"></h:inputText>
<a4j:commandButton status="profilePicStatus" onclick="checkEmptyURL();"  reRender="image-panel-id" actionListener="#{userBean.imgFromURLListener}" value="Upload"></a4j:commandButton>
</h:panelGroup>

</h:panelGrid>


</h:panelGroup>

<h:panelGroup>

<h:panelGrid columns="1">
<h:panelGroup>
<h:selectBooleanCheckbox id ="smImgCheckBox" value = "#{userBean.smImgCheckBox}" onclick="imgFromSocialMediaCheck();"></h:selectBooleanCheckbox>
<h:outputText styleClass="ea-heading-class" value="Select Picture from Social Media"></h:outputText>
</h:panelGroup>
<h:panelGroup>

<h:panelGrid columns="1" id="socialMediaImagePanel" style="display:none;">
<h:panelGroup rendered="#{!socialMediaBean.anySocialMediaConnected}">

<h:outputText styleClass="ea-content-class" value="You have not connected to any social Media."></h:outputText>
<br/>
<a4j:commandButton reRender="mainTab" actionListener="#{socialMediaBean.socialMediaConnectTabListener}" value="Connect Now"></a4j:commandButton>

</h:panelGroup>
<h:panelGroup rendered="#{socialMediaBean.anySocialMediaConnected}">

<h:panelGrid columns="1">

<h:panelGroup rendered="#{socialMediaBean.twitterConnected}">

<h:selectBooleanCheckbox id="twitterCheckBox" value="#{userBean.twitterCheckBox}" onclick="twitterImgRefresh('#{userBean.twitterImgUrl}')"></h:selectBooleanCheckbox>

<h:outputText styleClass="ea-content-class" value=" Twitter"></h:outputText>

<h:graphicImage width="30" height="30" id="twitterProfileImg"  value="#{userBean.twitterImgUrl}"/>

</h:panelGroup>

<h:panelGroup rendered="#{socialMediaBean.faceBookConnected}">

<h:selectBooleanCheckbox id="faceBookCheckBox" value="#{userBean.fbCheckBox}" onclick="faceBookImgRefresh('#{userBean.faceBookImgUrl}');"></h:selectBooleanCheckbox>

<h:outputText styleClass="ea-content-class" value=" FaceBook"></h:outputText>

<h:graphicImage width="30" height="30" id="fbProfileImg"  value="#{userBean.faceBookImgUrl}"/>

</h:panelGroup>

<h:panelGroup rendered="#{socialMediaBean.linkedInConnected}">

<h:selectBooleanCheckbox id="lnCheckBox" value="#{userBean.lnCheckBox}" onclick="linkedInImgRefresh('#{userBean.linkedInImgUrl}')"></h:selectBooleanCheckbox>

<h:outputText styleClass="ea-content-class" value=" LinkedIn"></h:outputText>

<h:graphicImage width="30" height="30" id="lnProfileImg"  value="#{userBean.linkedInImgUrl}"/>

</h:panelGroup>


</h:panelGrid>


</h:panelGroup>



</h:panelGrid>


</h:panelGroup>



</h:panelGrid>

</h:panelGroup>
	
	
</h:panelGrid>
</rich:panel>	
</h:panelGroup>

<h:panelGroup>
<rich:panel styleClass="image-panel" id="image-panel-id">
<h:panelGrid columns="1">
<h:panelGroup>
<rich:spacer height="50" ></rich:spacer>
</h:panelGroup>
<h:panelGroup id="imgDisplayId">

<rich:spacer width="100"></rich:spacer>
<h:graphicImage width="120" height="120" id="profileImgUpload"  rendered="#{userBean.profImgFileName != null}" value="#{userBean.profImgFileName}"/>
<h:graphicImage width="120" height="120" id="profileImgUpload1" value="/images/noPhoto.jpg" rendered="#{userBean.profImgFileName == null}"/>
</h:panelGroup>
<h:panelGroup>
<rich:spacer height="30" width="120" ></rich:spacer>
<a4j:status id="profilePicStatus" for="profilePicRegion">
                <f:facet name="start">
                   <h:graphicImage  value="/images/loading_refresh.gif" />
               </f:facet>
</a4j:status>
<a4j:commandLink status="profilePicStatus" id="removeImgId" rendered="#{userBean.profImgFileName != null}" reRender="image-panel-id" actionListener="#{userBean.removePictureListener}" value="Remove Picture"></a4j:commandLink>
</h:panelGroup>
</h:panelGrid>
</rich:panel>

</h:panelGroup>

</h:panelGrid>
</rich:panel>
</a4j:region>