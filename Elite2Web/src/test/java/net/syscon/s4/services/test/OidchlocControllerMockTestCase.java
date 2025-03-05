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
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.common.beans.BedAssignmentHistoriesCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OidchlocControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OidchlocControllerMockTestCase extends AbstractMockTestCase {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidchlocControllerMockTestCase.class);

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void bedAssignmentHistoriesExecuteQueryTestMethod() {
		try {
			final BedAssignmentHistories bedAssignHist = new BedAssignmentHistories();
			bedAssignHist.setOffenderBookId(18733);
			bedAssignHist.setBedAssignSeq(6);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(bedAssignHist);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidchloc/bedAhExecuteQuery", bedAssignHist)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].livingUnitId", is(5368)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void getrgAssignmentReasonRecordGroup() {
		try {

			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidchloc/rgAssignmentReasonRecordGroup").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("ADM")));
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
	public void bedAssignmentHistoriesCommitTestMethod() {
		try {
			final BedAssignmentHistories bedAssignHist = new BedAssignmentHistories();
			bedAssignHist.setOffenderBookId(18800);
			bedAssignHist.setLivingUnitId(5368);
			bedAssignHist.setAssignmentDate(new Date());
			bedAssignHist.setAssignmentTime(new Date());
			bedAssignHist.setAssignmentReason("ADM");
			bedAssignHist.setAssignmentEndDate(new Date());
			bedAssignHist.setAssignmentEndTime(new Date());
			bedAssignHist.setCreateDatetime(new Date());
			bedAssignHist.setCreateUserId("OMS");
			bedAssignHist.setModifyDatetime(new Date());
			bedAssignHist.setModifyUserId("OMS");
			bedAssignHist.setSealFlag(null);
			final List<BedAssignmentHistories> list = new ArrayList<BedAssignmentHistories>();
			list.add(bedAssignHist);
			final BedAssignmentHistoriesCommitBean insertList = new BedAssignmentHistoriesCommitBean();
			insertList.setInsertList(list);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(insertList);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidchloc/bedAhCommit", insertList).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
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
	public void bedAssignmentHistoriesRecordGroupTestMethod() {
		try {
			final BedAssignmentHistories bedAssignHist = new BedAssignmentHistories();
			bedAssignHist.setOffenderBookId(18733);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(bedAssignHist);
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidchloc/offBkgOnCheckDeleteMaster", bedAssignHist)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(1)));
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
			final SystemProfiles bean = new SystemProfiles();
			bean.setProfileType("CLIENT");
			bean.setProfileCode("SORT_PATH");
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(bean);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidchloc/sysPflExecuteQuery", bean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Sort Path")));
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
	public void cgfkchkBedAhBedAhVLiv() {
		try {
			final LivingUnits bean = new LivingUnits();
			bean.setLivingUnitId(BigDecimal.valueOf(5368));
			bean.setAgyLocId("SDTC");
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(bean);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidchloc/cgfkchkBedAhBedAhVLiv", bean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Sort Path")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}