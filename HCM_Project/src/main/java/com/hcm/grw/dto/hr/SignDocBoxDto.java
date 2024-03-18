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
@NoArgsConstructor
@AllArgsConstructor
public class SignDocBoxDto {

	private String sidb_doc_num;
	private String sidb_doc_writedt;
	private String empl_id;
	private String sidb_doc_title;
	private String sidb_doc_content;
	private String sica_cd;
	private String sidb_doc_expiredt;
	private String sidb_doc_flag;
	private String sidb_doc_apprdt;
	private String sidb_doc_delflag;
	private String sidb_doc_alflag;
	private String sidb_doc_stat;
	private String sidb_doc_modifydt;
	private String sidt_temp_cd;
	private String sidb_doc_json;
	private String empl_ref;
	private String empl_dept_cd;
	private String sidb_doc_be;
	private String sidb_doc_end;
	
	private String sica_name;
	private String sidt_temp_name;
	private String sidt_print_type;
	private String sidt_print_date;
}
