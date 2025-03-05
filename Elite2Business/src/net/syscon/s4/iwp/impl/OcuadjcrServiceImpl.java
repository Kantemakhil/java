package net.syscon.s4.iwp.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.legals.beans.OcdlegloSanctionHty;
import net.syscon.s4.inst.legals.beans.OffenderSentencesHty;
import net.syscon.s4.inst.legals.beans.OffenderSentencesHtyCommitBean;
import net.syscon.s4.iwp.OcuadjcrRepository;
import net.syscon.s4.iwp.OcuadjcrService;

/**
 * Class OcuadjcrServiceImpl
 */
@Service
public class OcuadjcrServiceImpl extends BaseBusiness implements OcuadjcrService {

	@Autowired
	private OcuadjcrRepository ocuadjcrRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderSentencesHty> ctlBlkExecuteQuery(OffenderSentencesHty searchRecord) {
		List<OffenderSentencesHty> offenderSentencesHtyList = ocuadjcrRepository.ctlBlkExecuteQuery(searchRecord);
		if (offenderSentencesHtyList != null && !offenderSentencesHtyList.isEmpty()) {
			for (OffenderSentencesHty offenderSentencesHty : offenderSentencesHtyList) {
				if (Optional.ofNullable(offenderSentencesHty).isPresent()) {
					offenderSentencesHty.setStaffId(ocuadjcrRepository.getStaffId());
					String staffName = ocuadjcrRepository.getStaffName(offenderSentencesHty.getStaffId());
					if (Optional.ofNullable(staffName).isPresent()) { 
						offenderSentencesHty.setStaffName(staffName);
					} 
				}

			}
		}
		return offenderSentencesHtyList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCTL_BLK
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer ctlBlkCommit(OffenderSentencesHty commitBean) {
		int liReturn = 0;
		int liReturn1 = 0;
		int liReturn2 = 0;
		if (commitBean != null) {
			commitBean.setOffenderSentenceHtyId(ocuadjcrRepository.preInsert());
			liReturn1 = ocuadjcrRepository.ctlBlkInsertOffenderSentenceHty(commitBean);
			liReturn2=ocuadjcrRepository.postInsert(commitBean.getNoOfUnexcusedAbsence(), commitBean.getOffenderBookId(), commitBean.getSentenceSeq());
		}
		
		if(liReturn1 ==1 && liReturn2== 1) {
			liReturn=1;
		} else {
			liReturn=0;
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderSentencesHty> offSenHtyExecuteQuery(OffenderSentencesHty searchRecord) {
		return ocuadjcrRepository.ctlBlkExecuteQuery(searchRecord);

	}

/**Insert the records from database table
 *
 * @param lstOFF_SEN_HTY
 *
 * @throws SQLException
 */
@Transactional
public Integer offSenHtyCommit(OffenderSentencesHtyCommitBean commitBean)  {
		int liReturn = 0;
		return liReturn;
}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgReasonRecordGroup() {
		return ocuadjcrRepository.rgReasonRecordGroup();

	}

	@Override
	public String staffName() {
		return ocuadjcrRepository.getStaffName(ocuadjcrRepository.getStaffId());
	}
	
	@Override
	public List<OcdlegloSanctionHty> ocdlegloSenHtyExecuteQuery(OffenderSentencesHty searchBean) {
		return ocuadjcrRepository.ocdlegloSenHtyExecuteQuery(searchBean);
	}
	
	@Override
	public Integer ocdlegloSentCommit(OcdlegloSanctionHty commitBean) {
		Integer returnVal = 0;
		if(commitBean != null) {
			BigDecimal htyId = ocuadjcrRepository.getSanctionHtyId();
			commitBean.setOffSanctionSentHtyId(htyId);
			commitBean.setStaffId(ocuadjcrRepository.getStaffId(commitBean.getCreateUserId()));
			returnVal = ocuadjcrRepository.ocdlegloSentCommit(commitBean);
		}
		return returnVal;
	}


}