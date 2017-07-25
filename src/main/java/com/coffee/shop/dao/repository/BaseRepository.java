package com.coffee.shop.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
interface BaseRepository<T> extends JpaRepository<T, Long> {

}
