package com.java.wiki.mapper;

import org.apache.ibatis.annotations.Param;

public interface DocMapperCust {

    void increaseViewCount(@Param("id") Long id);


}
