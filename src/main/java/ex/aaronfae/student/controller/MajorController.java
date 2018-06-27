package ex.aaronfae.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ex.aaronfae.student.domain.Major;
import ex.aaronfae.student.service.MajorService;
import ex.aaronfae.student.vo.MajorVO;

@Controller
public class MajorController {

	@Autowired
	private MajorService majorService;

	/**
	 * 列出专业的Id和名字
	 * 
	 * @return 专业的Id和名字
	 */
	@RequestMapping("/listMajorIdAndZym")
	@ResponseBody
	public List<MajorVO> listMajorIdAndZym() {
		return majorService.listMajorIdAndZym();
	}

	/**
	 * 保存专业
	 * 
	 * @param major
	 *            专业
	 * @return 影响的行数
	 */
	@RequestMapping("/saveMajor")
	@ResponseBody
	public int saveMajor(Major major) {
		return majorService.saveMajor(major);
	}

	/**
	 * 更新专业的人数，在录入学生时触发
	 * 
	 * @param id
	 *            专业id
	 * @param rs
	 *            人数
	 * @return 影响的行数
	 */
	@RequestMapping("/updateRsById")
	@ResponseBody
	public int updateRsById(Integer id, Integer rs) {
		return majorService.updateRsById(id, rs);
	}
}
