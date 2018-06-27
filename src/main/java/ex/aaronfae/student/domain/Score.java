package ex.aaronfae.student.domain;

import lombok.Data;


@Data
public class Score {

	/**
	 * 学号，联合主键
	 */
	private String xh;

	/**
	 * 课程号，联合主键
	 */
	private String kch;

	/**
	 * 成绩
	 */
	private Integer cj;

	/**
	 * 学分
	 */
	private Integer xf;
}
