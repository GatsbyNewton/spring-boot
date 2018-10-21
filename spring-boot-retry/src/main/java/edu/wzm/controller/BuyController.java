package edu.wzm.controller;

import edu.wzm.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyController {

    @Autowired
    private BuyService buyService;

    @RequestMapping("/buy")
    public String buy(@RequestParam("number") int number){
        try {
            int remainNumber = buyService.buy(number == 0 ? 1 : number);
            System.out.println("库存：" + remainNumber);

            return "下单成功";
        }catch (Exception e){
            e.printStackTrace();
            return "下单失败";
        }
    }
}
