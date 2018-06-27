package ex.aaronfae.student.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ex.aaronfae.student.domain.Course;
import ex.aaronfae.student.mapper.CourseMapper;
import ex.aaronfae.student.service.CourseService;
import ex.aaronfae.student.vo.CourseVO;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseMapper courseMapper;

	@Override
	public List<Course> listCourse() {
		return courseMapper.listCourse();
	}

	@Override
	public List<CourseVO> listCourseByXh(String xh) {
		return courseMapper.listCourseByXh(xh);
	}

	@Override
	public Course getKcByKch(String kch) {
		return courseMapper.getKcByKch(kch);
	}

	@Override
	public int getXfByKch(String kch) {
		return courseMapper.getXfByKch(kch);
	}

	@Override
	public int saveCourse(Course course) {
		return courseMapper.saveCourse(course);
	}

	@Override
	public int deleteCoureseByKch(String kch) {
		return courseMapper.deleteCoureseByKch(kch);
	}

	@Override
	public int updateCoureseByKch(Course course) {
		return courseMapper.updateCoureseByKch(course);
	}
}
