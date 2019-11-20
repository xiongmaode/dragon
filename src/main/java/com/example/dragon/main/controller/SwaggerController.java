package com.example.dragon.main.controller;

import com.example.dragon.main.service.SwaggerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassNAME SwaggerController
 * @Description 通过swagger调用service实现service脚本
 * @Author Xiongmao
 * @Date 2019-10-25
 */
@Slf4j
@RestController
@RequestMapping("/swagger")
public class SwaggerController {

    @Autowired
    private SwaggerService swaggerService;

    /**
     * 修改评估表原有的评估关系id为默认评估关系
     *
     * @return String
     */
    public String updateEvaluationId(){
        String result = swaggerService.updateEvaluationId();
        return result;
    }
}
