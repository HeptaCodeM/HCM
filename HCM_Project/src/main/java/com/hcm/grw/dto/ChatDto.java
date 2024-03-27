package com.hcm.grw.dto;


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
public class ChatDto {
	
	private String ch_no;
	private String ch_message;
	private String ch_sender;
	private String ch_target;
	private String ch_date;
	private String ch_set;
	
	private String sender_name;
	private String target_name;
	private byte[] sender_pic;
	private String sender_pic_str;
	
	private String empl_email;
	private String empl_name;
	private String dept_name;
	private String rank_name;

}
