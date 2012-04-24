package com.eventattend.portal.bl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.eventattend.portal.bo.Event;
import com.eventattend.portal.bo.Profile;
import com.eventattend.portal.dto.AttendeeDTO;
import com.eventattend.portal.dto.EventDTO;
import com.eventattend.portal.dto.ProfileDTO;
import com.eventattend.portal.dto.ResultDTO;
import com.eventattend.portal.exceptions.EventPortalException;

public class AttendeeBL extends BusinessLayer {
	
	/**
     * @method getSessionCommentLike - To get session comment like count
     * @param eventDTO
     * @return ResultDTO
     */
    @SuppressWarnings("unchecked")
    public ResultDTO getCommentsLikeCount(AttendeeDTO attendeeDTO){		    	
        	List sessionCommentList = null;	
        	ResultDTO resultDTO = null;
        	Collection commentsLikeCountList = null;   	
        	
        	commentsLikeCountList = new ArrayList();
        	resultDTO = new ResultDTO();        	
    		try {
    			if(attendeeDTO.getHiddenLikeId()!=null){
    				if(attendeeDTO.getHiddenLikeId().equals("sessionCommentLike")){
    					resultDTO = getSessionCommentLike(attendeeDTO);
    				}else if(attendeeDTO.getHiddenLikeId().equals("sessionLike")){
    					resultDTO = getSessionLike(attendeeDTO);
    				}else if(attendeeDTO.getHiddenLikeId().equals("eventLike")){
    					resultDTO = getEventLike(attendeeDTO);
    				}else if(attendeeDTO.getHiddenLikeId().equals("totalEventLike")){				
    					resultDTO = getTotalEventLike(attendeeDTO);
    				}
    			}
    			
    		} catch (Exception e){
    			e.printStackTrace(); 
    		}	
        	return resultDTO;    	
        }

	
	/**
     * @method getSessionCommentLike - To get session comment like count
     * @param eventDTO
     * @return ResultDTO
     */
    @SuppressWarnings("unchecked")
    public ResultDTO getSessionCommentLike(AttendeeDTO attendeeDTO){        	
        	SessionBL sessionBL = new SessionBL();
        	ResultDTO resultDTO = null;        	
        	resultDTO = new ResultDTO();        	
    		try {    			
    				resultDTO = sessionBL.getSessionCommentLike(attendeeDTO);
    		} catch (Exception e){
    			e.printStackTrace(); 
    		}	
        	return resultDTO;    	
        }
    
	/**
     * @method getSessionLike - To get session comment like count
     * @param eventDTO
     * @return ResultDTO
     */
    @SuppressWarnings("unchecked")
    public ResultDTO getSessionLike(AttendeeDTO attendeeDTO){        	
        	SessionBL sessionBL = new SessionBL();
        	ResultDTO resultDTO = null;        	
        	resultDTO = new ResultDTO();        	
    		try {    			
    				resultDTO = sessionBL.getSessionLike(attendeeDTO);
    		} catch (Exception e){
    			e.printStackTrace(); 
    		}	
        	return resultDTO;    	
        }

	/**
     * @method getEventLike - To get Event comment like count
     * @param eventDTO
     * @return ResultDTO
     */
    @SuppressWarnings("unchecked")
    public ResultDTO getEventLike(AttendeeDTO attendeeDTO){        	
        	EventBL eventBL = new EventBL();
        	ResultDTO resultDTO = null;        	
        	resultDTO = new ResultDTO();        	
    		try {    			
    				resultDTO = eventBL.getEventLike(attendeeDTO);
    		} catch (Exception e){
    			e.printStackTrace(); 
    		}	
        	return resultDTO;    	
        }
	
	/**
     * @method getTotalEventLike - To get sum of all Event like count
     * @param eventDTO
     * @return ResultDTO
     */
    @SuppressWarnings("unchecked")
    public ResultDTO getTotalEventLike(AttendeeDTO attendeeDTO){        	
        	EventBL eventBL = new EventBL();
        	ResultDTO resultDTO = null;        	
        	resultDTO = new ResultDTO();        	
    		try {    			
    				resultDTO = eventBL.getTotalEventLike(attendeeDTO);
    		} catch (Exception e){
    			e.printStackTrace(); 
    		}	
        	return resultDTO;    	
        }
    
  //Refined Methods
    
    public AttendeeDTO getAttendee(List userList, Map speakerMap, String currentUserId) throws EventPortalException{
		
		AttendeeDTO attendeeDTO = null;
		List attendeeList = null;
		
		List profileIdList = null;
		List profileDataList = null;
	
		UserBL userBL = null;
		ProfileBL profileBL = null;
	
		userBL = new UserBL();
		profileBL = new ProfileBL();
			
			
			profileIdList = (ArrayList) userBL.profileIdListFromUserIdList(userList);
			
			profileDataList = (ArrayList)profileBL.profileDataList(profileIdList);
			
			attendeeList = (List)profileBL.attendeeProfile(
					(List) profileDataList,speakerMap,currentUserId);
		
			attendeeDTO = new AttendeeDTO();
			
			if(attendeeList != null){
				
				attendeeDTO.setAttendeeCount(String.valueOf(attendeeList.size()));
				attendeeDTO.setAttendeeList(attendeeList);
			}
	
		return attendeeDTO;
	}
    
	
}
