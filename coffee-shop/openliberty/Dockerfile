FROM java:9-b149-jdk
LABEL maintainer="mail@sebastian-daschner.com"

# change version to current builds
ENV RELEASE 2018-01-11_-0152
ENV VERSION 17.0.0.4-20180111-0144

RUN wget https://public.dhe.ibm.com/ibmdl/export/pub/software/openliberty/runtime/nightly/${RELEASE}/openliberty-all-${VERSION}.zip \
    && unzip openliberty-all-${VERSION}.zip -d /opt \
    && rm openliberty-all-${VERSION}.zip

ENV DEPLOYMENT_DIR /opt/wlp/usr/servers/defaultServer/dropins/
ENV CONFIG_DIR /opt/wlp/usr/servers/defaultServer/

COPY server.xml $CONFIG_DIR
COPY postgresql-9.4-1201.jar /opt/wlp/lib/

CMD ["/opt/wlp/bin/server", "run", "defaultServer"]
