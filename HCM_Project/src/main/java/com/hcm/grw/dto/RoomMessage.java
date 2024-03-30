package com.hcm.grw.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RoomMessage implements Serializable {
	
	private static final long serialVersionUID = 2082503192322391880L;
    private String roomId;
    private String name;
    private String message;
    
}
