package com.coffee.shop.dao.entity;

import lombok.*;

import javax.persistence.*;

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
