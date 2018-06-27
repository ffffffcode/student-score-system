package ex.aaronfae.student.domain;

import lombok.Data;

@Data
public class Course {

	/**
	 * 课程号，主键
	 */
	private String kch;
	/**
	 * 课程名
	 */
	private String kcm;
	/**
	 * 开学学期
	 */
	private Integer kxxq;
	/**
	 * 学时
	 */
	private Integer xs;
	/**
	 * 学分
	 */
	private Integer xf;
}
