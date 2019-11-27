package com.example.handler.returnvaluedemo.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.handler.returnvaluedemo.annotation.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ExampleHandler implements HandlerMethodReturnValueHandler {

    /**
     * 表示是否支持该返回类型
     */
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        boolean hasJSONAnno = methodParameter.getMethodAnnotation(ResponseJson.class) != null || methodParameter.getMethodAnnotation(ResponseJson.class) != null;
        return hasJSONAnno;
    }

    /**
     * o    就是接口返回值
     * methodParameter  方法参数
     */
    @Override
    public void  handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest)throws Exception {
    modelAndViewContainer.setRequestHandled(true);

    HttpServletResponse response =  (HttpServletResponse)nativeWebRequest.getNativeResponse(HttpServletResponse.class);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding("UTF-8");

    this.log.info("请求成功，返回消息转换");

    Map<String, Object> rsp = new HashMap();

    rsp.put("code", "0");
    rsp.put("message", "成功");
    if (o != null)
        rsp.put("data", o);

    this.returnMsg(rsp, response);
    }

    private void returnMsg(Object msg, HttpServletResponse response) {

        try(PrintWriter writer = response.getWriter();){
            response.setContentType("text/plain;charset=UTF-8");
            writer.write(this.objToJsonString(msg));
            writer.flush();
        } catch (IOException var8) {
            this.log.error("统一格式返回", var8);
        }

    }

    private String objToJsonString(Object msg) {
        return JSON.toJSONString(msg, new SerializerFeature[]{SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullBooleanAsFalse});
    }


}
