package net.syscon.s4.sa.recordmaintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;

@EliteController
public class ProsdeacController {
	@Autowired
	private ProsdeacService prosdeacService;
	
	private static Logger logger = LogManager.getLogger(OumbadmiController.class.getName());
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/prosdeac/processDeacExecuteQuery", method = RequestMethod.GET)
	public List<BpmnProcess> processDeacExecuteQuery() {
		List<BpmnProcess> searchResult = new ArrayList<>();
		try {
			searchResult = prosdeacService.processDeacExecuteQuery();
		} catch (Exception e) {
			BpmnProcess bean = new BpmnProcess();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/prosdeac/suspendProcess")
	public @ResponseBody Integer suspendProcess(@RequestBody BpmnProcess bpmnProcess) {
		int liReturn = 0;
		try {
			liReturn = prosdeacService.suspendProcess(bpmnProcess);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/prosdeac/activateProcess")
	public @ResponseBody Integer activatedProcess(@RequestBody BpmnProcess bpmnProcess) {
		int liReturn = 0;
		try {
			liReturn = prosdeacService.activatedProcess(bpmnProcess);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/prosdeac/deleteProcess")
	public @ResponseBody Integer deleteProcess(@RequestBody BpmnProcess bpmnProcess) {
		int liReturn = 0;
		try {
			liReturn = prosdeacService.deleteProcess(bpmnProcess);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
}
