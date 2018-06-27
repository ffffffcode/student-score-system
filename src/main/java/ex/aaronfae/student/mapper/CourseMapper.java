package ex.aaronfae.student.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import ex.aaronfae.student.domain.Course;
import ex.aaronfae.student.vo.CourseVO;

@Component
@Mapper
public interface CourseMapper {

	/**
	 * 从course表中列出课程号，课程名，开学学期，学分，学时
	 * @return List<Course>
	 */
	@Select("select kch, kcm, kxxq, xf, xs from course")
	List<Course> listCourse();

	@Select("select kch,kcm from course where kch in (select sc.kch from score sc join student s on #{xh} = sc.xh)")
	List<CourseVO> listCourseByXh(String xh);

	@Select("select kch, kcm, kxxq, xf, xs from course where kch = #{kch}")
	Course getKcByKch(String kch);

	@Select("select xf from course where kch = #{kch}")
	int getXfByKch(String kch);

	@Insert("insert into course (kch, kcm, kxxq, xf, xs) values (#{kch}, #{kcm}, #{kxxq}, #{xf}, #{xs})")
	int saveCourse(Course course);

	@Delete("delete from course where kch = #{kch}")
	int deleteCoureseByKch(String kch);

	@Update("update course set kcm = #{kcm}, kxxq = #{kxxq}, xf = #{xf}, xs = #{xs} where kch = #{kch}")
	int updateCoureseByKch(Course course);
}
