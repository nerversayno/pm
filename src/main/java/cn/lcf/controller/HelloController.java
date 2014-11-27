package cn.lcf.controller;

import cn.lcf.dao.IUserDao;
import cn.lcf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.sql.DataSource;
import java.util.Date;

public class HelloController {

    @Autowired
    IUserDao userDao;
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(@RequestParam(value = "date" ,required = false,defaultValue = "2014-9-25") String date,
                            ModelMap model,ServletRequest servletRequest) {
      model.addAttribute("hello","hello "+new Date());
        return "hello" ;
    }

    @ResponseBody
	@RequestMapping(value = "/printWelcome",method = RequestMethod.GET)
	public ResponseEntity<String> printWelcome(ModelMap model,ServletRequest servletRequest) {
        StringBuilder sb = new StringBuilder();
        sb.append("{LocalAddr: " + servletRequest.getLocalAddr()+",      ");
        sb.append("LocalPort: " + servletRequest.getLocalPort()+",       ");
        sb.append("RemoteAddr: "+servletRequest.getRemoteAddr()+",       ");
        sb.append("RemotePort: "+servletRequest.getRemotePort()+"}");
        return new ResponseEntity(sb.toString(), HttpStatus.OK);
	}
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public ResponseEntity printWelcome(ModelMap model,@RequestParam(required = false) User user) {
        System.out.println(user);
        model.addAttribute("message",user);
        return new ResponseEntity(user, HttpStatus.OK);
    }
    @RequestMapping(value = "/user/add",method = RequestMethod.GET)
    public ResponseEntity add(ModelMap model,@RequestParam(required = false) User user) {
        if(user == null){
            user = new User();
        }
        user.setId(String.valueOf(System.currentTimeMillis()));
        user.setName("lcf");
        user.setPassword("winsion");
        boolean b  = userDao.add(user) ;
        System.out.println(user);
        model.addAttribute("message",user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

}