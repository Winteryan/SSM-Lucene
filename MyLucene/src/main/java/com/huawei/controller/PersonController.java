package com.huawei.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huawei.lucene.LuceneIndex;
import com.huawei.model.Person;
import com.huawei.service.IPersonService;

@Controller
@RequestMapping("/personController")
public class PersonController {
    
    private IPersonService personService;
    
    public IPersonService getPersonService() {
        return personService;
    }

    @Autowired
    public void setPersonService(IPersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("/showPerson")
    public String showPersons(Model model){
        List<Person> persons = personService.loadPersons();
        model.addAttribute("persons", persons);
        return "showperson";
    }
    
    @RequestMapping("/searchPerson")
    public String searchPerson(@RequestParam(value = "q", required = false,defaultValue = "") String q,Model model) throws Exception{
        LuceneIndex luceneIndex = new LuceneIndex();
        List<Person> persons = personService.loadPersons();
        for(Person person:persons){
        	luceneIndex.addIndex(person);
        }
        List<Person> persoList = luceneIndex.searchPerson(q);
        model.addAttribute("persons", persoList);
        return "showperson";
    }
    @RequestMapping("/searchPersonq")
    public String searchPersonq(@RequestParam(value = "q", required = false,defaultValue = "") String q,Model model) throws Exception{
    	 LuceneIndex luceneIndex = new LuceneIndex();
        List<Person> persoList = luceneIndex.searchPerson(q);
        model.addAttribute("persons", persoList);
        return "showperson";
    }
}