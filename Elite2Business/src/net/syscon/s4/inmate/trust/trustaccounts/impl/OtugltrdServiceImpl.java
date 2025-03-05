package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.trust.trustaccounts.OtugltrdRepository;
import net.syscon.s4.inmate.trust.trustaccounts.OtugltrdService;

/**
 * Class OtugltrdServiceImpl
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
@Service
public class OtugltrdServiceImpl extends BaseBusiness implements OtugltrdService {

	@Autowired
	private OtugltrdRepository otugltrdRepo;

	/**
	 * Creates new OtugltrdServiceImpl class Object
	 */
	public OtugltrdServiceImpl() {
		// OtugltrdServiceImpl
	}

	/**
	 * Fetch the records from database table
	 * Method glTxnExecuteQuery
	 * @param searchRecord
	 * return List<GlTransactions>
	 */
	public List<GlTransactions> glTxnExecuteQuery(final GlTransactions searchRecord) {
		final ArrayList<String> txnTypeList = new ArrayList<>();
		txnTypeList.add("OT");
		txnTypeList.add("AJ");
		txnTypeList.add("HS");
		txnTypeList.add("FT");
		if (txnTypeList.contains(searchRecord.getTxnType()) && searchRecord.getTxnEntrySeq() % 2 == 0) {
			searchRecord.setTxnEntrySeq(searchRecord.getTxnEntrySeq() - 1);
		} else if ("HS".equals(searchRecord.getTxnType()) || "REV".equals(searchRecord.getTxnType())) {
			searchRecord.setTxnEntrySeq(null);

		} else if ("DED".equals(searchRecord.getTxnType())) {
			final Integer valcnCount = otugltrdRepo.getCalcn(searchRecord);
			if (valcnCount > 0) {
				final Integer vglCount = otugltrdRepo.getCgl(searchRecord);
				if (vglCount == 0) {
					searchRecord.setTxnEntrySeq(searchRecord.getTxnEntrySeq() + 1);
				}
			}

		}
		return otugltrdRepo.glTxnExecuteQuery(searchRecord);

	}

}