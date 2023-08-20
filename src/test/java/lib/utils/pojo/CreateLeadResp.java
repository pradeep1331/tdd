package lib.utils.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data
@NoArgsConstructor
public class CreateLeadResp {

	
	@JsonProperty("id")
	private String id;
	@JsonProperty("success")
	private Boolean success;
	@JsonProperty("errors")
	private List<Object> errors;
	
}
