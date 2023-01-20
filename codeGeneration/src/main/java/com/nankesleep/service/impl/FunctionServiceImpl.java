package com.nankesleep.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nankesleep.domain.entity.Function;
import com.nankesleep.mapper.FunctionMapper;
import com.nankesleep.service.FunctionService;
import org.springframework.stereotype.Service;

/**
 * 功能信息表
(Function)表服务实现类
 *
 * @author nankesleep
 * @since 2023-01-16 19:18:24
 */
@Service("functionService")
public class FunctionServiceImpl extends ServiceImpl<FunctionMapper, Function> implements FunctionService {

}
