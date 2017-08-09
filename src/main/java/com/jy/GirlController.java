package com.jy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "girl")
@RestController
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Object list() {
        return girlRepository.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Object detail(@PathVariable("id") Long id) {
        return girlRepository.findOne(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object newGirl(@RequestParam(value = "cupSize") String cupSize, @RequestParam(value = "age") Integer age) {
        Girl girl = new Girl();
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return girlRepository.save(girl);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Object updateGirl(@RequestParam(value = "cupSize") String cupSize, @RequestParam(value = "age") Integer age, @PathVariable(value = "id") Long id) {
        Girl girl = new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return girlRepository.save(girl);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void removeGirl(@PathVariable("id") Long id) {
        girlRepository.delete(id);
    }


    @RequestMapping(value = "findByAge", method = RequestMethod.GET)
    public Object findByAge(@RequestParam(value = "age") Integer age) {
        return girlRepository.findByAge(age);
    }

}
