package com.jy.controller;

import com.jy.controller.result.JsonResult;
import com.jy.controller.valid.group.Insert;
import com.jy.controller.valid.group.Update;
import com.jy.dao.GirlRepository;
import com.jy.entity.Girl;
import com.jy.constants.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "girl")
@RestController
public class GirlController {

    private Logger logger = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlRepository girlRepository;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Object list() {
        return JsonResult.success("girls", girlRepository.findAll());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Object detail(@PathVariable("id") Long id) {
        return JsonResult.success("girl", girlRepository.findOne(id));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object newGirl(@Validated(Insert.class) Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info(ErrorCode.getDescribe(bindingResult.getFieldError().getDefaultMessage()));
            return JsonResult.newInstance(bindingResult.getFieldError().getDefaultMessage()).setMsg(ErrorCode.getDescribe(bindingResult.getFieldError().getDefaultMessage()));
        }
        return JsonResult.success("girl", girlRepository.save(girl));
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Object updateGirl(@Validated(Update.class) Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info(ErrorCode.getDescribe(bindingResult.getFieldError().getDefaultMessage()));
            return JsonResult.newInstance(bindingResult.getFieldError().getDefaultMessage()).setMsg(ErrorCode.getDescribe(bindingResult.getFieldError().getDefaultMessage()));
        }
        return JsonResult.success("girl", girlRepository.save(girl));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Object removeGirl(@PathVariable("id") Long id) {
        girlRepository.delete(id);
        return JsonResult.success();
    }


    @RequestMapping(value = "findByAge", method = RequestMethod.GET)
    public Object findByAge(@RequestParam(value = "age") Integer age) {
        return JsonResult.success("girls", girlRepository.findByAge(age));
    }

}
