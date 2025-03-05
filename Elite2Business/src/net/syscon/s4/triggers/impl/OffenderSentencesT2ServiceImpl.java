package net.syscon.s4.triggers.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.triggers.OffenderSentencesT2Repository;
import net.syscon.s4.triggers.OffenderSentencesT2Service;

@Service
public class OffenderSentencesT2ServiceImpl implements OffenderSentencesT2Service {
	Logger logger = LogManager.getLogger(OffenderSentencesT2ServiceImpl.class.getName());
	@Autowired
	OffenderSentencesT2Repository offenderSentencesT2Repository;

	@Override
	public Integer offenderSentencesT2Tgr(final List<OffenderSentences> offenderSentencesList) {
		Integer result = 0;
		final OffenderSentences targetObj = new OffenderSentences();
		if (!offenderSentencesList.isEmpty()) {
			try {
				for (final OffenderSentences newObj : offenderSentencesList) {
					final OffenderSentences old = offenderSentencesT2Repository.getOffenderSentences(newObj);
					if (newObj.getSealFlag() == null
							|| (old != null && (newObj.getSealFlag().equals(old.getSealFlag())))) {
						if (Optional.ofNullable(newObj.getStatusUpdateReason()).isPresent()
								&& Optional.ofNullable(newObj.getStatusUpdateReason()).isPresent()
								&& !newObj.getStatusUpdateReason().equals(old.getStatusUpdateReason()))
							BeanUtils.copyProperties(old, targetObj);
						targetObj.setCreateDatetime(newObj.getCreateDatetime());
						targetObj.setCreateUserId(newObj.getCreateUserId());
						targetObj.setModifyDatetime(newObj.getModifyDatetime());
						targetObj.setStatusUpdateReason("ACTIVE");
						targetObj.setStatusUpdateComment(newObj.getStatusUpdateComment());
						targetObj.setStatusUpdateDate(newObj.getStatusUpdateDate());
						targetObj.setStatusUpdateStaffId(newObj.getStatusUpdateStaffId());
						targetObj.setOffenderBookId(newObj.getOffenderBookId());
						if(targetObj.getStatusUpdateStaffId()!=null) {
						result = offenderSentencesT2Repository.insert(targetObj);
						}
					}
				}
			} catch (final Exception e) {
				result = 0;
				logger.error("offenderSentencesT2Tgr", e);
			}
		}
		return result;
	}
}
