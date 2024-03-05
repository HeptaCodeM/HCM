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
public class SignJsonDto {

	private String appr_id;
	private String appr_depth;
	private String appr_sign;
	private String appr_dt;
	private String appr_flag;
	private String appr_reply;
	
}
