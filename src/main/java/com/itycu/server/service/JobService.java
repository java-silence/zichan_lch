package com.itycu.server.service;

import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import com.itycu.server.model.JobModel;

public interface JobService {

	void saveJob(JobModel jobModel);

	void doJob(JobDataMap jobDataMap);

	void deleteJob(Long id) throws SchedulerException;

}
