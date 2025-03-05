package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.OmtofsbRepository;
import net.syscon.s4.triggers.OmtofsbService;
@Service
public class OmtofsbServiceImpl implements OmtofsbService {
	@Autowired
	private OmtofsbRepository omtofsbRepository;

	@Override
	public Integer deleteOffenderFileTrigger(String modifuUserId) {
		Integer count=null;
		count=omtofsbRepository.deleteOffenderFileTrigger(modifuUserId);
		return count;
	}

}
