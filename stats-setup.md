Setting up stats collection
===========================

Running statsd, graphite and grafana on Raspberry Pi 3 Model B.

Runs 2 docker containers, one hosting statsd and graphite another one running grafana 4.0.2.

First container is a customization of: https://github.com/Darkeye9/rpi2-graphite-grafana-statsd which removes grafana 2
Will be pushed to dockerhub eventually (not sure if it is useful to anyone).

```bash
$ docker run -d --name=grafana -p 3000:3000 fg2it/grafana-armhf:v4.0.2
$ docker run -itd -p 2003:2003 -p 8125:8125 -p 8888:8888 -p 8125:8125/udp --name stats leozilla/graphite-statsd
```