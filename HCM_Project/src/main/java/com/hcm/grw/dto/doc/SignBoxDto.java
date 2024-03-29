package com.hcm.grw.dto.doc;

import java.io.Serializable;
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
public class SignBoxDto implements Serializable {
	
	private static final long serialVersionUID = 1194695330680943887L;
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
	private String appr_name0;
	private String appr_flag0;
	private String appr_flag1;
	private String appr_flag2;
	private String appr_depth0;
	private String appr_depth1;
	private String appr_depth2;
	private String max_depth;
	private byte[] empl_picture;
	private String empl_pictureStr;
	private String sidb_curr_id;
	private String empl_rank;
	private String empl_sign;
	
}
