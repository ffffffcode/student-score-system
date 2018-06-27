package ex.aaronfae.student.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ex.aaronfae.student.domain.Score;
import ex.aaronfae.student.mapper.ScoreMapper;
import ex.aaronfae.student.service.ScoreService;
import ex.aaronfae.student.vo.ScoreVO;

@Service
public class ScoreServiceImpl implements ScoreService {

	@Autowired
	private ScoreMapper scoreMapper;

	@Override
	public List<ScoreVO> listScore() {
		return scoreMapper.listScore();
	}

	@Override
	public Integer getZxyByXh(String xh) {
		return scoreMapper.getZxyByXh(xh);
	}

	@Override
	public int updateScore(Score score) {
		return scoreMapper.updateScore(score);
	}
}
