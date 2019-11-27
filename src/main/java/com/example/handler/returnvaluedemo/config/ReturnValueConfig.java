package com.example.handler.returnvaluedemo.config;

import com.example.handler.returnvaluedemo.handler.ExampleHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 liuzhihui
 * @创建时间 2019/11/27
 * @描述  把spingmvc 自带的ReturnValueHandlersList(含@ResponseBody的handler) 替换成带自定义的ReturnValueHandlersList
 */
@Configuration
public class ReturnValueConfig implements InitializingBean {
    @Autowired
    RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> handlerMethodReturnValueHandlers=requestMappingHandlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> handlerMethodReturnValueHandlers1=new ArrayList<>(handlerMethodReturnValueHandlers.size());
        handlerMethodReturnValueHandlers.forEach(handlerMethodReturnValueHandler -> {
            if (handlerMethodReturnValueHandler instanceof RequestResponseBodyMethodProcessor )
                handlerMethodReturnValueHandlers1.add(new ExampleHandler());
            else
                handlerMethodReturnValueHandlers1.add(handlerMethodReturnValueHandler);
        });
        requestMappingHandlerAdapter.setReturnValueHandlers(handlerMethodReturnValueHandlers1);
    }
}
