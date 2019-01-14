package ice.maple.utils

import java.net.URLDecoder

/**
 * TODO
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-11 22:18
 */
object PathUtils{
    /**
     * 项目路径
     */
    val projectPath = System.getProperty("user.dir")!!

    /**
     * 获得资源文件夹下的文件路径
     */
    fun getResourcePath(file:String):String{
        return URLDecoder.decode(this::class.java.classLoader.getResource(file).path,"UTF-8")
    }
}