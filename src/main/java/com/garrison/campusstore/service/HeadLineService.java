package com.garrison.campusstore.service;

import com.garrison.campusstore.dto.HeadLineExecution;
import com.garrison.campusstore.dto.ImageHolder;
import com.garrison.campusstore.entity.HeadLine;

import java.io.IOException;
import java.util.List;

public interface HeadLineService {
    public static String HLLISTKEY = "headlinelist";
    /**
     * 根据传入的条件返回指定的头条列表
     * @param headLineCondition
     * @return
     * @throws IOException
     */
    List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;
    HeadLineExecution addHeadLine(HeadLine headLine, ImageHolder thumbnail);

    /**
     * 修改头条信息
     *
     * @param headLine
     * @param thumbnail
     * @return
     */
    HeadLineExecution modifyHeadLine(HeadLine headLine, ImageHolder thumbnail);

    /**
     * 删除单条头条
     *
     * @param headLineId
     * @return
     */
    HeadLineExecution removeHeadLine(long headLineId);

    /**
     * 批量删除头条
     *
     * @param headLineIdList
     * @return
     */
    HeadLineExecution removeHeadLineList(List<Long> headLineIdList);
}
