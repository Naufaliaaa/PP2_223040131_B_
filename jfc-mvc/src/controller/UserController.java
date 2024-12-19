/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Naufal Zul Faza
 */

import model.*;
import view.UserView;
import org.apache.ibatis.session.SqlSession; 
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserController {
    private UserView view;
    private UserMapper mapper;
    private SqlSession session; 
    
    public UserController(UserView view, UserMapper mapper) {
        this.view = view;
        this.mapper = mapper;
        this.session = MyBatisUtil.getSqlSession(); 
        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshListener(new RefreshListener());
    }
    
    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameInput();
            String email = view.getEmailInput();
            if (!name.isEmpty() && !email.isEmpty()) {
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                try {
                    mapper.insertUser(user); 
                    session.commit(); 
                    JOptionPane.showMessageDialog(view, "User added successfully!");
                    refreshUserList(); 
                } catch (Exception ex) {
                    session.rollback(); 
                    JOptionPane.showMessageDialog(view, "Error adding user: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please fill in all fields.");
            }
        }
    }
    
    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshUserList(); 
        }
    }
    
    private void refreshUserList() {
        List<User> users = mapper.getAllUsers();
        String[] userArray = users.stream()
            .map(u -> u.getName() + " (" + u.getEmail() + ")")
            .toArray(String[]::new);
        view.setUserList(userArray); 
    }
}

