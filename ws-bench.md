Websocket load generation
=========================

# TCP Kali

```bash
$ tcpkali -c 50 -r 1000 -T 60s --websocket --latency-marker "pong" -vvv --message "ping" localhost:8080
$ tcpkali -c 50 -r 1000 -T 60s --websocket --latency-marker "pong" -vvv --statsd --statsd-host 10.0.0.200 --message "ping" localhost:8080
```