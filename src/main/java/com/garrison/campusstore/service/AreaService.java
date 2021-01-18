package com.garrison.campusstore.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.garrison.campusstore.dto.AreaExecution;
import com.garrison.campusstore.entity.Area;

import java.io.IOException;
import java.util.List;

public interface AreaService {
    public static final String AREALISTKEY = "arealist";
    List<Area> getAreaList() throws JsonParseException, JsonMappingException,
            IOException;

    /**
     *
     * @param area
     * @return
     */
    AreaExecution addArea(Area area);

    /**
     *
     * @param area
     * @return
     */
    AreaExecution modifyArea(Area area);

    /**
     *
     * @param areaId
     * @return
     */
    AreaExecution removeArea(long areaId);

    /**
     *
     * @param areaIdList
     * @return
     */
    AreaExecution removeAreaList(List<Long> areaIdList);

}
