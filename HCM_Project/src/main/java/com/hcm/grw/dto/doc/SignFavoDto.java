package com.hcm.grw.dto.doc;

import com.hcm.grw.dto.hr.EmployeeDto;

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
public class SignFavoDto {

	private String siaf_favo_cd;
	private String empl_id;
	private String siaf_favo_name;
	private String siaf_appr_id;
	private String siaf_appr_line;
	private String siaf_appr_flag;
	
	private EmployeeDto employee;
}
