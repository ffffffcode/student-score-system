package ex.aaronfae.student.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import ex.aaronfae.student.service.MajorService;
import ex.aaronfae.student.service.ScoreService;
import ex.aaronfae.student.service.StudentService;
import ex.aaronfae.student.vo.StudentVO;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private MajorService majorService;

	@Autowired
	private ScoreService scoreService;

	/**
	 * 列出所有学生信息
	 * 
	 * @return 所有学生信息
	 */
	@RequestMapping("/listStudent")
	@ResponseBody
	public List<StudentVO> listStudent() {
		return studentService.listStudent();
	}

	/**
	 * 分页列出所有学生信息
	 * 
	 * @return 分页所有学生信息
	 */
	@RequestMapping("/listStudentWithPaging")
	@ResponseBody
	public List<StudentVO> listStudentWithPaging(Integer pageNum, Integer pageSize) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		PageHelper.startPage(pageNum, pageSize);
		return studentService.listStudent();
	}

	/**
	 * 分页带导航的列出所有学生信息
	 * 
	 * @return 分页带导航的所有学生信息
	 */
	@RequestMapping("/listStudentWithPagingAndNav")
	@ResponseBody
	public PageInfo<?> listStudentWithPagingAndNav(Integer pageNum, Integer pageSize) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		Page<Object> page = PageHelper.startPage(pageNum, pageSize);
		studentService.listStudent();
		PageInfo<?> pagehelper = page.toPageInfo();
		return pagehelper;
	}

	/**
	 * 列出所有学生姓名
	 * 
	 * @return 所有学生姓名
	 */
	@RequestMapping("/listStudentName")
	@ResponseBody
	public List<StudentVO> listStudentXhAndXm() {
		return studentService.listStudentXhAndXm();
	}

	@RequestMapping("/getStudentByXh")
	@ResponseBody
	public StudentVO getStudentByXh(String xh) {
		return studentService.getStudentByXh(xh);
	}

	/**
	 * 通过学号从数据库中取出学生照片到response的输出流中
	 * 
	 * @param xh
	 *            学号
	 * @param response
	 */
	@RequestMapping("/getZpByXh")
	public void getZpByXh(String xh, HttpServletResponse response) {
		try {
			byte[] bs = studentService.getZpByXh(xh).getZp();
			InputStream input = new ByteArrayInputStream(bs);
			BufferedImage image = ImageIO.read(input);
			ImageIO.write(image, "jpg", response.getOutputStream());
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 录入学生信息
	 * 
	 * @param student
	 *            学生信息
	 * @return 重定向到学生信息页面
	 */
	@RequestMapping("/saveStudent")
	public String saveStudent(StudentVO student) {
		student.setZxf(0);// 总学分默认为0
		byte[] zpBytes;
		try {
			zpBytes = student.getZp().getBytes();
			studentService.saveStudent(student, zpBytes);
			Integer rs = studentService.getRsByZyId(student.getZyId());
			majorService.updateRsById(student.getZyId(), rs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:xsInfo";
	}

	/**
	 * 通过学号删除学生信息
	 * 
	 * @param xh
	 *            学号
	 * @return 重定向到学生信息页面
	 */
	@RequestMapping(value = "/deleteStudentByXh")
	public String deleteStudentByXh(@RequestParam String xh) {
		studentService.deleteStudentByXh(xh);
		return "redirect:xsInfo";
	}

	/**
	 * 更新学生信息
	 * 
	 * @param student
	 * @return 重定向到学生信息页面
	 */
	@RequestMapping(value = "/updateStudentByXh")
	public String updateStudentByXh(StudentVO student) {
		System.out.println(student.getZp().isEmpty());
		if (student.getZp().isEmpty()) {
			studentService.updateStudentWithoutZpByXh(student);
		}
		try {
			byte[] zpBytes = student.getZp().getBytes();
			studentService.updateStudentByXh(student, zpBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:xsInfo";
	}

	/**
	 * 更新学生照片
	 * 
	 * @param xh
	 *            学号
	 * @param zp
	 *            照片
	 * @return 重定向学生详细信息页面
	 */
	@RequestMapping(value = "/updateZpByXh", method = RequestMethod.POST)
	public String updateZpByXh(String xh, MultipartFile zp) {
		try {
			byte[] bytes = zp.getBytes();
			studentService.updateZpByXh(xh, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/xsDetails?xh=" + xh;
	}

	/**
	 * 更新并显示总学分
	 * 
	 * @param xh
	 *            学号
	 * @return 总学分
	 */
	@RequestMapping(value = "/updateZxfByXh")
	@ResponseBody
	public int updateZxfByXh(@RequestParam(required = true) String xh) {
		Integer zxf = scoreService.getZxyByXh(xh);
		if (zxf == null) {
			zxf = 0;
		}
		studentService.updateZxfByXh(xh, zxf);
		return zxf;
	};
}
