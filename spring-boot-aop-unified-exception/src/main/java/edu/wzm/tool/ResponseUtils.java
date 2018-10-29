package edu.wzm.tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.wzm.exception.ErrorCode;
import edu.wzm.vo.Request;

public final class ResponseUtils {

    public static String build(ErrorCode code, Request request, Object data){
        JSONObject result = new JSONObject();
        result.put("code", code.getCode());
        result.put("message", code.getMessage());
        result.put("method", request.getMethod());
        result.put("data", data);

        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }

    public static String build(ErrorCode code, String msg, Request request){
        JSONObject result = new JSONObject();
        result.put("code", code.getCode());
        result.put("message", msg);
        result.put("method", request.getMethod());

        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }

    public static String build(ErrorCode code, Request request){
        JSONObject result = new JSONObject();
        result.put("code", code.getCode());
        result.put("message", code.getMessage());
        result.put("method", request.getMethod());

        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }
}
