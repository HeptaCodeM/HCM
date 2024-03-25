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
public class SignTempBoxDto {
	
	private String sitb_doc_num;
	private String sitb_doc_writedt;
	private String empl_id;
	private String sitb_doc_title;
	private String sitb_doc_content;
	private String sica_cd;
	private String sitb_doc_expiredt;
	private String sitb_doc_alflag;
	private String sidt_temp_cd;
	private String empl_ref;
	private String empl_dept_cd;
	private String sitb_doc_be;
	private String sitb_doc_end;
	private String emsi_seq;
	private String sitb_curr_id;
	
	private List<SignJsonDto> sitb_doc_json;
	
}
