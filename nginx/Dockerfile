FROM nginx:stable-alpine
#FROM owasp/modsecurity-crs:3.3-nginx-alpine-202304160904

ARG DOMAIN
ARG PORT

RUN apk update && apk add libressl-dev libffi-dev tzdata

COPY ./nginx.conf.template /etc/nginx/nginx.conf.template
COPY ./conf.d/default.conf.template /etc/nginx/conf.d/default.conf.template

RUN envsubst '$$DOMAIN $$PORT' < /etc/nginx/nginx.conf.template > /etc/nginx/nginx.conf ; \
    envsubst '$$DOMAIN $$PORT' < /etc/nginx/conf.d/default.conf.template > /etc/nginx/conf.d/default.conf ;