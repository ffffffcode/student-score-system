package ex.aaronfae.student.service;

import java.util.List;

import ex.aaronfae.student.domain.Major;
import ex.aaronfae.student.vo.MajorVO;

public interface MajorService {

	List<MajorVO> listMajorIdAndZym();

	int saveMajor(Major major);

	int updateRsById(Integer id, Integer rs);
}
