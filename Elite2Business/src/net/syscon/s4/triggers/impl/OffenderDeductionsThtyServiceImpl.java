package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.OffenderDeductionsHty;
import net.syscon.s4.triggers.OffenderDeductionsThtyRepository;
import net.syscon.s4.triggers.OffenderDeductionsThtyService;

@Service
public class OffenderDeductionsThtyServiceImpl implements OffenderDeductionsThtyService {
	private static Logger logger = LogManager.getLogger(OffenderDeductionsThtyServiceImpl.class);
	@Autowired
	private OffenderDeductionsThtyRepository offenderDeductionsThtyRepository;

	@Override
	public Integer OffenderDeductionsThtyTrigger(final OffenderDeductionsHty offenderDeductionsThty, final String insert) {
		OffenderDeductionsHty targetObj = new OffenderDeductionsHty();
		List<OffenderDeductionsHty> insertList = new ArrayList<>();
		Integer result = 0;
//		final OffenderDeductionsHty oldObj = offenderDeductionsThtyRepository
//				.getOffenderDeductionsHty(offenderDeductionsThty);
		if ("INSERT".equals(insert)) {
			dataMapping(offenderDeductionsThty, targetObj);
			insertList.add(targetObj);
			result = offenderDeductionsThtyRepository.insertingUpdateDelete(insertList);
		} else if ("UPDATE".equals(insert)) {
		//	final OffenderDeductionsHty oldObj = offenderDeductionsThtyRepository.getOffenderDeductionsHty(offenderDeductionsThty);
			targetObj = new OffenderDeductionsHty();
			insertList = new ArrayList<>();
			dataMapping(offenderDeductionsThty, targetObj);
			targetObj.setJnOperation("UPD");
			targetObj.setJnOracleUser(offenderDeductionsThty.getCreateUserId());
			insertList.add(targetObj);
			result = offenderDeductionsThtyRepository.insertingUpdateDelete(insertList);
		} else if ("DELETE".equals(insert)) {
			targetObj = new OffenderDeductionsHty();
			insertList = new ArrayList<>();
			dataMapping(offenderDeductionsThty, targetObj);
			targetObj.setJnOperation("DEL");
			insertList.add(targetObj);
			result = offenderDeductionsThtyRepository.insertingUpdateDelete(insertList);
		}
		return result;
	}

	private void dataMapping(final OffenderDeductionsHty offenderDeductionsThty,
			final OffenderDeductionsHty targetObj) {
		BeanUtils.copyProperties(offenderDeductionsThty, targetObj);
		targetObj.setJnOperation("INS");
		targetObj.setJnOracleUser(offenderDeductionsThty.getCreateUserId());
		targetObj.setJnDatetime(new Date());
		targetObj.setJnNotes(null);
		targetObj.setJnAppln(null);
		targetObj.setJnSession(new BigDecimal(0));
	}
}
