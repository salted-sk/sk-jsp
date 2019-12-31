package com.sk.jsp.controller.admin;

import com.base.common.AbstractController;
import com.base.util.Constant;
import com.base.vo.PaginationVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sk.jsp.entity.CesExam;
import com.sk.jsp.service.CesExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评价方案控制层
 *
 * @author zhangqiao
 * @since 2019-01-09 14:34:42
 */
@Controller
@RequestMapping("/admin/exam")
public class CesExamController extends AbstractController {

	@Autowired
	private CesExamService examService;

	/**
	 * 进入评价方案
	 *
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(CesExam exam) {
		ModelAndView view = new ModelAndView("/admin/exam/exam");
		view.addObject("exam", exam);
		return view;
	}

	/**
	 * 进入问卷方案
	 *
	 * @return
	 */
	@RequestMapping(value = "/comblist", method = RequestMethod.GET)
	public ModelAndView comblist(CesExam exam) {
		ModelAndView view = new ModelAndView("/admin/exam/combexam");
		view.addObject("exam", exam);
		return view;
	}

	/**
	 * 查询列表
	 *
	 * @param paginationVo
	 * @param exam
	 */
	@ResponseBody
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	public PaginationVo getList(PaginationVo paginationVo, CesExam exam) {
		PageHelper.startPage(paginationVo.getPageNum(), paginationVo.getPageSize());
		List<Map> list = examService.getAllList(exam);
		PageInfo pageInfo = new PageInfo<Map>(list);
		paginationVo.setData(pageInfo.getList());
		paginationVo.setRecordsTotal((int) pageInfo.getTotal());
		paginationVo.setRecordsFiltered(paginationVo.getRecordsTotal());
		return paginationVo;
	}

	/**
	 * 查询列表
	 *
	 * @param exam
	 */
	@ResponseBody
	@RequestMapping(value = "/getSelectList", method = RequestMethod.POST)
	public ModelAndView getSelectList(CesExam exam) {
		List<CesExam> list = examService.getSelectList(exam);
		return new ModelAndView(JSON_VIEW).addObject(Constant.RETURN, list);
	}

	/**
	 * 模糊查询分类
	 *
	 * @param name
	 *            模糊查询
	 */
	@ResponseBody
	@RequestMapping(value = "/getDownList", method = RequestMethod.GET)
	public Map<String, Object> getList(String purpose, String name) {
		List<CesExam> list = examService.getDowntList(purpose, name);
		Map<String, Object> map = new HashMap<>();
		map.put("q", name);
		map.put("p", false);
		map.put("s", list);
		return map;
	}

	/**
	 * 通过主键查询单条数据
	 *
	 * @param id
	 *            主键
	 * @return 单条数据
	 */
	@ResponseBody
	@RequestMapping(value = "/getById", method = RequestMethod.POST)
	public ModelAndView getCesExam(String id) {
		CesExam exam = examService.getCesExam(id);
		return new ModelAndView(JSON_VIEW).addObject(Constant.RETURN, exam);
	}

	/**
	 * 更新修改
	 *
	 * @param exam
	 * @return 1成功；0失败
	 */
	@ResponseBody
	@RequestMapping(value = "/saveAndUpdate", method = RequestMethod.POST)
	public ModelAndView saveAndUpdate(CesExam exam) {
		int ret = examService.saveAndUpdate(exam);
		return new ModelAndView(JSON_VIEW).addObject(Constant.RETURN, ret);
	}

	/**
	 * 删除
	 *
	 * @param id
	 *            主键
	 * @return 1成功；0失败
	 */
	@ResponseBody
	@RequestMapping(value = "/delById", method = RequestMethod.POST)
	public ModelAndView delete(String id) {
		int ret = examService.deleteById(id);
		return new ModelAndView(JSON_VIEW).addObject(Constant.RETURN, ret);
	}

}