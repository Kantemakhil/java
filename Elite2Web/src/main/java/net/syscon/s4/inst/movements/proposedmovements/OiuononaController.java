package net.syscon.s4.inst.movements.proposedmovements;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNaDetailsCommitBean;
import net.syscon.s4.im.beans.StgRelationships;
import java.util.List;

@EliteController
public class OiuononaController {

	@Autowired
	private OiuononaService oiuononaService;

	private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(OiuononaController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuonona/offNonAssoExecuteQuery", method = RequestMethod.POST)
	public List<OffenderNaDetails> offNonAssoExecuteQuery(@RequestBody OffenderNaDetails searchBean) {
		List<OffenderNaDetails> searchResult = new ArrayList<>();
		try {
			searchResult = oiuononaService.offNonAssoExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderNaDetails bean = new OffenderNaDetails();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param updateBean
	 */

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuonona/offNonAssoCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offNonAssoCommit(@RequestBody OffenderNaDetailsCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = oiuononaService.offNonAssoCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuonona/stgRelationshipsExecuteQuery", method = RequestMethod.POST)
	public List<StgRelationships> stgRelationshipsExecuteQuery(@RequestBody StgRelationships searchBean) {
		List<StgRelationships> searchResult = new ArrayList<>();

		try {
			searchResult = oiuononaService.stgRelationshipsExecuteQuery(searchBean);
		} catch (Exception e) {
			StgRelationships bean = new StgRelationships();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}
