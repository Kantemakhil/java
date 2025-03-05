update
	oms_owner.system_settings
set
	setting_value = '[
    {
        "KEY_DESC": "ENABLE NEW TAB",
        "KEY_CODE": "ENABLE_NEWTAB",
        "VALUE": "N"
    },
    {
        "KEY_DESC": "AUTOSAVE TIME",
        "KEY_CODE": "AUTOSAVE_TIME",
        "VALUE": "6000"
    },
    {
        "KEY_DESC": "PDF VIEWER SERVICE",
        "KEY_CODE": "PDF_SERVICE",
        "VALUE": "https://pdfviewer.dev.elitev5.com/api/pdfviewer"
    },
    {
        "KEY_DESC": "ENABLE FORM FIELDS",
        "KEY_CODE": "ENABLE_FORM_FIELDS",
        "VALUE": "Y"
    }
]',
	modify_user_id = 'OMS_OWNER',
	modify_datetime = current_timestamp
where
	setting_type = 'EliteDoc'
	and setting_provider_code = 'ELITE_DOC';