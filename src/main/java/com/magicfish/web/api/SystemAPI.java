package com.magicfish.web.api;

import com.alibaba.fastjson.JSONObject;
import com.magicfish.weroll.annotation.API;
import com.magicfish.weroll.annotation.Invoker;
import com.magicfish.weroll.annotation.Method;
import com.magicfish.weroll.annotation.Param;
import com.magicfish.weroll.exception.ServiceException;
import com.magicfish.weroll.net.APIAction;
import com.magicfish.weroll.net.ServiceInvoker;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.UUID;

@API(name = "system")
public class SystemAPI {

    @Method(name = "ping",
            params = {
                    @Param(name = "name", type = "string"),
                    @Param(name = "gender", type = "int", defaultValue = "1")
            })
    public Object ping(String name, int gender, APIAction request) throws ServiceException {
        JSONObject result = new JSONObject();
        result.put("ip", request.getRemoteClientIP());
        result.put("time", new Date().toString());
        result.put("name", name);
        result.put("gender", gender);
        return result;
    }

    @Method(name = "uuid",
            params = {
                    @Param(name = "name", type = "string", required = false)
            })
    public Object uuid(String name) throws ServiceException {
        JSONObject result = new JSONObject();
        result.put("name", name);
        result.put("uuid", UUID.randomUUID());
        return result;
    }

    @Invoker(name = "service")
    private ServiceInvoker service;

    @Method(name = "test",
            needLogin = false,
            params = {
//                    @Param(name = "id", type = "int", required = true)
            })
    public Object test() throws ServiceException {

        JSONObject params = new JSONObject();
        params.put("name", "jay");

        return service.call("system.uuid", params);
    }

}
