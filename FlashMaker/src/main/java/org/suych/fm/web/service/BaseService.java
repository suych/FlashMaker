package org.suych.fm.web.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T, ID extends Serializable> {

	void setBaseMapper();

	int insert(T record);

	int insertSelective(T record);

	int deleteByPrimaryKey(ID id);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKeyWithBLOBs(T record);

	int updateByPrimaryKey(T record);

	T selectByPrimaryKey(ID id);

	T selectByCondition(T record);

	List<T> selectListByCondition(T record);

	List<T> selectListByConditionByPage(T record);
}
