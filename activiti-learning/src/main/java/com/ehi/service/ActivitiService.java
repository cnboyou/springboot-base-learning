package com.ehi.service;

/**
 * ClassName: a
 *
 * @Author: WangYiHai
 * @Date: 2020/5/8 17:39
 * @Description: TODO
 */

import com.ehi.model.Activiti;
import com.ehi.model.ActivitiTask;

import java.util.List;


public interface ActivitiService {

    public Boolean startActiviti(Activiti vac, String userName);

    public List<Activiti> myActiviti(String userName);

    public List<ActivitiTask> myApproval(String userName);

    public Boolean passApproval(String userName, ActivitiTask activitiTask);

    public List<Activiti> myActivitiRecord(String userName);

    public List<Activiti> myApprovalRecord(String userName);

}


