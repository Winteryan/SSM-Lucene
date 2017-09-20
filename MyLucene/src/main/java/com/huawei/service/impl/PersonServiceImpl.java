package com.huawei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huawei.dao.PersonMapper;
import com.huawei.model.Person;
import com.huawei.service.IPersonService;

@Service("personService")
public class PersonServiceImpl implements IPersonService {
    
    private PersonMapper personMapper;

    public PersonMapper getPersonMapper() {
        return personMapper;
    }
    @Autowired
    public void setPersonMapper(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    public List<Person> loadPersons() {
        return personMapper.queryAll();
    }
	public int save(Person person) {
		// TODO Auto-generated method stub
		return personMapper.save(person);
	}
	public void update(Person person) {
		// TODO Auto-generated method stub
		personMapper.update(person);
	}
	public void delete(String id) {
		// TODO Auto-generated method stub
		personMapper.delete(id);
	}
    
}