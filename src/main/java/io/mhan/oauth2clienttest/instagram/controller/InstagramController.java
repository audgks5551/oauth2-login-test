package io.mhan.oauth2clienttest.instagram.controller;

import io.mhan.oauth2clienttest.instagram.client.InstagramApiClient;
import io.mhan.oauth2clienttest.instagram.client.InstagramGraphClient;
import io.mhan.oauth2clienttest.instagram.request.AccessTokenRequest;
import io.mhan.oauth2clienttest.instagram.response.ShortAccessTokenResponse;
import io.mhan.oauth2clienttest.instagram.response.UserInfoResponse;
import io.mhan.oauth2clienttest.security.SecurityUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Controller
@RequestMapping("/instagram")
public class InstagramController {
    private final InstagramApiClient instagramApiClient;
    private final InstagramGraphClient instagramGraphClient;

    public InstagramController(InstagramApiClient instagramApiClient, InstagramGraphClient instagramGraphClient) {
        this.instagramApiClient = instagramApiClient;
        this.instagramGraphClient = instagramGraphClient;
    }

    @GetMapping("/login")
    public String login(RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("client_id", "[client-id]");
        redirectAttributes.addAttribute("response_type", "code");
        redirectAttributes.addAttribute("redirect_uri", "https://localhost/instagram/callback");
        // https://localhost/instagram/callback?code=~~
        redirectAttributes.addAttribute("scope", "user_profile");
        return "redirect:https://www.instagram.com/oauth/authorize";
    }

    // restTemplate
    // openFein

    @GetMapping("/callback")
    public String callback(String code, HttpSession session) {

        AccessTokenRequest request = AccessTokenRequest.builder()
                .clientId("[client-id]")
                .clientSecret("[client-secret]")
                .code(code)
                .grantType("authorization_code")
                .redirectUri("https://localhost/instagram/callback")
                .build();

        ShortAccessTokenResponse accessTokenResponse = instagramApiClient.getAccessToken(request);

        UserInfoResponse userInfoResponse = instagramGraphClient.getUserInfo(
                accessTokenResponse.getAccessToken(), "id,username");

        String username = "INSTAGRAM" + "_" + userInfoResponse.getId();

        SecurityUser user = SecurityUser.builder()
                .username(username)
                .build();

        Authentication authentication =
                new OAuth2AuthenticationToken(user, user.getAuthorities(), "instagram");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // SPRING_SECURITY_CONTEXT_KEY = "SPRING_SECURITY_CONTEXT"
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        return "redirect:/check";
    }
}
