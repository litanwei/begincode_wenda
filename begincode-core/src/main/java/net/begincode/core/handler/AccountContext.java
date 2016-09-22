package net.begincode.core.handler;

import net.begincode.core.cookie.CookieOperation;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.service.BegincodeUserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by saber on 2016/9/9.
 */
@Component
public class AccountContext {
  /**
   * 根据request查询user
   *
   * @param request
   */
  @Resource
  BegincodeUserService begincodeUserService;

  public BegincodeUser getCurrentUser(HttpServletRequest request) {

    return begincodeUserService.findUserByOpenId(CookieOperation.getUser(request));
  }
}
