package com.huawei.service;

import java.util.List;

import com.huawei.model.Person;

public interface IPersonService {

    /**
     * 加载全部的person
     * @return
     */
    List<Person> loadPersons();

	int  save(Person person);

	void update(Person person);
}