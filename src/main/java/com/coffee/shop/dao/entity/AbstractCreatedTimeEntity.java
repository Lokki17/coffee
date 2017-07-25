package com.coffee.shop.dao.entity;

import com.coffee.shop.dao.converter.LocalDateTimeConverter;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@MappedSuperclass
public abstract class AbstractCreatedTimeEntity extends AbstractEntity {

    @Column(nullable = false, updatable = false)
    @Getter
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdTime;

    @PrePersist
    private void setCreatedTime() {
        if (createdTime == null) {
            createdTime = ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime();
        }
    }
}
