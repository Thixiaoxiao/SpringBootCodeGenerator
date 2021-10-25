<#if isWithPackage?exists && isWithPackage==true>package ${packageName}.controller;</#if>

<#if isAutoImport?exists && isAutoImport==true>
import ${packageName}.entity.${classInfo.className};
import ${packageName}.entity.ResponseForLayUIEntity;
import ${packageName}.services.${classInfo.className}Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
</#if>

/**
 * @description ${classInfo.classComment}
 * @author ${authorName}
 * @date ${.now?string('yyyy-MM-dd')}
 */
@Controller
@RequestMapping(value = "/${classInfo.className?uncap_first}")
public class ${classInfo.className}Controller {

    @Resource
    private ${classInfo.className}Service ${classInfo.className?uncap_first}Service;

    @GetMapping("/list")
    public String listPage(){
        return "${classInfo.className?lower_case}/list";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int id, HashMap<String, Object> hashMap){
        ${classInfo.className} ${classInfo.className?uncap_first}1 = new ${classInfo.className}();
        ${classInfo.className?uncap_first}1.setId(id);
        ${classInfo.className} ${classInfo.className?uncap_first} = ${classInfo.className?uncap_first}Repository.findById(id).orElse(${classInfo.className?uncap_first}1);
        hashMap.put("${classInfo.className?uncap_first}", ${classInfo.className?uncap_first});
        hashMap.put("id", id);
        return "${classInfo.className?lower_case}/edit";
    }

    /**
    * 新增
    * @author ${authorName}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(${classInfo.className} ${classInfo.className?uncap_first}){
        return ${classInfo.className?uncap_first}Service.insert(${classInfo.className?uncap_first});
    }

    /**
    * 刪除
    * @author ${authorName}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(int id){
        return ${classInfo.className?uncap_first}Service.delete(id);
    }

    /**
    * 更新
    * @author ${authorName}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    @ResponseBody
    @RequestMapping("/update")
    public Object update(${classInfo.className} ${classInfo.className?uncap_first}){
        return ${classInfo.className?uncap_first}Service.update(${classInfo.className?uncap_first});
    }

    /**
    * 查询 根据主键 id 查询
    * @author ${authorName}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    @ResponseBody
    @RequestMapping("/load")
    public Object load(int id){
        return ${classInfo.className?uncap_first}Service.load(id);
    }

    /**
    * 查询 分页查询
    * @author ${authorName}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    @ResponseBody
    @RequestMapping("/pageList")
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                                        @RequestParam(required = false, defaultValue = "10") int pagesize) {
        return ${classInfo.className?uncap_first}Service.pageList(offset, pagesize);
    }

}
