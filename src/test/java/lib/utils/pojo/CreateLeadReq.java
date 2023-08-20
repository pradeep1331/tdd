package lib.utils.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data
@Builder(toBuilder=true)
public class CreateLeadReq {
	
	@JsonProperty("FirstName")
	private String firstName;
	@JsonProperty("LastName")
	private String lastName;
	@JsonProperty("Company")
	private String company;
	
	

}
