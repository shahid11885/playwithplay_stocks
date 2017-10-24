#!/bin/sh -x

JARS=lib/etws-common-connections-1.0.jar:lib/commons-codec-1.10.jar:lib/commons-lang-2.6.jar:lib/etws-order-sdk-1.0.jar:lib/commons-httpclient-3.1.jar:lib/commons-logging-1.2.jar:lib/etws-market-sdk-1.0.jar:lib/log4j-1.2.17.jar:lib/commons-httpclient-contrib-ssl-3.1.jar:lib/etws-accounts-sdk-1.0.jar:lib/etws-oauth-sdk-1.0.jar:lib/xstream-1.3.1.jar

java -cp $JARS:./target/scala-2.12/classes services.EtradeTestService $@

