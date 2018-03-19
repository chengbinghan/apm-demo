package apm.web.apmservice;

import apm.web.beans.module.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ChengBing Han
 * @date 9:49  2018/2/11
 * @description
 */
public interface UserService {

    /**
     * @param user
     * @return 用户名密码是否正确
     * @description 用于查询拓扑图表格
     */
    boolean checkUser(User user);

    /**
     * @description 查看所有的用户
     */
    List<User> getUserList();

    /**
     * @description 删除用户
     * @param user
     * @return
     */
    void deleteUserById(User user) throws Exception;

    /**
     * @description 修改用户
     * @param user
     */
    void updateUser(User user);

    void addUser(User user) throws Exception;
}
