package com.coffee.shop.dao.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "kind", type = "coffeeKind", shards = 1, replicas = 0, refreshInterval = "-1")
public class SearchCoffeeKind {

    @Id
    private String name;

    private String description;

    private BigDecimal price;

    public SearchCoffeeKind(CoffeeKind coffeeKind) {
        this.name = coffeeKind.getName();
        this.description = coffeeKind.getDescription();
        this.price = coffeeKind.getPrice();
    }
}
