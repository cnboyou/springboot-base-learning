package com.ehi.config.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.ehi.model.UserExcelVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ExcelListener
 *
 * @Author: WangYiHai
 * @Date: 2020/5/9 11:30
 * @Description: TODO
 */
public class ExcelListener extends AnalysisEventListener<UserExcelVO> {
    private static final Logger logger = LoggerFactory.getLogger(ExcelListener.class);
    private List<UserExcelVO> datas = new ArrayList<>();
    private static final int BATCH_COUNT = 1;

    @Override
    public void invoke(UserExcelVO userExcelVO, AnalysisContext analysisContext) {
        //数据存储到datas，供批量处理，或后续自己业务逻辑处理。
        datas.add(userExcelVO);
        //达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if(datas.size() >= BATCH_COUNT){
            data();
            // 存储完成清理datas
            datas.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // datas.clear();//解析结束销毁不用的资源
    }

    private void data() {
        for(UserExcelVO userExcelVO : datas){
            logger.info("工号：{},姓名:{},电话:{}",
                    userExcelVO.getIcCar(),userExcelVO.getUsername(), userExcelVO.getPhoneNumber());
        }
    }
}