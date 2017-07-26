package com.coffee.shop.api.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@JsonInclude(value = NON_NULL)
public class SearchCoffeeKindResource extends AbstractResource {

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotNull
    private Double price;
}
