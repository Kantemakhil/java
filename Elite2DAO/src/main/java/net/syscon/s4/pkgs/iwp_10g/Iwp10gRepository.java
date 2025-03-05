package net.syscon.s4.pkgs.iwp_10g;

import java.util.List;
import net.syscon.s4.sa.admin.beans.IwpTemplateRoles;

public interface Iwp10gRepository {

	List<IwpTemplateRoles> getTempRolesDetails(final Long tempid, final String tempRoleCode);

	List<IwpTemplateRoles> getTempRolesDetailsSecond(final Long tempid);

}
