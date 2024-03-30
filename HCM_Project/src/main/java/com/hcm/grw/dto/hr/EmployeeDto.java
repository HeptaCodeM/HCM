package com.hcm.grw.dto.hr;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonSerialize
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto implements Serializable {
	
	private static final long serialVersionUID = 8178535326061438451L;
	
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
	private byte[] empl_picture;
	private String empl_picture_str;
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
	
	private String empl_dept_nm;
	private String empl_rank_nm;
	private String empl_position_nm;
	private String empl_auth_nm;
	
	private String coco_name_dnm;
	private String coco_name_rnm;
	private String coco_name_pnm;
	private String startdate;
	private String enddate;
	
	private CommonCodeDto comm;
	
	private String emsi_seq;
	
}
