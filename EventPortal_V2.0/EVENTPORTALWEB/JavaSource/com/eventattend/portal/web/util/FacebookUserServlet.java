package com.eventattend.portal.web.util;
//package com.eventcube.portal.web.util;
//
//import java.io.IOException;
//import java.sql.Array;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.EnumSet;
//import java.util.List;
//
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.w3c.dom.Document;
//
//
//import com.eventcube.portal.Factory.SocialMediaFactory;
//import com.eventcube.portal.controller.SocialMediaController;
//import com.eventcube.portal.dto.FaceBookDTO;
//import com.eventcube.portal.dto.LinkedInDTO;
//import com.eventcube.portal.dto.ResultDTO;
//import com.google.code.facebookapi.FacebookParam;
//import com.google.code.facebookapi.Permission;
//import com.google.code.facebookapi.ProfileField;
//import com.google.code.facebookapi.FacebookException;
//import com.google.code.facebookapi.FacebookJsonRestClient;
//import com.google.code.facebookapi.FacebookWebappHelper;
//import com.google.code.facebookapi.FacebookXmlRestClient;
//import com.google.code.facebookapi.IFacebookRestClient;
//import com.google.code.facebookapi.ProfileInfoField;
//import com.google.code.facebookapi.TemplatizedAction;
//
//
///**
// * Servlet implementation class FacebookUserServlet
// */
//public class FacebookUserServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	
//    private String api_key; 
//    private String secret; 
//    
//    private static final String FACEBOOK_USER_CLIENT = "facebook.user.client";
//     
//    
//    public void init(ServletConfig config) throws ServletException { 
//        api_key = "10511c6c70d52eaeb3b961cae9c94f8c"; 
//        secret = "11c61914df8ffc200c7a25263882568a"; 
//        if(api_key == null || secret == null) { 
//                throw new ServletException("Cannot initialise Facebook User Filter because the " + 
//                                                   "facebook_api_key or facebook_secret context init " + 
//                                                   "params have not been set. Check that they're there " + 
//                                                   "in your servlet context descriptor."); 
//        } else { 
//                System.out.println("Using facebook API key: " + api_key); 
//        } 
//} 
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public FacebookUserServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		 HttpSession session = null;
//		String profileId = null;
//		String sessionKey;
//		sessionKey = req.getParameter(FacebookParam.SESSION_KEY.toString()); 
//		
//		
//		FacebookWebappHelper<Document> facebook = null;
//		  String nextPage = null;
//		  try { 
//             
//              session = req.getSession(true); 
//              profileId = (String)session.getAttribute("USERPROFILEID");
//              System.out.println("Profile Id is"+profileId);
//              FacebookJsonRestClient userClient = getUserClient(session);  
//              if(userClient == null) { 
//              	 System.out.println("User session doesn't have a Facebook API client setup yet. Creating one and storing it in the user's session."); 
//                  userClient = new FacebookJsonRestClient(api_key, secret); 
//                  session.setAttribute(FACEBOOK_USER_CLIENT, userClient); 
//                  facebook = new FacebookWebappHelper<Document>(req, res, api_key, secret); 
//                  nextPage = req.getRequestURI();
//                  
//                  nextPage = nextPage.substring(nextPage.indexOf("/", 1) + 1); //cut out the first /, the context path and the 2nd / 
//                  System.out.println(nextPage);
//                  boolean redirectOccurred = facebook.requireLogin(nextPage); 
//                  
//                  if(redirectOccurred){
//                	  //res.sendRedirect("http://www.facebook.com/login.php?api_key=10511c6c70d52eaeb3b961cae9c94f8c&connect_display=popup&v=1.0&next=views/faceBook_connect.jsf&cancel_url=http://www.facebook.com/connect/login_failure.html&fbconnect=true&return_session=true&session_key_only=true&req_perms=read_stream,publish_stream,offline_access,sms,email,user_location");
//                	  res.sendRedirect("http://www.facebook.com/login.php?api_key=10511c6c70d52eaeb3b961cae9c94f8c&connect_display=popup&v=1.0&next=http://www.facebook.com/connect/login_success.html&cancel_url=http://www.facebook.com/connect/login_failure.html&fbconnect=true&return_session=true&session_key_only=true&req_perms=read_stream,publish_stream,offline_access,sms,email,user_location");
//                     return; 
//                  } 
//              }else{
//            	  String authToken=req.getParameter("auth_token");               
//                  if(authToken!=null){
//                      String authSession = null;                      
//                      try {
//                    	  authSession = userClient.auth_getSession(authToken);
//                    	  
//                    	  addToken(authSession,profileId);
//                    	  
//                          userClient = new FacebookJsonRestClient(api_key, secret,authSession); 
//                    	  JSONArray arrayObj = (JSONArray)userClient.friends_get();
//                  		  System.out.println(arrayObj);
//                  		List userIds = new ArrayList();
//                  		for(int i=0;i<arrayObj.length();i++){
//                  			 try {
//								System.out.println(arrayObj.getString(i));
//								userIds.add(arrayObj.getString(i));
//							} catch (JSONException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//                  	    }
//                       
//        			} catch (FacebookException e) {
//        			
//        				e.printStackTrace();
//        			}
//                      }  
//              }              
//            
//              res.sendRedirect("/EVENTPORTAL/views/faceBook_connect.jsf");
//              
//       } finally { 
//    	   session.invalidate();
//       } 
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		super.doPost(request, response);
//	}
//	
//	  public static FacebookJsonRestClient getUserClient(HttpSession session) { 
//          return (FacebookJsonRestClient)session.getAttribute(FACEBOOK_USER_CLIENT); 
//      } 
//	  
//	public void personInfo(FacebookJsonRestClient client,List userIds,EnumSet<ProfileField> fields){
//
//		JSONArray check = null;
//		try {
//			check = client.users_getInfo(userIds, fields);
//		} catch (FacebookException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		for(int i=0;i<check.length();i++){
//			try {						
//				 for (int j = 0; j < check.length(); j++) {
//			            JSONObject obj = check.getJSONObject(j);
//			            System.out.println("User Id    "+obj.getString("uid"));
//			            System.out.println("Wall Count "+obj.getString("wall_count"));
//			            System.out.println("Pic url    "+obj.getString("pic"));
//			            System.out.println("Full Name  "+obj.getString("first_name")+" "+obj.getString("last_name"));
//			            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			        }
//
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	
//	public void addToken(String authSession, String profileId){
//		FaceBookDTO faceBookDTO = new FaceBookDTO();
//		faceBookDTO.setAccessToken(authSession);
//		
//		if(profileId != null){
//			faceBookDTO.setProfileId(profileId);
//		}else{
//			faceBookDTO.setProfileId("600005");
//		}
//		
//		addFaceBookAccessToken(faceBookDTO);
//		
//	}
//	
//	public ResultDTO addFaceBookAccessToken(FaceBookDTO faceBookDTO){
//		ResultDTO resultDTO = null;
//		
//		SocialMediaController socialMediaController = null;
//	
//		try {
//			socialMediaController = SocialMediaFactory.addFaceBookToken();
//			resultDTO = socialMediaController.addFaceBookToken(faceBookDTO);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	 return resultDTO;
//	}
//}
