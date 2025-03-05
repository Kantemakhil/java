package net.syscon.s4.globalconfiguration.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReferenceCodesCommitBean;
import net.syscon.s4.common.beans.ReferenceDomains;
import net.syscon.s4.common.beans.ReferenceDomainsCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalconfiguration.OumrcodeRepository;
import net.syscon.s4.globalconfiguration.OumrcodeService;

@Service
public class OumrcodeServiceImpl extends BaseBusiness implements OumrcodeService {

	@Autowired
	private OumrcodeRepository oumrcodeRepository;

	/**
	 * Creates new OumrcodeserviceImpl class Object
	 */
	public OumrcodeServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceDomains cguvchkReferenceDomainsPk(ReferenceDomains paramBean)  {
		
		return oumrcodeRepository.cguvchkReferenceDomainsPk(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes cguvchkReferenceCodesPk(ReferenceCodes paramBean)  {
		
		return oumrcodeRepository.cguvchkReferenceCodesPk(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<ReferenceDomains> cgrichkReferenceDomains(ReferenceDomains paramBean)  {
		
		return oumrcodeRepository.cgrichkReferenceDomains(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<ReferenceDomains> refDmnExecuteQuery(ReferenceDomains searchRecord)  {
		return oumrcodeRepository.refDmnExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstREF_DMN
	 *
	 * @
	 */
	@Transactional
	public Integer refDmnCommit(ReferenceDomainsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (ReferenceDomains obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				if (obj.getParentDomain() == "") {
					obj.setParentDomain(null);
				}
			}
			 liReturn =
			 oumrcodeRepository.refDmnInsertReferenceDomains(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (ReferenceDomains obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			 liReturn =
			 oumrcodeRepository.refDmnUpdateReferenceDomains(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<ReferenceCodes> refCodeExecuteQuery(ReferenceCodes searchRecord)  {
		return oumrcodeRepository.refCodeExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstREF_CODE
	 *
	 * @
	 */
	@Transactional
	public Integer refCodeCommit(ReferenceCodesCommitBean commitBean)  {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (ReferenceCodes obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			 liReturn =
			oumrcodeRepository.refCodeInsertReferenceDomains(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (ReferenceCodes obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			 liReturn =
			 oumrcodeRepository.refCodeUpdateReferenceDomains(commitBean.getUpdateList());
		}
		return liReturn;
	}

}