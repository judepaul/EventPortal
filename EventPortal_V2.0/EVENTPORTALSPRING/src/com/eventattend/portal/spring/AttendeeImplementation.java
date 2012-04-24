package com.eventattend.portal.spring;

import com.eventattend.portal.bl.AttendeeBL;
import com.eventattend.portal.bl.EventBL;
import com.eventattend.portal.bl.SessionBL;
import com.eventattend.portal.bl.UserBL;
import com.eventattend.portal.controller.AttendeeController;
import com.eventattend.portal.controller.UserController;
import com.eventattend.portal.dto.AttendeeDTO;
import com.eventattend.portal.dto.ProfileDTO;
import com.eventattend.portal.dto.ResultDTO;

public class AttendeeImplementation implements AttendeeController {

	 public ResultDTO sendMailToAttendees(ProfileDTO profileDTO){
			UserBL userBL = new UserBL();			
			return userBL.sendMailToAttendees(profileDTO);
	 }
	 
	 public ResultDTO getAttendees(AttendeeDTO attendeeDTO){
		 
		 SessionBL sessionBL = new SessionBL();
		 ResultDTO resultDTO = null;
		// sessionBL.getSessionAttendees(attendeeDTO);
		 
		 return resultDTO;
		 
	 }
		 
	 
	 public ResultDTO getMailOptionsForAttendees(ProfileDTO profileDTO){
			UserBL userBL = new UserBL();			
			return userBL.getMailOptionsForAttendees(profileDTO);
		 }

	 
	 public ResultDTO updateSessionCommentLikeCount(AttendeeDTO attendeeDTO){
			EventBL eventBl = new EventBL();
			
			return eventBl.updateSessionCommentLikeCount(attendeeDTO);
		 }
	 
	 public ResultDTO updateSessionLikeCount(AttendeeDTO attendeeDTO){
			EventBL eventBl = new EventBL();
			
			return eventBl.updateSessionLikeCount(attendeeDTO);
		 }

	 
	 public ResultDTO getSessionCommentLike(AttendeeDTO attendeeDTO){
			AttendeeBL attendeeBL = new AttendeeBL();
			
			return attendeeBL.getCommentsLikeCount(attendeeDTO);
		 }

	 public ResultDTO getSessionLike(AttendeeDTO attendeeDTO){
			AttendeeBL attendeeBL = new AttendeeBL();			
			return attendeeBL.getCommentsLikeCount(attendeeDTO);
		 }
	 
	 public ResultDTO updateEventLikeCount(AttendeeDTO attendeeDTO){
			EventBL eventBl = new EventBL();
			
			return eventBl.updateEventLikeCount(attendeeDTO);
		 }

	 public ResultDTO getEventLike(AttendeeDTO attendeeDTO){
			AttendeeBL attendeeBL = new AttendeeBL();			
			return attendeeBL.getCommentsLikeCount(attendeeDTO);
		 }

	 public ResultDTO getTotalEventLike(AttendeeDTO attendeeDTO){
			AttendeeBL attendeeBL = new AttendeeBL();			
			return attendeeBL.getCommentsLikeCount(attendeeDTO);
		 }

	 public ResultDTO getToMailProfileInfo(ProfileDTO profileDTO){
			UserBL userBL = new UserBL();			
			return userBL.getToMailProfileInfo(profileDTO);
	 }

}
