package org.suych.fm.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.suych.fm.web.model.domain.TableBaseInfoDO;
import org.suych.fm.web.model.model.TableInfoModel;

@Mapper
public interface TableInfoConverter {

	TableInfoConverter INSTANCE = Mappers.getMapper(TableInfoConverter.class);

	@Mapping(target = "tableName", expression = "java(domain.getTable_name().toLowerCase())")
	@Mapping(target = "tableType", source = "table_type")
	TableInfoModel domainToModel(TableBaseInfoDO domain);
}
