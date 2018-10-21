package edu.wzm.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class BuyService {

    private final int TOTAL_NUMBER = 10000;

    @Retryable(value = Exception.class, maxAttempts = 4,
            backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public int buy(int number)throws IllegalArgumentException{
        System.out.println("请求重试时间：" + System.currentTimeMillis());
        if (number < 0){
            throw new IllegalArgumentException("购买数量异常。");
        }
        System.out.println("购买成功。");
        return TOTAL_NUMBER - number;
    }

    /**
     *
     * @param e 请求重试失败之后抛出的异常
     * @return
     */
    @Recover
    public int recover(Exception e){
        System.out.println("购买异常");
        e.printStackTrace();
        return TOTAL_NUMBER;
    }
}
