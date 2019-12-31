package com.sk.jsp.entity;

import com.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 评价方案实体
 *
 * @author zhangqiao
 * @since 2019-01-09 11:42:21
 */
@Table(name = "CES_EXAM")
public class CesExam extends BaseEntity {

	/**
	 * 方案编号
	 */
	@Column(name = "CODE")
	private String code;

	/**
	 * 方案名称
	 */
	@Column(name = "NAME")
	private String name;

	/**
	 * 问卷id
	 */
	@Column(name = "PAPER_ID")
	private String paperId;

	/**
	 * 评分标准id
	 */
	@Column(name = "STANDARD_ID")
	private String standardId;

	/**
	 * 学年id
	 */
	@Column(name = "STUDY_YEAR_ID")
	private String studyYearId;

	/**
	 * 学期
	 */
	@Column(name = "XQ")
	private String xq;

	/**
	 * 评价目的
	 */
	@Column(name = "DESP")
	private String desp;

	@Column(name = "PURPOSE")
	private String purpose;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public String getStandardId() {
		return standardId;
	}

	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}

	public String getStudyYearId() {
		return studyYearId;
	}

	public void setStudyYearId(String studyYearId) {
		this.studyYearId = studyYearId;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

}