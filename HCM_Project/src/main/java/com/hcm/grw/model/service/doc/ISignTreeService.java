package com.hcm.grw.model.service.doc;

import java.util.List;

import com.hcm.grw.dto.doc.SignTreeDto;

public interface ISignTreeService {
	
	public List<SignTreeDto> getSignTree();
	
	public int insertTree(SignTreeDto treeDto);
	
	public int updateTree(SignTreeDto treeDto);

}
