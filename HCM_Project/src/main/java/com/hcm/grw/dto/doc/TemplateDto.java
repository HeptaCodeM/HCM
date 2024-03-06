package com.hcm.grw.dto.doc;

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
public class TemplateDto {

	private String sidt_temp_cd;
	private String sica_cd;
	private String sidt_temp_name;
	private String sidt_temp_createdt;
	private String sidt_temp_modifydt;
	private String sidt_temp_flag;
	private String sidt_temp_content;
	private String sica_name;

}
