package com.visoft.helper.service.transport.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderNumberDto extends MultiLanguageNameDto{

    @Min(0)
    @NotNull
    private int orderNumber;
}
