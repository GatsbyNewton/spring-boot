package edu.wzm.vo;

import com.alibaba.fastjson.annotation.JSONField;
import edu.wzm.tool.ParamAlias;

public class UserVo {

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
        return "UserVo{" +
                "name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
