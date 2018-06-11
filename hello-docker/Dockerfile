FROM debian:9.3

LABEL maintainer="mail@sebastian-daschner.com"

COPY hello.txt /

RUN cat /hello.txt

RUN echo goodbye > /hello.txt

CMD ["/bin/cat", "/hello.txt"]
