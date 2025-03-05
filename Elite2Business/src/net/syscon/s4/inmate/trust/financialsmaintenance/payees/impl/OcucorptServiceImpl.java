package net.syscon.s4.inmate.trust.financialsmaintenance.payees.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.CorporateTypesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.CorporateTypes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inmate.trust.financialsmaintenance.payees.OcucorptRepository;
import net.syscon.s4.inmate.trust.financialsmaintenance.payees.OcucorptService;

@Service
public class OcucorptServiceImpl extends BaseBusiness implements OcucorptService {

	@Autowired
	private OcucorptRepository ocucorptRepository;

	/**
	 * Creates new OtmbaccoServiceImpl class Object
	 */

	/**
	 * Creates new OcucorptServiceImpl class Object
	 */
	public OcucorptServiceImpl() {
	}

	public List<CorporateTypes> corporateTypesExecuteQuery(final CorporateTypes searchRecord) {
		List<CorporateTypes> result = ocucorptRepository.corporateTypesExecuteQuery(searchRecord);
		if (result != null) {
			result.forEach(data -> {
				data.setNbtCorporateType(data.getCorporateType());
			});
		}
		return result;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCORPORATE_TYPES
	 *
	 * 
	 */
	@Transactional
	public Integer corporateTypesCommit(final CorporateTypesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			commitBean.getInsertList().forEach(data->{data.setCreateUserId(commitBean.getCreateUserId());});
			liReturn = ocucorptRepository.corporateTypesInsertCorporateTypes(commitBean.getInsertList());
			return liReturn;
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			commitBean.getUpdateList().forEach(data->{data.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = ocucorptRepository.corporateTypesUpdateCorporateTypes(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(data->{data.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = ocucorptRepository.corporateTypesDeleteCorporateTypes(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgCorpTypeRecordGroup() {
		return ocucorptRepository.rgCorpTypeRecordGroup();

	}

	@Override
	public Integer prevCaseloadCorpExists(final Corporates paramBean) {
		return ocucorptRepository.prevCaseloadCorpExists(paramBean);
	}

}