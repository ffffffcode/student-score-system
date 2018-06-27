package ex.aaronfae.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ex.aaronfae.student.domain.User;
import ex.aaronfae.student.mapper.UserMapper;

@Controller
public class UserController {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 用户注册
	 * @return 影响的行数
	 */
	@RequestMapping("/addUser")
	@ResponseBody
	public int addUser() {
		User user = new User();
		user.setUsername("aaronfae");
		user.setPassword("123");
		return userMapper.saveUser(user);
	}

}
