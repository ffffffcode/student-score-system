package ex.aaronfae.student.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class StudentVO {

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
	private String cssj;

	/**
	 * 专业ID
	 */
	private Integer zyId;

	/**
	 * 专业
	 */
	private String zym;

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
	private MultipartFile zp;
}
