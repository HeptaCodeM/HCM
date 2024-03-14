package com.hcm.grw.dto.sm;

import java.util.Date;
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
public class ScbDto {

	private String scbo_no;
	private String scbo_cgory_no;
	private String scbo_empno;
	private String scbo_writer;
	private String scbo_title;
	private String scbo_content;
	private Date scbo_regdate;
	private Date scbo_start;
	private Date scbo_end;
	private String scbo_modify_id;
	private Date scbo_modify_date;
	private String scbo_bigo;
	private String scbo_delflag;
	private String daygridmonth;
	private List<String> type;
	
	
	


	
	
	
}
