package com.coffee.shop.dao.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeKind extends AbstractEntity {

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private BigDecimal price;
}
