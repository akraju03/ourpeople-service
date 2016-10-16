package com.ourpeople.models;

import org.codehaus.jackson.map.annotate.JsonSerialize;



import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter()
@Setter()
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)

public class SignupDetails {
	private String first_name;
	
	private String address;
	
	private String spouse_name;
	
	private String father_name;
	
	private String mother_name;
	
 	private String email;
   
 	private String phone;
 	
 	private boolean community_id;
 	
 	private boolean id;

 	private String message;
}
