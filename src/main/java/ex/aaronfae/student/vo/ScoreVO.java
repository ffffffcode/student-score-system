package ex.aaronfae.student.vo;

import lombok.Data;

@Data
public class ScoreVO {

	/**
	 * 学号
	 */
	private String xh;

	/**
	 * 姓名
	 */
	private String xm;
	
	/**
	 * 课程号
	 */
	private String kch;

	/**
	 * 课程名
	 */
	private String kcm;

	/**
	 * 学分
	 */
	private Integer cj;

	/**
	 * 成绩
	 */
	private Integer xf;
}
