#!/usr/bin/env bash
docker run -d --name=grafana -p 3000:3000 fg2it/grafana-armhf:v4.0.2
docker run -itd -p 2003:2003 -p 8125:8125 -p 8888:8888 -p 8125:8125/udp --name stats leozilla/graphite-statsd
