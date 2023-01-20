package com.nankesleep.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nankesleep.domain.ResponseResult;
import com.nankesleep.domain.entity.Table;


/**
 * 表信息表(Table)表服务接口
 *
 * @author nankesleep
 * @since 2023-01-10 19:58:55
 */
public interface TableService extends IService<Table> {

    ResponseResult getTableList(Long id, Integer pageNum, Integer pageSize);

    ResponseResult getTableById(Long id);

    ResponseResult deleteTable(Long id);

    ResponseResult updateTable(Table table);

    ResponseResult addTable(Table table);
}

