package com.base.util;

import java.util.Random;

/**
 * 编号工具类
 *
 * @author zhangqiao
 * @since 2019-01-09
 */
public class CodeUtil {
	/**
	 * 随机数生成
	 *
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(62);
			sb.append(str.charAt(number));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 
	 * type 01 一级分类 02 二级分类
	 * 
	 * @return
	 */
	public static String getEntityCode(String prefixNo, String type) {
		String proNo = prefixNo;
		proNo += type;
		proNo += DateUtils.getCurrentDay("yy");
		proNo += DateUtils.getCurrentDay("MM");
		proNo += DateUtils.getCurrentDay("dd");
		proNo += getRandomString(4);
		return proNo;
	}

	public static void main(String[] args) {

	}

}
