package com.kinghao.dian.service;

import com.kinghao.dian.common.Page;
import com.kinghao.dian.dto.ExampleDo;
import com.kinghao.dian.dto.request.ExampleRequest;

/**
 * @author kinghao
 * @version 2020/7/26 13:42
 */
public interface ExampleService {

    Page<ExampleDo> doService(ExampleRequest exampleRequest);

}
