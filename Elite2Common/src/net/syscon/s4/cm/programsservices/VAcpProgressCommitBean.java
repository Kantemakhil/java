package net.syscon.s4.cm.programsservices;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class VAcpProgressCommitBean extends BaseModel{
		
			private static final long serialVersionUID = 1L;
			
			private List<VAcpProgress> insertList;
			private List<VAcpProgress> deleteList;
			private List<VAcpProgress> updateList;
			
			
			public List<VAcpProgress> getInsertList() {
				return insertList;
			}
			public void setInsertList(List<VAcpProgress> insertList) {
				this.insertList = insertList;
			}
			public List<VAcpProgress> getDeleteList() {
				return deleteList;
			}
			public void setDeleteList(List<VAcpProgress> deleteList) {
				this.deleteList = deleteList;
			}
			public List<VAcpProgress> getUpdateList() {
				return updateList;
			}
			public void setUpdateList(List<VAcpProgress> updateList) {
				this.updateList = updateList;
			}
			
			

}
