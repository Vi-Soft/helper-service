package com.visoft.helper.service.transport.mapper;

import com.visoft.helper.service.persistance.entity.IdEntity;
import org.mapstruct.Mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface GeneralMapper {

    default <T extends IdEntity> List<Long> toIds(List<T> children) {
        if (children != null && !children.isEmpty()) {
            return children.stream().map(IdEntity::getId).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
