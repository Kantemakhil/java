package net.syscon.s4.cm.programsservices;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class VOffenderSentCondActsCommitBean  extends BaseModel{
	
		private static final long serialVersionUID = 1L;
		
		private List<VOffenderSentCondActs> insertList;
		private List<VOffenderSentCondActs> deleteList;
		private List<VOffenderSentCondActs> updateList;
		
		public List<VOffenderSentCondActs> getInsertList() {
			return insertList;
		}
		public void setInsertList(List<VOffenderSentCondActs> insertList) {
			this.insertList = insertList;
		}
		public List<VOffenderSentCondActs> getDeleteList() {
			return deleteList;
		}
		public void setDeleteList(List<VOffenderSentCondActs> deleteList) {
			this.deleteList = deleteList;
		}
		public List<VOffenderSentCondActs> getUpdateList() {
			return updateList;
		}
		public void setUpdateList(List<VOffenderSentCondActs> updateList) {
			this.updateList = updateList;
		}
		
		

}
