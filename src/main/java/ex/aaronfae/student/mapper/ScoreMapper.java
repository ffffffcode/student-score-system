package ex.aaronfae.student.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import ex.aaronfae.student.domain.Score;
import ex.aaronfae.student.vo.ScoreVO;

@Component
@Mapper
public interface ScoreMapper {

	@Select("select s.xh, s.xm, c.kch, c.kcm, sc.cj, sc.xf from student s, course c, score sc where s.xh = sc.xh and c.kch = sc.kch")
	List<ScoreVO> listScore();

	@Select("select sum(xf) from score where xh = #{xh}")
	Integer getZxyByXh(String xh);

	@Insert("update score set cj = #{cj},xf = #{xf} where xh = #{xh} and kch = #{kch}")
	int updateScore(Score score);
}
