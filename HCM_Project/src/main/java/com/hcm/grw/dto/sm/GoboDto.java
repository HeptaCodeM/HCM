package com.hcm.grw.dto.sm;

import java.util.Date;


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
public class GoboDto {

	private String gobo_no;
	private String gobo_title;
	private String gobo_content;
	private String gobo_view;
	private String gobo_writer;
	private String gobo_writer_id;
	private Date gobo_regdate;
	private String gobo_modify_id;
	private Date gobo_modify_date;
	private String gobo_delflag;
	private String gobo_bigo;
}
