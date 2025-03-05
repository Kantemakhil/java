# Use official Tomcat runtime as a parent image
FROM tomcat:8.5-alpine
LABEL maintainer="wellington.souza@syscon.net"

USER root
RUN apk add ttf-opensans

ADD docker /assets
ADD .build-artifacts  /build-artifacts

RUN chmod +x  /assets/*.sh
RUN /assets/setup.sh

EXPOSE 8080

USER tomcat

CMD ["/assets/entrypoint.sh"]

