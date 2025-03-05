package net.syscon.s4.sa.admin.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.pkgs.tag_utils.TagUtilsService;
import net.syscon.s4.sa.admin.OmunvaryRepository;
import net.syscon.s4.sa.admin.OmunvaryService;
import net.syscon.s4.sa.admin.beans.NameSynonyms;
import net.syscon.s4.sa.admin.beans.NameSynonymsCommitBean;

/**
 * Class OmunvaryServiceImpl
 */
@Service
public class OmunvaryServiceImpl extends BaseBusiness implements OmunvaryService {

	@Autowired
	private OmunvaryRepository omunvaryRepository;  

	@Autowired
	private TagUtilsService tagUtilsService;
	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<NameSynonyms> nameSynonymsExecuteQuery(final NameSynonyms searchRecord) {
		return omunvaryRepository.nameSynonymsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstNAME_SYNONYMS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public String nameSynonymsCommit(final NameSynonymsCommitBean commitBean) {
		String liReturn = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (NameSynonyms obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			final String returnobj = omunvaryRepository.nameSynonymsInsertNameSynonyms(commitBean.getInsertList());
			if (returnobj.length() > 2) {
				liReturn = returnobj;
			} else if ("1".equals(returnobj)) {
				liReturn = "1";
			} 

		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = omunvaryRepository.nameSynonymsDeleteNameSynonyms(commitBean.getDeleteList());
		}
		return liReturn;
	}

	@Override
	public String getTableName(String liReturn) {
		return tagUtilsService.getTableName(liReturn);
	}

}