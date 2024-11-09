package com.globox.globoxtest.mappers;

import com.globox.globoxtest.dal.entities.Title;
import com.globox.globoxtest.dtos.TitleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TitleMapper {

    TitleMapper INSTANCE = Mappers.getMapper(TitleMapper.class);

    TitleDTO toTitleDTO(Title title);
    List<TitleDTO> toTitlesDTO(List<Title> titles);

}