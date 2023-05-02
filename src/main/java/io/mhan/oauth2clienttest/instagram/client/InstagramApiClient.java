package io.mhan.oauth2clienttest.instagram.client;

import io.mhan.oauth2clienttest.instagram.request.AccessTokenRequest;
import io.mhan.oauth2clienttest.instagram.response.ShortAccessTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "instagram-api-client", url = "https://api.instagram.com")
public interface InstagramApiClient {

    @PostMapping(value = "/oauth/access_token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ShortAccessTokenResponse getAccessToken(AccessTokenRequest request);
}
