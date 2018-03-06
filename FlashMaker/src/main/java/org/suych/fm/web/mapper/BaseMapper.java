package org.suych.fm.web.mapper;

import java.io.Serializable;
import java.util.List;

public interface BaseMapper<T, ID extends Serializable> {

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
