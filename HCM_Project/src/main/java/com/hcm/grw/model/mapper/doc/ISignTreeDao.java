package com.hcm.grw.model.mapper.doc;

import java.util.List;

import com.hcm.grw.dto.doc.SignTreeDto;

public interface ISignTreeDao {
	
	public List<SignTreeDto> getSignTree();
	
	public int insertTree(SignTreeDto treeDto);
	
	public int updateTree(SignTreeDto treeDto);

}
