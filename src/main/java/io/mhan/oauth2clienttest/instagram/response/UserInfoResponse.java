package io.mhan.oauth2clienttest.instagram.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("username")
    private String username;
}
