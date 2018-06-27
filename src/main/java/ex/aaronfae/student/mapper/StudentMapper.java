package ex.aaronfae.student.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import ex.aaronfae.student.domain.Student;
import ex.aaronfae.student.vo.StudentVO;

@Component
@Mapper
public interface StudentMapper {

	@Select("select s.xh, s.xm, s.xb, m.zym, s.cssj, s.zxf, s.bz from student s, major m where s.zy_id = m.id")
	List<StudentVO> listStudent();

	@Select("select xh, xm from student")
	List<StudentVO> listStudentXhAndXm();

	@Select("select s.xm, s.xb, s.zy_id, m.zym, s.cssj, s.zxf, s.bz from student s, major m where s.zy_id = m.id and xh = #{xh}")
	StudentVO getStudentByXh(String xh);

	@Select("select xh, zp from student where xh = #{xh}")
	Student getZpByXh(String xh);
	
	@Select("select count(*) from student where zy_id = #{zyId}")
	int getRsByZyId(Integer zyId);

	@Insert("insert into student (xh, xm, xb, cssj, bz, zy_id, zxf, zp) values (#{student.xh}, #{student.xm}, #{student.xb}, #{student.cssj}, #{student.bz}, #{student.zyId}, #{student.zxf}, #{zp,jdbcType=BLOB})")
	int saveStudent(@Param("student") StudentVO student, @Param("zp") byte[] zpBytes);

	@Delete("delete from student where xh=#{xh}")
	int deleteStudentByXh(String xh);

	@Update("update student set xm = #{student.xm}, xb = #{student.xb}, zy_id = #{student.zyId}, cssj = #{student.cssj}, bz = #{student.bz}, zp = #{zp,jdbcType=BLOB} where xh = #{student.xh}")
	int updateStudentByXh(@Param("student") StudentVO student, @Param("zp") byte[] zpBytes);

	@Update("update student set xm = #{xm}, xb = #{xb}, zy_id = #{zyId}, cssj = #{cssj}, bz = #{bz} where xh = #{xh}")
	int updateStudentWithoutZpByXh(StudentVO student);

	@Update("update student set zp = #{bytes,jdbcType=BLOB} where xh = #{xh}")
	int updateZpByXh(@Param("xh") String xh, @Param("bytes") byte[] bytes);

	@Update("update student set zxf = #{zxf} where xh = #{xh}")
	int updateZxfByXh(@Param("xh") String xh, @Param("zxf") Integer zxf);
}
