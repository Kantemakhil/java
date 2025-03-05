package net.syscon.s4.inst.property;

import java.util.List;

import net.syscon.s4.sa.admin.beans.PropertyBundles;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.sa.admin.beans.PropertyBundleItems;

public interface OumbundlRepository {

	List<PropertyBundles> getPropertyGroups();

	List<PropertyBundleItems> getPropertyItems(String groupId);

	Integer insertPropertyBundles(List<PropertyBundles> insertList);

	Integer updatePropertyBundles(List<PropertyBundles> updateList);

	Integer deletePropertyBundles(List<PropertyBundles> deleteList);

	Integer insertPropertyItems(List<PropertyBundleItems> list);

	Integer updatePropertyItems(List<PropertyBundleItems> updateList);

	Integer deletePropertyItems(List<PropertyBundleItems> deleteList);

	List<Caseloads> getCaseloads(StaffMembers searchBean);

}
