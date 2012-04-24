

function initScript(){
  	      clock();
    	  dateDisplay();
}

function popup(url) 
{
 var width  = 600;
 var height = 400;
 var left   = (screen.width  - width)/2;
 var top    = (screen.height - height)/2;
 var params = 'width='+width+', height='+height;
 params += ', top='+top+', left='+left;
 params += ', directories=no';
 params += ', location=no';
 params += ', menubar=no';
 params += ', resizable=no';
 params += ', scrollbars=yes';
 params += ', status=no';
 params += ', toolbar=no';
 newwin=window.open(url,'windowname5', params);
 if (window.focus) {newwin.focus();}
 return false;
}

window.onbeforeunload = function(){ 
	resetUserLogin(); 
	};
	
	
function resetUserLogin() {

	//document.getElementById('liveSessionForm:resetLiveSessionId').click();
	
}