//package io.springbatch.springbatchlecture;
//
//import lombok.Data;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.JobParametersInvalidException;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.launch.support.SimpleJobLauncher;
//import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
//import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
//import org.springframework.batch.core.repository.JobRestartException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//
//@RestController
//public class JobLaunchingController {
//
//    @Autowired
//    private Job job;
//
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private BasicBatchConfigurer basicBatchConfigurer;
//
//    @PostMapping("/batch")
//    public String launchJob(@RequestBody BatchDto batchDto) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
//
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addLong("chunkSize", batchDto.getChunkSize())
//                .toJobParameters();
//
//        SimpleJobLauncher launcher = (SimpleJobLauncher) basicBatchConfigurer.getJobLauncher();
//        launcher.setTaskExecutor(new SimpleAsyncTaskExecutor());
//        jobLauncher.run(job, jobParameters);
//
//        System.out.println("Job is completed");
//
//        return "batch Completed";
//    }
//
//    @Data
//    public static class BatchDto {
//        private String id;
//        private Long chunkSize;
//    }
//
//}
//
