#!/bin/bash

docker build -t transfer-mock-web-service --file docker/mws/MockWebService.Dockerfile .
docker build -t transfer-payment-microservice --file docker/pm/PaymentTransferMicroservice.Dockerfile .
docker build -t transfer-withdrawal-microservice --file docker/wm/WithdrawalMicroservice.Dockerfile .
docker build -t transfer-deposit-microservice --file docker/dm/DepositMicroservice.Dockerfile .     