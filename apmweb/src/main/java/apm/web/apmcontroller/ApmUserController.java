package apm.web.apmcontroller;

import apm.web.apmservice.UserService;
import apm.web.beans.module.User;
import apm.web.util.apmutil.JsonObject;

import org.apache.hadoop.hbase.shaded.org.apache.avro.data.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChengBing Han
 * @date 16:42  2018/2/8
 * @description 用户相关类
 */

@CrossOrigin
@Controller
public class ApmUserController {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Resource
    UserService userService;

    /**
     * @param user (name,pwd)
     * @return 用户名密码是否正确
     * @description
     */
    @RequestMapping(value = "/checkUser", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<Boolean> checkUser(User user) {


        JsonObject<Boolean> json = null;

        try {

            Boolean loginSuccess = userService.checkUser(user);
            json = new JsonObject<>();
            json.setSuccess(true);
            json.setObj(loginSuccess);
            json.setResponeCode(200);

        } catch (Exception e) {

            logger.error(e.getMessage());

            json = new JsonObject<>();
            json.setSuccess(false);
            json.setResponeCode(1000);
            json.setErrMsg(e.getMessage());


        }


        return json;
    }


    /**
     * @description 添加用户
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<User> addUser(User user){
        JsonObject<User> json = null;

        try{

            userService.addUser(user);
            List<User> userList = userService.getUserList();
            json = new JsonObject<>();
            json.setSuccess(true);
            json.setResponeCode(200);
            json.setObjectList(userList);


        }catch (Exception e){

            json = new JsonObject<>();
            json.setSuccess(false);
            json.setErrMsg(e.getMessage());
            json.setResponeCode(1002);
            logger.error(e.getMessage());
        }
           return json;

    }


    /**
     * @description 查看所有的用户
     */
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<User> getUserList() {

        JsonObject<User> json = null;


        try {

            List<User> userList = userService.getUserList();

            json = new JsonObject<>();
            json.setResponeCode(200);
            json.setSuccess(true);
            json.setObjectList(userList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            json = new JsonObject<>();
            json.setSuccess(false);
            json.setErrMsg(e.getMessage());
            json.setResponeCode(1000);

        }
        return json;
    }

    /**
     * @description 删除用户
     */
    @RequestMapping(value = "/deleteUserById", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<Boolean> deleteUserById(User user) {

        JsonObject<Boolean> json = null;


        try {
            userService.deleteUserById(user);
            json = new JsonObject<>();

            json.setObj(true);

            json.setResponeCode(200);
            json.setSuccess(true);

        } catch (Exception e) {
            logger.error(e.getMessage());
            json = new JsonObject<>();
            json.setSuccess(false);
            json.setErrMsg(e.getMessage());
            json.setResponeCode(1001);


        }

        return json;
    }

    /**
     * @description 修改用户
     */
    @RequestMapping(value = "/updateUserById", method = RequestMethod.GET)
    @ResponseBody

    public JsonObject<User> updateUserById(User user) {

        JsonObject<User> json = null;


        try {

            userService.updateUser(user);

            List<User> userList = userService.getUserList();
            json = new JsonObject<>();
            json.setResponeCode(200);
            json.setSuccess(true);
            json.setObjectList(userList);

        } catch (Exception e) {
            logger.error(e.getMessage());
            json = new JsonObject<>();
            json.setSuccess(false);
            json.setResponeCode(1001);
            json.setErrMsg(e.getMessage());
        }

      return json;
    }


}
