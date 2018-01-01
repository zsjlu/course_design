package cn.edu.ujs.controller;

import cn.edu.ujs.component.Task1;
import cn.edu.ujs.config.MyConfig;
import cn.edu.ujs.entity.Seat;
import cn.edu.ujs.exception.ReserveException;
import cn.edu.ujs.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by DELL on 2017/12/25.
 */
@RestController
public class helloController {

    @Autowired
    Task1 task1;

    @Autowired
    private MyConfig myConfig;

    @RequestMapping(value = "/hello")
    public String hello() {

        return myConfig.getOpenTime();
    }

    @RequestMapping("/task1")
    public String task1() throws Exception {

        task1.doTaskOne();
        task1.doTaskTwo();
        task1.doTaskThree();
        return "task1";
    }

    @RequestMapping("/nostop")
    public void nostop(){
        System.out.println(Thread.currentThread().getName());
        for(int i = 0; i<100; i++){
            //System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/getThread")
    public void getThread(){
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup = group;
// 遍历线程组树，获取根线程组
        while (group != null) {
            topGroup = group;
            group = group.getParent();
        }
// 激活的线程数加倍
        int estimatedSize = topGroup.activeCount() * 2;
        Thread[] slackList = new Thread[estimatedSize];
// 获取根线程组的所有线程
        int actualSize = topGroup.enumerate(slackList);
// copy into a list that is the exact size
        Thread[] list = new Thread[actualSize];
        System.arraycopy(slackList, 0, list, 0, actualSize);
        System.out.println("Thread list size == " + list.length);
        for (Thread thread : list) {
            System.out.println(thread.getName());
        }
    }
}
