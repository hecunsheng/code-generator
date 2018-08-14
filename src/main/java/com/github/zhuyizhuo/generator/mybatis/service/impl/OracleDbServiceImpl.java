package com.github.zhuyizhuo.generator.mybatis.service.impl;

import com.github.zhuyizhuo.generator.mybatis.service.abst.AbstractDbService;
import com.github.zhuyizhuo.generator.mybatis.vo.TableInfoFtl;
import com.github.zhuyizhuo.generator.utils.TypeConversion;
import com.google.common.collect.Lists;
import com.github.zhuyizhuo.generator.mybatis.database.mapper.OracleDataBaseMapper;
import com.github.zhuyizhuo.generator.mybatis.database.pojo.ColumnInfo;
import com.github.zhuyizhuo.generator.mybatis.database.pojo.DataBaseInfo;
import com.github.zhuyizhuo.generator.mybatis.database.pojo.DbTableInfo;
import com.github.zhuyizhuo.generator.mybatis.service.abst.AbstractDbService;
import com.github.zhuyizhuo.generator.mybatis.utils.SqlSessionUtils;
import com.github.zhuyizhuo.generator.mybatis.vo.TableInfoFtl;
import com.github.zhuyizhuo.generator.utils.LogUtils;
import com.github.zhuyizhuo.generator.utils.TypeConversion;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * class: OracleDbServiceImpl <br>
 * description: oracle数据库查询表结构实现 <br>
 * time: 2018/8/6 12:58
 *
 * @author yizhuo <br>
 * @version 1.0
 */
public class OracleDbServiceImpl extends AbstractDbService {

    @Override
    public List<TableInfoFtl> getTableColumns() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        OracleDataBaseMapper mapper = sqlSession.getMapper(OracleDataBaseMapper.class);
        DataBaseInfo dataBaseInfo = getDataBaseInfo();
        LogUtils.printInfo("dataBaseInfo:" + dataBaseInfo);
        List<DbTableInfo> tableList  = mapper.getTableNameListBySchema(dataBaseInfo);
        LogUtils.printInfo("DataBaseInfo:" + dataBaseInfo +",共查询出" + tableList.size() + "张表.");
        List<TableInfoFtl> tableInfos = getTableInfos(mapper, tableList);
        sqlSession.close();
        return tableInfos;
    }

    private List<TableInfoFtl> getTableInfos(OracleDataBaseMapper mapper, List<DbTableInfo> tableList) {
        List<TableInfoFtl> tableInfoFtls = Lists.newArrayList();
        TableInfoFtl ftlTableInfo = null;
        for (int i = 0; i < tableList.size(); i++) {
            DbTableInfo dbTableInfo = tableList.get(i);
            String tableName = dbTableInfo.getTableName();
            DbTableInfo allColumnsByTable = mapper.getAllColumnsByTable(dbTableInfo.getTableSchema(), tableName);
            ftlTableInfo = new TableInfoFtl();
            setTableInfoFtl(allColumnsByTable,ftlTableInfo);
            ftlTableInfo.setJavaTableName(getJavaTableName(tableName));
            ftlTableInfo.addPrimaryKeyColumn(getPrimaryKeys(mapper,dbTableInfo));
            tableInfoFtls.add(ftlTableInfo);
            LogUtils.printInfo(tableName + "表共" + allColumnsByTable.getColumnLists().size() + "列");
        }
        return tableInfoFtls;
    }

    private List<ColumnInfo> getPrimaryKeys(OracleDataBaseMapper mapper, DbTableInfo dbTableInfo) {
        return mapper.getPrimaryKeys(dbTableInfo);
    }

    @Override
    protected String getJavaDataType(ColumnInfo columnInfo) {
        String dataType = columnInfo.getDataType();
        if (GeneratorStringUtils.isNotBlank(dataType) && dataType.contains("TIMESTAMP")){
            dataType = "TIMESTAMP";
        }
        return TypeConversion.dbType2Java(TypeConversion.oracleDbType2JavaMap, dataType);
    }
}