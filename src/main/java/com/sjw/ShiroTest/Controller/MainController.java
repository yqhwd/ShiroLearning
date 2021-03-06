package com.sjw.ShiroTest.Controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sjw.ShiroTest.Service.AuthService;
import com.sjw.ShiroTest.Service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sjw.ShiroTest.Utils.RoleType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/index")
public class MainController {
	@Autowired
	AuthService authService;

	@Autowired
	MainService mainService;

	public ModelAndView initIndex(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("index.definition");
		return mv;
	}
	
	@RequestMapping(value="/editRole")
	public ModelAndView manageRoles(HttpServletRequest request) throws JsonProcessingException{
		ModelAndView mv = new ModelAndView();
		String[] roleType = new String[RoleType.values().length];
		for(RoleType r : RoleType.values()){
			roleType[r.ordinal()] = r.getRole();
		}
		mv.addObject("roleType",roleType);

		//Search My roles
		HttpSession session = request.getSession();
		List<String> role_list = authService.getRoleListService(session.getAttribute("username").toString());
		ObjectMapper objMapper = new ObjectMapper();
		mv.addObject("myRoles", objMapper.writeValueAsString(role_list));
		
		mv.setViewName("profile.definition");
		return mv;
	}

	@ResponseBody
	@RequestMapping(value="/getSubMenu",method = RequestMethod.POST)
	public String getSubMenu(HttpServletRequest request) throws JsonProcessingException {
		int id_int = Integer.parseInt(request.getParameter("mainMenu"));
		List<Map> subMenu= mainService.getSubMenuService(id_int);
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(subMenu);
	}

}
