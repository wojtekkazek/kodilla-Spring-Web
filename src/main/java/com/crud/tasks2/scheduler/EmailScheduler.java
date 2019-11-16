package com.crud.tasks2.scheduler;

import com.crud.tasks2.config.AdminConfig;
import com.crud.tasks2.domain.Mail;
import com.crud.tasks2.repository.TaskRepository;
import com.crud.tasks2.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

//    @Scheduled(cron = "0 0 10 * * *")
    @Scheduled(fixedDelay = 10000)
    public void sendInformation() {
        long size = taskRepository.count();
        String s;
        if(size>0) {
            s = "s";
        } else {
            s = "";
        }
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "Currently in database you got: " + size + " task" + s,
                null));
    }
}
