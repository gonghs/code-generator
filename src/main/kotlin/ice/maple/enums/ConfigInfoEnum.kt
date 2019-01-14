package ice.maple.enums

import ice.maple.utils.IMapper

/**
 * 读取配置的枚举类
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-11 22:44
 */
enum class ConfigInfoEnum(name: String) {
    GLOBAL("global"),
    DATA_SOURCE("dataSource"),
    PACKAGE("package"),
    TEMPLATE("template"),
    STRATEGY("strategy");
    private val k:String = name

    fun getKey() :String{
        return this.k
    }
}

enum class GlobalEnum(name: String) :IMapper{
    AUTHOR("author"),
    IS_KOTLIN("isKotlin"),
    OUTPUT_DIR("outputDir");
    private val k:String = name

    override fun getKey(): String {
        return this.k
    }

    override fun getRootNode(): String {
        return ConfigInfoEnum.GLOBAL.getKey()
    }
}

enum class DataSourceEnum(name: String) :IMapper{
    DB_URL("dbUrl"),
    DB_DRIVER_NAME("dbDriverName"),
    DB_USER_NAME("dbUserName"),
    DB_PASSWORD("dbPassword");
    private val k:String = name

    override fun getKey(): String {
        return this.k
    }

    override fun getRootNode(): String {
        return ConfigInfoEnum.DATA_SOURCE.getKey()
    }
}

enum class PackageEnum(name: String) :IMapper{
    BASE_PACKAGE("basePackage"),
    MODULE_NAME("moduleName");
    private val k:String = name

    override fun getKey(): String {
        return this.k
    }

    override fun getRootNode(): String {
        return ConfigInfoEnum.PACKAGE.getKey()
    }
}

enum class TemplateEnum(name: String) :IMapper {
    TEMPLATE_PATH("templatePath"),
    ENTITY("entity"),
    XML("xml"),
    MAPPER("mapper"),
    SERVICE("service"),
    SERVICE_IMPL("serviceImpl"),
    CONTROLLER("controller");
    private val k:String = name

    override fun getKey(): String {
        return this.k
    }

    override fun getRootNode(): String {
        return ConfigInfoEnum.TEMPLATE.getKey()
    }
}

enum class StrategyEnum(name: String) :IMapper{
    SUPER_ENTITY_CLASS("superEntityClass"),
    SUPER_CONTROLLER_CLASS("superControllerClass"),
    TABLE_NAMES("tableNames"),
    TABLE_PREFIX("tablePrefix");
    private val k:String = name

    override fun getKey(): String {
        return this.k
    }

    override fun getRootNode(): String {
        return ConfigInfoEnum.STRATEGY.getKey()
    }
}

fun main(args: Array<String>) {
    val str = "global"
    println(DataSourceEnum.DB_URL.getKey())
}