package net.syscon.s4.pkgs.tag_qm_pai.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.syscon.s4.pkgs.tag_qm_pai.TagQmPaiRepository;
import net.syscon.s4.pkgs.tag_qm_pai.TagQmPaiService;
import net.syscon.s4.triggers.QmActivitiesIns;
import net.syscon.s4.triggers.VQmPai;

@Repository
public class TagQmPaiServiceImpl implements TagQmPaiService {
	@Autowired
	TagQmPaiRepository tagQmPaiRepository;

	@Override
	public Integer prIns(final VQmPai lrOldRec, final VQmPai lrNewRec) {
		return null;
	}

	@Override
	public Integer prUpd(final VQmPai lrOldRec, final VQmPai lrNewRec) {
		final QmActivitiesIns amActivitiesIns = new QmActivitiesIns();
		amActivitiesIns.setActivityInsId(new BigDecimal(lrNewRec.getActivityInsId()));
		amActivitiesIns.setEndTime(lrOldRec.getEndTime());
		amActivitiesIns.setStaffId(lrOldRec.getStaffId());
		amActivitiesIns.setCreateUserId(lrNewRec.getCreateUserId());
		return tagQmPaiRepository.prUpd(amActivitiesIns);
	}

	@Override
	public Integer prDel(final VQmPai lrOldRec, final VQmPai lrNewRec) {
		return null;
	}

}
