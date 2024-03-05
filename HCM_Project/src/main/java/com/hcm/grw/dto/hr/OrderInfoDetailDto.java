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
public class OrderInfoDetailDto {
	private String emor_id;
	private int emod_seq;
	private String empl_id;
	private String emod_order_dt;
	private String emod_type;
	private String emod_prev_dept;
	private String emod_order_dept;
	private String emod_prev_rank;
	private String emod_order_rank;
	private String emod_prev_position;
	private String emod_order_position;
	private String emod_create_id;
	private Date emod_create_dt;
	private String emod_modify_id;
	private Date emod_modify_dt;
}
