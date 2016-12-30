#!/usr/bin/env bash
tcpkali -c 1 -r 1000 --websocket --latency-marker "pong" --statsd --statsd-host 10.0.0.15 --message "ping" localhost:8080