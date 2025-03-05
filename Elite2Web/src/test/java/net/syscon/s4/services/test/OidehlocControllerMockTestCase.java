package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.inst.movementexternal.beans.VHeaderBlockCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * class OidehlocControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OidehlocControllerMockTestCase extends AbstractMockTestCase {

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void vHeaderBlockExecuteQueryTestMethod() {
		try {
			final VHeaderBlock vheaderBlock = new VHeaderBlock();
			vheaderBlock.setLastName("SMITH");
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(vheaderBlock);

			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidehloc/vOffBkgExecuteQuery").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].lastName", is("SMITH")));
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
	public void vHeaderBlockCommitTestMethod() {
		try {

			final VHeaderBlock vHeaderBlock = new VHeaderBlock();
			vHeaderBlock.setOffenderBookId(new BigDecimal(18773));
			vHeaderBlock.setOffenderId(new BigDecimal(1019061));
			vHeaderBlock.setOffenderIdDisplay("0001019061");
			vHeaderBlock.setLastName("SMITH");
			vHeaderBlock.setFirstName("DAVID");
			vHeaderBlock.setMiddleName("MARK");
			vHeaderBlock.setBirthDate(null);
			vHeaderBlock.setBookingNo("2013-18829");
			vHeaderBlock.setAgyLocId("CCC");
			vHeaderBlock.setActiveFlag("Y");
			vHeaderBlock.setBookingType("C");
			vHeaderBlock.setLivingUnitDescription("; : ");
			vHeaderBlock.setInOutStatus("OUT");
			vHeaderBlock.setStatusDisplay("Active");
			vHeaderBlock.setRootOffenderId(new BigDecimal(1019061));
			vHeaderBlock.setAssignedStaffId(new BigDecimal(3026));
			vHeaderBlock.setAgyLocType("INST");
			vHeaderBlock.setCreateAgyLocId("CCC");
			vHeaderBlock.setBookingType("INST");
			vHeaderBlock.setStatusReason("NEW");
			vHeaderBlock.setAge(new BigDecimal(32));
			vHeaderBlock.setGender("Male");
			vHeaderBlock.setMovementReason("DISC");

			final List<VHeaderBlock> listHeBlock = new ArrayList<VHeaderBlock>();
			listHeBlock.add(vHeaderBlock);

			final VHeaderBlockCommitBean headerCommitBean = new VHeaderBlockCommitBean();
			headerCommitBean.setInsertList(listHeBlock);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(headerCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidehloc/vOffBkgCommit", headerCommitBean)
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
	public void vHeaderBlockRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidehloc/rgAssignmentReasonRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Administrative")));
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
	public void systemProfilesExecuteQueryTestMethod() {
		try {
			final SystemProfiles systemProfiles = new SystemProfiles();
			systemProfiles.setProfileType("FORMAT");
			this.mockMvc
					.perform(post("/api/oidehloc/sysPflExecuteQuery", systemProfiles)
							.contentType(MediaType.APPLICATION_JSON)
							.content(new ObjectMapper().writeValueAsString(systemProfiles))
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print()).andExpect(jsonPath("$[0].description").value("Birth Certificate Number"));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}