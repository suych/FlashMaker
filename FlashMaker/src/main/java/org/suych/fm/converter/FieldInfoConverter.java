package org.suych.fm.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.suych.fm.web.model.domain.FieldInfoDO;
import org.suych.fm.web.model.model.FieldInfoModel;

@Mapper
public interface FieldInfoConverter {

	FieldInfoConverter INSTANCE = Mappers.getMapper(FieldInfoConverter.class);

	@Mapping(target = "columnName", expression = "java(domain.getColumn_name().toLowerCase())")
	@Mapping(target = "propertyName", expression = "java(domain.getColumn_name().toLowerCase())")
	@Mapping(target = "dataType", source = "data_type")
	@Mapping(target = "dataLength", source = "data_length")
	@Mapping(target = "dataPrecision", source = "data_precision")
	FieldInfoModel domainToModel(FieldInfoDO domain);

	List<FieldInfoModel> listDomainToModel(List<FieldInfoDO> domains);
}
