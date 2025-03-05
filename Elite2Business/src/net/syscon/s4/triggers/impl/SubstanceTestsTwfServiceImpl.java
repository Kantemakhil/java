package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.tag_wfmsg.TagWfmsgService;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowService;
import net.syscon.s4.triggers.SubstanceTests;
import net.syscon.s4.triggers.SubstanceTestsTwfService;

@Service
public class SubstanceTestsTwfServiceImpl implements SubstanceTestsTwfService {
	final Logger logger = LogManager.getLogger(SubstanceTestsTwfServiceImpl.class.getName());
	@Autowired
	TagWfmsgService tagWfmsgService;
	@Autowired
	TagWorkflowService tagWorkflowService;

	@Override
	public Integer substanceTestsTwfTgr(final List<SubstanceTests> substanceTestsList) {
		if (!substanceTestsList.isEmpty()) {
			try {
				for (final SubstanceTests newObj : substanceTestsList) {
					if (Optional.ofNullable(newObj).isPresent() && "P".equals(newObj.getResults())) {
//					SQLXML lvXml = tagWfmsgService.createXml();
						tagWfmsgService.append("substance_seq", String.valueOf(newObj.getTestSeq()), null);
						tagWorkflowService.createCaseNote(new BigDecimal(newObj.getOffenderBookId()), "SUB_TEST",
								substanceTestsList, null, null, "AUTO", newObj.getCreateUserId());
					}
				}
			} catch (final Exception e) {
				logger.error("substanceTestsTwfTgr", e);
			}
		}
		return null;
	}

}
