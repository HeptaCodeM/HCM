package com.hcm.grw.dto.hr;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
	private String empl_id;
	private String empl_pwd;
	private String empl_name;
	private String empl_birth;
	private String empl_gender;
	private String empl_email;
	private String empl_phone;
	private String empl_tel;
	private String empl_fax;
	private String empl_joindate;
	private String empl_leavedate;
	private String empl_picture;
	private String empl_dept_cd;
	private String empl_rank_cd;
	private String empl_position_cd;
	private String empl_auth;
	private String empl_delflag;
	private Date empl_last_login_dt;
	private String empl_create_id;
	private Date empl_create_dt;
	private String empl_modify_id;
	private Date empl_modify_dt;
}
