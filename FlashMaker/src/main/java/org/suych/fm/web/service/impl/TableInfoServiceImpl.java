package org.suych.fm.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.suych.fm.converter.FieldInfoConverter;
import org.suych.fm.converter.TableInfoConverter;
import org.suych.fm.util.StringUtil;
import org.suych.fm.web.mapper.TableInfoMapper;
import org.suych.fm.web.model.domain.FieldInfoDO;
import org.suych.fm.web.model.domain.TableBaseInfoDO;
import org.suych.fm.web.model.model.FieldInfoModel;
import org.suych.fm.web.model.model.PrimaryKeyInfoModel;
import org.suych.fm.web.model.model.TableInfoModel;
import org.suych.fm.web.service.ITableInfoService;

@Service
public class TableInfoServiceImpl implements ITableInfoService {

	@Value("${jdbc.username}")
	private String jdbcUsername;

	@Autowired
	private TableInfoMapper tableInfoMapper;

	@Override
	public TableInfoModel getTableInfo(String tableName) {
		tableName = tableName.toUpperCase();

		// 1.加载基础信息
		TableBaseInfoDO tableBaseInfoDO = tableInfoMapper.getTableBaseInfo(tableName);
		TableInfoModel result = TableInfoConverter.INSTANCE.domainToModel(tableBaseInfoDO);

		// 2.加载字段信息，增加字段排序OrderBy
		List<FieldInfoDO> fieldInfoDOs = tableInfoMapper.listFieldInfo(tableName, jdbcUsername.toUpperCase());
		List<FieldInfoModel> fieldInfo = FieldInfoConverter.INSTANCE.listDomainToModel(fieldInfoDOs);
		for (FieldInfoModel temp : fieldInfo) {
			temp.setPropertyName(StringUtil.underlineToCamelAndFirstLetterToLowerCase(temp.getPropertyName()));
		}

		// 3.加载主键信息，如果查询主键为空，以fieldInfo第一个字段名称为主键
		String primaryKeyName = StringUtil.null2Empty(tableInfoMapper.getPrimaryKey(tableName));
		if ("".equals(primaryKeyName)) {
			primaryKeyName = fieldInfo.get(0).getColumnName();
		}

		// 4.加载主键信息
		PrimaryKeyInfoModel primaryKeyInfo = new PrimaryKeyInfoModel();
		for (FieldInfoModel field : fieldInfo) {
			if (field.getColumnName().equalsIgnoreCase(primaryKeyName)) {
				primaryKeyInfo.setColumnName(field.getColumnName());
				primaryKeyInfo.setPropertyName(field.getPropertyName());
				primaryKeyInfo.setDataType(field.getDataType());
				primaryKeyInfo.setDataLength(field.getDataLength());
				primaryKeyInfo.setDataPrecision(field.getDataPrecision());
				primaryKeyInfo.setComments(field.getComments());
				break;
			}
		}

		result.setPrimaryKey(primaryKeyInfo);
		result.setField(fieldInfo);
		return result;
	}
}
