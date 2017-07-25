package com.coffee.shop.dao.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * @author Lokki17
 * @since 22.07.2017
 */
@Entity
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Configuration extends AbstractCreatedTimeEntity {

    @Column
    private Integer cupCount;

    @Column
    private BigDecimal discountSumm;

    @Column
    private BigDecimal deliveryPrice;
}
