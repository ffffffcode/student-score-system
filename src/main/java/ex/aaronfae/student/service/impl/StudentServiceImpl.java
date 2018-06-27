package ex.aaronfae.student.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ex.aaronfae.student.domain.Student;
import ex.aaronfae.student.mapper.StudentMapper;
import ex.aaronfae.student.service.StudentService;
import ex.aaronfae.student.vo.StudentVO;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;

	@Override
	public List<StudentVO> listStudent() {
		return studentMapper.listStudent();
	}

	@Override
	public List<StudentVO> listStudentXhAndXm() {
		return studentMapper.listStudentXhAndXm();
	}

	@Override
	public StudentVO getStudentByXh(String xh) {
		return studentMapper.getStudentByXh(xh);
	}

	@Override
	public Student getZpByXh(String xh) {
		return studentMapper.getZpByXh(xh);
	}

	@Override
	public int getRsByZyId(Integer zyId) {
		return studentMapper.getRsByZyId(zyId);
	}

	@Override
	public int saveStudent(StudentVO student, byte[] zpBytes) {
		return studentMapper.saveStudent(student, zpBytes);
	}

	@Override
	public int deleteStudentByXh(String xh) {
		return studentMapper.deleteStudentByXh(xh);
	}

	@Override
	public int updateStudentByXh(StudentVO student, byte[] zpBytes) {
		return studentMapper.updateStudentByXh(student, zpBytes);
	}

	@Override
	public int updateStudentWithoutZpByXh(StudentVO student) {
		return studentMapper.updateStudentWithoutZpByXh(student);
	}

	@Override
	public int updateZpByXh(String xh, byte[] bytes) {
		return studentMapper.updateZpByXh(xh, bytes);
	}

	@Override
	public int updateZxfByXh(String xh, Integer zxf) {
		return studentMapper.updateZxfByXh(xh, zxf);
	}
}
