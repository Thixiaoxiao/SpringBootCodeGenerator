<#if isWithPackage?exists && isWithPackage==true>package ${packageName}.services.impl;</#if>

<#if isAutoImport?exists && isAutoImport==true>

import ${packageName}.entity.${classInfo.className};

import ${packageName}.services.${classInfo.className}Service;

import ${packageName}.dao.${classInfo.className}Mapper;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
</#if>
/**
 * @description ${classInfo.classComment}
 * @author ${authorName}
 * @date ${.now?string('yyyy-MM-dd')}
 */
@Service
public class ${classInfo.className}ServiceImpl implements ${classInfo.className}Service {

	@Resource
	private ${classInfo.className}Mapper ${classInfo.className?uncap_first}Mapper;


	@Override
	public ${classInfo.className} insert(${classInfo.className} ${classInfo.className?uncap_first}) {
		${classInfo.className?uncap_first}Mapper.insert(${classInfo.className?uncap_first});
        return ${classInfo.className?uncap_first};
	}


	@Override
	public int delete(int id) {
		return ${classInfo.className?uncap_first}Mapper.delete(id);
	}


	@Override
	public int update(${classInfo.className} ${classInfo.className?uncap_first}) {
		return ${classInfo.className?uncap_first}Mapper.update(${classInfo.className?uncap_first});
	}


	@Override
	public ${classInfo.className} load(int id) {
		return ${classInfo.className?uncap_first}Mapper.load(id);
	}


	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<${classInfo.className}> pageList = ${classInfo.className?uncap_first}Mapper.pageList(offset, pagesize);
		int totalCount = ${classInfo.className?uncap_first}Mapper.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
