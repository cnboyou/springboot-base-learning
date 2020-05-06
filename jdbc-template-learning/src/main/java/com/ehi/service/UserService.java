package com.ehi.service;

import com.ehi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

/**
 * ClassName: UserService
 *
 * @Author: WangYiHai
 * @Date: 2020/5/6 17:58
 * @Description: TODO
 */
@Service
public class UserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /*增*/

    /**
     * 除了查询有几个API之外，增删改统一都使用update来操作
     * @param user
     * @return SQL执行受影响的行数
     */
    public int addUser(User user) {
        String sql = "insert into user (username,address) values (?,?);";
        return jdbcTemplate.update(sql, user.getUsername(), user.getAddress());
    }

    /**
     * 数据插入的过程中希望实现主键回填，那么可以使用PreparedStatementCreator
     * @param user
     * @return
     */
    public int addUser2(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into user (username,address) values (?,?);";
        int update = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getAddress());
                return ps;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
        System.out.println(user);
        return update;
    }

    /*删*/

    public int deleteUserById(Long id) {
        return jdbcTemplate.update("delete from user where id=?", id);
    }

    /*改*/

    public int updateUserById(User user) {
        return jdbcTemplate.update("update user set username=?,address=? where id=?",
                user.getUsername(), user.getAddress(),user.getId());
    }

    /*查*/

    public List<User> getAllUsers() {
        return jdbcTemplate.query("select * from user", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                String username = resultSet.getString("username");
                String address = resultSet.getString("address");
                long id = resultSet.getLong("id");
                User user = new User();
                user.setAddress(address);
                user.setUsername(username);
                user.setId(id);
                return user;
            }
        });
    }

    public List<User> getAllUsers2() {
        return jdbcTemplate.query("select * from user", new BeanPropertyRowMapper<>(User.class));
    }
}