package com.garrison.campusstore.service;

import com.garrison.campusstore.entity.HeadLine;

import java.io.IOException;
import java.util.List;

public interface HeadLineService {
    /**
     * 根据传入的条件返回指定的头条列表
     * @param headLineCondition
     * @return
     * @throws IOException
     */
    public static String HLLISTKEY = "headlinelist";
    List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;
}
