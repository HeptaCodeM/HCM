package com.hcm.grw.dto.hr;

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
public class EmpSignDto {

	private String emsi_seq;
	private String empl_id;
	private String emsi_title;
	private String emsi_sign_img;
	private String emsi_stamp_img;
	private String emsi_delflag;
	private String emsi_create_id;
	private String emsi_create_dt;
	private String emsi_modify_id;
	private String emsi_modify_dt;
	private String emsi_setflag;
	
}
