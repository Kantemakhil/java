package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OffendersCommitBean;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.beans.OffenderSubAccountsCommitBean;
import net.syscon.s4.inmate.trust.trustaccounts.OtusubacRepository;
import net.syscon.s4.inmate.trust.trustaccounts.OtusubacService;
/**
 * Class OtusubacServiceImpl
 */
@Service
public class OtusubacServiceImpl extends BaseBusiness implements OtusubacService{

@Autowired
private OtusubacRepository otusubacRepository;

/**
 *Creates new OtusubacServiceImpl class Object 
 */
public OtusubacServiceImpl(){
}

/**
 * This method is execute a Dao class method when trigger event is fired
 *
 * @param params
 *
 * @throws Exception
*/
	public Caseloads offSubaPreQuery(Caseloads paramBean) {

		return paramBean;
}


/**
 * This method is execute a Dao class method when trigger event is fired
 *
 * @param params
 *
 * @throws Exception
*/
	public AccountCodes CgfkchkOffSubaOffSubaAc(AccountCodes paramBean) {
//			AccountCodes accountCodes = otusubacRepository.cgfkchkOffSubaOffSubaAcLsubaccttypecur (paramBean);
//			AccountCodes accountCodes = otusubacRepository.cgfkchkOffSubaOffSubaAcLdesccur (paramBean);
 return  paramBean;
}

/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * @throws SQLException
 */
public List<Offenders> offNameExecuteQuery(Offenders searchRecord) {
		return otusubacRepository.offNameExecuteQuery(searchRecord);

}

/**Insert the records from database table
 *
 * @param lstOFF_NAME
 *
 * @throws SQLException
 */
@Transactional
public Integer offNameCommit(OffendersCommitBean commitBean) {
		int liReturn = 0;
		return liReturn;
}

/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * @throws SQLException
 */
public List<OffenderSubAccounts> offSubaExecuteQuery(OffenderSubAccounts searchRecord) {
	List<OffenderSubAccounts> resultData = otusubacRepository.offSubaExecuteQuery(searchRecord);
	final ReferenceCodes referenceCodes = new ReferenceCodes();
	final AccountCodes accountCodes = new AccountCodes();
	if (resultData != null) {
		resultData.forEach(data -> {
			if (data.getTrustAccountCode() != null) {
				accountCodes.setAccountCode(Integer.parseInt(data.getTrustAccountCode().toString()));
			}
			final AccountCodes accountCodeResult = otusubacRepository.cgfkchkOffSubaOffSubaAc(accountCodes);
			if (accountCodeResult != null) {
				data.setCode(accountCodeResult.getSubAccountType());
			}
			if (data.getCode() != null) {
				referenceCodes.setCode(data.getCode());
				final ReferenceCodes referenceCodesResult = otusubacRepository.cgfkchkOffSubaOffSubaAc(referenceCodes);
				if (referenceCodesResult != null) {
					data.setDescription(referenceCodesResult.getDescription());
				}
			}
		});
	}
		return resultData;

}

/**Insert the records from database table
 *
 * @param lstOFF_SUBA
 *
 * @throws SQLException
 */
@Transactional
public Integer offSubaCommit(OffenderSubAccountsCommitBean commitBean) {
		int liReturn = 0;
		return liReturn;
}

/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * @throws SQLException
 */
public List<SystemProfiles> sysPflExecuteQuery(SystemProfiles searchRecord) {
		return otusubacRepository.sysPflExecuteQuery(searchRecord);

}

/**Insert the records from database table
 *
 * @param lstSYS_PFL
 *
 * @throws SQLException
 */
@Transactional
public Integer sysPflCommit(SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		return liReturn;
}



}