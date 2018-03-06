package org.suych.fm.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.suych.fm.web.mapper.impl.ExampleMapper;
import org.suych.fm.web.model.ExampleModel;
import org.suych.fm.web.service.IExampleService;

@Transactional
@Service
public class ExampleSerivceImpl implements IExampleService {

	@Autowired
	private ExampleMapper exampleMapper;

	@Override
	public List<ExampleModel> getExampleById(String id) throws Exception {
		return exampleMapper.getExampleById(id);
	}

	@Override
	public List<ExampleModel> getExampleByIds(List<String> ids) throws Exception {
		return exampleMapper.getExampleByIds(ids);
	}

	@Override
	public int insertExample(ExampleModel example) throws Exception {
		return exampleMapper.insertExample(example);
	}

	@Override
	public int updateExampleById(ExampleModel example, String id) throws Exception {
		return exampleMapper.updateExampleById(example, id);
	}

	@Override
	public int deleteExampleById(String id) throws Exception {
		return exampleMapper.deleteExampleById(id);
	}

	@Override
	public int batchInsertExample(List<ExampleModel> examples) throws Exception {
		return exampleMapper.batchInsertExample(examples);
	}

	@Override
	public int batchDeleteExample(List<String> ids) throws Exception {
		return exampleMapper.batchDeleteExample(ids);
	}

}
