package org.suych.fm.web.service;

import java.util.List;

import org.suych.fm.web.model.ExampleModel;

public interface IExampleService {

	/**
	 * 根据单个ID查询
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<ExampleModel> getExampleById(String id) throws Exception;

	/**
	 * 根据批量ID查询
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	List<ExampleModel> getExampleByIds(List<String> ids) throws Exception;

	/**
	 * 新增
	 * 
	 * @param example
	 * @return
	 * @throws Exception
	 */
	int insertExample(ExampleModel example) throws Exception;

	/**
	 * 根据ID修改
	 * 
	 * @param example
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int updateExampleById(ExampleModel example, String id) throws Exception;

	/**
	 * 根据ID删除
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int deleteExampleById(String id) throws Exception;

	/**
	 * 批量新增
	 * 
	 * @param examples
	 * @return
	 * @throws Exception
	 */
	int batchInsertExample(List<ExampleModel> examples) throws Exception;

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	int batchDeleteExample(List<String> ids) throws Exception;
}
