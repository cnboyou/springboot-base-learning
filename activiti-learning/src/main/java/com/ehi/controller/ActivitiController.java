package com.ehi.controller;

import com.ehi.common.ResponseMsg;
import com.ehi.model.Activiti;
import com.ehi.model.ActivitiTask;
import com.ehi.model.User;
import com.ehi.service.ActivitiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: a
 *
 * @Author: WangYiHai
 * @Date: 2020/5/8 17:37
 * @Description: TODO
 */
@Controller
@RequestMapping("/activity")
public class ActivitiController {

    @Autowired
    private ActivitiService activitiService;

    @RequestMapping(value = "/allRecord", method = RequestMethod.GET)
    public String allRecord(HttpSession session,Model model) {
        //myActiviti();
        List<Activiti> list = myActiviti(session);
        model.addAttribute("list",list);
        //myApproval();
        List<ActivitiTask> list3 = myApproval(session);
        model.addAttribute("list3",list3);
        //myActivitiRecord();
        List<Activiti> list2 = myActivitiRecord(session);
        model.addAttribute("list2",list2);
        //myApprovalRecord();
        List<Activiti> list4 = myApprovalRecord(session);
        model.addAttribute("list4",list4);
        return "activitiList";
    }


    @RequestMapping(value = "/createActiviti", method = RequestMethod.POST)
    @ResponseBody
    public Object createActiviti(Activiti vac,HttpSession session){
        ResponseMsg<Object> msg = new ResponseMsg<Object>();
        User user=(User)session.getAttribute("user");
        String userName = user.getUsername();
        activitiService.startActiviti(vac, userName);
        msg.setCode(0);
        return msg;
    }

    //我正在申请的假
    @RequestMapping(value = "/myActiviti", method = RequestMethod.GET)
    public List<Activiti> myActiviti(HttpSession session) {
        User user=(User)session.getAttribute("user");
        String userName = user.getUsername();
        List<Activiti> list = activitiService.myActiviti(userName);
        return list;
    }

    /**
     * 待我审核的请假
     * @param session
     * @return
     */
    @RequestMapping(value = "/myApproval", method = RequestMethod.GET)
    public List<ActivitiTask> myApproval(HttpSession session) {
        User user=(User)session.getAttribute("user");
        //String userName = user.getUsername();
        String userName = "huangxu2";
        List<ActivitiTask> list3 = activitiService.myApproval(userName);
        return list3;
    }

    @RequestMapping(value = "/passApproval", method = RequestMethod.POST)
    @ResponseBody
    public Object passApproval(String id,String result,HttpSession session) {
        ResponseMsg<Object> msg = new ResponseMsg<Object>();
        User user=(User)session.getAttribute("user");
        ActivitiTask activitiTask = new ActivitiTask();
        Activiti activiti = new Activiti();
        activitiTask.setId(id);
        activiti.setResult(result);
        activitiTask.setActiviti(activiti);
        String userName = user.getUsername();
        activitiService.passApproval(userName, activitiTask);
        msg.setCode(0);
        return msg;
    }

    /**
     * 我申请过的假
     * @param session
     * @return
     */
    @RequestMapping(value = "/myActivitiRecord", method = RequestMethod.GET)
    public List<Activiti> myActivitiRecord(HttpSession session) {
        User user=(User)session.getAttribute("user");
        String userName = user.getUsername();
        List<Activiti> list2 = activitiService.myActivitiRecord(userName);
        return list2;
    }

    /**
     * 我的审核记录
     */
    @RequestMapping(value = "/myApprovalRecord", method = RequestMethod.GET)
    public List<Activiti> myApprovalRecord(HttpSession session) {
        User user=(User)session.getAttribute("user");
        String userName = user.getUsername();
        List<Activiti> list4 = activitiService.myApprovalRecord(userName);
        return list4;
    }
}

