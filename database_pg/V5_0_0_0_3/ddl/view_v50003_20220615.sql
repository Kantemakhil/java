CREATE OR REPLACE VIEW oms_owner.v_living_unit_off_bkgs
AS SELECT lu.agy_loc_id,
    lu.living_unit_id,
    lu.description AS living_unit_desc,
    lu.parent_living_unit_id,
    lu.root_living_unit_id,
    bkg.living_unit_id AS in_living_unit_id,
    bkg.agency_iml_id AS in_internal_location_id,
    bkg.in_out_status,
    bkg.active_flag,
    bkg.offender_id,
    bkg.offender_book_id
   FROM ( WITH RECURSIVE a AS (
                 SELECT lu1.agy_loc_id,
                    lu1.living_unit_id AS root_living_unit_id,
                    lu1.living_unit_id,
                    lu1.parent_living_unit_id,
                    lu1.description
                   FROM living_units lu1
                UNION ALL
                 SELECT d.agy_loc_id,
                    a_1.root_living_unit_id,
                    d.living_unit_id,
                    d.parent_living_unit_id,
                    d.description
                   FROM living_units d
                     JOIN a a_1 ON a_1.living_unit_id = d.parent_living_unit_id
                )
         SELECT a.agy_loc_id,
            a.root_living_unit_id,
            a.living_unit_id,
            a.parent_living_unit_id,
            a.description
           FROM a) lu
     LEFT JOIN offender_bookings bkg ON lu.living_unit_id = bkg.living_unit_id AND lu.agy_loc_id::text = bkg.agy_loc_id::text;
	 

CREATE OR REPLACE VIEW oms_owner.v_living_unit_rolls_2
AS WITH RECURSIVE cte AS (
         SELECT ail.agy_loc_id,
            ail.internal_location_id AS root_living_unit_id,
            ail.internal_location_id,
            ail.parent_internal_location_id,
                CASE
                    WHEN COALESCE(bkg.living_unit_id::text, ''::text) = ''::text THEN 0
                    ELSE 1
                END AS "case",
                CASE
                    WHEN COALESCE(bkg.agency_iml_id::text, ''::text) = ''::text THEN
                    CASE
                        WHEN bkg.in_out_status::text = 'IN'::text THEN 1
                        ELSE 0
                    END
                    ELSE 0
                END AS "case",
                CASE
                    WHEN COALESCE(bkg.agency_iml_id::text, ''::text) = ''::text THEN 0
                    ELSE
                    CASE
                        WHEN bkg.in_out_status::text = 'IN'::text THEN 1
                        ELSE 0
                    END
                END AS "case",
                CASE
                    WHEN bkg.in_out_status::text = 'OUT'::text THEN 1
                    ELSE 0
                END AS "case",
            COALESCE(res.res_count, 0::bigint) AS "coalesce"
           FROM agency_internal_locations ail
             LEFT JOIN offender_bookings bkg ON ail.internal_location_id = bkg.living_unit_id AND ail.agy_loc_id::text = bkg.agy_loc_id::text
             LEFT JOIN ( SELECT reserve_bed_locations.living_unit_id,
                    count(*) AS res_count
                   FROM reserve_bed_locations
                  WHERE reserve_bed_locations.reserve_until_date >= date_trunc('day'::text, LOCALTIMESTAMP)
                  GROUP BY reserve_bed_locations.living_unit_id) res ON ail.internal_location_id = res.living_unit_id
          WHERE ail.unit_type IS NOT NULL AND ail.unit_type::text <> ''::text
        UNION ALL
         SELECT ail.agy_loc_id,
            c.root_living_unit_id,
            ail.internal_location_id,
            ail.parent_internal_location_id,
                CASE
                    WHEN COALESCE(bkg.living_unit_id::text, ''::text) = ''::text THEN 0
                    ELSE 1
                END AS "case",
                CASE
                    WHEN COALESCE(bkg.agency_iml_id::text, ''::text) = ''::text THEN
                    CASE
                        WHEN bkg.in_out_status::text = 'IN'::text THEN 1
                        ELSE 0
                    END
                    ELSE 0
                END AS "case",
                CASE
                    WHEN COALESCE(bkg.agency_iml_id::text, ''::text) = ''::text THEN 0
                    ELSE
                    CASE
                        WHEN bkg.in_out_status::text = 'IN'::text THEN 1
                        ELSE 0
                    END
                END AS "case",
                CASE
                    WHEN bkg.in_out_status::text = 'OUT'::text THEN 1
                    ELSE 0
                END AS "case",
            COALESCE(res.res_count, 0::bigint) AS "coalesce"
           FROM agency_internal_locations ail
             LEFT JOIN offender_bookings bkg ON ail.internal_location_id = bkg.living_unit_id AND ail.agy_loc_id::text = bkg.agy_loc_id::text
             LEFT JOIN ( SELECT reserve_bed_locations.living_unit_id,
                    count(*) AS res_count
                   FROM reserve_bed_locations
                  WHERE reserve_bed_locations.reserve_until_date >= date_trunc('day'::text, LOCALTIMESTAMP)
                  GROUP BY reserve_bed_locations.living_unit_id) res ON ail.internal_location_id = res.living_unit_id AND ail.unit_type IS NOT NULL AND ail.unit_type::text <> ''::text
             JOIN cte c(agy_loc_id, root_living_unit_id, internal_location_id, parent_internal_location_id, "case", case_1, case_2, case_3, "coalesce") ON c.internal_location_id = ail.parent_internal_location_id
        )
 SELECT cte.agy_loc_id,
    cte.root_living_unit_id,
    cte.internal_location_id AS living_unit_id,
    cte.parent_internal_location_id AS parent_living_unit_id,
    cte."case" AS allocated,
    cte.case_1 AS in_living_units,
    cte.case_2 AS out_of_living_units,
    cte.case_3 AS out_of_agy,
    cte."coalesce" AS reserved_beds
   FROM cte cte(agy_loc_id, root_living_unit_id, internal_location_id, parent_internal_location_id, "case", case_1, case_2, case_3, "coalesce");
   
