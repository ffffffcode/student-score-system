package ex.aaronfae.student.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import ex.aaronfae.student.domain.User;

@Component
@Mapper
public interface UserMapper {

	@Insert("insert into user(username,password) values (#{username},#{password})")
	int saveUser(User user);
}
