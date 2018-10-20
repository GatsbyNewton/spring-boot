package edu.wzm.query;

import com.alibaba.fastjson.annotation.JSONField;
import edu.wzm.tool.ParamAlias;
import org.springframework.context.annotation.Description;

/**
 * @author Jimmy Wong
 * @description 重命名 POST、GET 等请求传 key-value 的参数转换成的实体类
 */
@Description("GET、POST等的key-value型下划线/驼峰请求参数转成之后的驼峰命名属性的POJO")
public class UserGet {

    private String name;

    @ParamAlias(name = "country_code")
    @JSONField(name = "country_code")
    private String countryCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "UserGet{" +
                "name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
