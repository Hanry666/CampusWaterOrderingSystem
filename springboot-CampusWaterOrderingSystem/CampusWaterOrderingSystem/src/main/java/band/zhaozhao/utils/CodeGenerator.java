package band.zhaozhao.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGenerator {

    public static void main(String[] args) {
        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
        // 是否支持AR模式
        config.setActiveRecord(true)
                // 设置作者
                .setAuthor("Zzhaozhao")
                // 生成路径，最好使用绝对路径，window路径是不一样的 记得修改为自己的项目地址
                .setOutputDir("/Users/zhaozhao/IdeaProjects/CampusWaterOrderingSystem/src/main/java")
                /**
                 *      // 项目路径
                 *     .setOutputDir(projectPath + "/src/main/java");
                 */

                // 生成后是否打开资源管理器
                .setOpen(false)
                // 重新生成文件后是否覆盖文件
                .setFileOverride(true)
                // 主键策略
                .setIdType(IdType.AUTO)
                //定义生成的实体类中日期类型
                .setDateType(DateType.ONLY_DATE)
                // 设置生成的service接口的名字的首字母是否为I，默认Service是以I开头的
                .setServiceName("%sService")
                //实体类结尾名称 如 UserVO
//                .setEntityName("VO")
                //生成基本的resultMap
                .setBaseResultMap(true)
                //不使用AR模式
                .setActiveRecord(false)
                // Swagger 2注解
                .setSwagger2(true)
                // 生成基本的SQL片段
                .setBaseColumnList(true);
        //2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        // 设置数据库类型
        dsConfig.setDbType(DbType.ORACLE)
                // JDBC 驱动
                .setDriverName("oracle.jdbc.OracleDriver")
                // jdbc:oracle:thin:@ip地址(或者localhost):端口:数据库名
                .setUrl("jdbc:oracle:thin:@43.138.82.57:1521:ORCL")
                // oracle 账号
                .setUsername("Zzhaozhao")
                // oracle 密码
                .setPassword("oracle");
        //3. 策略配置globalConfiguration中
        StrategyConfig stConfig = new StrategyConfig();
        //全局大写命名
        stConfig.setCapitalMode(true)
                // 数据库表映射到实体的命名策略
                .setNaming(NamingStrategy.underline_to_camel)   // 下划线转驼峰命名  如 created_time  ---> createdTime
                // 【推荐】  命名方式不变化,与数据库名称保持一致，这样在Mapper.xml文件不需要单独修改字段名和实体属性名称之间的对应关系
                //.setNaming(NamingStrategy.no_change)
                //使用lombok
                .setEntityLombokModel(true)
                //使用 restcontroller 注解
                .setRestControllerStyle(true)
                // 生成的表, 支持多表一起生成，以数组形式填写
               //.setInclude("ADDRESS")
                .setInclude(new String[]{"SHOPPINGCART","CARTPRODUCT"})
                // 去除表前缀
                .setTablePrefix("APP_BD_");

        //4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("band.zhaozhao")
                // mapper层
                .setMapper("mapper")
                // service 层
                .setService("service")
                // controller 控制层
                .setController("controller")
                // 实体类包名  domain VO BO POJO...
                .setEntity("entity")
                // 生成 mapper.xml 模板文件
                .setXml("mapper.xml");
        //5. 整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);
        //6. 执行操作
        ag.execute();
        System.out.println("======= Done 相关代码生成完毕  ========");
    }

}
