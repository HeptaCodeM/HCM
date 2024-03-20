package com.hcm.grw.dto.hr;

import java.util.Date;

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
public class AuthDto {
	private String auco_cd;
	private String auco_name;
	private String auco_delflag;
	private String auco_create_id;
	private Date auco_create_dt;
	private String auco_modify_id;
	private Date auco_modify_dt;
}
