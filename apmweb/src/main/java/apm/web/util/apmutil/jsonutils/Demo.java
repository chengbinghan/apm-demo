package apm.web.util.apmutil.jsonutils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author ChengBing Han
 * @date 23:27  2018/1/29
 * @description
 */
class Article {
    private String id;
    private String title;
    private String content;
    // ... getter/setter
}

// Demo
public class Demo {

    public static void main(String[] args) throws JsonProcessingException {
        new Demo().main();
    }
    public  void main() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JacksonJsonFilter jacksonFilter = new JacksonJsonFilter();

        // 过滤除了 id,title 以外的所有字段，也就是序列化的时候，只包含 id 和 title
        jacksonFilter.include(Article.class, new String[] {"id","title"});
        mapper.setFilterProvider(jacksonFilter);  // 设置过滤器
        mapper.addMixIn(Article.class, jacksonFilter.getClass()); // 为Article.class类应用过滤器
        String include= mapper.writeValueAsString(new Article());


        // 序列化所有字段，但是排除 id 和 title，也就是除了 id 和 title之外，其他字段都包含进 json
        jacksonFilter = new JacksonJsonFilter();
        jacksonFilter.filter(Article.class, new String[] {"id","title"});
        mapper = new ObjectMapper();
        mapper.setFilterProvider(jacksonFilter);
        mapper.addMixIn(Article.class, jacksonFilter.getClass());

        String filter = mapper.writeValueAsString(new Article());

        System.out.println("include:" + include);
        System.out.println("filter :" + filter);
    }
}
