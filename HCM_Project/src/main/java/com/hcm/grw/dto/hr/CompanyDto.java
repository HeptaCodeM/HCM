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
public class CompanyDto {

	private String comp_id;
	private String comp_name;
	private String comp_num;
	private String comp_ceo_name;
	private String comp_tel;
	private String comp_fax;
	private String comp_email;
	private String comp_post;
	private String comp_addr1;
	private String comp_addr2;
	private byte[] comp_seal;
	private String comp_create_id;
	private String comp_create_dt;
	private String comp_modify_id;
	private String comp_modify_dt;
	
	private String comp_seal_str;
	
}
