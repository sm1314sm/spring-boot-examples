package com.neo.controller;

import com.neo.config.BaseResult;
import com.neo.domain.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

@Api(value = "用户管理", description = "用户管理API", position = 100, protocols = "http")
@RestController
@RequestMapping(value = "/user")
public class UserController {
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());

    @ApiOperation(value = "获取用户列表", notes = "查询用户列表")
    @RequestMapping(value = {""}, method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 100, message = "异常数据")
    })
    public List<User> getUserList() {
        return new ArrayList<>(users.values());
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "age", value = "年龄", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "ipAddr", value = "ip哟", dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public BaseResult<User> postUser(@ApiIgnore User user) {
        users.put(user.getId(), user);
        return BaseResult.successWithData(user);
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }

    /**
     * 1. name ：参数名。
     * 2. value ： 参数的具体意义，作用。
     * 3. required ： 参数是否必填。
     * 4. dataType ：参数的数据类型。
     * 5. paramType ：查询参数类型，这里有几种形式：
     * path 以地址的形式提交数据
     * query 直接跟参数完成自动映射赋值
     * body 以流的形式提交 仅支持POST
     * header 参数在request headers 里边提交
     * form 以form表单的形式提交 仅支持POST
     */
    @ApiOperation(value = "更新用户信息", notes = "根据用户ID更新信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "age", value = "年龄", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public BaseResult<User> putUser(@PathVariable Long id, @ApiIgnore User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return BaseResult.successWithData(u);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }

    @RequestMapping(value = "/ignoreMe/{id}", method = RequestMethod.DELETE)
    public String ignoreMe(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }
}