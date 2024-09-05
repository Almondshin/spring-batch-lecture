package io.springbatch.springbatchlecture;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchLectureApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchLectureApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(JobLauncher jobLauncher, Job exampleJob) {
//        return args -> {
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addString("chunkSize", "20") // chunkSize 설정
//                    .toJobParameters();
//
//            jobLauncher.run(exampleJob, jobParameters);
//        };
//    }

}
