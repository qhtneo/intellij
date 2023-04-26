package com.project.trip.service;

public interface EmailService {
    String sendSimpleMessage(String to)throws Exception;
    String sendFindPasswordMessage(String to)throws Exception;
}
