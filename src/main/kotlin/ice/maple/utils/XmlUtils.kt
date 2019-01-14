package ice.maple.utils

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils
import org.dom4j.Element
import org.dom4j.io.SAXReader
import java.io.File
import java.io.IOException
import javax.swing.text.Document

/**
 * TODO
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-14 08:57
 */
class XmlUtils(private val file:File){
    /**
     * 文档对象
     */
    private val document = SAXReader().read(file)

    private fun checkFile(){
        if(!file.exists()){
            try {
                file.createNewFile()
            }catch (e:IOException){
                println(e.message)
            }
        }
    }

    /**
     * 获得第一个节点列表
     */
    private fun getRootList(rootNode:String): Element? {
        checkFile()
        return document.rootElement.element(rootNode)
    }

    private fun getLeafNode(rootNode:String, leafNode:String): Element? {
        return getRootList(rootNode)?.element(leafNode)
    }

    /**
     * 获得第二个节点数据
     */
    fun getLeafData(rootNode:String,leafNode:String): String? {
        return getLeafNode(rootNode,leafNode)?.data as? String
    }

    /**
     * 获得第二个节点数据
     */
    fun getLeafData(mapper:IMapper): String? {
        val str = getLeafNode(mapper.getRootNode(),mapper.getKey())?.data.toString()
        return if (str.isBlank()) null else str
    }

    /**
     * 获得第二个节点的布尔数据
     */
    fun getBoolData(mapper:IMapper): Boolean? {
        return getLeafNode(mapper.getRootNode(),mapper.getKey())?.data.toString().toBoolean()
    }
}