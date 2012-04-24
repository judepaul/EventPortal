package com.eventattend.portal.controller;

import com.eventattend.portal.dto.AttendeeDTO;
import com.eventattend.portal.dto.ProfileDTO;
import com.eventattend.portal.dto.ResultDTO;

public interface AttendeeController {

	public ResultDTO sendMailToAttendees(ProfileDTO profileDTO) throws Exception;
	
	public ResultDTO getAttendees(AttendeeDTO attendeeDTO) throws Exception;
	public ResultDTO getMailOptionsForAttendees(ProfileDTO profileDTO) throws Exception;
	public ResultDTO updateSessionCommentLikeCount(AttendeeDTO attendeeDTO) throws Exception;
	public ResultDTO updateSessionLikeCount(AttendeeDTO attendeeDTO) throws Exception;
	public ResultDTO getSessionCommentLike(AttendeeDTO attendeeDTO) throws Exception;
	public ResultDTO getSessionLike(AttendeeDTO attendeeDTO) throws Exception;	
	public ResultDTO updateEventLikeCount(AttendeeDTO attendeeDTO) throws Exception;
	public ResultDTO getEventLike(AttendeeDTO attendeeDTO) throws Exception;
	public ResultDTO getTotalEventLike(AttendeeDTO attendeeDTO) throws Exception;	
	
	public ResultDTO getToMailProfileInfo(ProfileDTO profileDTO) throws Exception;
	
}
