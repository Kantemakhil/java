package net.syscon.s4.triggers.impl;

import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetails;
import net.syscon.s4.triggers.OffenderSentCalculations;
import net.syscon.s4.triggers.OffenderSentCalculationsT2Repository;
import net.syscon.s4.triggers.OffenderSentCalculationsT2Service;

@Service
public class OffenderSentCalculationsT2ServiceImpl implements OffenderSentCalculationsT2Service {
	final Logger logger = LogManager.getLogger(OffenderSentCalculationsT2ServiceImpl.class);
	@Autowired
	OffenderSentCalculationsT2Repository offenderSentCalculationsT2Repository;

	@Override
	public Integer offenderSentCalculationsT2Trg(final OffenderSentCalculations offeSentCalc) {
		Integer lvCount;
		Date lvApprovedReleaseDate;
		Date lvAutoReleaseDate;
		Date lvDtoApprovedDate;
		Date lvDtoMidTermDate;
		Integer result = 0;
		OffenderReleaseDetails offenderReleDet = new OffenderReleaseDetails();
		lvApprovedReleaseDate = Optional.ofNullable(offeSentCalc.getHdcadOverridedDate()).isPresent()
				? offeSentCalc.getHdcadOverridedDate()
				: offeSentCalc.getHdcedCalculatedDate();
		if (Optional.ofNullable(lvApprovedReleaseDate).isPresent()) {
			final Date apdOverridedDateVal = Optional.ofNullable(offeSentCalc.getApdOverridedDate()).isPresent()
					? offeSentCalc.getApdOverridedDate()
					: offeSentCalc.getApdCalculatedDate();
			if (Optional.ofNullable(apdOverridedDateVal).isPresent()
					&& lvApprovedReleaseDate.compareTo(apdOverridedDateVal) < 0) {
				lvApprovedReleaseDate = Optional.ofNullable(offeSentCalc.getApdOverridedDate()).isPresent()
						? offeSentCalc.getApdOverridedDate()
						: offeSentCalc.getApdCalculatedDate();
			}
		} else {
			lvApprovedReleaseDate = Optional.ofNullable(offeSentCalc.getApdOverridedDate()).isPresent()
					? offeSentCalc.getApdOverridedDate()
					: offeSentCalc.getApdCalculatedDate();

		}

		if (Optional.ofNullable(lvApprovedReleaseDate).isPresent()) {
			final Date apdOverridedDateVal = Optional.ofNullable(offeSentCalc.getPrrdOverridedDate()).isPresent()
					? offeSentCalc.getPrrdOverridedDate()
					: offeSentCalc.getPrrdCalculatedDate();
			if (Optional.ofNullable(apdOverridedDateVal).isPresent()
					&& lvApprovedReleaseDate.compareTo(apdOverridedDateVal) < 0) {
				lvApprovedReleaseDate = Optional.ofNullable(offeSentCalc.getPrrdOverridedDate()).isPresent()
						? offeSentCalc.getPrrdOverridedDate()
						: offeSentCalc.getPrrdCalculatedDate();
			}
		} else {
			lvApprovedReleaseDate = Optional.ofNullable(offeSentCalc.getPrrdOverridedDate()).isPresent()
					? offeSentCalc.getPrrdOverridedDate()
					: offeSentCalc.getPrrdCalculatedDate();

		}

		lvAutoReleaseDate = Optional.ofNullable(offeSentCalc.getCrdOverridedDate()).isPresent()
				? offeSentCalc.getCrdOverridedDate()
				: offeSentCalc.getCrdCalculatedDate();
		if (Optional.ofNullable(lvAutoReleaseDate).isPresent()) {
			final Date ardOverridedDateVal = Optional.ofNullable(offeSentCalc.getArdOverridedDate()).isPresent()
					? offeSentCalc.getArdOverridedDate()
					: offeSentCalc.getArdCalculatedDate();
			if (Optional.ofNullable(ardOverridedDateVal).isPresent()
					&& lvAutoReleaseDate.compareTo(ardOverridedDateVal) < 0) {
				lvAutoReleaseDate = Optional.ofNullable(offeSentCalc.getArdOverridedDate()).isPresent()
						? offeSentCalc.getArdOverridedDate()
						: offeSentCalc.getArdCalculatedDate();
			}
		} else {
			lvAutoReleaseDate = Optional.ofNullable(offeSentCalc.getArdOverridedDate()).isPresent()
					? offeSentCalc.getArdOverridedDate()
					: offeSentCalc.getArdCalculatedDate();

		}

		if (Optional.ofNullable(lvAutoReleaseDate).isPresent()) {
			final Date npdOverridedDateVal = Optional.ofNullable(offeSentCalc.getNpdOverridedDate()).isPresent()
					? offeSentCalc.getNpdOverridedDate()
					: offeSentCalc.getNpdCalculatedDate();
			if (Optional.ofNullable(npdOverridedDateVal).isPresent()
					&& lvAutoReleaseDate.compareTo(npdOverridedDateVal) < 0) {
				lvAutoReleaseDate = Optional.ofNullable(offeSentCalc.getNpdOverridedDate()).isPresent()
						? offeSentCalc.getNpdOverridedDate()
						: offeSentCalc.getNpdCalculatedDate();
			}
		} else {
			lvAutoReleaseDate = Optional.ofNullable(offeSentCalc.getNpdOverridedDate()).isPresent()
					? offeSentCalc.getNpdOverridedDate()
					: offeSentCalc.getNpdCalculatedDate();

		}

		lvDtoApprovedDate = Optional.ofNullable(offeSentCalc.getMtdOverridedDate()).isPresent()
				? offeSentCalc.getMtdOverridedDate()
				: offeSentCalc.getMtdCalculatedDate();
		if (Optional.ofNullable(lvDtoApprovedDate).isPresent()) {
			final Date ltdOverridedDateVal = Optional.ofNullable(offeSentCalc.getLtdOverridedDate()).isPresent()
					? offeSentCalc.getLtdOverridedDate()
					: offeSentCalc.getLtdCalculatedDate();
			if (Optional.ofNullable(ltdOverridedDateVal).isPresent()
					&& lvDtoApprovedDate.compareTo(ltdOverridedDateVal) < 0) {
				lvDtoApprovedDate = Optional.ofNullable(offeSentCalc.getLtdOverridedDate()).isPresent()
						? offeSentCalc.getLtdOverridedDate()
						: offeSentCalc.getLtdCalculatedDate();
			}
		} else {
			lvDtoApprovedDate = Optional.ofNullable(offeSentCalc.getLtdOverridedDate()).isPresent()
					? offeSentCalc.getLtdOverridedDate()
					: offeSentCalc.getLtdCalculatedDate();

		}
		if (Optional.ofNullable(lvDtoApprovedDate).isPresent()) {
			final Date ardOverridedDateVal = Optional.ofNullable(offeSentCalc.getEtdOverridedDate()).isPresent()
					? offeSentCalc.getEtdOverridedDate()
					: offeSentCalc.getEtdCalculatedDate();
			if (Optional.ofNullable(ardOverridedDateVal).isPresent()
					&& lvDtoApprovedDate.compareTo(ardOverridedDateVal) < 0) {
				lvDtoApprovedDate = Optional.ofNullable(offeSentCalc.getEtdOverridedDate()).isPresent()
						? offeSentCalc.getEtdOverridedDate()
						: offeSentCalc.getEtdCalculatedDate();
			}
		} else {
			lvDtoApprovedDate = Optional.ofNullable(offeSentCalc.getEtdOverridedDate()).isPresent()
					? offeSentCalc.getEtdOverridedDate()
					: offeSentCalc.getEtdCalculatedDate();

		}
		lvDtoMidTermDate = offeSentCalc.getMtdOverridedDate();
		lvCount = offenderSentCalculationsT2Repository.lvCount(offeSentCalc.getOffenderBookId());

		if (lvCount > 0) {
			offenderReleDet = new OffenderReleaseDetails();
			offenderReleDet.setApprovedReleaseDate(lvApprovedReleaseDate);
			offenderReleDet.setAutoReleaseDate(lvAutoReleaseDate);
			offenderReleDet.setDtoApprovedDate(lvDtoApprovedDate);
			offenderReleDet.setDtoMidTermDate(lvDtoMidTermDate);
			offenderReleDet.setModifyUserId(offeSentCalc.getModifyUserId());
			offenderReleDet.setModifyDatetime(offeSentCalc.getModifyDatetime());
			result = offenderSentCalculationsT2Repository.update(offenderReleDet);
		} else {
			offenderReleDet = new OffenderReleaseDetails();
			offenderReleDet.setApprovedReleaseDate(lvApprovedReleaseDate);
			offenderReleDet.setAutoReleaseDate(lvAutoReleaseDate);
			offenderReleDet.setDtoApprovedDate(lvDtoApprovedDate);
			offenderReleDet.setDtoMidTermDate(lvDtoMidTermDate);
			offenderReleDet.setModifyDatetime(offeSentCalc.getModifyDatetime());
			offenderReleDet.setCreateDatetime(offeSentCalc.getCreateDatetime());
			offenderReleDet.setCreateUserId(offeSentCalc.getCreateUserId());
			result = offenderSentCalculationsT2Repository.insert(offenderReleDet);
		}

		return result;
	}

}
