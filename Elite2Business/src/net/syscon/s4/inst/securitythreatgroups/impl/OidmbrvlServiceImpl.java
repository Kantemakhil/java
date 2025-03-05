package net.syscon.s4.inst.securitythreatgroups.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderStgDetails;
import net.syscon.s4.im.beans.OffenderStgDetailsCommitBean;
import net.syscon.s4.inst.securitythreatgroups.OidmbrvlRepository;
import net.syscon.s4.inst.securitythreatgroups.OidmbrvlService;
/**
 * Class OidmbrvlServiceImpl */
@Service
public class OidmbrvlServiceImpl extends BaseBusiness implements OidmbrvlService{

@Autowired
private OidmbrvlRepository oidmbrvlRepository;

/**
 * 
 */
public OidmbrvlServiceImpl() {
}

/**
 * This method is execute a Dao class method when trigger event is fired
 *
 * @param params
 *
 
*/
	public BigDecimal offenderStgDetailsPreInsert(final OffenderStgDetails paramBean)  {
		
		return oidmbrvlRepository.offenderStgDetailsPreInsertPreInsert(paramBean);
}


/**
 * This method is execute a Dao class method when trigger event is fired
 *
 * @param params
 *
 
*/
	public Date validateValDate(final OffenderStgDetails paramBean)  {
		return oidmbrvlRepository.validateValDate(paramBean);
}

/**Fetch the records from database table
 *
 * @param searchRecord
 *
 
 */
public List<OffenderStgDetails> offenderStgDetailsExecuteQuery(final OffenderStgDetails searchRecord)  {
		return oidmbrvlRepository.offenderStgDetailsExecuteQuery(searchRecord);

}

/**Insert the records from database table
 *
 * @param lstOFFENDER_STG_DETAILS
 *
 
 */
@Transactional(rollbackFor=Exception.class)
public Integer offenderStgDetailsCommit(final OffenderStgDetailsCommitBean commitBean)  {
		int liReturn = 0;
		final List<OffenderStgDetails> InsertList = new ArrayList<>();
			if (commitBean.getInsertList() != null	&& !commitBean.getInsertList().isEmpty()) {
				commitBean.getInsertList().stream().forEach(data -> {
					InsertList.clear();
					data.setCreateUserId(commitBean.getCreateUserId());
					data.setModifyUserId(commitBean.getCreateUserId());
					final BigDecimal detailSeq = offenderStgDetailsPreInsert(data);
					if (detailSeq != null) {
						data.setDetailSeq(detailSeq);
					}
					InsertList.add(data);
					oidmbrvlRepository.offenderStgDetailsInsertOffenderStgDetails(InsertList);
					oidmbrvlRepository.updateOffenderStgAffiliationsAppealDate(data);
					
				});
				liReturn = InsertList.size();
			}
			return liReturn;
}
/**
 * This method is used to execute a record group
 *
 
*/
public List<ReferenceCodes> rgActionRecordGroup()  {
		return oidmbrvlRepository.rgActionRecordGroup();

}
/**
 * This method is used to execute a record group
 *
 
*/
public List<ReferenceCodes> rgReasonRecordGroup()  {
		return oidmbrvlRepository.rgReasonRecordGroup();

}

}