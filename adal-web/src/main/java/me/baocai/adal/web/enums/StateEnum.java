package me.baocai.adal.web.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StateEnum {
    ACTIVATED(true, "启用"), DEACTIVATED(false, "禁用");

    @EnumValue//标记数据库存的值是code
    private final boolean code;
    @JsonValue
    private final String descp;

    StateEnum(boolean code, String descp) {
        this.code = code;
        this.descp = descp;
    }

    @Override
    public String toString() {
        return "StateEnum{" +
                "code=" + code +
                ", descp='" + descp + '\'' +
                '}';
    }
}
