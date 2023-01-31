package com.tutorial.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RequestDto {

    private String uri;
    private String method;
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public RequestDto(String uri, String method) {
		super();
		this.uri = uri;
		this.method = method;
	}
	
	public RequestDto() {
		super();
		this.uri= "";
		this.method = "";
	}
	
    
	
    
}
