package com.zhuyizhuo.generator.mybatis.factory;

import com.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.zhuyizhuo.generator.mybatis.enums.DbTypeEnums;
import com.zhuyizhuo.generator.mybatis.service.DbService;
import com.zhuyizhuo.generator.mybatis.service.impl.MysqlDbServiceImpl;
import com.zhuyizhuo.generator.utils.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * class: DbServiceFactory <br>
 * description: 数据库service构建工厂 <br>
 * time: 2018/7/30 12:57
 *
 * @author yizhuo <br>
 * @version 1.0
 */
public class DbServiceFactory {

    private static Map<String,DbService> serviceMap = new ConcurrentHashMap<>();

    static{
        serviceMap.put(DbTypeEnums.MYSQL.toString(), new MysqlDbServiceImpl());
    }

    public static DbService getDbService() {
        String properties = PropertiesUtils.getProperties(ConfigConstants.DB_TYPE);
        if (StringUtils.isBlank(properties)){
            String errorMsg = "未指定数据库类型:" + ConfigConstants.DB_TYPE + ",请在generate-config.properties中指定.DB_TYPE 值列表请参照 DbTypeEnums.java";
            System.out.println(errorMsg);
            throw new RuntimeException(errorMsg);
        }
        DbService dbService = serviceMap.get(properties);
        if (dbService == null){
            String errorMsg =  ConfigConstants.DB_TYPE + "配置类型不支持,所支持类型请参照 DbTypeEnums.java";
            System.out.println(errorMsg);
            throw new RuntimeException(errorMsg);
        }
        return dbService;
    }
}
