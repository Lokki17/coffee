package com.coffee.shop.api.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@JsonInclude(value = NON_NULL)
public class ConfigurationResource extends AbstractResource {

    private Long id;

    private Integer cupCount;

    private Double discountSumm;

    private Double deliveryPrice;
}
