package apm.web.apmservice;

import apm.web.beans.module.User;

import apm.web.dao.apmmysql.UserDao;
import apm.web.util.apmutil.ApmStringUtil;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 9:49  2018/2/11
 * @description 用户相关业务处理
 */
@Service
public class ApmUserServiceImpl implements UserService {


    @Resource
    private UserDao userDao;


    /**
     * @param user
     * @return 用户名密码是否正确
     * @description 用于查询拓扑图表格
     */
    @Override
    public boolean checkUser(User user) {

        // TODO: 2018/2/11
        user.setName("hcb");
        user.setPwd("pp123");
        if (ApmStringUtil.isEmpty(user.getName()) || ApmStringUtil.isEmpty(user.getPwd())) {
            return false;
        }

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("userName", user.getName());
        hashMap.put("pwd", user.getPwd());

        User dbUser = userDao.checkUser(hashMap);

        if (dbUser == null) {
            return false;
        }

        return true;
    }

    /**
     * @description 查看所有的用户
     */
    @Override
    public List<User> getUserList() {

        return userDao.getUserList();

    }

    /**
     * @param user
     * @return
     * @description 删除用户
     */
    @Override
    public void deleteUserById(User user) throws Exception {

        // TODO: 2018/2/11
        // TODO: 2018/2/11
        // TODO: 2018/2/11
        user = new User();
        user.setId(2);

        if (user == null || user.getId()  == null) {

            throw new Exception("param is empty or param is null");
        }
        userDao.deleteUserById(user.getId());
    }

    /**
     * @param user
     * @description 修改用户
     */
    @Override
    public void updateUser(User user) {

        // TODO: 2018/2/11
        user.setId(1);

        userDao.updateUser(user);
    }

    @Override
    public void addUser(User user) throws Exception {
        if(user == null || ApmStringUtil.isEmpty(user.getName()) ||
                ApmStringUtil.isEmpty(user.getPwd())  || ApmStringUtil.isEmpty(user.getEmail())){
            throw  new Exception("param user is null");

        }
        // TODO: 2018/2/26  正则表达式验证用户邮箱，手机号。
        userDao.addUser(user);


    }
}
