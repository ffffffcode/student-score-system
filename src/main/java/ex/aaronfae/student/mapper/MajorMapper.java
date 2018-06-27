package ex.aaronfae.student.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import ex.aaronfae.student.domain.Major;
import ex.aaronfae.student.vo.MajorVO;

@Component
@Mapper
public interface MajorMapper {

	@Select("select id,zym from major")
	List<MajorVO> listMajorIdAndZym();

	@Select("insert into major (id,fdy,zym) values (#{id},#{fdy},#{zym})")
	int saveMajor(Major major);

	@Update("update major set rs = #{rs} where id = #{id}")
	int updateRsById(@Param("id") Integer id, @Param("rs") Integer rs);
}
