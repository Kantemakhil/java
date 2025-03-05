package net.syscon.s4.triggers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.ImageOriginals;
import net.syscon.s4.triggers.ImagesT1Repository;
import net.syscon.s4.triggers.ImagesT1Service;
@Service
public class ImagesT1ServiceImpl implements ImagesT1Service{

	@Autowired
	ImagesT1Repository imagest1repository;
	
	@Override
	public Integer imageOriginalsInsert(List<ImageOriginals> insertList) {
		return imagest1repository.imageOriginalsInsert(insertList);
	}

}
