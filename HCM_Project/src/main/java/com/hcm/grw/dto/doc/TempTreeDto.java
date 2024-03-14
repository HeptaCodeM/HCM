package com.hcm.grw.dto.doc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TempTreeDto {
	
	private String id       ;
	private String parent   ;
	private String text     ;
	private String icon     ;
	private String delflag  ;
	
	
	
	
}
