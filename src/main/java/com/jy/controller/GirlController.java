package com.jy.controller;

import com.jy.controller.valid.group.Insert;
import com.jy.controller.valid.group.Update;
import com.jy.dao.GirlRepository;
import com.jy.entity.Girl;
import com.jy.constants.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public Object newGirl(@Validated(Insert.class) Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(ErrorCode.getDescribe(bindingResult.getFieldError().getDefaultMessage()));
            return bindingResult.getFieldError().getDefaultMessage();
        }
        return girlRepository.save(girl);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Object updateGirl(@Validated(Update.class) Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(ErrorCode.getDescribe(bindingResult.getFieldError().getDefaultMessage()));
            return bindingResult.getFieldError().getDefaultMessage();
        }
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
