COMMENT ON TABLE IWP_BOOKMARKS IS 'The bookmark';
COMMENT ON COLUMN IWP_BOOKMARKS.BOOKMARK_NAME IS 'The name of the bookmark';
COMMENT ON COLUMN IWP_BOOKMARKS.ACTIVE_FLAG IS '? if the bookmark actively used';
COMMENT ON COLUMN IWP_BOOKMARKS.BOOKMARK_TYPE IS 'Bookmark type';
COMMENT ON COLUMN IWP_BOOKMARKS.DATE_CREATED IS 'User field : The record creation date';
COMMENT ON COLUMN IWP_BOOKMARKS.SQL_TEXT IS 'The SQL text';
COMMENT ON COLUMN IWP_BOOKMARKS.SQL_VERIFIED_FLAG IS '? If the SQL verified';
COMMENT ON COLUMN IWP_BOOKMARKS.USER_CREATED IS 'User field : The user who creates the record';
COMMENT ON COLUMN IWP_BOOKMARKS.DESCRIPTION IS 'Description';
COMMENT ON COLUMN IWP_BOOKMARKS.EXPIRY_DATE IS 'The expiry Date';
COMMENT ON COLUMN IWP_BOOKMARKS.CREATE_DATETIME IS 'The timestamp when the record was created.';
COMMENT ON COLUMN IWP_BOOKMARKS.CREATE_USER_ID IS 'The user account that created the record.';
COMMENT ON COLUMN IWP_BOOKMARKS.MODIFY_DATETIME IS 'The timestamp when the record was last modified.';
COMMENT ON COLUMN IWP_BOOKMARKS.MODIFY_USER_ID IS 'The user account that last modified the record.';
COMMENT ON COLUMN IWP_BOOKMARKS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE IWP_BOOKMARK_PARAMETERS IS 'The parameter value of a bookmark';
COMMENT ON COLUMN IWP_BOOKMARK_PARAMETERS.PARAMETER_NAME IS 'The parameter name';
COMMENT ON COLUMN IWP_BOOKMARK_PARAMETERS.BOOKMARK_NAME IS 'The name of the bookmark';
COMMENT ON COLUMN IWP_BOOKMARK_PARAMETERS.ACTIVE_FLAG IS '?if the parameter active';
COMMENT ON COLUMN IWP_BOOKMARK_PARAMETERS.DATE_CREATED IS 'The date the bookmark parameter is created';
COMMENT ON COLUMN IWP_BOOKMARK_PARAMETERS.PARAMETER_DATA_TYPE IS 'The data type of the parameter';
COMMENT ON COLUMN IWP_BOOKMARK_PARAMETERS.PARAMETER_TYPE IS 'The type of Parameter';
COMMENT ON COLUMN IWP_BOOKMARK_PARAMETERS.USER_CREATED IS 'The user who create the bookmark';
COMMENT ON COLUMN IWP_BOOKMARK_PARAMETERS.DESCRIPTION IS 'The general description';
COMMENT ON COLUMN IWP_BOOKMARK_PARAMETERS.CREATE_DATETIME IS 'The timestamp when the record was created.';
COMMENT ON COLUMN IWP_BOOKMARK_PARAMETERS.CREATE_USER_ID IS 'The user account that created the record.';
COMMENT ON COLUMN IWP_BOOKMARK_PARAMETERS.MODIFY_DATETIME IS 'The timestamp when the record was last modified.';
COMMENT ON COLUMN IWP_BOOKMARK_PARAMETERS.MODIFY_USER_ID IS 'The user account that last modified the record.';
COMMENT ON COLUMN IWP_BOOKMARK_PARAMETERS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE IWP_DOCUMENTS IS 'The document';
COMMENT ON COLUMN IWP_DOCUMENTS.DOCUMENT_ID IS 'The ID of the documents';
COMMENT ON COLUMN IWP_DOCUMENTS.OFFENDER_BOOK_ID IS 'The Offender Book ID';
COMMENT ON COLUMN IWP_DOCUMENTS.ACTIVE_FLAG IS 'Active data indicator';
COMMENT ON COLUMN IWP_DOCUMENTS.DATE_CREATED IS 'The date when the document is created';
COMMENT ON COLUMN IWP_DOCUMENTS.DOCUMENT_NAME IS 'The name of the document';
COMMENT ON COLUMN IWP_DOCUMENTS.DOCUMENT_STATUS IS 'The document status';
COMMENT ON COLUMN IWP_DOCUMENTS.TEMPLATE_ID IS 'The ID of the template that the document is based on';
COMMENT ON COLUMN IWP_DOCUMENTS.USER_CREATED IS 'The user who created the document';
COMMENT ON COLUMN IWP_DOCUMENTS.COMMENT_TEXT IS 'The general comment text';
COMMENT ON COLUMN IWP_DOCUMENTS.DATE_MODIFIED IS 'The date when the document is modified';
COMMENT ON COLUMN IWP_DOCUMENTS.LOCATION IS 'The physical location of the template';
COMMENT ON COLUMN IWP_DOCUMENTS.CREATE_DATETIME IS 'The timestamp when the record was created.';
COMMENT ON COLUMN IWP_DOCUMENTS.CREATE_USER_ID IS 'The user account that created the record.';
COMMENT ON COLUMN IWP_DOCUMENTS.MODIFY_DATETIME IS 'The timestamp when the record was last modified.';
COMMENT ON COLUMN IWP_DOCUMENTS.MODIFY_USER_ID IS 'The user account that last modified the record.';
COMMENT ON COLUMN IWP_DOCUMENTS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE IWP_PARAMETER_MAPPINGS IS 'Define mapping between iwp parameter which belong to bookmark and field wich belong to module block. It is used during iwp document generation to find value for context parameters from sceen block.  ';
COMMENT ON COLUMN IWP_PARAMETER_MAPPINGS.TEMPLATE_MODULE_ID IS 'Identifier -PK for IWP_TEMPLATE_MODULES table';
COMMENT ON COLUMN IWP_PARAMETER_MAPPINGS.CREATE_DATETIME IS 'The timestamp when the record was created.';
COMMENT ON COLUMN IWP_PARAMETER_MAPPINGS.CREATE_USER_ID IS 'The user account that created the record.';
COMMENT ON COLUMN IWP_PARAMETER_MAPPINGS.MODIFY_DATETIME IS 'The timestamp when the record was last modified.';
COMMENT ON COLUMN IWP_PARAMETER_MAPPINGS.MODIFY_USER_ID IS 'The user account that last modified the record.';
COMMENT ON COLUMN IWP_PARAMETER_MAPPINGS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE IWP_PARAMETER_VALUES IS 'The parameter values of IPW template';
COMMENT ON COLUMN IWP_PARAMETER_VALUES.TEMPLATE_ID IS 'The ID of the template';
COMMENT ON COLUMN IWP_PARAMETER_VALUES.BOOKMARK_NAME IS 'The name of the bookmark';
COMMENT ON COLUMN IWP_PARAMETER_VALUES.PARAMETER_NAME IS 'The name of the parameter';
COMMENT ON COLUMN IWP_PARAMETER_VALUES.DATE_CREATED IS 'The date the parameter is created';
COMMENT ON COLUMN IWP_PARAMETER_VALUES.USER_CREATED IS 'The user who creates the parameter';
COMMENT ON COLUMN IWP_PARAMETER_VALUES.PARAMETER_VALUE IS 'The value of the parameter';
COMMENT ON COLUMN IWP_PARAMETER_VALUES.CREATE_DATETIME IS 'The timestamp when the record was created.';
COMMENT ON COLUMN IWP_PARAMETER_VALUES.CREATE_USER_ID IS 'The user account that created the record.';
COMMENT ON COLUMN IWP_PARAMETER_VALUES.MODIFY_DATETIME IS 'The timestamp when the record was last modified.';
COMMENT ON COLUMN IWP_PARAMETER_VALUES.MODIFY_USER_ID IS 'The user account that last modified the record.';
COMMENT ON COLUMN IWP_PARAMETER_VALUES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON COLUMN IWP_TEMPLATES.TEMPLATE_ID IS 'The ID of the template document';
COMMENT ON COLUMN IWP_TEMPLATES.TEMPLATE_NAME IS 'Short name of the template';
COMMENT ON COLUMN IWP_TEMPLATES.DATE_CREATED IS 'The date when the template is created';
COMMENT ON COLUMN IWP_TEMPLATES.USER_CREATED IS 'User who create the template';
COMMENT ON COLUMN IWP_TEMPLATES.ACTIVE_FLAG IS 'If the template active?';
COMMENT ON COLUMN IWP_TEMPLATES.DESCRIPTION IS 'The description of the tempalte';
COMMENT ON COLUMN IWP_TEMPLATES.EXPIRY_DATE IS 'The expiry date of the template';
COMMENT ON COLUMN IWP_TEMPLATES.OBJECT_TYPE IS 'Reference_Code (OBJECT_TYPE)';
COMMENT ON COLUMN IWP_TEMPLATES.CREATE_DATETIME IS 'The timestamp when the record was created.';
COMMENT ON COLUMN IWP_TEMPLATES.CREATE_USER_ID IS 'The user account that created the record.';
COMMENT ON COLUMN IWP_TEMPLATES.MODIFY_DATETIME IS 'The timestamp when the record was last modified.';
COMMENT ON COLUMN IWP_TEMPLATES.MODIFY_USER_ID IS 'The user account that last modified the record.';
COMMENT ON COLUMN IWP_TEMPLATES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE IWP_TEMPLATE_MODULES IS 'The association relationship between template and modules or template and module blocks. Maintenance linking Integrated Word Processing templates to modules and blocks. This allows users to have access to specific IWP templates via the Toolbar when they enter the screen or select any record in the screen block. It is used in parameters mappings to map parameters to block fields.';
COMMENT ON COLUMN IWP_TEMPLATE_MODULES.TEMPLATE_MODULE_ID IS 'An internal unique identifier for the template and module. ';
COMMENT ON COLUMN IWP_TEMPLATE_MODULES.MODULE_NAME IS 'The code used to uniquely identify the name of the module.  Application Form/Screen Name (i.e. OTDCLOSE)';
COMMENT ON COLUMN IWP_TEMPLATE_MODULES.TEMPLATE_ID IS 'The ID of the template';
COMMENT ON COLUMN IWP_TEMPLATE_MODULES.ACTIVE_FLAG IS 'If the record actively in use? ';
COMMENT ON COLUMN IWP_TEMPLATE_MODULES.BLOCK_DESCRIPTION IS 'Block description';
COMMENT ON COLUMN IWP_TEMPLATE_MODULES.BLOCK_NAME IS 'Name of the blok belonging to module_name';
COMMENT ON COLUMN IWP_TEMPLATE_MODULES.EXPIRY_DATE IS 'The expiry date. ';
COMMENT ON COLUMN IWP_TEMPLATE_MODULES.CREATE_DATETIME IS 'The timestamp when the record was created.';
COMMENT ON COLUMN IWP_TEMPLATE_MODULES.CREATE_USER_ID IS 'The user account that created the record.';
COMMENT ON COLUMN IWP_TEMPLATE_MODULES.MODIFY_DATETIME IS 'The timestamp when the record was last modified.';
COMMENT ON COLUMN IWP_TEMPLATE_MODULES.MODIFY_USER_ID IS 'The user account that last modified the record.';
COMMENT ON COLUMN IWP_TEMPLATE_MODULES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE IWP_TEMPLATE_OBJECTS IS 'The association of template with other database objects';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.IWP_TEMPLATE_OBJECT_ID IS 'The IWP template object ID';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.TEMPLATE_ID IS 'The ID of the template';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.ACTIVE_FLAG IS '?If the record is active';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.OBJECT_TYPE IS 'The general object Type';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.EXPIRY_DATE IS 'expiry date of the relationship';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.LIST_SEQ IS 'the order of listing';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.OBJECT_CODE IS 'The object code';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.CREATE_DATETIME IS 'The timestamp when the record was created.';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.CREATE_USER_ID IS 'The user account that creates the record.';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.MODIFY_DATETIME IS 'The timestamp when the record was last modified.';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.MODIFY_USER_ID IS 'The user account that last modified the record.';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE IWP_TEMPLATE_ROLES IS 'The associate roles with the templates';
COMMENT ON COLUMN IWP_TEMPLATE_ROLES.TEMPLATE_ID IS 'The ID of the template';
COMMENT ON COLUMN IWP_TEMPLATE_ROLES.ROLE_CODE IS 'The User role';
COMMENT ON COLUMN IWP_TEMPLATE_ROLES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN IWP_TEMPLATE_ROLES.CREATE_USER_ID IS 'The user account that creates the record';
COMMENT ON COLUMN IWP_TEMPLATE_ROLES.MODIFY_DATETIME IS 'The timestamp when the record was last modified';
COMMENT ON COLUMN IWP_TEMPLATE_ROLES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN IWP_TEMPLATE_ROLES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE MODULE_PRIVILEGES IS 'Maintenance of linkage between module (screen or report) and Elite user role. The user''s access privilege (All or Query Only) is also defined in this table.';
COMMENT ON COLUMN MODULE_PRIVILEGES.MODULE_NAME IS 'The code used to uniquely identify the name of the module.';
COMMENT ON COLUMN MODULE_PRIVILEGES.ROLE_ID IS 'The unique identifier for the user role.';
COMMENT ON COLUMN MODULE_PRIVILEGES.ACCESS_PRIVILEGE IS 'The code used to identify the type of privileges assigned to this module by this role. Reference domain ACCESS_PRIVI';
COMMENT ON COLUMN MODULE_PRIVILEGES.VERIFICATION_FLAG IS 'A flag used to indicate whether the user can perform verification (Y/N).';
COMMENT ON COLUMN MODULE_PRIVILEGES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN MODULE_PRIVILEGES.CREATE_USER_ID IS 'The user account that creates the record';
COMMENT ON COLUMN MODULE_PRIVILEGES.MODIFY_DATETIME IS 'The timestamp when the record was last modified';
COMMENT ON COLUMN MODULE_PRIVILEGES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN MODULE_PRIVILEGES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE OFFENDER_BOOKINGS IS 'Stores one or more bookings (periods of supervision) within an offender record. The majority of offender institutional and community data is stored in the context of a booking. This table regulates whether an offender record is active or inactive (an active offender record has at least one open booking; inactive offender records have only closed bookings). This table is a direct link to system security, controlling which offender records can be retrieved by a user account (a user account can retrieve bookings where any of the offender''s attached agency location(s) is contained within that user account''s caseload).';
COMMENT ON COLUMN OFFENDER_BOOKINGS.OFFENDER_BOOK_ID IS 'An internal unique identifier for the Offender Booking. Uses sequence OFFENDER_BOOK_ID.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.AGENCY_IML_ID IS 'An internal unique identifier for an institutional internal location--a non-bed location within the institution. Indicates the offender''s current location when outside the assigned living unit.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.AGY_LOC_ID IS 'Stores the code to uniquely identify the latest institutional agency location for the booking. Once released or transferred from the institution, this column will store ''OUT'' or ''TRN''.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.LIVING_UNIT_ID IS 'A unique identifier for an offender bed location.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.OFFENDER_ID IS 'The internal unique identifier for the offender name associated with this booking, for the selected unique offender (root_offender_id).';
COMMENT ON COLUMN OFFENDER_BOOKINGS.BOOKING_BEGIN_DATE IS 'The date the booking was created.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.DISCLOSURE_FLAG IS 'A flag relating to the disclosure of offender information. Default ''N''. Not used.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.IN_OUT_STATUS IS 'Indicates whether the offender is currently in or out of the facility. Reference domain IN_OUT_STS';
COMMENT ON COLUMN OFFENDER_BOOKINGS.YOUTH_ADULT_CODE IS 'Indicates whether the offender is considered a youth (Y/N) for this booking. Default = N (adult).';
COMMENT ON COLUMN OFFENDER_BOOKINGS.ACTIVE_FLAG IS ' Indicates whether the offender is active or inactive at an institution (Y/N).';
COMMENT ON COLUMN OFFENDER_BOOKINGS.ACTIVITY_DATE IS 'Community only: populated when community booking is closed';
COMMENT ON COLUMN OFFENDER_BOOKINGS.AGY_LOC_ID_LIST IS 'A list of all of the community agency locations the offender is attached to.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.ASSIGNED_STAFF_ID IS 'Populated with the STAFF_ID performing the Intake (Community) or Admission (Institution).';
COMMENT ON COLUMN OFFENDER_BOOKINGS.BOOKING_CREATED_DATE IS 'The date the booking was created.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.BOOKING_END_DATE IS 'The date the booking was closed.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.BOOKING_NO IS 'The client defined identifier for the booking which is visible to the user.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.BOOKING_STATUS IS 'The open or closed status of the booking. Reference domain BOOK_STS.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.BOOKING_TYPE IS 'Default ''INST'' unless there is no Institutional Booking then default ''COMM''.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.CASE_DATE IS 'Date associated with institutional case officer assignment';
COMMENT ON COLUMN OFFENDER_BOOKINGS.CASE_OFFICER_ID IS 'INST side only, indicating current case officer assignment (staff_id).';
COMMENT ON COLUMN OFFENDER_BOOKINGS.CASE_TIME IS 'Time associated with institutional case officer assignment';
COMMENT ON COLUMN OFFENDER_BOOKINGS.COMMUNITY_ACTIVE_FLAG IS 'Default ''Y'' if offender has an Active community booking or ''N'' if Inactive; leave NULL if there is no community booking.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.COMMUNITY_AGY_LOC_ID IS 'Community agency location having ownership of record. ''MULTI'' is entered if multiple offices have ownership.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.COMM_STAFF_ID IS 'The STAFF_ID of the current community primary officer as defined in the CASE_PLANS table.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.COMM_STAFF_ROLE IS 'Role associated with assigned COMM_STAFF_ID as defined in STAFF_LOCATION_ROLES.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.COMM_STATUS IS 'Defines the current community status of the booking. Status codes are defined in SYSTEM_PROFILES for Profile Code PROB_STATUS.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.CREATE_AGY_LOC_ID IS 'Institutional AGY_LOC_ID creating the booking.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.CREATE_INTAKE_AGY_LOC_ID IS 'The Community agency location the offender is initially assigned to.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.EARNED_CREDIT_LEVEL IS 'Not used.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.EKSTRAND_CREDIT_LEVEL IS 'Not used.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.FINGER_PRINTED_STAFF_ID IS 'The staff member finger printing the offender. Not used.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.INTAKE_AGY_LOC_ID IS 'The Community agency location which the offender is initially moved into. Set to ''CLOSE'' when the community portion of the booking is closed. Required for retrieval on community headers.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.INTAKE_CASELOAD_ID IS 'The Community CASELOAD_ID performing the intake; not required for system functionality.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.INTAKE_USER_ID IS 'Not used.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.NO_COMM_AGY_LOC_ID IS 'Total number of active community agency locations having ownership of the booking.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.PHOTO_TAKING_STAFF_ID IS 'Id of staff member who took offender''s image. Not used.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.ROOT_OFFENDER_ID IS 'The internal unique identifier for an offender.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.SEARCH_STAFF_ID IS 'Id of staff member who searched offender. Not used.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.SERVICE_FEE_FLAG IS 'Y/N flag populated from Service Fee flag on Bail\Bond screen.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.STATUS_REASON IS 'For Ontario Header Status. Institutional status for offenders currently out of the facility. Field is populated with MOVEMENT_TYPE and MOVEMENT_REASON_CODE from OFFENDER_EXTERNAL_MOVEMENTS.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.TOTAL_UNEXCUSED_ABSENCES IS 'Captures the total unexcused absences for the offender within the booking based on the event_outcome column in offender_schedules table.';
COMMENT ON COLUMN OFFENDER_BOOKINGS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN OFFENDER_BOOKINGS.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN OFFENDER_BOOKINGS.MODIFY_DATETIME IS 'The timestamp when the record was last modified';
COMMENT ON COLUMN OFFENDER_BOOKINGS.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN OFFENDER_BOOKINGS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE OMS_MODULES IS 'Maintenance table storing the name, description and associated characteristics for every screen and report within Elite.';
COMMENT ON COLUMN OMS_MODULES.MODULE_NAME IS 'The code used to uniquely identify the name of the module.';
COMMENT ON COLUMN OMS_MODULES.APPLN_CODE IS 'Code defining the application area that the module resides within. E.g., Trust, Community, Commissary etc.';
COMMENT ON COLUMN OMS_MODULES.DEFAULT_COPY IS 'Not used.';
COMMENT ON COLUMN OMS_MODULES.DESCRIPTION IS 'The description for the module.';
COMMENT ON COLUMN OMS_MODULES.HELP_DIRECTORY IS 'Link to section of on-line help.';
COMMENT ON COLUMN OMS_MODULES.MODULE_TYPE IS 'The code used to identify the type of the module e.g., SCREEN, REPORT, TAB, etc.';
COMMENT ON COLUMN OMS_MODULES.OUTPUT_TYPE IS 'Output type for the report.';
COMMENT ON COLUMN OMS_MODULES.PREVIEW_FLAG IS 'Preview. Not used.';
COMMENT ON COLUMN OMS_MODULES.PRINT_FORMAT_CODE IS 'The code used to identify the print format for a report type module i.e. Landscape, Letter etc. Reference domain PRINT_FORMAT';
COMMENT ON COLUMN OMS_MODULES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN OMS_MODULES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN OMS_MODULES.MODIFY_DATETIME IS 'The timestamp when the record was last modified';
COMMENT ON COLUMN OMS_MODULES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN OMS_MODULES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE OMS_ROLES IS 'Maintenance table for defining the user role and description associated with user security within Elite. These roles are associated with business units within institutions and community offices.';
COMMENT ON COLUMN OMS_ROLES.ROLE_ID IS 'The unique identifier of the user role';
COMMENT ON COLUMN OMS_ROLES.ROLE_CODE IS 'The system role (as Oracle database role)';
COMMENT ON COLUMN OMS_ROLES.ROLE_NAME IS 'The name associated with the user role';
COMMENT ON COLUMN OMS_ROLES.PARENT_ROLE_CODE IS 'The parent of the system role (for grouping)';
COMMENT ON COLUMN OMS_ROLES.ROLE_SEQ IS 'Controls the Screen/LOV display sequence.';
COMMENT ON COLUMN OMS_ROLES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN OMS_ROLES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN OMS_ROLES.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN OMS_ROLES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN OMS_ROLES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE STAFF_MEMBERS IS 'Maintains basic information on staff members. Staff members optionally may have system access through the assignment of a USER_ID.';
COMMENT ON COLUMN STAFF_MEMBERS.STAFF_ID IS 'A unique identifier for a staff member at an institution or community office. Uses sequence STAFF_ID.';
COMMENT ON COLUMN STAFF_MEMBERS.USER_ID IS 'The unique User Account for the staff member.';
COMMENT ON COLUMN STAFF_MEMBERS.ASSIGNED_CASELOAD_ID IS 'The caseload that is the primary work location for the associated staff member. Only applies to staff members with a user account.';
COMMENT ON COLUMN STAFF_MEMBERS.COMM_RECEIPT_PRINTER_ID IS 'Not used.';
COMMENT ON COLUMN STAFF_MEMBERS.SUPERVISOR_STAFF_ID IS 'Supervisor''s staff id. Not used.';
COMMENT ON COLUMN STAFF_MEMBERS.WORKING_CASELOAD_ID IS 'The caseload this staff member is currently working on. Only applies to staff members with a user account.';
COMMENT ON COLUMN STAFF_MEMBERS.FIRST_NAME IS 'First name of the staff member.';
COMMENT ON COLUMN STAFF_MEMBERS.LAST_NAME IS 'Last name of the staff member.';
COMMENT ON COLUMN STAFF_MEMBERS.UPDATE_ALLOWED_FLAG IS 'Not used. Default Y';
COMMENT ON COLUMN STAFF_MEMBERS.ABBREVIATION IS 'Abbreviation of staff member''s name. Not used.';
COMMENT ON COLUMN STAFF_MEMBERS.AS_OF_DATE IS 'Date that the current STATUS was initiated.';
COMMENT ON COLUMN STAFF_MEMBERS.BADGE_ID IS 'Officer Badge No. Not used.';
COMMENT ON COLUMN STAFF_MEMBERS.BIRTHDATE IS 'Staff member''s birth date.';
COMMENT ON COLUMN STAFF_MEMBERS.DEFAULT_PRINTER_ID IS ' Default printer for the staff member. Not used.';
COMMENT ON COLUMN STAFF_MEMBERS.EMERGENCY_CONTACT IS 'Staff member''s emergency contact information.';
COMMENT ON COLUMN STAFF_MEMBERS.FIRST_LOGON_FLAG IS '? If it is the first logon of the staff';
COMMENT ON COLUMN STAFF_MEMBERS.FORCE_PASSWORD_CHANGE_FLAG IS 'Not used.';
COMMENT ON COLUMN STAFF_MEMBERS.LAST_PASSWORD_CHANGE_DATE IS 'Tracks the date the password was last changed.';
COMMENT ON COLUMN STAFF_MEMBERS.LICENSE_CODE IS 'Type of driver''s license for the staff member. Reference domain TPT_VEH_LIC';
COMMENT ON COLUMN STAFF_MEMBERS.LICENSE_EXPIRY_DATE IS 'Staff member''s license expiration date.';
COMMENT ON COLUMN STAFF_MEMBERS.MIDDLE_NAME IS 'Middle name of the staff member.';
COMMENT ON COLUMN STAFF_MEMBERS.NAME_SEQUENCE IS 'Determines the order of staff names (first / middle / last) to appear on printed labels or generated comments for correspondence.';
COMMENT ON COLUMN STAFF_MEMBERS.PERSONNEL_TYPE IS 'Type of staff member e.g., Staff, Contractor, Volunteer etc. Reference domain PERSONNEL_TP';
COMMENT ON COLUMN STAFF_MEMBERS.POSITION IS 'Staff member''s job position. Reference domain PERSONNEL_PO';
COMMENT ON COLUMN STAFF_MEMBERS.ROLE IS 'Not used.';
COMMENT ON COLUMN STAFF_MEMBERS.SEX_CODE IS 'Gender of staff member. Reference domain SEX';
COMMENT ON COLUMN STAFF_MEMBERS.STATUS IS 'Current status of staff member record. E.g., Active, Inactive etc. Reference domain STAFF_STATUS';
COMMENT ON COLUMN STAFF_MEMBERS.SUSPENDED_FLAG IS 'Allows for the temporary suspension of the staff member''s user account.';
COMMENT ON COLUMN STAFF_MEMBERS.SUSPENSION_DATE IS 'Not used.';
COMMENT ON COLUMN STAFF_MEMBERS.SUSPENSION_REASON IS 'Not used.';
COMMENT ON COLUMN STAFF_MEMBERS.TERMINATION_DATE IS 'Date of user account termination.';
COMMENT ON COLUMN STAFF_MEMBERS.TITLE IS 'The title of the staff';
COMMENT ON COLUMN STAFF_MEMBERS.WORKING_STOCK_LOC_ID IS 'The commissary stock location this staff member is currently working on. Only applies to staff members with a user account.';
COMMENT ON COLUMN STAFF_MEMBERS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN STAFF_MEMBERS.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN STAFF_MEMBERS.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN STAFF_MEMBERS.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN STAFF_MEMBERS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE STAFF_MEMBER_ROLES IS 'Maintains the attachment of staff members to user roles, for defining the security access of staff members.';
COMMENT ON COLUMN STAFF_MEMBER_ROLES.ROLE_ID IS 'The unique identifier of an OMS Role';
COMMENT ON COLUMN STAFF_MEMBER_ROLES.STAFF_ID IS 'The unique identifier of a staff member';
COMMENT ON COLUMN STAFF_MEMBER_ROLES.ROLE_CODE IS 'The system role (Oracle DB role)';
COMMENT ON COLUMN STAFF_MEMBER_ROLES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN STAFF_MEMBER_ROLES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN STAFF_MEMBER_ROLES.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN STAFF_MEMBER_ROLES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN STAFF_MEMBER_ROLES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE WORKS IS 'Key maintenance table for integrated case management and workflow, which defines three major system components: 1) all case notes / contact log types and subtypes; 2) all tasks - work items assignable to teams and officers; 3) all memos - messages directed to teams and officers. All three can be manually entered or automatically system-generated by triggers. Parent table to WORK_IWP_TEMPLATES (for linking WIP documents to tasks, memos or case notes), WORK_TRIGGERS (for defining triggers to automatically generate a WORK item), and WORK_FUNCTIONS (to configure recipients of tasks and memos based on current TEAMS assigned to individual offenders to perform certain FUNCTIONS).';
COMMENT ON COLUMN WORKS.WORK_ID IS 'Unique identifier for this work item.';
COMMENT ON COLUMN WORKS.WORKFLOW_TYPE IS 'Classification of workflow type, casenote, task, or memo. Reference Code (ALERT_TASK)';
COMMENT ON COLUMN WORKS.WORK_TYPE IS 'Type of work. Reference Domain TASK_TYPE';
COMMENT ON COLUMN WORKS.WORK_SUB_TYPE IS 'Subtype of work. Reference Domain TASK_SUBTYPE';
COMMENT ON COLUMN WORKS.ACTIVE_FLAG IS 'Standard maintenance indicator as to whether this record is currently active.';
COMMENT ON COLUMN WORKS.MANUAL_CLOSE_FLAG IS 'For tasks and memos only, not casenotes: indicates (Y/N) whether a user can manually indicate work of this type to be complete.';
COMMENT ON COLUMN WORKS.CASELOAD_TYPE IS 'Defines work items applicable to INST or COMM work environments. Reference domain CLOAD_TYPE';
COMMENT ON COLUMN WORKS.EMAIL_BODY IS 'Details of the email.';
COMMENT ON COLUMN WORKS.EMAIL_SUBJECT IS 'email subject information.';
COMMENT ON COLUMN WORKS.EXPIRY_DATE IS 'Standard indicator; if ACTIVE_FLAG = N, the date this record became inactive.';
COMMENT ON COLUMN WORKS.MANUAL_SELECT_FLAG IS 'For casenotes only, not tasks or memos: indicates (Y/N) if this casenote type/subtype can be manually entered. If N, casenote can only be automatically generated via a trigger.';
COMMENT ON COLUMN WORKS.MODULE_NAME IS 'Name of the module from which this work item can be completed.';
COMMENT ON COLUMN WORKS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN WORKS.CREATE_USER_ID IS 'The user account that created this record';
COMMENT ON COLUMN WORKS.MODIFY_DATETIME IS 'The timestamp when the record was last modified.';
COMMENT ON COLUMN WORKS.MODIFY_USER_ID IS 'The user account that last modified this record';
COMMENT ON COLUMN WORKS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE WORK_IWP_TEMPLATES IS 'Identifies pre-defined Integrated Word Processing (IWP) templates that are associated with a selected casenote, task or memo. (Documents for casenotes are typically form letters.  Documents for tasks may be much larger, such as lengthy pre-sentence investigation reports.)';
COMMENT ON COLUMN WORK_IWP_TEMPLATES.WORK_ID IS 'Unique identifier of selected work item';
COMMENT ON COLUMN WORK_IWP_TEMPLATES.TEMPLATE_ID IS 'The unique identifier for the selected document template';
COMMENT ON COLUMN WORK_IWP_TEMPLATES.ACTIVE_FLAG IS 'Standard maintenance indicator as to whether this record is currently active.';
COMMENT ON COLUMN WORK_IWP_TEMPLATES.EXPIRY_DATE IS 'Standard indicator; if ACTIVE_FLAG = N, the date this record became inactive.';
COMMENT ON COLUMN WORK_IWP_TEMPLATES.LIST_SEQ IS 'Controls sort order (list sequence) for this record within LOVs / pick lists.';
COMMENT ON COLUMN WORK_IWP_TEMPLATES.CREATE_DATETIME IS 'The timestamp when the record is created';
COMMENT ON COLUMN WORK_IWP_TEMPLATES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN WORK_IWP_TEMPLATES.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN WORK_IWP_TEMPLATES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN WORK_IWP_TEMPLATES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
