package ice.maple.config

import ice.maple.enums.GlobalEnum
import ice.maple.utils.IMapper
import ice.maple.utils.PathUtils
import ice.maple.utils.XmlUtils
import java.io.File

/**
 * 配置读取类
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-11 22:13
 */
object ConfigBuilder {
    /**
     * 配置文件路径
     */
    private val configPath = PathUtils.getResourcePath("config.xml")

    /**
     * xml工具对象
     */
    private val xmlUtils = XmlUtils(File(configPath))

    /**
     * 读取配置文件信息
     */
    fun getData(mapper:IMapper): String? {
        return xmlUtils.getLeafData(mapper)
    }

    /**
     * 读取配置文件信息
     */
    fun getBoolData(mapper:IMapper): Boolean? {
        return xmlUtils.getBoolData(mapper)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(getBoolData(GlobalEnum.IS_KOTLIN))
    }
}