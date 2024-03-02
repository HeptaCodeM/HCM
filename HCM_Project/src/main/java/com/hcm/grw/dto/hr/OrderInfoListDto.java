package com.hcm.grw.dto.hr;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoListDto {
	private String emor_id;
	private String empl_id;
	private String empl_name;
	private Date emod_order_dt;
	private String emod_type;
	private String emod_type_nm;
	private String emod_prev_dept;
	private String emod_prev_dept_nm;
	private String emod_order_dept;
	private String emod_order_dept_nm;
	private String emod_prev_rank;
	private String emod_prev_rank_nm;
	private String emod_order_rank;
	private String emod_order_rank_nm;
	private String emod_prev_position;
	private String emod_prev_position_nm;
	private String emod_order_position;
	private String emod_order_position_nm;
}
