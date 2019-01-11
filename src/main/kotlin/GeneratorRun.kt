import GeneratorRun.scanner
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException
import com.baomidou.mybatisplus.generator.AutoGenerator
import com.baomidou.mybatisplus.generator.config.DataSourceConfig
import com.baomidou.mybatisplus.generator.config.GlobalConfig
import com.baomidou.mybatisplus.generator.config.PackageConfig
import java.util.*
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy
import com.baomidou.mybatisplus.generator.config.StrategyConfig
import com.baomidou.mybatisplus.generator.config.TemplateConfig
import com.baomidou.mybatisplus.core.toolkit.StringPool
import com.baomidou.mybatisplus.generator.config.FileOutConfig
import com.baomidou.mybatisplus.generator.config.po.TableInfo
import java.util.ArrayList
import com.baomidou.mybatisplus.generator.InjectionConfig





/**
 * 执行生成的主类
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-11 17:49
 */
object GeneratorRun {
    fun scanner(tip: String): String? {
        val scanner = Scanner(System.`in`)
        println("请输入$tip:")
        if (scanner.hasNext()) {
            val ipt = scanner.next()
            if (ipt.isNotEmpty())
                return ipt
        }
        throw MybatisPlusException("请输入正确的$tip!")
    }

}

fun main(args: Array<String>) {
    //代码生成器
    val mpg = AutoGenerator()

    //全局配置
    val gc = GlobalConfig()
    val projectPath = System.getProperty("user.dir")
    gc.outputDir = "$projectPath/src/main/kotlin"
    gc.author = "maple"
    gc.isOpen = false
    gc.isKotlin = true
    mpg.globalConfig = gc

    //数据源配置
    val dsc = DataSourceConfig()
    dsc.url = "jdbc:mysql://localhost:3306/myblog?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT"
    dsc.driverName = "com.mysql.cj.jdbc.Driver"
    dsc.username = "maple"
    dsc.password = "maple"
    mpg.dataSource = dsc

    //包配置
    val pc = PackageConfig()
    pc.moduleName = scanner("模块名")
    pc.parent = "ice.maple"
    mpg.packageInfo = pc

//    val templatePath = "/templates/mapper.xml.ftl"
//
//    // 自定义输出配置
//    val focList = ArrayList<FileOutConfig>()
//    // 自定义配置会被优先输出
//    focList.add(object : FileOutConfig(templatePath) {
//        override fun outputFile(tableInfo: TableInfo): String {
//            // 自定义输出文件名
//            return ("$projectPath/src/main/resources/mapper/${pc.moduleName}/${tableInfo.entityName}Mapper${StringPool.DOT_XML}")
//        }
//    })
    // 自定义配置
    val cfg = object : InjectionConfig() {
        override fun initMap() {
            // to do nothing
        }
    }

//    cfg.fileOutConfigList = focList
    mpg.cfg = cfg

    // 配置模板
    val templateConfig = TemplateConfig()

    // 配置自定义输出模板
    // templateConfig.setEntity();
    // templateConfig.setService();
    // templateConfig.setController();

    templateConfig.xml = null
    mpg.template = templateConfig

    // 策略配置
    val strategy = StrategyConfig()
    strategy.naming = NamingStrategy.underline_to_camel
    strategy.columnNaming = NamingStrategy.underline_to_camel
    strategy.superEntityClass = "com.baomidou.ant.common.BaseEntity"
    strategy.isEntityLombokModel = true
    strategy.isRestControllerStyle = true
    strategy.superControllerClass = "com.baomidou.ant.common.BaseController"
    strategy.setInclude(scanner("表名"))
    strategy.setSuperEntityColumns("id")
    strategy.isControllerMappingHyphenStyle = true
    strategy.setTablePrefix(pc.moduleName + "_")
    mpg.strategy = strategy
    mpg.templateEngine = FreemarkerTemplateEngine()
    mpg.execute()
}