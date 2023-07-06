package org.ysu.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.ysu.pojo.Checkout;
import org.ysu.pojo.CheckoutExample;

public interface CheckoutMapper {
    long countByExample(CheckoutExample example);

    int deleteByExample(CheckoutExample example);

    int insert(Checkout record);

    int insertSelective(Checkout record);

    List<Checkout> selectByExample(CheckoutExample example);

    int updateByExampleSelective(@Param("record") Checkout record, @Param("example") CheckoutExample example);

    int updateByExample(@Param("record") Checkout record, @Param("example") CheckoutExample example);
}