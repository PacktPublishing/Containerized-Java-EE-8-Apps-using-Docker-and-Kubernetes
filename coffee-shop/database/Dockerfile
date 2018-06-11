FROM postgres:9.5

ENV POSTGRES_USER=postgres \
    POSTGRES_PASSWORD=postgres
    PGDATA=/var/lib/postgresql/data/pgdata

COPY create-tables.sql /docker-entrypoint-initdb.d/
