package com.base.util;

import java.util.ArrayList;
import java.util.List;
import com.sk.jsp.entity.CesStatisticsGroupInfo;

/**
 * 静态工具类
 *
 * @author zhangqiao
 * @since 2018-12-28
 */
public class Constant {

	public static Integer DEFAULT_PAGE_SIZE = 10;

	public static String RESPONSE_OK = "ok";
	public static String RESPONSE_ERROR = "error";

	public static String SUCCESS_MESSAGE = "成功";
	public static String FAILURE_MESSAGE = "失败";

	public static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DEFAULT_TIME_FORMAT_NO_SPACE = "yyyyMMddHHmmss";
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	public static final String DEFAULT_MONTH_FORMAT = "yyyy-MM";
	public static final String DEFAULT_YEAR_FORMAT = "yyyy";
	public static final String DEFAULT_TIME_FORMAT_NO_DATE = "HH:mm:ss";
	public static final String REG_EMAIL = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
	public static final String REG_TELEPHONE = "^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\\d{8}$";
	public static final String REG_VALIDATECODE = "^\\d{6}$";
	public static final String REG_PWD = "^[A-Za-z0-9]{6,20}$";
	public static final String CONTENT_TYPE = "image/png;charset=UTF-8";
	public static final String ENCODING = "UTF-8";
	public static final String LINE = "\r\n";
	public static final String SINGLETON_COMMENT = "//";
	public static final String NULL = "null";
	public static final String DOT = ".";
	public static final String EQUAL = "=";
	public static final String QUESTION = "?";
	public static final String NOT_EQUAL = "!=";
	public static final String BLANK = " ";
	public static final String IMG_URL = "vendor/images/photo/";

	public static final String RETURN = "return";

	public final static String NODE_NAME = "根节点";

	public static final String BMS_JDBC_NAME = "ysx_bms";

	// 0:未填写，1：已填写，2：未到填写时间，3：已过填写时间
	public static final String QUES_STATUS_NO_FILL = "0";
	public static final String QUES_STATUS_ALREADY_FILL = "1";
	public static final String QUES_STATUS_NOT_STARTED = "2";
	public static final String QUES_STATUS_OVERTIME = "3";

	// 全校
	public static final String STAT_PARAMETER_TYPE_SCHOOL = "0";
	// 部门
	public static final String STAT_PARAMETER_TYPE_ORG = "1";
	// 教师
	public static final String STAT_PARAMETER_TYPE_PERSON = "2";
	public static final String STAT_PARAMETER_TYPE_PERSON_NAME = "教师";
	// 专任教师
	public static final String STAT_PARAMETER_TYPE_FULL_TEACHER = "3";
	public static final String STAT_PARAMETER_TYPE_FULL_TEACHER_NAME = "专任教师";
	// A类教师
	public static final String STAT_PARAMETER_TYPE_A_TEACHER = "4";
	public static final String STAT_PARAMETER_TYPE_A_TEACHER_NAME = "A类教师";
	// 行政兼课教师
	public static final String STAT_PARAMETER_TYPE_ADMIN_COURSE_TEACHER = "5";
	public static final String STAT_PARAMETER_TYPE_ADMIN_COURSE_TEACHER_NAME = "行政兼课教师";
	// 外聘教师
	public static final String STAT_PARAMETER_TYPE_EXTERNAL_TEACHER = "6";
	public static final String STAT_PARAMETER_TYPE_EXTERNAL_TEACHER_NAME = "外聘教师";

	// 默认统计类别和总分
	public static final String[][] STAT_GROUP_ID_SCORE_ARR = { { "11", "7" }, { "12", "6" }, { "13", "7" },
			{ "21", "5" }, { "22", "25" }, { "23", "20" }, { "31", "30" } };
	// 统计类别
	public static List<CesStatisticsGroupInfo> LIST_STAT_GROUP_INFO = new ArrayList<CesStatisticsGroupInfo>();
	// 统计的方案ces_exam的id
	public static final String STAT_EXAM_ID = "0a29f6081d524ac5b20d9b0cec86701f";
}
