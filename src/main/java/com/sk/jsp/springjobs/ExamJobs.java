package com.sk.jsp.springjobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.sk.jsp.service.CesUserExamService;

@Component
public class ExamJobs {
	@Autowired
	private CesUserExamService userExamService;

	/**
	 * 0 0 1 * * ? 每天凌晨1点执行一次
	 */
	@Scheduled(cron = "0 0 1 * * ?")
	public void doExamJob() {
		userExamService.updateUserExam();
		System.out.println("每天更新测评任务状态。");
	}
}
