package ex.aaronfae.student.domain;

import lombok.Data;

@Data
public class Major {

	/**
	 * 专业ID，主键
	 */
	private Integer id;

	/**
	 * 专业名
	 */
	private String zym;

	/**
	 * 人数
	 */
	private Integer rs;

	/**
	 * 辅导员名字
	 */
	private String fdy;
}
