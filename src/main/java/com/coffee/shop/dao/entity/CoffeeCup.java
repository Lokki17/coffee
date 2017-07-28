package com.coffee.shop.dao.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author Lokki17
 * @since 22.07.2017
 */
@Entity
@Table
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeCup extends AbstractEntity {

    @OneToOne
    @JoinColumn(updatable = false)
    private CoffeeKind coffeeKind;

    @Column
    private Integer count;
}
