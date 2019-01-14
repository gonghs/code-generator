package ice.maple.utils

import com.baomidou.mybatisplus.generator.AutoGenerator
import com.baomidou.mybatisplus.generator.config.*
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine
import ice.maple.config.ConfigBuilder
import ice.maple.enums.*
import java.util.*

/**
 * 代码生成器工具类
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-11 21:39
 */
object GeneratorUtils{
    /**
     * 代码生成器主类
     */
    val autoGenerator = AutoGenerator()

    /**
     * 全局配置
     */
    val globalConfig = GlobalConfig()

    /**
     * 数据源配置
     */
    val dataSourceConfig = DataSourceConfig()

    /**
     * 包配置
     */
    val packageConfig = PackageConfig()

    /**
     * 模板配置
     */
    val templateConfig = TemplateConfig()

    /**
     * 策略配置
     */
    val strategyConfig = StrategyConfig()

    /**
     * 自定义输出配置
     */
    val fileOutConfigList = ArrayList<FileOutConfig>()


    fun build():GeneratorUtils {
        ConfigBuilder.getData(GlobalEnum.OUTPUT_DIR)?.let { globalConfig.outputDir = PathUtils.projectPath + it }
        ConfigBuilder.getData(GlobalEnum.AUTHOR)?.let { globalConfig.author = it }
        globalConfig.isOpen = false
        ConfigBuilder.getBoolData(GlobalEnum.IS_KOTLIN)?.let { globalConfig.isKotlin = it }

        dataSourceConfig.url = ConfigBuilder.getData(DataSourceEnum.DB_URL)
        dataSourceConfig.driverName = ConfigBuilder.getData(DataSourceEnum.DB_DRIVER_NAME)
        dataSourceConfig.username = ConfigBuilder.getData(DataSourceEnum.DB_USER_NAME)
        dataSourceConfig.password = ConfigBuilder.getData(DataSourceEnum.DB_PASSWORD)

        ConfigBuilder.getData(PackageEnum.BASE_PACKAGE)?.let { packageConfig.parent = it }
        ConfigBuilder.getData(PackageEnum.MODULE_NAME)?.let { packageConfig.moduleName = it }

        val baseTemplatePath = ConfigBuilder.getData(TemplateEnum.TEMPLATE_PATH)
        ConfigBuilder.getData(TemplateEnum.ENTITY)?.let {
            if(it.contains(".kt")){
                templateConfig.setEntityKt(if(baseTemplatePath.isNullOrBlank()) it else baseTemplatePath+it)
            }else{
                templateConfig.setEntity(if(baseTemplatePath.isNullOrBlank()) it else baseTemplatePath+it)
            }
        }
        ConfigBuilder.getData(TemplateEnum.XML)?.let { templateConfig.xml = if(baseTemplatePath.isNullOrBlank()) it else baseTemplatePath+it }
        ConfigBuilder.getData(TemplateEnum.MAPPER)?.let { templateConfig.mapper = if(baseTemplatePath.isNullOrBlank()) it else baseTemplatePath+it }
        ConfigBuilder.getData(TemplateEnum.SERVICE)?.let { templateConfig.service = if(baseTemplatePath.isNullOrBlank()) it else baseTemplatePath+it }
        ConfigBuilder.getData(TemplateEnum.SERVICE_IMPL)?.let { templateConfig.serviceImpl = if(baseTemplatePath.isNullOrBlank()) it else baseTemplatePath+it }
        ConfigBuilder.getData(TemplateEnum.CONTROLLER)?.let { templateConfig.controller = if(baseTemplatePath.isNullOrBlank()) it else baseTemplatePath+it }

        strategyConfig.naming = NamingStrategy.underline_to_camel
        strategyConfig.columnNaming = NamingStrategy.underline_to_camel
        ConfigBuilder.getData(StrategyEnum.SUPER_ENTITY_CLASS)?.let { strategyConfig.superEntityClass = it }
        ConfigBuilder.getData(StrategyEnum.SUPER_CONTROLLER_CLASS)?.let { strategyConfig.superControllerClass = it }
        ConfigBuilder.getData(StrategyEnum.TABLE_PREFIX)?.let { strategyConfig.setTablePrefix(it+"_") }
        ConfigBuilder.getData(StrategyEnum.TABLE_NAMES)?.let {
            val list = it.split(",")
            val array = Array(list.size){i -> list[i] }
            strategyConfig.setInclude(*array)
        }
        return this
    }

    fun execute(){
        autoGenerator.globalConfig = globalConfig
        autoGenerator.dataSource = dataSourceConfig
        autoGenerator.packageInfo = packageConfig
        autoGenerator.template = templateConfig
        autoGenerator.strategy = strategyConfig
        autoGenerator.templateEngine = FreemarkerTemplateEngine()
        autoGenerator.execute()
    }
}