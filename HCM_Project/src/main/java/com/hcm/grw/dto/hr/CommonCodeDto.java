package com.hcm.grw.dto.hr;

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
@AllArgsConstructor
@NoArgsConstructor
public class CommonCodeDto {

	private String coco_cd;
	private String coco_name;
	private String coco_parent_id;
	private String coco_delflag;
	private String coco_create_id;
	private String coco_create_dt;
	private String coco_modify_id;
	private String coco_modify_dt;
	
}
