package ru.yaroglek.naujava.practice3.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.yaroglek.naujava.practice3.app.repository.ReportRepository;
import ru.yaroglek.naujava.practice3.app.repository.TaskRepository;
import ru.yaroglek.naujava.practice3.app.repository.UserRepository;
import ru.yaroglek.naujava.practice3.domain.Report;
import ru.yaroglek.naujava.practice3.domain.Task;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public Long createReport() {
        Report report = Report.builder()
                .status(Report.Status.CREATED)
                .content("")
                .build();
        report = reportRepository.save(report);
        return report.getId();
    }

    public String getReportContent(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));

        return switch (report.getStatus()) {
            case CREATED -> "Report is still being generated.";
            case ERROR -> "Error during report generation.";
            default -> report.getContent();
        };
    }

    @Async
    public void formReport(long id) {
        CompletableFuture.runAsync(() -> {
            try {
                Report report = reportRepository.findById(id).orElseThrow();

                long startTime = System.currentTimeMillis();
                final long[] timers = new long[2];

                final long[] userCount = new long[1];
                Thread countUsers = new Thread(() -> {
                    long start = System.currentTimeMillis();
                    userCount[0] = userRepository.count();
                    long elapsed = System.currentTimeMillis() - start;
                    timers[0] = elapsed;
                });

                final List<Task>[] allTasks = new List[1];
                Thread getTasks = new Thread(() -> {
                    long start = System.currentTimeMillis();
                    allTasks[0] = taskRepository.findAll();
                    long elapsed = System.currentTimeMillis() - start;
                    timers[1] = elapsed;
                });

                countUsers.start();
                getTasks.start();
                countUsers.join();
                getTasks.join();

                long elapsed = (System.currentTimeMillis() - startTime);

                StringBuilder html = new StringBuilder();
                html.append("<html><body>");
                html.append("<h1>Отчет</h1>");
                html.append("<p>Количество пользователей: ").append(userCount[0]).append("</p>");
                html.append("<p>Время, затраченное на нахождение числа пользователей: ").append(timers[0]).append(" мс</p>");
                html.append("<h2>Список задач</h2>");
                html.append("<table border='1'><tr><th>ID</th><th>Заголовок</th><th>Описание</th></tr>");
                for (Task task : allTasks[0]) {
                    html.append("<tr>")
                            .append("<td>").append(task.getId()).append("</td>")
                            .append("<td>").append(task.getTitle()).append("</td>")
                            .append("<td>").append(task.getDescription()).append("</td>")
                            .append("</tr>");
                }
                html.append("</table>");
                html.append("<p>Время, затраченное на нахождение всех задач: ").append(timers[1]).append(" мс</p>");
                html.append("<p>Общее время формирования отчета: ").append(elapsed).append(" мс</p>");
                html.append("</body></html>");

//                String sb = "Total user: " + userCount[0] + ". Elapsed time: " + timers[0] + "\n" +
//                        "All tasks: " + allTasks[0] + ". Elapsed time: " + timers[1] + "\n" +
//                        "Overall time: " + elapsed;

                report.setContent(html.toString());
                report.setStatus(Report.Status.COMPLETED);
                reportRepository.save(report);
            } catch (Exception e) {
                Report report = reportRepository.findById(id).orElseThrow();
                report.setStatus(Report.Status.ERROR);
                reportRepository.save(report);
            }
        });
    }
}
