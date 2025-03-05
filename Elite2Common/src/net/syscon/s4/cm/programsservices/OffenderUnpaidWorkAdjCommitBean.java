package net.syscon.s4.cm.programsservices;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class OffenderUnpaidWorkAdjCommitBean extends BaseModel{
		
			private static final long serialVersionUID = 1L;
			
			private List<OffenderUnpaidWorkAdj> insertList;
			private List<OffenderUnpaidWorkAdj> deleteList;
			private List<OffenderUnpaidWorkAdj> updateList;
			
			
			public List<OffenderUnpaidWorkAdj> getInsertList() {
				return insertList;
			}
			public void setInsertList(List<OffenderUnpaidWorkAdj> insertList) {
				this.insertList = insertList;
			}
			public List<OffenderUnpaidWorkAdj> getDeleteList() {
				return deleteList;
			}
			public void setDeleteList(List<OffenderUnpaidWorkAdj> deleteList) {
				this.deleteList = deleteList;
			}
			public List<OffenderUnpaidWorkAdj> getUpdateList() {
				return updateList;
			}
			public void setUpdateList(List<OffenderUnpaidWorkAdj> updateList) {
				this.updateList = updateList;
			}
			
			

}
