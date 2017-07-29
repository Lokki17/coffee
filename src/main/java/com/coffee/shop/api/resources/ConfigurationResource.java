package com.coffee.shop.api.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@JsonInclude(value = NON_NULL)
public class ConfigurationResource extends AbstractResource {

    private Long id;

    @NotNull
    private Integer cupCount;

    @NotNull
    private Double discountSumm;

    @NotNull
    private Double deliveryPrice;
}
