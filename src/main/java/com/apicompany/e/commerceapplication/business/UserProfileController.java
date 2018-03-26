
package com.apicompany.e.commerceapplication.business;

import com.apicompany.e.commerceapplication.dal.dao.daoimpl.UserDAO;
import com.apicompany.e.commerceapplication.dal.entities.User;

public class UserProfileController {
  public User getuserdata(int userid)
  {
      UserDAO myUserDao = new UserDAO();
      User myuser=myUserDao.getUserById(userid);
      return myuser;
  }
  public boolean updateUserData(User user)
  {
    UserDAO myUserDao = new UserDAO();
    boolean res=myUserDao.updateUser(user);
    return res;
  
  }
}
