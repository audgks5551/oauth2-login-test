package io.mhan.oauth2clienttest.instagram.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShortAccessTokenResponse {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("user_id")
    private String userId;
}
