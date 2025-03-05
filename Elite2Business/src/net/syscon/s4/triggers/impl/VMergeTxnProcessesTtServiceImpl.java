package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.sa.admin.beans.VMergeTransactionProcesses;
import net.syscon.s4.triggers.VMergeTxnProcessesTtRepository;
import net.syscon.s4.triggers.VMergeTxnProcessesTtService;

@Service
public class VMergeTxnProcessesTtServiceImpl implements VMergeTxnProcessesTtService {
	private final Logger logger = LogManager.getLogger(VMergeTxnProcessesTtServiceImpl.class.getName());
	@Autowired
	VMergeTxnProcessesTtRepository vMergeTxnProcessesTtRepository;

	@Override
	public Integer vMergeTxnProcessesTtTgr(final VMergeTransactionProcesses old, final VMergeTransactionProcesses newObj) {
		final VMergeTransactionProcesses targerObj = new VMergeTransactionProcesses();
		Integer result = 0;
		try {
			if ((Optional.ofNullable(newObj).isPresent() && "Y".equals(newObj.getTransferFlag()))
					&& (Optional.ofNullable(old).isPresent() && "N".equals(old.getTransferFlag()))) {
				result = vMergeTxnProcessesTtRepository.insert(newObj);
			}
			if ((Optional.ofNullable(newObj).isPresent() && "Y".equals(newObj.getTransferFlag()))
					&& (Optional.ofNullable(old).isPresent() && "Y".equals(old.getTransferFlag()))) {
				BeanUtils.copyProperties(newObj, targerObj);
				targerObj.setProcessId(old.getProcessId());
				targerObj.setMergeTransactionId(old.getMergeTransactionId());
				result = vMergeTxnProcessesTtRepository.update(targerObj);
			}
			if ((Optional.ofNullable(newObj).isPresent() && "N".equals(newObj.getTransferFlag()))
					&& (Optional.ofNullable(old).isPresent() && "Y".equals(old.getTransferFlag()))) {
				result = vMergeTxnProcessesTtRepository.delete(old);
			}
		} catch (final Exception e) {
			result = 0;
			logger.error("vMergeTxnProcessesTtTgr", e);
		}
		return result;
	}

}
