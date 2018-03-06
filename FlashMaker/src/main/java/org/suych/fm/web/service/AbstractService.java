package org.suych.fm.web.service;

import java.io.Serializable;
import java.util.List;

import org.suych.fm.web.mapper.BaseMapper;

public abstract class AbstractService<T, ID extends Serializable> implements BaseService<T, ID> {

	private BaseMapper<T, ID> baseMapper;

	public void setBaseMapper(BaseMapper<T, ID> baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public int insert(T record) {
		return baseMapper.insert(record);
	}

	@Override
	public int insertSelective(T record) {
		return baseMapper.insertSelective(record);
	}

	@Override
	public int deleteByPrimaryKey(ID id) {
		return baseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		return baseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(T record) {
		return baseMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(T record) {
		return baseMapper.updateByPrimaryKey(record);
	}

	@Override
	public T selectByPrimaryKey(ID id) {
		return baseMapper.selectByPrimaryKey(id);
	}

	@Override
	public T selectByCondition(T record) {
		return baseMapper.selectByCondition(record);
	}

	@Override
	public List<T> selectListByCondition(T record) {
		return baseMapper.selectListByCondition(record);
	}

	@Override
	public List<T> selectListByConditionByPage(T record) {
		return baseMapper.selectListByConditionByPage(record);
	}

}
