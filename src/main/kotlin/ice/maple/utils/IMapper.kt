package ice.maple.utils

import ice.maple.enums.ConfigInfoEnum

/**
 * 公共接口继承的枚举
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-14 10:23
 */
interface IMapper {
    fun getKey():String

    fun getRootNode(): String
}