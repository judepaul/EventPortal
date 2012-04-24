/* Scripts for Events Page */




function getEventDetails(eventId,joinedEvent){
	
	document.getElementById("eventsForm:eventId").value = eventId;
	
	if(joinedEvent=='true'){
		
		document.getElementById("eventsForm:hiddenActionForPopulateEvent").click();	
		
	}else if(joinedEvent=='past'){		
		//document.getElementById("eventsForm:eventCategoryId").value = 'PAST';
		document.getElementById("eventsForm:hiddenActionForPopulateEvent").click();
	}else{
		document.getElementById("eventsForm:submitEventIdforPopup").click();
		}
	
}

function setEventId(eventID){
	
	//alert('ID==>'+eventID)
	document.getElementById("eventsForm:eventId").value = eventID;
	document.getElementById("eventsForm:submitEventIdforAgenda").click();
	
} 

function joinEventId(eventID){
	//alert('ID==>'+eventID);
	document.getElementById("eventsForm:eventId").value = eventID;
	document.getElementById("eventsForm:submitEventIdforJoin").click();
}

function unJoinLiveEvent(eventID){
	
	document.getElementById("eventsForm:liveEventId").value = eventID;
	//document.getElementById("eventsForm:submitEventIdforUnJoin").click();
	
}

function unJoinFutureEvent(eventID){

	//alert('ID==>'+eventID);
	document.getElementById("eventsForm:futureEventId").value = eventID;
	//document.getElementById("eventsForm:submitEventIdforUnJoin").click();
	
}
