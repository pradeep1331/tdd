package lib.utils.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data
//@Builder(toBuilder=true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenPojo {
	
	
	

	
@JsonProperty("access_token")
private String accessToken;
@JsonProperty("instance_url")
private String instanceUrl;
@JsonProperty("id")
private String id;
@JsonProperty("token_type")
private String tokenType;
@JsonProperty("issued_at")
private String issuedAt;
@JsonProperty("signature")
private String signature;

}
