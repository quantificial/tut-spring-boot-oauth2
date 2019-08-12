package com.example;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController	
public class TestController {
	
	@RequestMapping("/who")
	public String who(Principal user, HttpServletRequest request, HttpServletResponse response) throws ServletException {       
        
		return "Hello " + user.getName();
    }
	
	@RequestMapping("/user")
	public Principal user(Principal principal) {
	       return principal;
	}
	
	@RequestMapping("/exit")
	public String exit(HttpServletRequest request) {
	    request.getSession().invalidate();
	    new SecurityContextLogoutHandler().logout(request, null, null);
	    
	    return "success";
	}
	
	@RequestMapping("/fade")
	public String fade(HttpServletRequest request, HttpServletResponse httpResponse) {
		
		Authentication authentication =
			    SecurityContextHolder
			        .getContext()
			        .getAuthentication();

		OAuth2Authentication oAuth2Authentication =
			    (OAuth2Authentication) authentication;
		
		
	    final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) oAuth2Authentication.getDetails();
	    
	    //token
	    String accessToken = details.getTokenValue();
	    
	    //logout in client app
	    request.getSession().invalidate();
	    new SecurityContextLogoutHandler().logout(request, null, null);
	    
	    //logout from facebook
	    //https://www.facebook.com/logout.php?next=YOUR_REDIRECT_URL&access_token=USER_ACCESS_TOKEN
	    String uri = "https://www.facebook.com/logout.php?next=http://localhost:8081/exit" + "&access_token=" +accessToken;
	    
	    RestTemplate restTemplate = new RestTemplate();
	    
	    String result = "";
	    //result = restTemplate.getForObject(uri, String.class);
	    //return "redirect:/showData";
	    
	    try {
			httpResponse.sendRedirect(uri);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
		return result;
	}
	
//	@Autowired
//	private OAuth2AuthorizedClientService clientService;
	
//	@Autowired
//	private TokenStore tokenStore;
	
	@RequestMapping("/accessToken")
	public String authcode(Principal principal) {
		
		Authentication authentication =
			    SecurityContextHolder
			        .getContext()
			        .getAuthentication();

		OAuth2Authentication oAuth2Authentication =
			    (OAuth2Authentication) authentication;
		
		
	    final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) oAuth2Authentication.getDetails();
	    
	    //token
	    String accessToken = details.getTokenValue();
	    
	    //reference
	    //final OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
	   
	    // clientid
	    String clientId = oAuth2Authentication.getOAuth2Request().getClientId();

//		OAuth2AuthenticationToken oauthToken =
//			    (OAuth2AuthenticationToken) authentication;
		
		//System.out.println(oauth2);
		
		
//		OAuth2User o = (OAuth2User) oauth2.getPrincipal();
//		
//		System.out.println(o);
		
		
//		OAuth2AuthorizedClient client =
//			    clientService.loadAuthorizedClient(
//			            oauthToken.getAuthorizedClientRegistrationId(),
//			            oauthToken.getName());
//
//		String accessToken = client.getAccessToken().getTokenValue();
		
		
		return clientId+":"+accessToken;
		
	}

}
