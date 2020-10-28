package com.example.dragon.main.dao.model.base;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * @author: Songxinwei
 * @date: 2020/10/20
 **/
@Data
public class Page<T> {
    /**
     * 页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;
    /**
     * 总数
     */
    private long total;

    private List<T> list;

    public Page(List<T> list) {
        PageInfo page = new PageInfo(list);
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.total = page.getTotal();
        this.list = list;
    }
}
