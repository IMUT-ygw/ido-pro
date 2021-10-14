package com.ido.cloudconsumer80.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.UUID;

/**
 * mybatis-plus自动填充
 */
public class MyBatisMetaObjectHandle implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"id", String.class,UUID.randomUUID().toString());
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
