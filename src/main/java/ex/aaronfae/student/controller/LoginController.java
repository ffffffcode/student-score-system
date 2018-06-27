package ex.aaronfae.student.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	/**
	 * 访问登录页面的路径
	 * 
	 * @return 登录页面
	 */
	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(String uname, String passwd, Boolean rememberMe) {
		System.out.println(rememberMe);
		ModelAndView modelAndView = new ModelAndView("login");
		if (uname == null || "".equals(uname)) {
			return modelAndView.addObject("msg", "请输入用户名");
		}
		if (passwd == null || "".equals(passwd)) {
			return modelAndView.addObject("msg", "请输入密码");
		}
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(uname, passwd, rememberMe != null);
		try {
			subject.login(token);
			modelAndView.addObject("msg", "登陆成功");
			return new ModelAndView("xsInfo");
		} catch (UnknownAccountException e) {
			modelAndView.addObject("msg", "用户名不存在");
		} catch (IncorrectCredentialsException e) {
			modelAndView.addObject("msg", "密码不正确");
		}
		return modelAndView;
	}

	/**
	 * 用户登录
	 * 
	 * @param uname
	 *            用户名
	 * @param passwd
	 *            密码
	 * @param rememberMe
	 *            记住我
	 * @return 返回给用户的信息：status状态码 message反馈信息
	 */
	@PostMapping(value = "/ajaxLogin")
	@ResponseBody
	public Map<String, Object> ajaxLogin(String uname, String passwd, Boolean rememberMe) {
		Map<String, Object> resultMap = new LinkedHashMap<>();
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(uname, passwd, rememberMe);
			SecurityUtils.getSubject().login(token);
			resultMap.put("n", 200);
			resultMap.put("message", "登录成功");
		} catch (UnknownAccountException e) {
			resultMap.put("n", 500);
			resultMap.put("message", "用户名不存在");
		} catch (IncorrectCredentialsException e) {
			resultMap.put("n", 500);
			resultMap.put("message", "密码不正确");
		}
		return resultMap;
	}
}
