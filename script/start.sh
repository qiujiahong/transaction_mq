#!/usr/bin/env bash


docker run --name='activemq' -d --rm -p 61616:61616 -p 8161:8161 webcenter/activemq:latest
