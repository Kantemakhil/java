package net.syscon.s4.inst.legalscreens.maintenance.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.legalscreens.maintenance.OumhocodRepository;
import net.syscon.s4.inst.legalscreens.maintenance.OumhocodService;
import net.syscon.s4.inst.legalscreens.maintenance.bean.HoCodes;
import net.syscon.s4.inst.legalscreens.maintenance.bean.HoCodesCommitBean;

/**
 * Class OumhocodServiceImpl
 */
@Service
public class OumhocodServiceImpl extends BaseBusiness implements OumhocodService {

	@Autowired
	private OumhocodRepository oumhocodRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<HoCodes> hoCodesExecuteQuery(final HoCodes searchRecord) {
		return oumhocodRepository.hoCodesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstHO_CODES
	 */
	@Transactional
	public List<HoCodes> hoCodesCommit(final HoCodesCommitBean commitBean) {
		final List<HoCodes> liReturnData = new ArrayList<>();
		final HoCodes hoCodes = new HoCodes();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (HoCodes obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oumhocodRepository.hoCodesInsertHoCodes(commitBean.getInsertList());

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (HoCodes obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumhocodRepository.hoCodesUpdateHoCodes(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oumhocodRepository.hoCodesDeleteHoCodes(commitBean.getDeleteList());
		}
		hoCodes.setReturnValue(liReturn);
		liReturnData.add(hoCodes);
		return liReturnData;
	}

	/**
	 * Performing the if the record is having parent data or not
	 */
	@Override
	public Integer hoCodesCheckDeleteMaster(final HoCodes searchBean) {
		return oumhocodRepository.hoCodesCheckDeleteMaster(searchBean);
	}

}