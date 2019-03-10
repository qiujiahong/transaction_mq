#!/usr/bin/env bash

curl -X POST -d '{"customerId":1,"ticketNum": 100}' -H 'Content-Type: application/json' http://localhost:8083/api/ticket/lock