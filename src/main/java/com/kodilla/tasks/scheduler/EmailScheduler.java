package com.kodilla.tasks.scheduler;

import com.kodilla.tasks.config.AdminConfig;
import com.kodilla.tasks.domain.Mail;
import com.kodilla.tasks.repository.TaskRepository;
import com.kodilla.tasks.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;
    private static final String SUBJECT = "Tasks: Once a day email";

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String message;

        if (size == 1) {
            message = "Currently in database you got: " + size + " task.";
        } else {
            message = "Currently in database you got: " + size + " tasks.";
        }

        simpleEmailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        message,
                        null
                )
        );
    }
}
