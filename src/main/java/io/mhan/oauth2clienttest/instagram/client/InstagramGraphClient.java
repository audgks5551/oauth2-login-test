package io.mhan.oauth2clienttest.instagram.client;

import io.mhan.oauth2clienttest.instagram.response.UserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "instagram-graph-client", url = "https://graph.instagram.com")
public interface InstagramGraphClient {

    @GetMapping("/v16.0/me")
    UserInfoResponse getUserInfo(
            @RequestParam(name = "access_token") String accessToken,
            @RequestParam(name = "fields") String fields
    );
}
