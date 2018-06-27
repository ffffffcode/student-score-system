package ex.aaronfae.student.service;

import java.util.List;

import ex.aaronfae.student.domain.Score;
import ex.aaronfae.student.vo.ScoreVO;

public interface ScoreService {

	List<ScoreVO> listScore();

	Integer getZxyByXh(String xh);

	int updateScore(Score score);
}
