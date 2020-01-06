package com.itycu.server.service;

import java.util.List;

import com.itycu.server.model.Mail;

public interface MailService {

	void save(Mail mail, List<String> toUser);
}
