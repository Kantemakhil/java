package net.syscon.s4.pkgs.tag_off_ap_v1.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderActionPlans;
import net.syscon.s4.pkgs.tag_off_ap_v1.TagOffApV1Repository;
import net.syscon.s4.pkgs.tag_off_ap_v1.TagOffApV1Service;

@Service
public class TagOffApV1ServiceImpl implements TagOffApV1Service {
	Logger logger = LogManager.getLogger(TagOffApV1ServiceImpl.class);
	@Autowired
	TagOffApV1Repository tagOffApV1Repository;

	@Override
	public Integer prInsProcedure(final OffenderActionPlans lrOldRec, final OffenderActionPlans lrNewRec) {
		Integer result = 0;
		final OffenderActionPlans targetObj = new OffenderActionPlans();
		try {
			BeanUtils.copyProperties(lrNewRec, targetObj);
			result = tagOffApV1Repository.prIns(targetObj);
		} catch (final Exception e) {
			logger.error("prInsProcedure", e);
		}
		return result;
	}

	@Override
	public Integer prDelProcedure(final OffenderActionPlans lrOldRec, final OffenderActionPlans lrNewRec) {
		Integer result = 0;
		try {
			result = tagOffApV1Repository.prDel(lrOldRec);
		} catch (final Exception e) {
			logger.error("prDelProcedure", e);
		}
		return result;
	}

	@Override
	public Integer prUpdProcedure(final OffenderActionPlans lrOldRec, final OffenderActionPlans lrNewRec) {
		Integer result = 0;
		final OffenderActionPlans targetObj = new OffenderActionPlans();
		try {
			BeanUtils.copyProperties(lrNewRec, targetObj);
			result = tagOffApV1Repository.prUpd(targetObj);
		} catch (final Exception e) {
			logger.error("prUpdProcedure", e);
			result = 0;
		}
		return result;
	}

}
