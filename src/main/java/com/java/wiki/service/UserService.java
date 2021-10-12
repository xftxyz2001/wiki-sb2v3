package com.java.wiki.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.wiki.entity.User;
import com.java.wiki.entity.UserExample;
import com.java.wiki.exception.BusinessException;
import com.java.wiki.exception.BusinessExceptionCode;
import com.java.wiki.mapper.UserMapper;
import com.java.wiki.req.UserLoginReq;
import com.java.wiki.req.UserQueryReq;
import com.java.wiki.req.UserResetPasswordReq;
import com.java.wiki.req.UserSaveReq;
import com.java.wiki.resp.PageResp;
import com.java.wiki.resp.UserLoginResp;
import com.java.wiki.resp.UserQueryResp;
import com.java.wiki.util.CopyUtil;
import com.java.wiki.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private SnowFlake snowFlake;


    public PageResp<UserQueryResp> list(UserQueryReq req) {

        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getLoginName())) {
            criteria.andLoginNameEqualTo(req.getLoginName());
        }

        PageHelper.startPage(req.getNum(), req.getSize());
        List<User> userList = userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo<>(userList);

        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        PageResp<UserQueryResp> pageResp = new PageResp<>();

        List<UserQueryResp> list = CopyUtil.copyList(userList, UserQueryResp.class);

        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;

    }

    //保存
    public void save(UserSaveReq req) {
        User user = CopyUtil.copy(req, User.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            if (ObjectUtils.isEmpty(this.selectByLoginName(req.getLoginName()))) {
                //新增
                user.setId(snowFlake.nextId());
                userMapper.insert(user);
            } else {
                // 用户名已经已存在
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
            }
        } else {
            //更新
            user.setLoginName(null);
            user.setPassword(null);
            userMapper.updateByPrimaryKeySelective(user);
        }
    }

    /**
     * 删除用户
     *
     * @param id
     */
    public void delete(Long id) {
        userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据登录名查询用户对象
     *
     * @param loginName
     * @return
     */
    public User selectByLoginName(String loginName) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
        List<User> userList = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    //修改密码
    public void resetPassword(UserResetPasswordReq req) {
        User user = CopyUtil.copy(req, User.class);
        userMapper.updateByPrimaryKeySelective(user);
    }

    //登录校验
    public UserLoginResp login(UserLoginReq req) {
        User userDb = selectByLoginName(req.getLoginName());
        if (ObjectUtils.isEmpty(userDb)) {
            LOG.info("用户名不存在,{}", req.getLoginName());
            throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
        } else {
            if (userDb.getPassword().equals(req.getPassword())) {
                //登录成功
                return CopyUtil.copy(userDb, UserLoginResp.class);
            } else {
                LOG.info("密码不对，输入密码：{},数据库密码：{}", req.getPassword(), userDb.getPassword());
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
            }
        }
    }
}
