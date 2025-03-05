create or replace function oms_owner.oms_utils_combine_date_time (p_date timestamp,
p_time timestamp) returns timestamp as $body$
declare

   v_return_datetime timestamp;

begin
   v_return_datetime :=
      to_timestamp(to_char(date_TRUNC ('D', p_date)+(p_time - date_trunc ('D', p_time)), 'DD-MM-YYYY HH24:MI:SS' ), 'DD-MM-YYYY HH24:MI:SS' );

return v_return_datetime;

exception
when others then
raise notice '% %',
sqlerrm,
sqlstate;
end;

$body$
language PLPGSQL
;