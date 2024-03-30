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
public class ReplyDto {

	private String rebo_no;
	private String gobo_no;
	private String rebo_content;
	private String rebo_writer;
	private String rebo_writer_id;
	private Date rebo_regdate;
	private String rebo_modify_id;
	private Date rebo_modify_date;
	private String rebo_step;
	private String rebo_depth;
	private String rebo_delflag;
	private byte[] empl_picture;
	private String empl_picture_str;
	
}
