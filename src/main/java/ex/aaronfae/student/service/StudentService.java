package ex.aaronfae.student.service;

import java.util.List;

import ex.aaronfae.student.domain.Student;
import ex.aaronfae.student.vo.StudentVO;

public interface StudentService {

	List<StudentVO> listStudent();

	List<StudentVO> listStudentXhAndXm();

	StudentVO getStudentByXh(String xh);

	Student getZpByXh(String xh);

	int getRsByZyId(Integer zyId);

	int saveStudent(StudentVO student, byte[] zpBytes);

	int deleteStudentByXh(String xh);

	int updateStudentByXh(StudentVO student, byte[] zpBytes);

	int updateStudentWithoutZpByXh(StudentVO student);

	int updateZpByXh(String xh, byte[] bytes);

	int updateZxfByXh(String xh, Integer zxf);
}
