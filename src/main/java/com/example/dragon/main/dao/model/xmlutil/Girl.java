package com.example.dragon.main.dao.model.xmlutil;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @ClassNAME girl
 * @Description 女孩儿对象
 * @Author XiongMao
 * @Date 2019-8-8
 */
@Data
@XmlRootElement(name = "GIRL")
@XmlAccessorType(XmlAccessType.FIELD)
public class Girl<T> {
    @XmlElement(name = "NAME")
    private String name;
//    @XmlElementWrapper(name = "AGEANDSEXS")
    @XmlElement(name = "AGEANDSEX")
    private T ageAndSex;
}
