package ru.clevertec.task1feign.mapper;

import org.mapstruct.*;
import ru.clevertec.task1feign.model.Client;
import ru.clevertec.task1feign.model.ClientInfoDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientInfoMapper {

    @Mapping(source = "clientInfo.clientDetails.checks.restrictWithoutTIN", target = "restrictWithoutTIN")
    @Mapping(source = "clientInfo.clientDetails.checks.restrictWithoutConflictExplained",
            target = "restrictWithoutConflictExplained")
    ClientInfoDto  toDto(Client client);
}
