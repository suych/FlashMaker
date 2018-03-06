package org.suych.fm.web.mapper.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.suych.fm.web.model.ExampleModel;

public interface ExampleMapper {

	@Select("select * from exampleforspringmybatis t where t.example1=#{id}")
	@Results({ @Result(property = "example1", column = "example1", javaType = String.class),
			@Result(property = "example2", column = "example2", javaType = Integer.class),
			@Result(property = "example3", column = "example3", javaType = Date.class),
			@Result(property = "example4", column = "example4", javaType = String.class) })
	public List<ExampleModel> getExampleById(@Param("id") String id) throws Exception;

	public List<ExampleModel> getExampleByIds(List<String> ids) throws Exception;

	@Insert("insert into exampleforspringmybatis values (#{example.example1},#{example.example2},#{example.example3},#{example.example4})")
	public int insertExample(@Param("example") ExampleModel example) throws Exception;

	@Update("update exampleforspringmybatis set example2=#{example.example2},example3=#{example.example3},example4=#{example.example4} where example1=#{id}")
	public int updateExampleById(@Param("example") ExampleModel example, @Param("id") String id) throws Exception;

	@Delete("delete from exampleforspringmybatis where example1=#{id} ")
	public int deleteExampleById(@Param("id") String id) throws Exception;

	public int batchInsertExample(@Param("examples") List<ExampleModel> examples) throws Exception;

	public int batchDeleteExample(@Param("ids") List<String> ids) throws Exception;
}
