package com.coffee.shop.api.resources;

import com.coffee.shop.api.resources.validation.CoffeeKindExists;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;


@EqualsAndHashCode(callSuper = false, of = {"coffeeKind"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(value = NON_NULL)
public class CoffeeCupResource extends AbstractResource {

    private Long id;

    @CoffeeKindExists
    @NotNull
    private String coffeeKind;

    @Min(0)
    @NotNull
    private Integer count;
}
