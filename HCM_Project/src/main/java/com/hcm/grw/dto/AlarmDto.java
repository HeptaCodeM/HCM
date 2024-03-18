package com.hcm.grw.dto;

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
@NoArgsConstructor
@AllArgsConstructor
public class AlarmDto {
	
	private String al_no;
	private String al_title;
	private String al_producer;
	private String al_target;
	private String al_date;
	private String al_flag;
	private String al_key;
	private String al_set;
	
	private String producer_name;
	private String target_name;

	private String template;
}
