
OIUSSTRI_GET_SCHEDULED_TRIPS_DATA{
SELECT * FROM v_oiusstri WHERE departure_date BETWEEN TO_DATE(:fromDepartureDate, 'YYYY-MM-DD hh24:mi:ss') AND TO_DATE(:toDepartureDate, 'YYYY-MM-DD hh24:mi:ss') OR departure_date=:departureDate
}

