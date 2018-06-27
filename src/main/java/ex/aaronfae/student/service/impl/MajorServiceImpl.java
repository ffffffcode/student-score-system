package ex.aaronfae.student.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ex.aaronfae.student.domain.Major;
import ex.aaronfae.student.mapper.MajorMapper;
import ex.aaronfae.student.service.MajorService;
import ex.aaronfae.student.vo.MajorVO;

@Service
public class MajorServiceImpl implements MajorService {

	@Autowired
	private MajorMapper majorMapper;

	@Override
	public List<MajorVO> listMajorIdAndZym() {
		return majorMapper.listMajorIdAndZym();
	}

	@Override
	public int saveMajor(Major major) {
		return majorMapper.saveMajor(major);
	}

	@Override
	public int updateRsById(Integer id, Integer rs) {
		return majorMapper.updateRsById(id, rs);
	}
}
