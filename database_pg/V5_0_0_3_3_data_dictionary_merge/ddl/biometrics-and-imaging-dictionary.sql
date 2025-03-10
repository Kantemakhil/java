COMMENT ON TABLE BIO_EVENT_LOGS IS 'Logs related to the performance of biometric verification against existing biometric information for a selected biometric subject.';
COMMENT ON COLUMN BIO_EVENT_LOGS.DATE_TAKEN IS 'The date and time of the biometric verification.';
COMMENT ON COLUMN BIO_EVENT_LOGS.MATCH_SCORE IS 'A number representing the match score.';
COMMENT ON COLUMN BIO_EVENT_LOGS.OPERATOR IS 'The user_id of the staff member who performed the search.';
COMMENT ON COLUMN BIO_EVENT_LOGS.OUTCOME IS 'An indicator of whether the score is considered a MATCH or NO_MATCH.';
COMMENT ON COLUMN BIO_EVENT_LOGS.SUBJECT_ID IS 'The unique identifier of the biometric subject who was being compared.';
COMMENT ON COLUMN BIO_EVENT_LOGS.STATION_IP IS 'The IP address of the station that performed the match.';
COMMENT ON COLUMN BIO_EVENT_LOGS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN BIO_EVENT_LOGS.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN BIO_EVENT_LOGS.MODIFY_DATETIME IS 'The timestamp when the record was last modified';
COMMENT ON COLUMN BIO_EVENT_LOGS.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN BIO_EVENT_LOGS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE BIO_FP_SAMPLES IS 'The fingerprint biometric data samples, scores, and indicator of whether the record is a member of the master record. ';
COMMENT ON COLUMN BIO_FP_SAMPLES.SCAN_ID IS 'Identifies the associated scan event record. Generated from sequence ( BIO_SCAN_ID )';
COMMENT ON COLUMN BIO_FP_SAMPLES.SUBJECT_ID IS 'Identifies the parent bio subject to which this sample data belongs.';
COMMENT ON COLUMN BIO_FP_SAMPLES.POSITION IS 'A code uniquely identifying the finger sampled. Ref Domain ( FINGER_ENR )';
COMMENT ON COLUMN BIO_FP_SAMPLES.SAMPLE_FORMAT IS 'Identifies the biometric data format used to store the sample data.';
COMMENT ON COLUMN BIO_FP_SAMPLES.MRS_FLAG IS 'A flag (Y/N) indicating whether this sample is included in the master subject record. Only one record for each subject and position can form part of the master record.';
COMMENT ON COLUMN BIO_FP_SAMPLES.NRECORD IS 'Stores binary data that records a measurable biological characteristic.';
COMMENT ON COLUMN BIO_FP_SAMPLES.SAMPLE_DATA IS 'Stores binary data that represents the biometric image.';
COMMENT ON COLUMN BIO_FP_SAMPLES.SAMPLE_SCORE IS 'The quality score of the biometric sample.';
COMMENT ON COLUMN BIO_FP_SAMPLES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN BIO_FP_SAMPLES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN BIO_FP_SAMPLES.MODIFY_DATETIME IS 'The timestamp when the record was last modified';
COMMENT ON COLUMN BIO_FP_SAMPLES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN BIO_FP_SAMPLES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE BIO_SCANS IS 'Biometric data capture events.';
COMMENT ON COLUMN BIO_SCANS.SCAN_ID IS 'The unique identifier of this scanning event record.';
COMMENT ON COLUMN BIO_SCANS.SUBJECT_ID IS 'Identifies the parent bio subject to which this scan data belongs.';
COMMENT ON COLUMN BIO_SCANS.OPERATOR IS 'The user id of the staff member who recorded the scan.';
COMMENT ON COLUMN BIO_SCANS.SCAN_DATE IS 'The system timestamp of the scan.';
COMMENT ON COLUMN BIO_SCANS.SCAN_TYPE IS 'Identifies the type of biometric scan. FINGERPRINT for fingerprints.';
COMMENT ON COLUMN BIO_SCANS.REASON IS 'Identifies the reason for the scan--whether for initial enrollment or to update the biometric data.';
COMMENT ON COLUMN BIO_SCANS.STATION_IP IS 'The IP address of the station that recorded the scan.';
COMMENT ON COLUMN BIO_SCANS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN BIO_SCANS.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN BIO_SCANS.MODIFY_DATETIME IS 'The timestamp when the record was last modified';
COMMENT ON COLUMN BIO_SCANS.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN BIO_SCANS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE BIO_SUBJECTS IS 'Identifies the subject of biometric data capture and stores the master template (master record).';
COMMENT ON COLUMN BIO_SUBJECTS.SUBJECT_ID IS 'The unique identifier of the biometric subject. Generated from sequence ( BIO_SUBJECT_ID )';
COMMENT ON COLUMN BIO_SUBJECTS.PERSON_ID IS 'Identifies the person identification record.';
COMMENT ON COLUMN BIO_SUBJECTS.ROOT_OFFENDER_ID IS 'Identifies the primary or root offender identification record';
COMMENT ON COLUMN BIO_SUBJECTS.CLUSTER_HASH IS 'A random hash value assigned to the subject when the record is created.';
COMMENT ON COLUMN BIO_SUBJECTS.GENDER IS 'A code identifying the gender of the subject. Allowed values: M, F, U (Male, Female, Unknown).';
COMMENT ON COLUMN BIO_SUBJECTS.NTEMPLATE_UPDATE IS 'The date the NTemplate was last updated.';
COMMENT ON COLUMN BIO_SUBJECTS.ALERT_FLAG IS 'Not used.';
COMMENT ON COLUMN BIO_SUBJECTS.CONSENT_GIVEN IS 'A flag (Y/N/NULL) identifying whether consent was given for the scan. Unconsented data will be subject to purge from the database after the period of days defined in system profile BIO BIO_PURGE. Consent only relates to person records.';
COMMENT ON COLUMN BIO_SUBJECTS.NTEMPLATE IS 'Stores binary data that represents a digital reference of distinct biometric characteristics.';
COMMENT ON COLUMN BIO_SUBJECTS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN BIO_SUBJECTS.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN BIO_SUBJECTS.MODIFY_DATETIME IS 'The timestamp when the record was last modified';
COMMENT ON COLUMN BIO_SUBJECTS.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN BIO_SUBJECTS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE IMAGES IS 'Master image table, storing image information for all parent entities to simplify data management and system changes. The thumbnail version of all images are stored here for quick display on application, and includes image context information, to tie the image to the parent entity.';
COMMENT ON COLUMN IMAGES.IMAGE_ID IS 'The unique identifier of the image record.';
COMMENT ON COLUMN IMAGES.CAPTURE_DATE IS 'The date and time the image was captured.';
COMMENT ON COLUMN IMAGES.IMAGE_OBJECT_ID IS 'Unique identifier for this image owner. Stores offender_book_id for OFF_BKG, OFF_IDM, and PPTY; oic_incident_id for OIC; staff_id for STAFF; stg_id for STG.';
COMMENT ON COLUMN IMAGES.IMAGE_OBJECT_TYPE IS 'A code identifying the type of image. Included image types are: offender mugshot, OFF_BKG; offence in custody, OIC; property, PPTY; body marks, OFF_IDM; staff, STAFF; gangs, STG';
COMMENT ON COLUMN IMAGES.ACTIVE_FLAG IS 'A flag (Y/N) identifying whether the image is the active image.';
COMMENT ON COLUMN IMAGES.IMAGE_OBJECT_SEQ IS 'Additional unique identifier for image context, if needed. Stores property_item_seq for PPTY; id_mark_seq for OFF_IDM.';
COMMENT ON COLUMN IMAGES.IMAGE_THUMBNAIL IS 'Stores binary data for thumbnail version of image.';
COMMENT ON COLUMN IMAGES.IMAGE_VIEW_TYPE IS 'A code identifying further detail on image orientation or context. Various sources. For OFF_BKG, STAFF, or PERSON, Ref Domain ( PART_ORIENT ); for Property, contextual property type value; for body marks, contextual mark type value; for offences in custody, static OIC; for gangs, stores contextual profile_type value.';
COMMENT ON COLUMN IMAGES.ORIENTATION_TYPE IS 'A code identifying the orientation or other characteristic of the image. Various sources. For OFF_BKG, STAFF, or PERSON, Ref Domain ( IMAGE_VIEW ); for Property, contextual property type value; for body marks, contextual body part value; for offences in custody, Ref Domain ( IMAGE_OIC ); for gangs, static value STG.';
COMMENT ON COLUMN IMAGES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN IMAGES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN IMAGES.MODIFY_DATETIME IS 'The timestamp when the record was last modified';
COMMENT ON COLUMN IMAGES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN IMAGES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE IMAGE_ORIGINALS IS 'Master image, storing the full scale image and the original captured image for full display. This record is stored in parallel with the thumbnail image, and is retrieved through the context provided in the images table.';
COMMENT ON COLUMN IMAGE_ORIGINALS.IMAGE_ID IS 'The unique identifier of the image record. Value matches the ID for the thumbnail image on images table.';
COMMENT ON COLUMN IMAGE_ORIGINALS.IMAGE_FULL IS 'Stores binary data for the full scale image.';
COMMENT ON COLUMN IMAGE_ORIGINALS.IMAGE_ORIGINAL IS 'Stores binary data for the original captured image.';
COMMENT ON COLUMN IMAGE_ORIGINALS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN IMAGE_ORIGINALS.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN IMAGE_ORIGINALS.MODIFY_DATETIME IS 'The timestamp when the record was last modified';
COMMENT ON COLUMN IMAGE_ORIGINALS.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN IMAGE_ORIGINALS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE IMAGE_PROPERTIES IS 'Additional characteristics describing the image.';
COMMENT ON COLUMN IMAGE_PROPERTIES.IMAGE_PROPERTY_ID IS 'The unique identifier of the record. Generated from sequence ( IMAGE_PROPERTIES_ID )';
COMMENT ON COLUMN IMAGE_PROPERTIES.IMAGE_ID IS 'The unique identifier of the parent image record.';
COMMENT ON COLUMN IMAGE_PROPERTIES.PROPERTY IS 'A code identifying an image characteristic. Ref Domain ( IMAGE_PROP )';
COMMENT ON COLUMN IMAGE_PROPERTIES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN IMAGE_PROPERTIES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN IMAGE_PROPERTIES.MODIFY_DATETIME IS 'The timestamp when the record was last modified';
COMMENT ON COLUMN IMAGE_PROPERTIES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN IMAGE_PROPERTIES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE OFFENDERS IS 'Master offender names table, holding one record for every name associated with an offender. Names are linked together for an individual under a single common root_offender_id value.';
COMMENT ON COLUMN OFFENDERS.OFFENDER_ID IS 'The unique identifier number for the offender name record.';
COMMENT ON COLUMN OFFENDERS.ALIAS_OFFENDER_ID IS 'Pointer to the relevant root offender Id.';
COMMENT ON COLUMN OFFENDERS.CREATE_DATE IS 'Date of record creation.';
COMMENT ON COLUMN OFFENDERS.ID_SOURCE_CODE IS 'A code indicating the source of the offender_id_display. Value of SEQ indicates the value is system generated; any other value is user entered, and may indicate the particular source of the identifier. Ref Domain ( ID_SOURCE ).';
COMMENT ON COLUMN OFFENDERS.LAST_NAME IS 'Last name of the offender';
COMMENT ON COLUMN OFFENDERS.LAST_NAME_KEY IS 'Key for the storage of last name retaining only upper case alphabetic characters (spaces and special characters removed).';
COMMENT ON COLUMN OFFENDERS.SEX_CODE IS 'A code that identifies the gender of the offender. Ref Domain ( SEX ).';
COMMENT ON COLUMN OFFENDERS.ADD_INFO_CODE IS 'No longer used.';
COMMENT ON COLUMN OFFENDERS.AGE IS 'The age of the Offender. Not used.';
COMMENT ON COLUMN OFFENDERS.ALIAS_NAME_TYPE IS 'Code that qualifies the type of name. E.g., working name, given name, alias, moniker, gang name. Ref Domain ( NAME_TYPE )';
COMMENT ON COLUMN OFFENDERS.BIRTH_COUNTRY_CODE IS 'The birth country of the offender. Ref Domain ( COUNTRY )';
COMMENT ON COLUMN OFFENDERS.BIRTH_COUNTY IS 'No longer used.';
COMMENT ON COLUMN OFFENDERS.BIRTH_DATE IS 'The birthdate of the offender.';
COMMENT ON COLUMN OFFENDERS.BIRTH_PLACE IS 'Place where the offender was born.';
COMMENT ON COLUMN OFFENDERS.BIRTH_STATE IS 'No longer used.';
COMMENT ON COLUMN OFFENDERS.CASELOAD_TYPE IS 'The type of caseload (INST or COMM) the user is signed into when creating the offender name record.';
COMMENT ON COLUMN OFFENDERS.FIRST_NAME IS 'Indicates the first name of the offender.';
COMMENT ON COLUMN OFFENDERS.FIRST_NAME_KEY IS 'Key for the storage of first name retaining only upper case alphabetic characters (spaces and special characters removed).';
COMMENT ON COLUMN OFFENDERS.LAST_NAME_ALPHA_KEY IS 'Column to store the first alphabet for the last name field - this field will be used to help in search and will have a non-unique index created on it.';
COMMENT ON COLUMN OFFENDERS.LAST_NAME_SOUNDEX IS 'Soundex of the last name.';
COMMENT ON COLUMN OFFENDERS.MIDDLE_NAME IS 'Indicates middle name of the offender.';
COMMENT ON COLUMN OFFENDERS.MIDDLE_NAME_2 IS 'Indicates the second middle name of the offender. For UK it is Given_Name 3.';
COMMENT ON COLUMN OFFENDERS.MIDDLE_NAME_KEY IS 'Key for the storage of middle name retaining only upper case alphabetic characters (spaces and special characters removed).';
COMMENT ON COLUMN OFFENDERS.NAME_SEQUENCE IS 'The order of names displayed. Not used.';
COMMENT ON COLUMN OFFENDERS.NAME_TYPE IS 'No longer used.';
COMMENT ON COLUMN OFFENDERS.OFFENDER_ID_DISPLAY IS 'The visible unique identifier number for the offender.';
COMMENT ON COLUMN OFFENDERS.OFFENDER_NAME_SEQ IS 'No longer used. System generated number.';
COMMENT ON COLUMN OFFENDERS.PARENT_OFFENDER_ID IS 'No longer used.';
COMMENT ON COLUMN OFFENDERS.RACE_CODE IS 'A code that identifies the race of the offender. Ref Domain ( ETHNICITY ).';
COMMENT ON COLUMN OFFENDERS.REMARK_CODE IS 'No longer used.';
COMMENT ON COLUMN OFFENDERS.ROOT_OFFENDER_ID IS 'An internal unique number identifying the primary root offender record. Each unique offender in the system will have a single unique root_offender_id associated to their set of known names.';
COMMENT ON COLUMN OFFENDERS.SUFFIX IS 'Code that identifies the offender name suffix (ie JR or III). Ref Domain ( SUFFIX ).';
COMMENT ON COLUMN OFFENDERS.SUSPENDED_DATE IS 'No longer used.';
COMMENT ON COLUMN OFFENDERS.SUSPENDED_FLAG IS 'No longer used.';
COMMENT ON COLUMN OFFENDERS.TITLE IS 'The title of the offender';
COMMENT ON COLUMN OFFENDERS.UNIQUE_OBLIGATION_FLAG IS 'No longer used.';
COMMENT ON COLUMN OFFENDERS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN OFFENDERS.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN OFFENDERS.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN OFFENDERS.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN OFFENDERS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
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
COMMENT ON TABLE OFFENDER_FINGERPRINTS IS 'Detail on offender fingerprints. Stored as a binary string against each finger selected. No longer used.';
COMMENT ON COLUMN OFFENDER_FINGERPRINTS.ROOT_OFFENDER_ID IS 'Identifies the primary or root offender identification record';
COMMENT ON COLUMN OFFENDER_FINGERPRINTS.HAND_ID IS 'A code used to identify which hand the fingerprint is from (''RIGHT'' or ''LEFT'')';
COMMENT ON COLUMN OFFENDER_FINGERPRINTS.FINGER_ID IS 'A code used to identify which finger the fingerprint is from (''THUMB'', ''INDEX'', ''MIDDLE'', ''THIRD'', ''FOURTH'')';
COMMENT ON COLUMN OFFENDER_FINGERPRINTS.DATE_CREATED IS 'The date the record was created.';
COMMENT ON COLUMN OFFENDER_FINGERPRINTS.FINGER_CODE IS 'The fingerprint identification detail.';
COMMENT ON COLUMN OFFENDER_FINGERPRINTS.USER_CREATED IS 'The user who created the record.';
COMMENT ON COLUMN OFFENDER_FINGERPRINTS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN OFFENDER_FINGERPRINTS.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN OFFENDER_FINGERPRINTS.MODIFY_DATETIME IS 'The timestamp when the record was last modified';
COMMENT ON COLUMN OFFENDER_FINGERPRINTS.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN OFFENDER_FINGERPRINTS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE OFFENDER_IMAGES IS 'The images of an offender. No longer used.';
COMMENT ON COLUMN OFFENDER_IMAGES.OFFENDER_IMAGE_ID IS 'Unique sequence identifying each image - Oracle Sequence - (PK)';
COMMENT ON COLUMN OFFENDER_IMAGES.OFFENDER_BOOK_ID IS 'Unique Sequence identifying the Offender''s Booking record.';
COMMENT ON COLUMN OFFENDER_IMAGES.ACTIVE_FLAG IS 'Flag to identify if the image record is currently active or not.';
COMMENT ON COLUMN OFFENDER_IMAGES.CAPTURE_DATETIME IS 'The timestamp when the image is taken';
COMMENT ON COLUMN OFFENDER_IMAGES.ORIENTATION_TYPE IS 'The orientation type ';
COMMENT ON COLUMN OFFENDER_IMAGES.FULL_SIZE_IMAGE IS 'The full size image (MUGSHOT) of the offender stored as jpeg format.';
COMMENT ON COLUMN OFFENDER_IMAGES.IMAGE_OBJECT_ID IS 'The ID of the image object.';
COMMENT ON COLUMN OFFENDER_IMAGES.IMAGE_OBJECT_SEQ IS 'The seq ID of the image object.';
COMMENT ON COLUMN OFFENDER_IMAGES.THUMBNAIL_IMAGE IS 'The thumbnail size image (MUGSHOT) of the offender stored as jpeg format.';
COMMENT ON COLUMN OFFENDER_IMAGES.CREATE_DATETIME IS 'The timestamp when the record is created';
COMMENT ON COLUMN OFFENDER_IMAGES.CREATE_USER_ID IS 'The user who creates the record';
COMMENT ON COLUMN OFFENDER_IMAGES.MODIFY_DATETIME IS 'The timestamp when the record is modified ';
COMMENT ON COLUMN OFFENDER_IMAGES.MODIFY_USER_ID IS 'The user who modifies the record';
COMMENT ON TABLE PERSONS IS 'Details persons who have contact with offenders.';
COMMENT ON COLUMN PERSONS.PERSON_ID IS 'A unique identifier for this person. Generated from sequence ( PERSON_ID )';
COMMENT ON COLUMN PERSONS.ROOT_PERSON_ID IS 'No longer used.';
COMMENT ON COLUMN PERSONS.FIRST_NAME IS 'The first name of the person.';
COMMENT ON COLUMN PERSONS.LAST_NAME IS 'The last name of the person.';
COMMENT ON COLUMN PERSONS.ALIAS_PERSON_ID IS 'No longer used.';
COMMENT ON COLUMN PERSONS.ATTENTION IS 'If a check payment is to be sent to the attention of some person or department, enter that person''s name.';
COMMENT ON COLUMN PERSONS.BIRTHDATE IS 'The person''s date of birth.';
COMMENT ON COLUMN PERSONS.BIRTH_PLACE IS 'Place where the offender was born.';
COMMENT ON COLUMN PERSONS.CARE_OF IS 'If a check is to be mailed in care of someone, enter that person''s name.';
COMMENT ON COLUMN PERSONS.CITIZENSHIP IS 'A code used to indicate the person''s country of citizenship. Ref Domain ( COUNTRY )';
COMMENT ON COLUMN PERSONS.COMPREHEND_ENGLISH_FLAG IS 'No longer used.';
COMMENT ON COLUMN PERSONS.CORONER_NUMBER IS 'The coroner identifier number on the death certificate.';
COMMENT ON COLUMN PERSONS.CRIMINAL_HISTORY_TEXT IS 'Textual information pertaining to the person''s criminal history.';
COMMENT ON COLUMN PERSONS.DECEASED_DATE IS 'The date the person was pronounced deceased.';
COMMENT ON COLUMN PERSONS.EMPLOYER IS 'No longer used.';
COMMENT ON COLUMN PERSONS.FIRST_NAME_KEY IS 'No longer used.';
COMMENT ON COLUMN PERSONS.INTERPRETER_REQUIRED IS 'A flag (Y/N) used to indicate whether the person requires an interpreter.';
COMMENT ON COLUMN PERSONS.LANGUAGE_CODE IS 'No longer used.';
COMMENT ON COLUMN PERSONS.LAST_NAME_KEY IS 'Key for the storage of last name without spaces or invalid characters for search purposes.';
COMMENT ON COLUMN PERSONS.LAST_NAME_SOUNDEX IS 'An internal representation of the last name for soundex search purposes.';
COMMENT ON COLUMN PERSONS.MARITAL_STATUS IS 'A code used to indicate the person''s marital status. Ref Domain ( MARITAL_STAT )';
COMMENT ON COLUMN PERSONS.MEMO_TEXT IS 'Other information pertaining to the person.';
COMMENT ON COLUMN PERSONS.MIDDLE_NAME IS 'The middle name of the person.';
COMMENT ON COLUMN PERSONS.MIDDLE_NAME_KEY IS 'No longer used.';
COMMENT ON COLUMN PERSONS.NAME_TYPE IS 'No longer used.';
COMMENT ON COLUMN PERSONS.OCCUPATION_CODE IS 'No longer used.';
COMMENT ON COLUMN PERSONS.PRIMARY_LANGUAGE_CODE IS 'A code used to indicate the primary language of the person. Ref Domain ( LANG )';
COMMENT ON COLUMN PERSONS.PROFILE_CODE IS 'No longer used.';
COMMENT ON COLUMN PERSONS.REMITTER_FLAG IS 'No longer used.';
COMMENT ON COLUMN PERSONS.SEX IS 'The gender of the person. Ref Domain ( SEX )';
COMMENT ON COLUMN PERSONS.STAFF_FLAG IS 'A flag (Y/N) indicating whether the person is a staff member.';
COMMENT ON COLUMN PERSONS.SUSPENDED_DATE IS 'The date of suspension when a person is suspended as a payment beneficiary.';
COMMENT ON COLUMN PERSONS.SUSPENDED_FLAG IS 'A flag (Y/N) to indicate whether a person is suspended as a payment beneficiary.';
COMMENT ON COLUMN PERSONS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN PERSONS.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN PERSONS.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN PERSONS.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN PERSONS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE TAG_IMAGES IS 'The images of TAG system. No longer used.';
COMMENT ON COLUMN TAG_IMAGES.TAG_IMAGE_ID IS 'Unique sequence identifying each image - Oracle Sequence - (PK)';
COMMENT ON COLUMN TAG_IMAGES.ACTIVE_FLAG IS 'Flag to identify if the image record is currently active or not.';
COMMENT ON COLUMN TAG_IMAGES.CAPTURE_DATETIME IS 'The timestamp when the image is taken';
COMMENT ON COLUMN TAG_IMAGES.IMAGE_OBJECT_ID IS 'The ID of the object.';
COMMENT ON COLUMN TAG_IMAGES.IMAGE_OBJECT_TYPE IS 'The object type of the image.';
COMMENT ON COLUMN TAG_IMAGES.ORIENTATION_TYPE IS 'The orientation type ';
COMMENT ON COLUMN TAG_IMAGES.FULL_SIZE_IMAGE IS 'The full size image (MUGSHOT) of the offender stored as jpeg format.';
COMMENT ON COLUMN TAG_IMAGES.IMAGE_OBJECT_SEQ IS 'The seq of the object (2nd column of the PK).';
COMMENT ON COLUMN TAG_IMAGES.THUMBNAIL_IMAGE IS 'The thumbnail size image (MUGSHOT) of the offender stored as jpeg format.';
COMMENT ON COLUMN TAG_IMAGES.CREATE_DATETIME IS 'The timestamp when the record is created';
COMMENT ON COLUMN TAG_IMAGES.CREATE_USER_ID IS 'The user who creates the record';
COMMENT ON COLUMN TAG_IMAGES.MODIFY_DATETIME IS 'The timestamp when the record is modified ';
COMMENT ON COLUMN TAG_IMAGES.MODIFY_USER_ID IS 'The user who modifies the record';
