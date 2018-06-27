package ex.aaronfae.student.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Student {

	/**
	 * 学号，主键
	 */
	private String xh;

	/**
	 * 姓名
	 */
	private String xm;

	/**
	 * 性别
	 */
	private Boolean xb;

	/**
	 * 出生时间
	 */
	private Date cssj;

	/**
	 * 专业ID
	 */
	private Integer zyId;

	/**
	 * 总学分
	 */
	private Integer zxf;

	/**
	 * 备注
	 */
	private String bz;

	/**
	 * 照片
	 */
	private byte[] zp;
}
