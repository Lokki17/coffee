package com.coffee.shop.dao.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

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
