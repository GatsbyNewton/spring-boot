package edu.wzm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.wzm.vo.UserVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jimmy Wong
 * @description 重命名 POST、GET 等请求传 key-value 的参数
 */
@RestController
public class GetPostKVParamController {

    /**
     * curl -d 'name=jimmy&country_code=zh_CN' http://localhost:8080/post_kv
     * curl -d 'name=jimmy&countryCode=zh_CN' http://localhost:8080/post_kv
     * @param user
     * @return
     */
    @RequestMapping(value = "/post_kv", method = RequestMethod.POST)
    public String post(UserVo user){
        return JSON.toJSONString(user, SerializerFeature.WriteMapNullValue);
    }

    /**
     * curl "http://localhost:8080/get_kv?name=jimmy&country_code=zh_CN"
     * curl "http://localhost:8080/get_kv?name=jimmy&countryCode=zh_CN"
     * @param user
     * @return
     */
    @RequestMapping(value = "/get_kv", method = RequestMethod.GET)
    public String get(UserVo user){
        return JSON.toJSONString(user, SerializerFeature.WriteMapNullValue);
    }
}
