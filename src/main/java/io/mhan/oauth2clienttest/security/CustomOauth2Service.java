package io.mhan.oauth2clienttest.security;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOauth2Service extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String registrationId = userRequest.getClientRegistration().getRegistrationId().toUpperCase();

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String oauthId = oAuth2User.getAttribute("id");

        // FACEBOOK_[ID]
        String username = registrationId + "_" + oauthId;

        SecurityUser securityUser = new SecurityUser(username);

        return securityUser;
    }
}
