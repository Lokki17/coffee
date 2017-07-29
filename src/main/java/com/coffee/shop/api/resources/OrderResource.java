package com.coffee.shop.api.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@JsonInclude(value = NON_NULL)
public class OrderResource extends AbstractResource {

    private Long id;

    @NotEmpty
    private String userName;

    @NotEmpty
    private String address;

    @NotEmpty
    @Valid
    private Set<CoffeeCupResource> cups;

    private Double price;
}
