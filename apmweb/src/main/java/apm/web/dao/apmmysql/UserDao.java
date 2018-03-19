package apm.web.dao.apmmysql;

import apm.web.beans.module.Monitor;
import apm.web.beans.module.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 9:58  2018/2/11
 * @description
 */
public interface UserDao {
    User checkUser(Map<String, String> hashMap);

    List<User> getUserList();

    void deleteUserById(Integer id);

    void updateUser(User user);

    List<User> getUserListByIds(@Param("userIdArr") String[] userIdArr);

    void addUser(User user);
}
