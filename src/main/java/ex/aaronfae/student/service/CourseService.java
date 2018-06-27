package ex.aaronfae.student.service;

import java.util.List;

import ex.aaronfae.student.domain.Course;
import ex.aaronfae.student.vo.CourseVO;

public interface CourseService {

	List<Course> listCourse();

	List<CourseVO> listCourseByXh(String xh);
	
	Course getKcByKch(String kch);

	int getXfByKch(String kch);

	int saveCourse(Course course);

	int deleteCoureseByKch(String kch);

	int updateCoureseByKch(Course course);
}
