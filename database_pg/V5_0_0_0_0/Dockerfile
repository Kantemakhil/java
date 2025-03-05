FROM postgres:13.5
USER postgres
RUN mkdir -p /var/lib/postgresql/client_scripts
ADD . /var/lib/postgresql/client_scripts
ADD scripts_execute.sh /docker-entrypoint-initdb.d/
RUN mkdir -p /var/lib/postgresql/postgresql_data
EXPOSE 5432
