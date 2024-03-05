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
public class SignFileDto {

	private String sidf_file_num;
	private String sidb_doc_num;
	private String sidf_file_origin;
	private String sidf_file_stored;
	private String sidf_file_size;
	private String sidf_file_content;
	private String sidf_file_flag;

}
