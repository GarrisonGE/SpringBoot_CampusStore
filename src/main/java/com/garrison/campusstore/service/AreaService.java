package com.garrison.campusstore.service;

import com.garrison.campusstore.entity.Area;

import java.util.List;

public interface AreaService {
    public static final String AREALISTKEY="arealist";
    List<Area> getAreaList();
}
