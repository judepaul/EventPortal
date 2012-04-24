/* Scripts for Live Session Page */


function submitSessionId(sessionId,liveSession){
	
	document.getElementById('liveSessionForm:sessionId').value = sessionId;
	document.getElementById('liveSessionForm:liveSession').value = liveSession;
	
}

function showSpeakerInfo(sessionId){
	//alert(sessionId);
	document.getElementById('liveSessionForm:sessionId').value = sessionId;

}

function showSessionInfo(sessionId){
	//alert(sessionId);
	document.getElementById('liveSessionForm:sessionId').value = sessionId;
	
}

function sessionLeaveConfirm(){
	
}

function connectViaEmail(mailId,toName){
	//alert(mailId);
	document.getElementById('liveSessionForm:toName').value = toName;
	document.getElementById('liveSessionForm:toProfileId').value = mailId;	
			
}

	function showUserSocialProfile(faceBookId,twitterId,linkedInId){	
		//alert("LIve "+faceBookId+twitterId+linkedInId);
		document.getElementById("liveSessionForm:faceBookId").value=faceBookId;
		document.getElementById("liveSessionForm:twitterId").value=twitterId;
		document.getElementById("liveSessionForm:linkedInId").value=linkedInId;	
	//	document.getElementById("liveSessionForm:userSocialProfile").click();	
}

function connectSocialMedia(profileId,profileName,twitterFollow,faceBookFriendsConnect,linkedinFriendsConnect){
	//alert("LIve "+profileId+" "+profileName+" "+twitterFollow+" "+faceBookFriendsConnect+" "+linkedinFriendsConnect);
	document.getElementById('liveSessionForm:profileIdToConnect').value = profileId;
	document.getElementById('liveSessionForm:profileName').value = profileName;
	document.getElementById('liveSessionForm:twitterFollow').value = twitterFollow;
	document.getElementById('liveSessionForm:faceBookFriendsConnect').value = faceBookFriendsConnect;
	document.getElementById('liveSessionForm:linkedinFriendsConnect').value = linkedinFriendsConnect;
	document.getElementById('liveSessionForm:alreadyFollowingInTwitter').value = false;
	document.getElementById('liveSessionForm:alreadyFriendInFB').value = false;
	document.getElementById('liveSessionForm:alreadyFriendInLin').value = false;
}

function setSessionCommentLikeId(id){

	document.getElementById("liveSessionForm:sessionCommentLikeId").value = id;
	
}

window.onbeforeunload = function(){ 
	myUnloadEvent(); 
	};
	
	
function myUnloadEvent() {

	document.getElementById('liveSessionForm:resetLiveSessionId').click();
	
}

function setSessionLikeId(id){
//alert('Like Session Id'+id);
	document.getElementById("liveSessionForm:likeId").value = id;
	
}

