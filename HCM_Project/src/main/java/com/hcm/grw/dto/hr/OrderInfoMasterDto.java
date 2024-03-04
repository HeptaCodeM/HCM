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
public class OrderInfoMasterDto {
	private String emor_id;
	private String emor_create_id;
	private Date emor_create_dt;
	private String emor_modify_id;
	private Date emor_modify_dt;
}
