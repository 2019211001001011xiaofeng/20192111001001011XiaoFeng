package com.XiaoFeng.dao;

import com.XiaoFeng.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDao implements IUserDao {

    @Override
    public boolean saveUser(Connection con, User user) throws SQLException {
        String sql = "INSERT INTO usertable(username,password,email,gender,birthdate) VALUES(?,?,?,?,?)";
        PreparedStatement st;
        st = con.prepareStatement(sql);
        st.setString(1, user.getUsername());
        st.setString(2, user.getPassword());
        st.setString(3, user.getEmail());
        st.setString(4, user.getGender());
        st.setDate(5, (java.sql.Date) user.getBirthDate());
        int resultSet = st.executeUpdate();
        if (resultSet > 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int deleteUser(Connection con, User user) throws SQLException {
        String sql = "DELETE FROM usertable WHERE username=?";
        PreparedStatement st;
        st = con.prepareStatement(sql);
        st.setString(1, user.getUsername());
        return st.executeUpdate();
    }

    @Override
    public int updateUser(Connection con, User user) throws SQLException {
        String sql = "UPDATE usertable SET password=?,email=?,gender=?,birthdate=?,username=? WHERE id=?";
        PreparedStatement st;
        st = con.prepareStatement(sql);
        st.setString(1, user.getPassword());
        st.setString(2, user.getEmail());
        st.setString(3, user.getGender());
        st.setDate(4, (java.sql.Date) user.getBirthDate());
        st.setString(5, user.getUsername());
        st.setInt(6, user.getId());
        return st.executeUpdate();

    }

    @Override
    public User findById(Connection con, Integer id) throws SQLException {
        String sql = "SELECT * FROM usertable WHERE id=?";
        PreparedStatement st = con.prepareStatement(sql);
        User user = null;
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthDate(rs.getDate("birthdate"));

        }

        return user;
    }

    @Override
    public User findByUsernamePassword(Connection con, String username, String password) throws SQLException {
        String sql = "SELECT * FROM usertable WHERE username=? and password=?";
        User user = null;
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, username);
        st.setString(2, password);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthDate(rs.getDate("birthdate"));

        }

        return user;
    }

    @Override
    public List<User> findByUsername(Connection con, String username) throws SQLException {
        String sql = "SELECT * FROM usertable WHERE username=?";
        User user = null;
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, user.getUsername());
        ResultSet rs = st.executeQuery();
        List<User> List = new ArrayList<>();
        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthDate(rs.getDate("birthdate"));
            List.add(user);
        }

        return List;
    }

    @Override
    public List<User> findByPassword(Connection con, String password) throws SQLException {
        String sql = "SELECT * FROM usertable WHERE password=?";
        User user = null;
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, user.getPassword());
        ResultSet rs = st.executeQuery();
        List<User> List = new ArrayList<>();
        // User user=new User();
        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthDate(rs.getDate("birthdate"));
            List.add(user);
        }
        return List;
    }

    @Override
    public List<User> findByEmail(Connection con, String email) throws SQLException {
        String sql = "SELECT * FROM usertable WHERE email=?";
        User user = null;
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, user.getEmail());
        ResultSet rs = st.executeQuery();
        List<User> List = new ArrayList<>();
        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthDate(rs.getDate("birthdate"));
            List.add(user);
        }

        return List;
    }

    @Override
    public List<User> findByGender(Connection con, String gender) throws SQLException {
        String sql = "SELECT * FROM usertable WHERE gender=?";
        User user = null;
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, user.getGender());
        ResultSet rs = st.executeQuery();
        List<User> List = new ArrayList<>();
        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthDate(rs.getDate("birthdate"));
            List.add(user);
        }

        return List;
    }

    @Override
    public List<User> findByBirthdate(Connection con, Date birthDate) throws SQLException {
        String sql = "SELECT * FROM usertable WHERE birthdate=?";
        User user = null;
        PreparedStatement st = con.prepareStatement(sql);
        st.setDate(1, (java.sql.Date) user.getBirthDate());
        ResultSet rs = st.executeQuery();
        List<User> List = new ArrayList<>();
        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthDate(rs.getDate("birthdate"));
            List.add(user);
        }

        return List;
    }

    @Override
    public List<User> findAllUser(Connection con) throws SQLException {

        String sql = "SELECT * FROM usertable";
        PreparedStatement st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        User user = null;
        List<User> List = new ArrayList<>();
        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthDate(rs.getDate("birthdate"));
            List.add(user);
        }

        return List;
    }
}
