package com.sjw.ShiroTest.Controller;

import com.sjw.ShiroTest.Settings.RealmForShiro;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sjw.ShiroTest.Utils.RoleType;

@Controller
@RequestMapping("/index")
public class MainController {
	@Autowired
	RealmForShiro realmForShiro;
	
	@RequestMapping(value="/editRole")
	public ModelAndView manageRoles(){
		ModelAndView mv = new ModelAndView();
		String[] roleType = new String[RoleType.values().length];
		for(RoleType r : RoleType.values()){
			roleType[r.ordinal()] = r.getRole();
		}
		mv.addObject("roleType",roleType);

		//Search My roles

		mv.setViewName("profile.definition");
		return mv;
	}

}
