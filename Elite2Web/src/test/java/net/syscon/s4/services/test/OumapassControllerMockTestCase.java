package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.StaffAccessibleCaseloads;
import net.syscon.s4.im.beans.StaffMembersCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OumapassControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OumapassControllerMockTestCase extends AbstractMockTestCase {
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void staffMembersExecuteQueryTestMethod() {
		try {
			StaffMembers staffMembers = new StaffMembers();
			staffMembers.setStaffId(3049);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(staffMembers);
			ResultActions resultActions = this.mockMvc
					.perform(
							post("/api/oumapass/staffExecuteQuery", staffMembers).contentType(MediaType.APPLICATION_JSON)
									.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].assignedCaseloadId", is("CTAG")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void staffMembersCommitTestMethod() {
		try {
			StaffMembers staffMembers = new StaffMembers();
			staffMembers.setAssignedCaseloadId("ITAG");
			staffMembers.setWorkingStockLocId("Store");
			staffMembers.setWorkingCaseloadId("ITAG");
			staffMembers.setUserId("Sri");
			staffMembers.setBadgeId("CC");
			staffMembers.setLastName("kanth");
			staffMembers.setFirstName("Sri");
			staffMembers.setMiddleName("b");
			staffMembers.setAbbreviation("goi");
			staffMembers.setPosition("centre");
			staffMembers.setBirthdate(new Date());
			staffMembers.setTerminationDate(new Date());
			staffMembers.setUpdateAllowedFlag("Y");
			staffMembers.setDefaultPrinterId(1);
			staffMembers.setSuspendedFlag("N");
			staffMembers.setSupervisorStaffId(3049);
			staffMembers.setCommReceiptPrinterId("LPT1");
			staffMembers.setPersonnelType("person");
			staffMembers.setAsOfDate(new Date());
			staffMembers.setEmergencyContact("hs");
			staffMembers.setRole("role");
			staffMembers.setSexCode("M");
			staffMembers.setStatus("Active");
			staffMembers.setSuspensionDate(new Date());
			staffMembers.setSuspensionReason("misBeh");
			staffMembers.setForcePasswordChangeFlag("A");
			staffMembers.setLastPasswordChangeDate(new Date());
			staffMembers.setLicenseCode("S5");
			staffMembers.setLicenseExpiryDate(new Date());
			staffMembers.setCreateDateTime(new Date());
			staffMembers.setCreateUserId("AA");
			staffMembers.setModifyDateTime(new Date());
			staffMembers.setModifyUserId("RR");
			staffMembers.setTitle("RA");
			staffMembers.setNameSequence("KK");
			staffMembers.setQueueClusterId(2);
			staffMembers.setFirstLogonFlag("c");
			staffMembers.setSuffix("K");
			staffMembers.setSealFlag("Y");
			staffMembers.setStaffId(7709);
			List<StaffMembers> list = new ArrayList<StaffMembers>();
			list.add(staffMembers);
			StaffMembersCommitBean staffMembersCommitBean = new StaffMembersCommitBean();
			staffMembersCommitBean.setUpdateList(list);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(staffMembersCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oumapass/staffCommit", staffMembersCommitBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void staffAccessibleCaseloadsExecuteQueryTestMethod() {
		try {
			StaffAccessibleCaseloads staffAccessibleCaseloads = new StaffAccessibleCaseloads();
			staffAccessibleCaseloads.setStaffId(3129);
			staffAccessibleCaseloads.setCaseloadId("ARKIN");
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(staffAccessibleCaseloads);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oumapass/staffAcExecuteQuery", staffAccessibleCaseloads)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].updateAllowedFlag", is("N")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}