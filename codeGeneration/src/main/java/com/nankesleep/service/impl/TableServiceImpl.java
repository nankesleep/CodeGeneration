package com.nankesleep.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nankesleep.domain.ResponseResult;
import com.nankesleep.domain.entity.Field;
import com.nankesleep.domain.entity.Table;
import com.nankesleep.domain.entity.UserProject;
import com.nankesleep.domain.vo.PageVo;
import com.nankesleep.domain.vo.TableInfoVo;
import com.nankesleep.domain.vo.TableVo;
import com.nankesleep.mapper.TableMapper;
import com.nankesleep.service.TableService;
import com.nankesleep.service.UserService;
import com.nankesleep.untils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nankesleep.service.FieldService;

import java.util.List;
import java.util.function.Consumer;

/**
 * 表信息表(Table)表服务实现类
 *
 * @author nankesleep
 * @since 2023-01-10 19:58:56
 */
@Service("tableService")
public class TableServiceImpl extends ServiceImpl<TableMapper, Table> implements TableService {

    @Autowired
    private UserService userService;
    @Autowired
    private FieldService fieldService;

    //查询创建人员、更新人员和字段数
    public Table getNickNameAndCount(Table table){
        QueryWrapper<Field> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("pid,count(*) as fieldCount");
        queryWrapper.eq("pid",table.getId());
        Field field = fieldService.getOne(queryWrapper);
        table.setFieldCount(field.getFieldCount());
        table.setCreateName(userService.getById(table.getCreateBy()).getNickName());
        table.setUpdateName(userService.getById(table.getUpdateBy()).getNickName());
        return table;
    }

    //分页查询项目表
    @Override
    public ResponseResult getTableList(Long id, Integer pageNum, Integer pageSize) {
        //查询项目中所有的表
        LambdaQueryWrapper<Table> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Table::getPid,id);
        //分页查询
        Page<Table> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);
        List<Table> tableList = page.getRecords();
        tableList.stream().forEach(this::getNickNameAndCount);
        //封装vo
        List<TableVo> tableVos = BeanCopyUtils.copyBeanList(tableList, TableVo.class);
        PageVo pageVo = new PageVo(tableVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    //单个查询
    @Override
    public ResponseResult getTableById(Long id) {
        Table table =  getById(id);
        TableInfoVo TableInfoVo = BeanCopyUtils.copyBean(table, TableInfoVo.class);
        return ResponseResult.okResult(TableInfoVo);
    }

    //删除表
    @Override
    public ResponseResult deleteTable(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }

    //修改表
    @Override
    public ResponseResult updateTable(Table table) {
        updateById(table);
        return ResponseResult.okResult();
    }

    //添加表
    @Override
    public ResponseResult addTable(Table table) {
        save(table);
        return ResponseResult.okResult();
    }
}
