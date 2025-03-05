update oms_owner.json_spec 
 set   modify_user_id = 'OMS_OWNER'
	   ,create_user_id = 'OMS_OWNER'; 

update oms_owner.json_spec 
 set   modify_datetime = current_timestamp
       ,modify_user_id = 'OMS_OWNER' 
       ,json_specs = '[
  {
    "operation": "shift",
    "spec": {
      "PrisonerDetails":  {
        "*": {
          "eliteoffenderid": "prisonerDetails.eliteOffenderId",
          "givenname": "prisonerDetails.givenName",
          "othergivennames": "prisonerDetails.otherGivenNames",
          "familyname": "prisonerDetails.familyName",
          "dateofbirth": "prisonerDetails.dateOfBirth",
		  "datetime": "astriaMessageHeader.messageCreationTimestamp"
		 
		   
        }
      },
	  "MessageId":{
       "*": {
         "messageid": ["astriaMessageHeader.correlationId","astriaMessageHeader.messageId"]
        }

      },
       "AstriaPersonId": {
        "*": {
          "astriapersonid": "prisonerDetails.astriaPersonId"
        }
      },
      "PrisonerAliasDetails": {
        "*": {
          "aliastypecode": "prisonerDetails.aliases[#2].aliasTypeCode",
          "familyname": "prisonerDetails.aliases[#2].familyName",
          "givenname": "prisonerDetails.aliases[#2].givenName",
          "othergivennames": "prisonerDetails.aliases[#2].otherGivenNames",
          "dateofbirth": "prisonerDetails.aliases[#2].dateOfBirth"
        }
      },
       "PrisonerIdentifierDetails":  {
        "*": {
          "identifiertypecode": "prisonerDetails.additionalIdentifiers[#2].identifierTypeCode",
          "identifier": "prisonerDetails.additionalIdentifiers[#2].identifier",
          "issuer": "prisonerDetails.additionalIdentifiers[#2].issuer"
        }
      },
      "PrisonerHousingDetails": {
        "*": {
          "newhousingdateandtime": "prisonerEventDetails.prisonerHousing.newHousingDateAndTime",
          "newhousinglevelonecode": "prisonerEventDetails.prisonerHousing.newHousingLevelOneCode",
          "newhousinglevelonetypecode": "prisonerEventDetails.prisonerHousing.newHousingLevelOneTypeCode",
          "newhousingleveltwocode": "prisonerEventDetails.prisonerHousing.newHousingLevelTwoCode",
          "newhousingleveltwotypecode": "prisonerEventDetails.prisonerHousing.newHousingLevelTwoTypeCode",
          "newhousinglevelthreecode": "prisonerEventDetails.prisonerHousing.newHousingLevelThreeCode",
          "newhousinglevelthreetypecode": "prisonerEventDetails.prisonerHousing.newHousingLevelThreeTypeCode",
          "newhousinglevelfourcode": "prisonerEventDetails.prisonerHousing.newHousingLevelFourCode",
          "newhousinglevelfourtypecode": "prisonerEventDetails.prisonerHousing.newHousingLevelFourTypeCode",
          "newhousinglocationcode": "prisonerEventDetails.prisonerHousing.newHousingLocationCode",
          "newhousingfacilitycode": "prisonerEventDetails.prisonerHousing.newHousingFacilityCode"
        }
      }
    }
  },
  {
    "operation": "default",
    "spec": {
      "astriaMessageHeader": {
        "sourceSystem": "Elite",
        "messageType": "Prisoner Event",
        "messageTypeCode": "Elite-PrEvent",
        "messageTypeVersion": "1"
        
      },
      "prisonerEventDetails": {
        "eventType": "Prisoner_Housing"
      }
    }
  },
  {
  "operation": "sort"
  }

  ]
'::bytea 
 where spec_key = 'TRANSFORM_HOUSING';
