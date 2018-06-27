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

import ex.aaronfae.student.domain.Course;
import ex.aaronfae.student.service.CourseService;
import ex.aaronfae.student.vo.CourseVO;

@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;

	/**
	 * 列出所有课程
	 * 
	 * @return 所有课程
	 */
	@RequestMapping("/listCourse")
	@ResponseBody
	public List<Course> listCourse() {
		return courseService.listCourse();
	}

	/**
	 * 分页列出所有课程信息
	 * 
	 * @return 分页所有课程信息
	 */
	@RequestMapping("/listCourseWithPaging")
	@ResponseBody
	public List<Course> listCourseWithPaging(Integer pageNum, Integer pageSize) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		PageHelper.startPage(pageNum, pageSize);
		return courseService.listCourse();
	}

	/**
	 * 分页带导航的列出所有课程信息
	 * 
	 * @return 分页带导航的所有课程信息
	 */
	@RequestMapping("/listCourseWithPagingAndNav")
	@ResponseBody
	public PageInfo<?> listCourseWithPagingAndNav(Integer pageNum, Integer pageSize) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		Page<Object> page = PageHelper.startPage(pageNum, pageSize);
		courseService.listCourse();
		PageInfo<?> pageInfo = page.toPageInfo();
		return pageInfo;
	}

	/**
	 * 通过学号查找课程
	 * 
	 * @param xh
	 *            学号
	 * @return 学号所选的课程
	 */
	@RequestMapping(value = "/listCourseByXh")
	@ResponseBody
	public List<CourseVO> listCourseByXh(@RequestParam String xh) {
		return courseService.listCourseByXh(xh);
	}

	/**
	 * 通过课程号查找课程
	 * 
	 * @param kch
	 *            课程号
	 * @return 课程信息
	 */
	@RequestMapping(value = "/getKcByKch")
	@ResponseBody
	public Course getKcByKch(@RequestParam String kch) {
		return courseService.getKcByKch(kch);
	}

	/**
	 * 录入课程信息
	 * 
	 * @param course
	 *            课程
	 * @return 重定向到课程信息页面
	 */
	@RequestMapping("/saveCourse")
	public String saveCourse(Course course) {
		courseService.saveCourse(course);
		return "redirect:kcInfo";
	}

	/**
	 * 通过课程号删除课程
	 * 
	 * @param kch
	 *            课程号
	 * @return 重定向到课程信息页面
	 */
	@RequestMapping(value = "/deleteCoureseByKch")
	public String deleteCourese(@RequestParam String kch) {
		courseService.deleteCoureseByKch(kch);
		return "redirect:kcInfo";
	}

	/**
	 * 通过课程号定位信息更新课程
	 * 
	 * @param kch
	 *            课程号
	 * @return 重定向到课程信息页面
	 */
	@RequestMapping(value = "/updataeCoureseByKch")
	public String updateCoureseByKch(Course course) {
		courseService.updateCoureseByKch(course);
		return "redirect:kcInfo";
	}
}
