package com.multi.gradle.module.data.mybatis.repository;

import com.multi.gradle.module.data.mybatis.core.DefaultMapper;

/**
 * Created by dhokim on  2018-10-22
 */
@DefaultMapper
public interface ListMapper {
    Integer totalCount();
}
