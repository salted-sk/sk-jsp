package com.sk.jsp.springjobs;

import java.util.List;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.base.util.Constant;
import com.sk.jsp.entity.CesQuestionGroup;
import com.sk.jsp.entity.CesStatisticsGroupInfo;
import com.sk.jsp.service.CesQuestionGroupService;
import com.sk.jsp.service.CesStatisticsService;

@Component
public class InitSystemConfig {
	@Autowired
	private CesStatisticsService cesStatisticsService;
	@Autowired
	private CesQuestionGroupService cesQuestionGroupService;

	@PostConstruct
	public void init() {
		initStatisticsCategory();
	}

	private void initStatisticsCategory() {
		try {
			String[][] groupScore = Constant.STAT_GROUP_ID_SCORE_ARR;
			for (String[] gs : groupScore) {
				String groupId = gs[0];
				CesQuestionGroup group = cesQuestionGroupService.getCesQuestionGroup(groupId);
				CesStatisticsGroupInfo csgo = new CesStatisticsGroupInfo();
				csgo.setGroupId(groupId);
				csgo.setMaxScore(str2Double(gs[1]));
				csgo.setGroupName(group.getName());
				List<String> list = cesStatisticsService.listQuestionId(groupId);
				csgo.setListQuestionId(list);
				Constant.LIST_STAT_GROUP_INFO.add(csgo);
			}
		} catch (Exception e) {
			System.out.println("初始化统计项失败。" + e);
		}
	}

	private double str2Double(String str) {
		double d = 0;
		try {
			d = Double.parseDouble(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

}
