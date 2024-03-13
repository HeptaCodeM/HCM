package com.hcm.grw.dto.doc;

import java.util.List;

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
public class SignBoxDto {
	
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
	private String empl_ref;
	private String empl_dept_cd;
	private String sidb_doc_be;
	private String sidb_doc_end;
	
	private List<SignJsonDto> sidb_doc_json;
	private String empl_name;
	
	private String appr_reply;
	private String appr_id;
	private String appr_depth;
	private String appr_sign;
	private String appr_dt;
	private String appr_flag;
	private String appr_name;
	private String appr_rank;
	private String writer_dt;
	private int turn_index;
	private String string_index = Integer.toString(turn_index);
	private String emsi_seq;
	private String sidt_temp_name;
	private String appr_name1;
	private String appr_name2;
}
