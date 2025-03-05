package net.syscon.s4.cf.offendertransactions;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderDeductions;

/**
 * Interface OcudpdisRepository
 */
public interface OcudpdisRepository {

	List<OffenderDeductions> offTransTypeExecuteQuery(OffenderDeductions paramBean);

	BigDecimal offAccBalExecuteQuery(OffenderDeductions paramBean);

	Integer preInsert();

	Long getOffenderBookID(OffenderDeductions paramBean);

	Integer tbdDetailsComitt(OffenderTransactions paramBean);

	OffenderTransactions getSubAccTypeDetails(OffenderDeductions paramBean);

}
