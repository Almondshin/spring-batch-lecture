package io.springbatch.springbatchlecture;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class StartNextConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job batchJob() {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(reChunkSizeStep1())
                .next(step2())
                .build();
    }
    @Bean
    public Job batchJob2() {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(reChunkSizeStep1())
                .next(step2())
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> step2 has executed");
                    System.out.println("contribution.getStepExecution(): " + contribution.getStepExecution());
                    return RepeatStatus.FINISHED;
                })
                .allowStartIfComplete(true)
                .build();
    }

    @Value("${batch.job.chunkSize}")
    private int chunkSize;

    @Bean
    @JobScope
    public Step reChunkSizeStep1(){
        return stepBuilderFactory.get("reChunkSizeStep1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println(">> reChunkSizeStep1 has executed");
                    int chunkSizeValue = chunkSize;
                    System.out.println("Chunk Size: " + chunkSizeValue);
                    return RepeatStatus.FINISHED;
                })
                .allowStartIfComplete(true)
                .build();
    }


    @Bean
    @JobScope
    public Step reChunkSizeStep2(@Value("#{jobParameters[chunkSize]}") String chunkSize){
        return stepBuilderFactory.get("reChunkSizeStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        int chunkSizeValue = Integer.parseInt(chunkSize);
                        // chunkSizeValue를 사용하여 필요한 작업 수행
                        System.out.println("Chunk Size: " + chunkSizeValue);
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

}
