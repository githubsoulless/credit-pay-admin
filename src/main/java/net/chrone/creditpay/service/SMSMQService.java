package net.chrone.creditpay.service;

public interface SMSMQService {
    int produce(Object message);
}