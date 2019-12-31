package com.sk.jsp.service;

import com.base.util.CodeUtil;
import com.base.util.Constant;
import com.base.util.EmptyUtils;
import com.base.util.UUIDUtils;
import com.sk.jsp.dao.CesExamDao;
import com.sk.jsp.entity.CesExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 评价方案服务层
 *
 * @author zhangqiao
 * @since 2019-01-09 11:42:21
 */
@Service
public class CesExamService {

	@Autowired
	private CesExamDao examDao;

	/**
	 * 获取列表
	 *
	 * @return
	 */
	public List<Map> getAllList(CesExam exam) {
		List<Map> examList = examDao.getExamList(exam, Constant.BMS_JDBC_NAME);
		return examList;
	}

	/**
	 * 获取列表
	 *
	 * @return
	 */
	public List<CesExam> getSelectList(CesExam exam) {
		Example example = new Example(CesExam.class);
		Example.Criteria criteria = example.createCriteria();
		if (EmptyUtils.isNotEmpty(exam)) {
			if (EmptyUtils.isNotEmpty(exam.getStatus())) {
				criteria.andEqualTo("status", exam.getStatus());
			}
			if (EmptyUtils.isNotEmpty(exam.getPurpose())) {
				criteria.andEqualTo("purpose", exam.getPurpose());
			}
		}
		criteria.andEqualTo("deleted", "0");
		// 排序
		example.orderBy("createTime").desc();
		return examDao.selectByExample(example);
	}

	/**
	 * 获取下拉搜索列表
	 *
	 * @return
	 */
	public List<CesExam> getDowntList(String purpose, String name) {
		Example example = new Example(CesExam.class);
		Example.Criteria criteria = example.createCriteria();
		if (EmptyUtils.isNotEmpty(name)) {
			criteria.andCondition("(name like '%" + name + "%' or code like '%" + name + "%')");
		}
		if (EmptyUtils.isNotEmpty(purpose)) {
			criteria.andEqualTo("purpose", purpose);
		}
		criteria.andEqualTo("deleted", "0");
		// 排序
		example.orderBy("createTime").desc();
		return examDao.selectByExample(example);
	}

	/**
	 * 保存/更新
	 *
	 * @param exam
	 * @return 1成功/0失败
	 */
	public int saveAndUpdate(CesExam exam) {
		int ret = 0;
		if (EmptyUtils.isNotEmpty(exam)) {
			if (EmptyUtils.isNotEmpty(exam.getId())) {
				exam.setUpdateTime(new Date());
				ret = examDao.updateByPrimaryKeySelective(exam);
			} else {
				if (EmptyUtils.isEmpty(exam.getCode())) {
					exam.setCode(CodeUtil.getEntityCode("PL", ""));
				}
				exam.setId(UUIDUtils.getUUID());
				ret = examDao.insertSelective(exam);
			}
		}
		return ret;
	}

	/**
	 * 获取实体
	 *
	 * @param id
	 *            ID
	 * @return
	 */
	public CesExam getCesExam(String id) {
		CesExam exam = new CesExam();
		if (EmptyUtils.isNotEmpty(id)) {
			exam = examDao.selectByPrimaryKey(id);
		}
		return exam;
	}

	/**
	 * 软删除
	 *
	 * @param id
	 *            ID
	 * @return 1成功/0失败
	 */
	public int deleteById(String id) {
		CesExam exam = getCesExam(id);
		int ret = 0;
		if (EmptyUtils.isNotEmpty(exam)) {
			exam.setDeleted("1");
			ret = saveAndUpdate(exam);
		}
		return ret;
	}

	/**
	 * 硬删除
	 *
	 * @param id
	 *            ID
	 * @return 1成功/0失败
	 */
	public int delete(String id) {
		int ret = 0;
		if (EmptyUtils.isNotEmpty(id)) {
			ret = examDao.deleteByPrimaryKey(id);
		}
		return ret;
	}

}