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
public class HolidayDto {
	private String sidt_temp_name;
	private Date sidb_doc_apprdt;
	private Date sidb_doc_be;
	private Date sidb_doc_end;
	private String sidb_doc_date;
	private String holiday;
}
