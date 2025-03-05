package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class BeneficiaryTransactionsCommitBean
 *
 */
@XmlRootElement
public class BeneficiaryTransactionsCommitBean extends BaseModel implements Serializable {
		private static final long serialVersionUID = 1L;
		private List<BeneficiaryTransactions> insertList;
		private List<BeneficiaryTransactions> deleteList;
		private List<BeneficiaryTransactions> updateList;

		public void setInsertList(final List<BeneficiaryTransactions> insertList) {
			this.insertList = insertList;
		}

		public void setUpdateList(final List<BeneficiaryTransactions> updateList) {
			this.updateList = updateList;
		}

		public void setDeleteList(final List<BeneficiaryTransactions> deleteList) {
			this.deleteList = deleteList;
		}

		public List<BeneficiaryTransactions> getInsertList() {
			return insertList;
		}

		public List<BeneficiaryTransactions> getUpdateList() {
			return updateList;
		}

		public List<BeneficiaryTransactions> getDeleteList() {
			return deleteList;
		}

}
