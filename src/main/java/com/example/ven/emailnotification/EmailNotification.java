package com.example.ven.emailnotification;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.example.ven.vaccination.Vaccination;
import com.example.ven.vaccination.vm.ResponseVM;

@Component
public class EmailNotification {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Vaccination vaccination;
	private String dateFormat = "dd-MM-yyyy";
	
	public static final Logger logger = LoggerFactory.getLogger(EmailNotification.class);
	
	@Scheduled(fixedDelay = 5000)
	public void emailSender() {
		List<String> dates = new ArrayList<>();
		dates.add(new SimpleDateFormat(dateFormat).format(new Date()));
		for(int days = 1;days <= 7;days++) {
			String dateInString = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
			dates.add(dateInString);
		}
		for(String date:dates) {
			
			List<ResponseVM> responseVM = vaccination.getslots(date,18);
			if (ObjectUtils.isEmpty(responseVM)) {
				logger.error("No slots found on {}",date);
			} else {

				String body = "";
				int index = 1;
				for (ResponseVM response : responseVM) {
					body = body + index++ + "." + response.toString() + "\n";
				}

				SimpleMailMessage message = new SimpleMailMessage();
				message.setFrom("lolbaba.lb@gmail.com");
				message.setTo("tanmayshakya98@gmail.com");
				message.setSubject("Covid - 19 Vaccination " + responseVM.size() + " slots");
				message.setText(body);

				javaMailSender.send(message);
				logger.error("{} Slots found {}",responseVM.size(),date);
			}
		}
		
	}
}
