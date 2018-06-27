package ex.aaronfae.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import ex.aaronfae.student.domain.Score;
import ex.aaronfae.student.service.CourseService;
import ex.aaronfae.student.service.ScoreService;
import ex.aaronfae.student.service.StudentService;
import ex.aaronfae.student.vo.ScoreVO;

@Controller
public class ScoreController {

	@Autowired
	private ScoreService scoreService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private StudentService studentService;

	/**
	 * 列出所有学生成绩
	 * 
	 * @return 所有学生成绩
	 */
	@RequestMapping(value = "/listScore")
	@ResponseBody
	public List<ScoreVO> listScore() {
		return scoreService.listScore();
	}

	/**
	 * 分页列出所有学生成绩
	 * 
	 * @return 分页所有学生成绩
	 */
	@RequestMapping(value = "/listScoreWithPaging")
	@ResponseBody
	public List<ScoreVO> listScoreWithPaging(Integer pageNum, Integer pageSize) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		PageHelper.startPage(pageNum, pageSize);
		return scoreService.listScore();
	}

	/**
	 * 分页带导航的列出所有学生成绩
	 * 
	 * @return 分页带导航的所有学生成绩
	 */
	@RequestMapping(value = "/listScoreWithPagingAndNav")
	@ResponseBody
	public PageInfo<?> listScoreWithPagingAndNav(Integer pageNum, Integer pageSize) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		Page<Object> page = PageHelper.startPage(pageNum, pageSize);
		scoreService.listScore();
		PageInfo<?> pagehelper = page.toPageInfo();
		return pagehelper;
	}

	/**
	 * 录入学生成绩，并更新学生的总学分
	 * 
	 * @param xh
	 *            学号
	 * @param kch
	 *            课程号
	 * @param cj
	 *            成绩
	 * @return 重定向到成绩信息页面
	 */
	@RequestMapping(value = "/setCjAndXf")
	public String setCjAndXf(@RequestParam String xh, @RequestParam String kch, @RequestParam Integer cj) {
		Score score = new Score();
		score.setXh(xh);
		score.setKch(kch);
		score.setCj(cj);

		int xf = 0;
		/**
		 * 如果课程成绩大于等于60分，就可以获得学分
		 */
		if (cj >= 60) {
			xf = courseService.getXfByKch(kch);
		}
		score.setXf(xf);
		scoreService.updateScore(score);
		Integer zxf = scoreService.getZxyByXh(xh);
		/**
		 * 更新学生的总学分
		 */
		studentService.updateZxfByXh(xh, zxf);
		return "xscjInfo";
	}

	/**
	 * 删除（置零）学生的成绩和学分，并更新学生的总学分
	 * 
	 * @param xh
	 *            学号
	 * @param kch
	 *            课程号
	 * @return 重定向到成绩信息页面
	 */
	@RequestMapping(value = "/setCjAndXfToZero")
	public String setCjAndXfToZero(@RequestParam String xh, @RequestParam String kch) {
		Score score = new Score();
		score.setXh(xh);
		score.setKch(kch);
		score.setCj(0);
		score.setXf(0);
		scoreService.updateScore(score);
		Integer zxf = scoreService.getZxyByXh(xh);
		if (zxf == null) {
			zxf = 0;
		}
		/**
		 * 更新学生的总学分
		 */
		studentService.updateZxfByXh(xh, zxf);
		return "xscjInfo";
	}
}
