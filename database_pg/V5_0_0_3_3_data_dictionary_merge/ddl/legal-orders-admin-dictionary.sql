COMMENT ON TABLE ALLOWABLE_OFFENCE_TYPES IS 'Types of offences (Summary, Indictable, Felony, Misdemeanor etc.) that apply to an offence, as well as defined bail amount for the charge.';
COMMENT ON COLUMN ALLOWABLE_OFFENCE_TYPES.OFFENCE_CODE IS 'The code used to uniquely identify the legal offence.';
COMMENT ON COLUMN ALLOWABLE_OFFENCE_TYPES.STATUTE_CODE IS 'The code used to uniquely identify the legal statute.';
COMMENT ON COLUMN ALLOWABLE_OFFENCE_TYPES.OFFENCE_TYPE IS 'Offence Type e.g., Summary, Indictable. Ref Domain ( OFFENCE_TYPE )';
COMMENT ON COLUMN ALLOWABLE_OFFENCE_TYPES.BAIL_ALLOWED_FLAG IS 'A flag (Y/N) that indicates whether bail is allowed for this offence and offence type.';
COMMENT ON COLUMN ALLOWABLE_OFFENCE_TYPES.RECOMMENDED_BAIL_AMOUNT IS 'Indicates the recommended bail amount for this offence and offence type. Only relevant if BAIL_ALLOWED_FLAG is ''Y''.';
COMMENT ON COLUMN ALLOWABLE_OFFENCE_TYPES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN ALLOWABLE_OFFENCE_TYPES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN ALLOWABLE_OFFENCE_TYPES.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN ALLOWABLE_OFFENCE_TYPES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN ALLOWABLE_OFFENCE_TYPES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE COMMUNITY_CONDITIONS IS 'Defines all conditions that can be imposed on an offender in conjunction with a community sentence (probation or parole order).';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.COMM_CONDITION_TYPE IS 'A code that identifies the sentence category for the community condition. Ref Domain ( CATEGORY )';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.COMM_CONDITION_CODE IS 'A code that, in conjunction with the condition type and category type, uniquely identifies the community condition.';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.CATEGORY_TYPE IS 'A code that identifies the community category. Typically this is used to categorize the condition codes. E.g. - Standard (default), Non standard. Ref Domain ( COM_CON_TYPE )';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.ACTIVE_FLAG IS 'A flag (Y/N) indicating whether the record is available for use.';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.AMOUNT_REQUIRED_FLAG IS 'Not used.';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.DESCRIPTION IS 'Description of the community condition.';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.DUE_DATE_REQUIRED_FLAG IS 'Not used.';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.PROVISO_FLAG IS 'Not used.';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.UPDATE_ALLOWED_FLAG IS 'Not used.';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.CASE_PLAN_FLAG IS 'A flag (Y/N) indicating whether this condition shall be copied to the offender case plan.';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.CONDITION_TEXT IS 'A template for the full description of the condition, including all stipulations.';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.CONDITION_UNIT_TYPE IS 'The unit type used for measuring the fulfillment of the condition. Ref Domain ( COND_UNIT )';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.CREATION_DATE IS 'Not used.';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.CREATION_USER IS 'Not used.';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.CSO_FLAG IS 'Not used.';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.DEFAULT_FLAG IS 'A flag (Y/N) indicating whether this condition will automatically be displayed in the list of conditions when creating new conditions against the offender.';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.EXPIRY_DATE IS 'The date the community condition was deactivated.';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.FINANCIAL_FLAG IS 'Not used.';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.FUNCTION_TYPE IS 'Identifies the Team to which this condition is assigned (for community supervision). Ref Domain ( FUNCTION )';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.LIST_SEQ IS 'Controls the Screen/LOV display sequence.';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.MAXIMUM_AMOUNT IS 'Not used.';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.PROGRAM_FLAG IS 'Not used.';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.PROGRAM_METHOD IS 'Indicates that this condition compels the offender to take a type of program (accredited, community service, etc.). Ref Domain ( PS_CATEGORY )';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.PROGRAM_UNITS IS 'Not used.';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.SUBSTANCE_FLAG IS 'Not used.';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN COMMUNITY_CONDITIONS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE HO_CODES IS 'Defines optional category codes for offences, intended for search & sort criteria on Offence Selection LOVs (valuable as the number of offences in a jurisdiction tends into the thousands).';
COMMENT ON COLUMN HO_CODES.HO_CODE IS 'Unique categorization code.';
COMMENT ON COLUMN HO_CODES.ACTIVE_FLAG IS 'A flag (Y/N) indicating whether the record is available for use.';
COMMENT ON COLUMN HO_CODES.CREATE_DATE IS 'The date When the record was created.';
COMMENT ON COLUMN HO_CODES.DESCRIPTION IS 'The description of the code';
COMMENT ON COLUMN HO_CODES.EXPIRY_DATE IS 'The date the record was deactivated.';
COMMENT ON COLUMN HO_CODES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN HO_CODES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN HO_CODES.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN HO_CODES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN HO_CODES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE IWP_TEMPLATE_OBJECTS IS 'The association of template with other database objects';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.IWP_TEMPLATE_OBJECT_ID IS 'The IWP template object ID';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.TEMPLATE_ID IS 'The ID of the template';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.ACTIVE_FLAG IS 'A flag (Y/N) indicating whether the record is active.';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.OBJECT_TYPE IS 'The general object Type. ''ORDER'' for linkage to legal order types.';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.EXPIRY_DATE IS 'expiry date of the relationship';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.LIST_SEQ IS 'the order of listing';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.OBJECT_CODE IS 'The object code. ORDER_TYPE value for linkage to legal order types.';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.CREATE_DATETIME IS 'The timestamp when the record was created.';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.CREATE_USER_ID IS 'The user account that creates the record.';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.MODIFY_DATETIME IS 'The timestamp when the record was last modified.';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.MODIFY_USER_ID IS 'The user account that last modified the record.';
COMMENT ON COLUMN IWP_TEMPLATE_OBJECTS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE LEGAL_UPDATE_REASONS IS 'Defines reasons for changing the status of an offender legal order (case, sentence or condition), plus supporting detail.';
COMMENT ON COLUMN LEGAL_UPDATE_REASONS.UPDATE_REASON_CODE IS 'A code uniquely identifying the update reason.';
COMMENT ON COLUMN LEGAL_UPDATE_REASONS.ACTIVE_FLAG IS 'A flag (Y/N) indicating whether the update reason is available for use.';
COMMENT ON COLUMN LEGAL_UPDATE_REASONS.ACTIVE_TYPE IS 'Resultant status of a legal order after this reason code is applied. Ref Domain ( ACTIVE_TYPE )';
COMMENT ON COLUMN LEGAL_UPDATE_REASONS.DESCRIPTION IS 'The description';
COMMENT ON COLUMN LEGAL_UPDATE_REASONS.REASON_CATEGORY IS 'A code classifying the reason code. Ref Domain ( LGL_RSN_CAT )';
COMMENT ON COLUMN LEGAL_UPDATE_REASONS.EFFECTIVE_DATE IS 'Date this reason code became effective.';
COMMENT ON COLUMN LEGAL_UPDATE_REASONS.EXPIRY_DATE IS 'The date this reason code was deactivated.';
COMMENT ON COLUMN LEGAL_UPDATE_REASONS.LIST_SEQ IS 'Controls the Screen/LOV display sequence.';
COMMENT ON COLUMN LEGAL_UPDATE_REASONS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN LEGAL_UPDATE_REASONS.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN LEGAL_UPDATE_REASONS.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN LEGAL_UPDATE_REASONS.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN LEGAL_UPDATE_REASONS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE LEGAL_UPDATE_USAGES IS 'Defines legal update reason codes that may be applied to a case, sentence, or condition.';
COMMENT ON COLUMN LEGAL_UPDATE_USAGES.LEGAL_CLASS IS 'Selected legal entity for which this reason code applies (case, sentence or condition). Ref Domain ( LEGAL_BLOCK )';
COMMENT ON COLUMN LEGAL_UPDATE_USAGES.UPDATE_REASON_CODE IS 'The parent update reason code for which this record applies.';
COMMENT ON COLUMN LEGAL_UPDATE_USAGES.ACTIVE_FLAG IS 'A flag (Y/N) indicating whether this usage is available for use.';
COMMENT ON COLUMN LEGAL_UPDATE_USAGES.EXPIRY_DATE IS 'The date the usage was deactivated.';
COMMENT ON COLUMN LEGAL_UPDATE_USAGES.LIST_SEQ IS 'Controls the Screen/LOV display sequence.';
COMMENT ON COLUMN LEGAL_UPDATE_USAGES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN LEGAL_UPDATE_USAGES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN LEGAL_UPDATE_USAGES.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN LEGAL_UPDATE_USAGES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN LEGAL_UPDATE_USAGES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE OFFENCES IS 'Offence codes and their descriptions.';
COMMENT ON COLUMN OFFENCES.OFFENCE_CODE IS 'The unique identifier for the legal offence within a particular statute.';
COMMENT ON COLUMN OFFENCES.STATUTE_CODE IS 'The code used to uniquely identify the legal statute.';
COMMENT ON COLUMN OFFENCES.HO_CODE IS 'Category of offence.';
COMMENT ON COLUMN OFFENCES.OLD_STATUTE_CODE IS 'Not used.';
COMMENT ON COLUMN OFFENCES.ACTIVE_FLAG IS 'A flag (Y/N) indicating whether the offence is currently available for use.';
COMMENT ON COLUMN OFFENCES.CREATE_DATE IS 'The date when Offence gets Created';
COMMENT ON COLUMN OFFENCES.DESCRIPTION IS 'The description of the legal offence.';
COMMENT ON COLUMN OFFENCES.UPDATE_ALLOWED_FLAG IS 'A flag (Y/N) to indicate whether the offence record may be updated. Not used.';
COMMENT ON COLUMN OFFENCES.CHECK_BOX1 IS 'Not used.';
COMMENT ON COLUMN OFFENCES.CHECK_BOX2 IS 'Not used.';
COMMENT ON COLUMN OFFENCES.CHECK_BOX3 IS 'Not used.';
COMMENT ON COLUMN OFFENCES.DEFAULT_OFFENCE_TYPE IS 'Not used.';
COMMENT ON COLUMN OFFENCES.EXPIRY_DATE IS 'The date the offence was deactivated.';
COMMENT ON COLUMN OFFENCES.LIST_SEQ IS 'Controls the Screen/LOV display sequence.';
COMMENT ON COLUMN OFFENCES.MAX_GOOD_TIME_PERC IS 'Not used.';
COMMENT ON COLUMN OFFENCES.MAX_SENTENCE_LENGTH IS 'Not used.';
COMMENT ON COLUMN OFFENCES.OFFENCE_GROUP IS 'Not used.';
COMMENT ON COLUMN OFFENCES.OFFENSE_DEGREE IS 'Not used.';
COMMENT ON COLUMN OFFENCES.REPEALED_DATE IS 'Not used.';
COMMENT ON COLUMN OFFENCES.SENTENCE_UNIT_CODE IS 'Not used.';
COMMENT ON COLUMN OFFENCES.SEVERITY_RANKING IS 'The code used to identify how severe the charge is. Ref Domain ( SEVERE_RANK )';
COMMENT ON COLUMN OFFENCES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN OFFENCES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN OFFENCES.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN OFFENCES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN OFFENCES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE OFFENCE_INDICATORS IS 'Maintenance table configuring multiple table-driven indicators for selected offences.';
COMMENT ON COLUMN OFFENCE_INDICATORS.OFFENCE_INDICATOR_ID IS 'An internal unique identifier for an offence indicator. Generated from sequence ( OFFENCE_INDICATOR_ID )';
COMMENT ON COLUMN OFFENCE_INDICATORS.OFFENCE_CODE IS 'The code used to uniquely identify the legal offence.';
COMMENT ON COLUMN OFFENCE_INDICATORS.STATUTE_CODE IS 'The code used to uniquely identify the legal statute.';
COMMENT ON COLUMN OFFENCE_INDICATORS.INDICATOR_CODE IS 'The indicator code to indicate additional information about the offence, typically used for categorization. Ref Domain ( OFFENCE_IND )';
COMMENT ON COLUMN OFFENCE_INDICATORS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN OFFENCE_INDICATORS.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN OFFENCE_INDICATORS.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN OFFENCE_INDICATORS.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN OFFENCE_INDICATORS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE OFFENCE_RESULT_CODES IS 'Defines codes used to record the status of each offence at each court hearing or breach / revocation proceeding.';
COMMENT ON COLUMN OFFENCE_RESULT_CODES.RESULT_CODE IS 'Unique offence result code.';
COMMENT ON COLUMN OFFENCE_RESULT_CODES.CHARGE_STATUS IS 'Defines the resultant status of an offender charge if this result is selected. Ref Domain ( CHARGE_STS )';
COMMENT ON COLUMN OFFENCE_RESULT_CODES.DESCRIPTION IS 'Description of the results.';
COMMENT ON COLUMN OFFENCE_RESULT_CODES.DISPOSITION_CODE IS 'Indicates if this offence result code is a Final (complete), Interim (incomplete) or Partial (partially complete) outcome. Final generally means sentenced or acquitted / dropped. Ref Domain ( OFF_RESULT )';
COMMENT ON COLUMN OFFENCE_RESULT_CODES.ACTIVE_FLAG IS 'A flag (Y/N) indicating whether the result code is currently available for use.';
COMMENT ON COLUMN OFFENCE_RESULT_CODES.CONVICTION_FLAG IS 'Indicates (Y/N) if this offence code means the offender has been convicted of the selected offence.';
COMMENT ON COLUMN OFFENCE_RESULT_CODES.EXPIRY_DATE IS 'The date the record was deactivated.';
COMMENT ON COLUMN OFFENCE_RESULT_CODES.LIST_SEQ IS 'Controls the Screen/LOV display sequence.';
COMMENT ON COLUMN OFFENCE_RESULT_CODES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN OFFENCE_RESULT_CODES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN OFFENCE_RESULT_CODES.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN OFFENCE_RESULT_CODES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN OFFENCE_RESULT_CODES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE ORDER_TYPES IS 'Defines court orders other than sentences (see SENTENCE_CALC_TYPES). Current product supports hold orders and court reports.';
COMMENT ON COLUMN ORDER_TYPES.ORDER_TYPE IS 'A unique code identifying the order type.';
COMMENT ON COLUMN ORDER_TYPES.ACTIVE_FLAG IS 'A flag (Y/N) indicating whether the order type is available for use.';
COMMENT ON COLUMN ORDER_TYPES.CHARGES_FLAG IS 'Not used.';
COMMENT ON COLUMN ORDER_TYPES.CUSTODY_FLAG IS 'Not used.';
COMMENT ON COLUMN ORDER_TYPES.DESCRIPTION IS 'Description of order type.';
COMMENT ON COLUMN ORDER_TYPES.SCHEDULE_FLAG IS 'Not used.';
COMMENT ON COLUMN ORDER_TYPES.TIME_SENSITIVE_FLAG IS 'Not used.';
COMMENT ON COLUMN ORDER_TYPES.UPDATE_ALLOWED_FLAG IS 'Not used.';
COMMENT ON COLUMN ORDER_TYPES.YOUTH_ORDER_FLAG IS 'Not used.';
COMMENT ON COLUMN ORDER_TYPES.CASELOAD_TYPE IS 'Not used.';
COMMENT ON COLUMN ORDER_TYPES.CUSTODY_DAYS IS 'Defines the number of days for which 
the order type authorises custody';
COMMENT ON COLUMN ORDER_TYPES.EXPIRY_DATE IS 'The date the order type was deactivated.';
COMMENT ON COLUMN ORDER_TYPES.LIST_SEQ IS 'Controls the Screen/LOV display sequence.';
COMMENT ON COLUMN ORDER_TYPES.NO_OF_HOLD_DAYS IS 'Not used.';
COMMENT ON COLUMN ORDER_TYPES.ORDER_CATEGORY IS 'Category of order type. Ref Domain ( ORD_CATEGORY )';
COMMENT ON COLUMN ORDER_TYPES.SEVERITY_RANK IS 'Not used.';
COMMENT ON COLUMN ORDER_TYPES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN ORDER_TYPES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN ORDER_TYPES.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN ORDER_TYPES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN ORDER_TYPES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE SENTENCE_ADJUSTMENTS IS 'Types of adjustments which can be applied to offender sentences. These adjustments are applied to reflect either Debits (increase) or Credits (decrease) to the overall sentence time.';
COMMENT ON COLUMN SENTENCE_ADJUSTMENTS.SENTENCE_ADJUST_CODE IS 'A unique code that identifies the sentence adjustment such as ''GT'', ''WT''';
COMMENT ON COLUMN SENTENCE_ADJUSTMENTS.DESCRIPTION IS 'The description of the above code such as ''Good Time'', ''Earned Time''';
COMMENT ON COLUMN SENTENCE_ADJUSTMENTS.ACTIVE_FLAG IS 'A flag (Y/N) indicating whether this sentence adjustment is available for use.';
COMMENT ON COLUMN SENTENCE_ADJUSTMENTS.DEBIT_CREDIT_CODE IS 'A code that Identifies whether the adjustment is a debit or credit. Ref Domain ( AC_TXN_POST )';
COMMENT ON COLUMN SENTENCE_ADJUSTMENTS.EXPIRY_DATE IS 'The date the sentence adjustment was deactivated.';
COMMENT ON COLUMN SENTENCE_ADJUSTMENTS.USAGE_CODE IS 'A code that indicates how the adjustment is applied or used, i.e., whether is it system generated, manual applied, applied against offences in custody, etc. Ref Domain ( USG_CODE )';
COMMENT ON COLUMN SENTENCE_ADJUSTMENTS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN SENTENCE_ADJUSTMENTS.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN SENTENCE_ADJUSTMENTS.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN SENTENCE_ADJUSTMENTS.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN SENTENCE_ADJUSTMENTS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE SENTENCE_CALC_TYPES IS 'Maintains all sentences (institutional and community), and the types of sentence calculation routine which will be used by the system.';
COMMENT ON COLUMN SENTENCE_CALC_TYPES.SENTENCE_CATEGORY IS 'A code that categorizes the sentence type. E.g. ''SENT'' (Sentences), ''WEND'' (Weekender), ''INV'' (Investigation). Ref Domain ( CATEGORY )';
COMMENT ON COLUMN SENTENCE_CALC_TYPES.SENTENCE_CALC_TYPE IS 'A unique code that identifies the type of sentence that needs to be calculated. E.g., ''''SENT'', ''WEND'', ''PAR''';
COMMENT ON COLUMN SENTENCE_CALC_TYPES.HEADER_SEQ IS 'Numeric value indicating 1) display this sentence in header block for each affected offender, and 2) if offender has more than one such sentence, which sentence to select (based on this numeric value, which must be unique - i.e. a particular value can apply to only one sentence in this table). Selection based on lowest value.';
COMMENT ON COLUMN SENTENCE_CALC_TYPES.ACTIVE_FLAG IS 'A flag (Y/N) indicating whether this sentence calculation type is available for use.';
COMMENT ON COLUMN SENTENCE_CALC_TYPES.DESCRIPTION IS 'Description for the type of sentence calculation. E.g., ''Sentence'', ''Weekenders'', ''Supervised Parole''';
COMMENT ON COLUMN SENTENCE_CALC_TYPES.SENTENCE_TYPE IS 'Identifies this sentence as custodial (INST), community (COMM) or BOTH. Ref Domain ( SENT_TYPE )';
COMMENT ON COLUMN SENTENCE_CALC_TYPES.EXPIRY_DATE IS 'The date the sentence calculation type was deactivated.';
COMMENT ON COLUMN SENTENCE_CALC_TYPES.FUNCTION_TYPE IS 'Identifies the Team to which this sentence / parole release order is assigned (for community supervision). Ref Domain ( FUNCTION )';
COMMENT ON COLUMN SENTENCE_CALC_TYPES.HEADER_LABEL IS 'If HEADER_SEQ indicates this sentence should appear in the header block for the selected offender, indicates the static value to display in the header.';
COMMENT ON COLUMN SENTENCE_CALC_TYPES.LIST_SEQ IS 'Controls the Screen/LOV display sequence.';
COMMENT ON COLUMN SENTENCE_CALC_TYPES.PROGRAM_METHOD IS 'Indicates that this sentence compels the offender to take a type of program (accredited, community service, etc.). Ref Domain ( PS_CATEGORY )';
COMMENT ON COLUMN SENTENCE_CALC_TYPES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN SENTENCE_CALC_TYPES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN SENTENCE_CALC_TYPES.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN SENTENCE_CALC_TYPES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN SENTENCE_CALC_TYPES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE SENTENCE_TERMS IS 'Defines the type(s) of terms applicable to a selected sentence type.  Child to SENTENCE_CALC_TYPES.  Each sentence must have at least one associated TERM_CODE.';
COMMENT ON COLUMN SENTENCE_TERMS.SENTENCE_CATEGORY IS 'A code that identifies the sentence category from the parent sentence calculation type.';
COMMENT ON COLUMN SENTENCE_TERMS.SENTENCE_CALC_TYPE IS 'A code that identifies the parent sentence calculation type.';
COMMENT ON COLUMN SENTENCE_TERMS.TERM_CODE IS 'A code that identifies the type of sentence term. E.g. ''STR'' (Straight Sentence), ''INT'' (Intermittent Sentence), ''MAX'' (Maximum). Ref Domain ( SENT_TERM )';
COMMENT ON COLUMN SENTENCE_TERMS.ACTIVE_FLAG IS 'A flag (Y/N) to indicate whether the sentence or request type is available for use.';
COMMENT ON COLUMN SENTENCE_TERMS.EXPIRY_DATE IS 'The date the sentence or request type was deactivated.';
COMMENT ON COLUMN SENTENCE_TERMS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN SENTENCE_TERMS.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN SENTENCE_TERMS.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN SENTENCE_TERMS.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN SENTENCE_TERMS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE SENTENCE_UPDATE_REASONS IS 'Defines valid status codes for each offender sentence, and valid reasons for each status change.';
COMMENT ON COLUMN SENTENCE_UPDATE_REASONS.SENTENCE_CATEGORY IS 'A code that identifies the sentence category from the parent sentence calculation type.';
COMMENT ON COLUMN SENTENCE_UPDATE_REASONS.SENTENCE_CALC_TYPE IS 'A code that identifies the parent sentence calculation type.';
COMMENT ON COLUMN SENTENCE_UPDATE_REASONS.UPDATE_REASON_CODE IS 'A code identifying the parent reason for changing offender sentence status.';
COMMENT ON COLUMN SENTENCE_UPDATE_REASONS.LEGAL_CLASS IS 'A code identifying the parent legal class for this update reason. Always ''SENTENCE'' for sentences.';
COMMENT ON COLUMN SENTENCE_UPDATE_REASONS.ACTIVE_FLAG IS 'A flag (Y/N) to indicate whether the sentence update reason is available for use.';
COMMENT ON COLUMN SENTENCE_UPDATE_REASONS.DESCRIPTION IS 'Description of selected reason code, editable for each individual sentence.';
COMMENT ON COLUMN SENTENCE_UPDATE_REASONS.EXPIRY_DATE IS 'The date the reason code was deactivated.';
COMMENT ON COLUMN SENTENCE_UPDATE_REASONS.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN SENTENCE_UPDATE_REASONS.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN SENTENCE_UPDATE_REASONS.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN SENTENCE_UPDATE_REASONS.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN SENTENCE_UPDATE_REASONS.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
COMMENT ON TABLE STATUTES IS 'Maintains categorization of offences by source of legislation.';
COMMENT ON COLUMN STATUTES.STATUTE_CODE IS 'The unique code that identifies the legal statute.';
COMMENT ON COLUMN STATUTES.ACTIVE_FLAG IS 'A flag (Y/N) indicating whether the statute is available for use.';
COMMENT ON COLUMN STATUTES.DESCRIPTION IS 'The description of the legal statute.';
COMMENT ON COLUMN STATUTES.LEGISLATING_BODY_CODE IS 'A code identifying the legislating body. Ref Domain ( LEGISL BODY )';
COMMENT ON COLUMN STATUTES.UPDATE_ALLOWED_FLAG IS 'A flag (Y/N) to indicate whether the statute record may be updated. Not used.';
COMMENT ON COLUMN STATUTES.EXPIRY_DATE IS 'The date the statute was deactivated.';
COMMENT ON COLUMN STATUTES.LIST_SEQ IS 'Controls the Screen/LOV display sequence.';
COMMENT ON COLUMN STATUTES.CREATE_DATETIME IS 'The timestamp when the record was created';
COMMENT ON COLUMN STATUTES.CREATE_USER_ID IS 'The user account that created the record';
COMMENT ON COLUMN STATUTES.MODIFY_DATETIME IS 'The timestamp when the record was last modified ';
COMMENT ON COLUMN STATUTES.MODIFY_USER_ID IS 'The user account that last modified the record';
COMMENT ON COLUMN STATUTES.SEAL_FLAG IS 'Flag to indicate whether the record has been sealed or not (Y/N).';
