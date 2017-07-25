package com.coffee.shop.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * @author Lokki17
 * @since 22.07.2017
 */
@Entity
@Table(name = "order_")
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Order extends AbstractEntity {

    @Column
    private String userName;

    @Column
    private String address;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = CoffeeCup.class)
    private Set<CoffeeCup> cups;

    @Column
    private BigDecimal price;
}
