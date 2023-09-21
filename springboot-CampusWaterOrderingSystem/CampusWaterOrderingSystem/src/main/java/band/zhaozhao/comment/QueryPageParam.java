package band.zhaozhao.comment;

import lombok.Data;
import java.util.HashMap;

@Data
public class QueryPageParam {

    /**
     * PAGE_SIZE 默认分页的参数
     */
    private static int PAGE_SIZE=20;
    /**
     * 默认页数
     */
    private static int PAGE_NUM=1;

    /**
     * pageSize 从前端接收默认为我们设置的分页参数
     */
    private int pageSize = PAGE_SIZE;
    private int pageNum = PAGE_NUM;

    private HashMap param;
}