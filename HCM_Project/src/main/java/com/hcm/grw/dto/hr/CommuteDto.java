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
public class CommuteDto {
	private int emco_seq;
	private String empl_id;
	private Date emco_in_dt;
	private Date emco_out_dt;

}
