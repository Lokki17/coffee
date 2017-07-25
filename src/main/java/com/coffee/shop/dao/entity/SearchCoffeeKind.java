package com.coffee.shop.dao.entity;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

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
@Document(indexName = "kind", type = "coffeeKind", shards = 1, replicas = 0, refreshInterval = "-1")
public class SearchCoffeeKind{

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private BigDecimal price;
}
