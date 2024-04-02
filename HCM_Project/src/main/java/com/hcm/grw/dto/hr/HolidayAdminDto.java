package com.hcm.grw.dto.hr;

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
public class HolidayAdminDto {
	private String empl_id;
	private String empl_name;
	private String empl_dept_nm;
	private String empl_rank_nm;
	private String empl_position_nm;
	private String total_holiday;
	private String use_holiday;
	private String rest_holiday;
	private String standard_holiday;
}
