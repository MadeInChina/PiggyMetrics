#!/usr/bin/env bash
wrk -t1 -c10 -d30s --latency 'http://localhost:4000/accounts/current' -H 'Authorization: Bearer 76439118-4479-4063-9c4f-91739ee5144b'