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
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.VHeaderBlock2;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OcdalertControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OidadmisControllerMockTestCase extends AbstractMockTestCase {

	private static Logger logger = LogManager.getLogger(OidadmisControllerMockTestCase.class);

	@Test
	public void cgfkOffEmDspDescriptionAgyLocIdRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidadmis/cgfkOffEmDspDescriptionAgyLocIdRecordGroup")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[6].description").value("Edmonton Remand Centre"));
		} catch (Exception e) {
			logger.error("cgfkOffEmDspDescription6RecordGroup", e);
		}
	}

	@Test
	public void cgfkOffEmDspDescriptionCaseloadIdRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidadmis/cgfkOffEmDspDescriptionCaseloadIdRecordGroup?const0=ITAG")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[2].description").value("Syscon Probation Office"));
		} catch (Exception e) {
			logger.error("cgfkOffEmDspDescription5RecordGroup", e);
		}
	}

	@Test
	public void cgfkOffEmDspDescriptionMrRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidadmis/cgfkOffEmDspDescriptionMrRecordGroup")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[1].description").value("Recaptured"));
		} catch (Exception e) {
			logger.error("cgfkOffEmDspDescription4RecordGroup", e);
		}
	}

	@Test
	public void cgfkOffEmDspDescriptionRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidadmis/cgfkOffEmDspDescriptionRecordGroup?systemMode=MODE")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[2].description").value("Revenue, Customs & Excise"));
		} catch (Exception e) {
			logger.error("cgfkOffEmDspDescriptionRecordGroup", e);
		}
	}

	@Test
	public void cgfkOffEmDspDescriptionRcRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidadmis/cgfkOffEmDspDescriptionRcRecordGroup?systemMode=MODE")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[3].description").value("Escort Contractors"));
		} catch (Exception e) {
			logger.error("cgfkOffEmDspDescriptionRcRecordGroup", e);
		}
	}

	@Test
	public void cgfkBedAhDspDescriptionRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidadmis/cgfkBedAhDspDescriptionRecordGroup?const0=MODE")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description").value("CCC-AD"));
		} catch (Exception e) {
			logger.error("cgfkBedAhDspDescriptionRecordGroup", e);
		}
	}

	@Test
	public void offbkgExecuteQueryTestMethod() {
		final VHeaderBlock2 searchBean = new VHeaderBlock2();
		searchBean.setOffenderId(new BigDecimal(1019081));
		final ObjectMapper mapper = new ObjectMapper();
		try {
			final String jsonInString = mapper.writeValueAsString(searchBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidadmis/offbkgExecuteQuery").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].lastName", is("ROBSON")));
		} catch (Exception e) {
			logger.error("offbkgExecuteQuery", e);
		}
	}

	@Test
	public void offbkgsExecuteQueryTestMethod() {
		final OffenderBookings searchBean = new OffenderBookings();
		searchBean.setOffenderBookId(Long.valueOf(18773));
		final ObjectMapper mapper = new ObjectMapper();
		try {
			final String jsonInString = mapper.writeValueAsString(searchBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidadmis/offbkgsExecuteQuery").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].bookingNo", is("2013-18829")));
		} catch (Exception e) {
			logger.error("offbkgsExecuteQuery", e);
		}
	}
	
	@Test
	public void offbkgCommitInsertTestMethod() {
		try {
			final OffenderBookingsCommitBean offenderBookingsCommitBean = new OffenderBookingsCommitBean();
			final OffenderBookings offenderBookings = new OffenderBookings();
			offenderBookings.setOffenderBookId(Long.valueOf(18773));
			offenderBookings.setBookingBeginDate(new Date(0, 0, 0, 0, 0, 0));
			offenderBookings.setBookingEndDate(new Date(0, 0, 0, 0, 0, 0));
			offenderBookings.setBookingNo("2013-18829");
			offenderBookings.setOffenderId(BigDecimal.valueOf(1019061));
			offenderBookings.setAgyLocId("ITAG");
			offenderBookings.setLivingUnitId(BigDecimal.valueOf(5368));
			offenderBookings.setDisclosureFlag("");
			offenderBookings.setInOutStatus("OUT");
			offenderBookings.setActiveFlag("");
			offenderBookings.setBookingStatus("");
			offenderBookings.setYouthAdultCode("N");
			offenderBookings.setFingerPrintedStaffId(null);
			offenderBookings.setSearchStaffId(null);
			offenderBookings.setAgencyImlId(BigDecimal.valueOf(5368));
//			offenderBookings.sets
//			offenderBookings.set
//			offenderBookings.set
//			offenderBookings.set
//			offenderBookings.set
//			offenderBookings.set
//			offenderBookings.set
//			offenderBookings.set
			final List<OffenderBookings> updateList = new ArrayList<OffenderBookings>();
			updateList.add(offenderBookings);
			
			offenderBookingsCommitBean.setInsertList(updateList);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offenderBookingsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidadmis/offBkgsCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(0)));
		} catch (Exception e) {
			logger.error("offemCommit", e);
		}
	}

	@Test
	public void offemExecuteQueryTestMethod() {
		final OffenderExternalMovements searchBean = new OffenderExternalMovements();
		searchBean.setOffenderBookId(Long.valueOf(18773));
		final ObjectMapper mapper = new ObjectMapper();
		try {
			final String jsonInString = mapper.writeValueAsString(searchBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidadmis/offemExecuteQuery").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].movementType", is("TRN")));
		} catch (Exception e) {
			logger.error("offemExecuteQuery", e);
		}
	}

	@Test
	public void offemCommitInsertTestMethod() {
		try {
			final OffenderExternalMovementsCommitBean offenderExternalMovementsCommitBean = new OffenderExternalMovementsCommitBean();
			final OffenderExternalMovements offenderExternalMovements = new OffenderExternalMovements();
			offenderExternalMovements.setOffenderBookId(Long.valueOf(18773));
			offenderExternalMovements.setMovementSeq(Long.valueOf(94));
			offenderExternalMovements.setMovementDate(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setMovementTime(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setInternalScheduleType("APP");
			offenderExternalMovements.setInternalScheduleReasonCode("IA");
			offenderExternalMovements.setMovementType("ADM");
			offenderExternalMovements.setMovementReasonCode("NEW");
			offenderExternalMovements.setDirectionCode("IN");
			offenderExternalMovements.setArrestAgencyLocId("");
			offenderExternalMovements.setToProvStatCode("");
			offenderExternalMovements.setEscortCode("");
			offenderExternalMovements.setFromAgyLocId("CCC");
			offenderExternalMovements.setToAgyLocId("CCC");
			offenderExternalMovements.setActiveFlag("N");
			offenderExternalMovements.setEscortText("");
			offenderExternalMovements.setCommentText("");
			offenderExternalMovements.setReportingDate(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setToCity("");
			offenderExternalMovements.setFromCity("");
			offenderExternalMovements.setReportingTime(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setEventId(BigDecimal.valueOf(313132));
			offenderExternalMovements.setParentEventId(BigDecimal.valueOf(12456));
			offenderExternalMovements.setToCountryCode("");
			offenderExternalMovements.setOjLocationCode("");
			offenderExternalMovements.setApplicationDate(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setApplicationTime(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setToAddressId(BigDecimal.valueOf(13320));
			offenderExternalMovements.setFromAddressId(BigDecimal.valueOf(13320));
			offenderExternalMovements.setSealFlag("");
			offenderExternalMovements.setCreateDatetime(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setCreateUserId("OMS_OWNER");
			offenderExternalMovements.setModifyDatetime(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setModifyUserId("ADMINQA");
			final List<OffenderExternalMovements> insertList = new ArrayList<OffenderExternalMovements>();
			insertList.add(offenderExternalMovements);

			offenderExternalMovementsCommitBean.setInsertList(insertList);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offenderExternalMovementsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidadmis/offemCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(0)));
		} catch (Exception e) {
			logger.error("offemCommit", e);
		}
	}

	@Test
	public void offemCommitUpdateTestMethod() {
		try {
			final OffenderExternalMovementsCommitBean offenderExternalMovementsCommitBean = new OffenderExternalMovementsCommitBean();
			final OffenderExternalMovements offenderExternalMovements = new OffenderExternalMovements();
			offenderExternalMovements.setOffenderBookId(Long.valueOf(18773));
			offenderExternalMovements.setMovementSeq(Long.valueOf(94));
			offenderExternalMovements.setMovementDate(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setMovementTime(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setInternalScheduleType("APP");
			offenderExternalMovements.setInternalScheduleReasonCode("IA");
			offenderExternalMovements.setMovementType("ADM");
			offenderExternalMovements.setMovementReasonCode("NEW");
			offenderExternalMovements.setDirectionCode("IN");
			offenderExternalMovements.setArrestAgencyLocId("");
			offenderExternalMovements.setToProvStatCode("");
			offenderExternalMovements.setEscortCode("");
			offenderExternalMovements.setFromAgyLocId("CCC");
			offenderExternalMovements.setToAgyLocId("CCC");
			offenderExternalMovements.setActiveFlag("N");
			offenderExternalMovements.setEscortText("F");
			offenderExternalMovements.setCommentText("");
			offenderExternalMovements.setReportingDate(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setToCity("");
			offenderExternalMovements.setFromCity("");
			offenderExternalMovements.setReportingTime(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setEventId(BigDecimal.valueOf(313132));
			offenderExternalMovements.setParentEventId(BigDecimal.valueOf(12456));
			offenderExternalMovements.setToCountryCode("");
			offenderExternalMovements.setOjLocationCode("");
			offenderExternalMovements.setApplicationDate(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setApplicationTime(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setToAddressId(BigDecimal.valueOf(13320));
			offenderExternalMovements.setFromAddressId(BigDecimal.valueOf(13320));
			offenderExternalMovements.setSealFlag("");
			offenderExternalMovements.setCreateDatetime(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setCreateUserId("OMS_OWNER");
			offenderExternalMovements.setModifyDatetime(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setModifyUserId("ADMINQA");
			final List<OffenderExternalMovements> updateList = new ArrayList<OffenderExternalMovements>();
			updateList.add(offenderExternalMovements);

			offenderExternalMovementsCommitBean.setUpdateList(updateList);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offenderExternalMovementsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidadmis/offemCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("offemCommit", e);
		}
	}

	@Test
	public void bedahCommitInsertTestMethod() {
		try {
			final BedAssignmentHistoriesCommitBean bedAssignmentHistoriesCommitBean = new BedAssignmentHistoriesCommitBean();
			final BedAssignmentHistories bedAssignmentHistories = new BedAssignmentHistories();
			bedAssignmentHistories.setOffenderBookId(18773);
			bedAssignmentHistories.setBedAssignSeq(5);
			bedAssignmentHistories.setLivingUnitId(2585);
			bedAssignmentHistories.setAssignmentDate(new Date(0, 0, 0, 0, 0, 0));
			bedAssignmentHistories.setAssignmentTime(new Date(0, 0, 0, 0, 0, 0));
			bedAssignmentHistories.setAssignmentReason("ADM");
			bedAssignmentHistories.setAssignmentEndDate(new Date(0, 0, 0, 0, 0, 0));
			bedAssignmentHistories.setAssignmentEndTime(new Date(0, 0, 0, 0, 0, 0));
			bedAssignmentHistories.setCreateDatetime(new Date(0, 0, 0, 0, 0, 0));
			bedAssignmentHistories.setCreateUserId("ADMINQA");
			bedAssignmentHistories.setModifyDatetime(new Date(0, 0, 0, 0, 0, 0));
			bedAssignmentHistories.setModifyUserId("ADMINQA");
			bedAssignmentHistories.setSealFlag("");
			final List<BedAssignmentHistories> insertList = new ArrayList<BedAssignmentHistories>();
			insertList.add(bedAssignmentHistories);

			bedAssignmentHistoriesCommitBean.setInsertList(insertList);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(bedAssignmentHistoriesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidadmis/bedahCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("bedahCommit", e);
		}
	}

	@Test
	public void cgfkchkOffBkgsOffBkgStafcTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidadmis/cgfkchkOffBkgsOffBkgStafc?assignedStaffId=3004")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].agyLocId", is("SDTC")));
		} catch (Exception e) {
			logger.error("cgfkchkOffBkgsOffBkgStafc", e);
		}
	}

	@Test
	public void cgfkchkOffBkgsOffBkgRefTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidadmis/cgfkchkOffBkgsOffBkgRef?bookingStatus=3004")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].agyLocId", is("SDTC")));
		} catch (Exception e) {
			logger.error("cgfkchkOffBkgsOffBkgStafc", e);
		}
	}

}
