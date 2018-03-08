
package com.apicompany.e.commerceapplication.business;

import com.apicompany.e.commerceapplication.dal.dao.daoimpl.UserDAO;
import com.apicompany.e.commerceapplication.dal.models.User;

public class UserProfileController {
  public User getuserdata(int userid)
  {
      UserDAO myUserDao = new UserDAO();
      User myuser=myUserDao.getUser(userid);
      return myuser;
  }
  public boolean updateUserData(User user)
  {
    UserDAO myUserDao = new UserDAO();
    boolean res=myUserDao.updateUser(user);
    return res;
  
  }
}
