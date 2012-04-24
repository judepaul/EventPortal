/* Scripts for Event Page */

function sessionDetail(sessionId, liveSession){

	//alert('Live Session==>'+liveSession);
	   document.getElementById('eventForm:sessionId').value = sessionId;
	   document.getElementById('eventForm:liveSessionId').value = liveSession;
	  
}

function sessionDetail(sessionId){
	document.getElementById('eventForm:sessionId').value = sessionId;
	  
}


function sessionAttend(eventId,sessionId){
	//alert(">> "+eventId+" >> "+sessionId);	
	document.getElementById('eventForm:eventId').value = eventId;
	document.getElementById('eventForm:sessionId').value = sessionId;
	  
}
function submitSocialMediaEventId(eventId){
	document.getElementById('eventForm:socialMediaEventId').value = eventId;
}


function connectViaEmail(mailId,toName){
	//alert(mailId);
	document.getElementById('eventForm:toName').value = toName;
	document.getElementById('eventForm:toProfileId').value = mailId;	
	//document.getElementById("connectMailForm:submitMailIdforPopup").click();
	 //Richfaces.showModalPanel('modalConnectMailPanel');
		
}
function connectSocialMedia(profileId,profileName,twitterFollow,faceBookFriendsConnect,linkedinFriendsConnect){
	//alert(profileId+" "+profileName+" "+twitterFollow+" "+faceBookFriendsConnect+" "+linkedinFriendsConnect);
	document.getElementById('eventForm:profileIdToConnect').value = profileId;
	document.getElementById('eventForm:profName').value = profileName;
	document.getElementById('eventForm:twitterFollow').value = twitterFollow;
	document.getElementById('eventForm:faceBookFriendsConnect').value = faceBookFriendsConnect;
	document.getElementById('eventForm:linkedinFriendsConnect').value = linkedinFriendsConnect;
	document.getElementById('eventForm:alreadyFollowingInTwitter').value = false;
	document.getElementById('eventForm:alreadyFriendInFB').value = false;
	document.getElementById('eventForm:alreadyFriendInLin').value = false;
}

function openUrlInModal(url){

	document.getElementById('eventForm:urlToOpenId').value = url;
}


/* To set the session id and live session flag */

function setLiveSessionId(sessionId, liveSession){
	document.getElementById('eventForm:sessionId').value = sessionId;
	document.getElementById('eventForm:liveSessionId').value = liveSession;
	
}

function submitEventIdForJoin(eventId){
	document.getElementById('eventForm:eventIdForJoin').value = eventId;
}

function setEventLikeId(id){

	document.getElementById("eventForm:likeId").value = id;
	
}
