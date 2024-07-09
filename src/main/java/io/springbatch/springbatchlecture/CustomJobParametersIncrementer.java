package io.springbatch.springbatchlecture;

import org.springframework.batch.core.*;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomJobParametersIncrementer implements JobParametersIncrementer {

    static final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");

    @Override
    public JobParameters getNext(JobParameters jobParameters) {
        String id = formatter.format(new Date());
        return new JobParametersBuilder().addString("run.id", id).toJobParameters();
    }
}
