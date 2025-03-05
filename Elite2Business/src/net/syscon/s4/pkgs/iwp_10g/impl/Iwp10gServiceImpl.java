package net.syscon.s4.pkgs.iwp_10g.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.pkgs.iwp_10g.Iwp10gRepository;
import net.syscon.s4.pkgs.iwp_10g.Iwp10gService;
import net.syscon.s4.sa.admin.beans.IwpTemplateRoles;

/*
 * Below comments are copied from package IWP_10G
 * -- ======================================================================================
   v_version CONSTANT VARCHAR2(60) := ' 2.40   13-FEB-2012';
   -- ======================================================================================

   -- Purpose: Briefly explain the functionality of the package body
   -- 
   -- MODIFICATION HISTORY
   -- Person      Date       Version  Comments 
   -- ---------   ------     -------  ----------------------- ----------------------
   -- Ruxandra  13-FEB-2012   2.40    removed agency_incident_id from the system parameters list as in product the incident id is not a system parameter
   -- Eric      06-Mar-2011   2.39    Modified iwp_context_param_rec added context_flag
   --                                 Modified set_block_data added context_flag
   --                                 Modified get_block_data added context_flag
   --                                 Added:    PROCEDURE delete_block_data
   -- Nasir     01-DEC-2010   2.38    Modified is_template_accessible to fit Tag security.
   -- Nasir     22-Oct-2010   2.37    Add template_id in set_block_data and get_block_data.
   -- Nasir     10-Aug-2010   2.36    Make modification for IWP cursor selection logic
   -- Niko      27-Oct-2009   2.35    Added a new system params - AGENCY_INCIDENT_ID
   --                                 Modified v_system_params_tab to (7)
   -- Sarah     03-Sep-2009   2.34    D#1180: Modified set_parameters_values to support date type
   --                                 D#1181: Modified get_parameters to only disply parameters once
   --                                 when there are multiple parameters exist with same name for each template
   -- Niko      28-Aug-2009   2.33    Modified v_system_params_tab to (6) for stg_id
   -- Peter     30-JUN-2009   2.32    made functions makeupsql and collectdata  public
   -- Niko      02-Jun-2009   2.31    Added a new system variable - STG_ID
   -- Sarah     12-Feb-2009   2.30    Tr#461: Return value in collectdata exception to not be failed
   --                                 in get_data and so document generated correctly even the bookmark fails
   -- Sarah     10-Dec-2008   2.29    Tr#7679: Modified is_template_accessible to filter
   --                                 LOV with p_template_id as well.
   -- Eric      23-Jul-2008   2.28    Modified is_template_accessible and is_screen_accessible
   --                                 to fit Tag security datamodel
   -- Surya     12-Jul-2007    2.27    User Admin Security - Modified is_screen_accessible and
   --                                 is_template_accessible.
   -- Surya       11-Jul-2007  2.26   User Admin Security - Replaced staff_member_roles.
   -- Surya       08-Jul-2007  2.25   User Admin Security - User Id fix: Modified
   --                                 is_screen_accessible and is_template_accessible.
   --                                 Removed the version history from Spec as it is not required.
   -- GJC         14 Oct 2006  2.24   SHOW_VERSION changed from procedure to function
   -- Patrick     01-AUG-2006  2.23   Defect 3417.  Added more system global variables in get_system_params procedure
   -- Erin      17-May-2006    2.22   #1920 - Modified functions 'is_template_accessible' and 'is_screen_accessible'  to check for object_type = 'IWP'
   -- Krishna     10-Mar-2006  2.21   Removed redundant smts. from parsesql as per Venu
   -- Patrick     02-MAR-2006  2.20   Added function check_mod_templ_active to check whether the module has
   --                                 access right to the template
   -- Krishna     14-Feb-2006  2.19   Removed the Function check_security and optained the new role security
   --                                 through tag_security.check_priveledge_status. (Johnson's change)
   -- Patrick     31-JAN-2006  2.16   Fixed compilation problem
   -- Patrick     27-JAN-2006  2.15   collectdata raise no exception so document generated correctly even the  bookmark fails
   -- Patrick     19-JAN-2006  2.14   Added procedure upd_templ_codes to enable the form to update role_code
   -- Patrick     19-JAN-2006  2.13   Changed get_parameters to have param_type. To let the form to validate the data type
   -- Patrick     17-JAN-2006  2.12   Fixed bug 232.  Added a parameter in the procedure iwp_10g.get_templ_roles to  filter the role_code query
   --                                 Removed getdocsize fuction.
   -- Patrick     17-JAN-2006  2.11   Removed the replacement of '?' with '1' from parsesql procedure
   -- Patrick     03-JAN-2006  2.10   Fixed version number
   -- Krishna     23-Dec-2005  2.7    Removed code in Parsesql function checking sql smt.
   --                                 Removed get_temsize function after discussing with Venu
   --                                 Removed few parameters from get_system_params function
   -- Krishna     25-Nov-2005  2.5    Added dbms_sql.parse smts. in parsesql function
   -- Patrick     25-Nov-2005 1.0     Created package
   --
   -- Enter procedure, function bodies as shown below
*/

@Service
public class Iwp10gServiceImpl implements Iwp10gService {
	@Autowired
	private Iwp10gRepository iwp10gRepository;

	// This PROCEDURE is used to get_templ_roles
	@Override
	public List<IwpTemplateRoles> getTemplRoles(final IwpTemplates templRolesCur) {
		final List<IwpTemplateRoles> retList;
		if (templRolesCur.getRoleCode() != null) {
			// This PROCEDURE is used to get_templ_roles details
			retList = iwp10gRepository.getTempRolesDetails(templRolesCur.getTemplateId().longValue(), null);
		} else {
			retList = iwp10gRepository.getTempRolesDetailsSecond(templRolesCur.getTemplateId().longValue());
		}
		return retList;
	}
}
