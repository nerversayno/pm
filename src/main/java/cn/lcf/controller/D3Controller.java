package cn.lcf.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * User: lcf
 * Date: 2014/10/20
 * Time: 11:16
 * Description:
 */
@Controller
public class D3Controller {
    @RequestMapping(value = "/generateData")
    public ResponseEntity generateData(){
        List list = new ArrayList<Integer>();
        for(int i=0; i<15;i++){
            list.add(new Random().nextInt(50));
        }
       return new ResponseEntity(list, HttpStatus.OK) ;
    }
}
