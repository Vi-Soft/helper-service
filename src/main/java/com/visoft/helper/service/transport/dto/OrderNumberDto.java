package com.visoft.helper.service.transport.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class OrderNumberDto extends MultiLanguageNameDto{

    @Min(0)
    private int orderNumber;
}
