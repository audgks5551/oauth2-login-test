package io.mhan.oauth2clienttest.instagram.request;

import feign.form.FormProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenRequest {
    @FormProperty("client_id")
    private String clientId;

    @FormProperty("client_secret")
    private String clientSecret;

    @FormProperty("code")
    private String code;

    @FormProperty("grant_type")
    private String grantType;

    @FormProperty("redirect_uri")
    private String redirectUri;
}
