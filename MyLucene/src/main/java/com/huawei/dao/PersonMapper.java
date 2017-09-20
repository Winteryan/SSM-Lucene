package com.huawei.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huawei.model.Person;

public interface PersonMapper {
    /**
     * 插入一条记录
     * @param person
     */
    void insert(Person person);
    
    /**
     * 查询所有
     * @return
     */
    List<Person> queryAll();

	int save(Person person);

	void update(Person person);

	void delete(@Param("id") String id);
}