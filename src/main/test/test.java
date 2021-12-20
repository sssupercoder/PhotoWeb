import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @Author shoke
 * @Date 2021/12/14 21:33
 * @Version 1.0
 */
public class test {
    @Test
    public void test(){
        JSONObject object = new JSONObject();
        object.put("a","b");
        System.out.println(object.toString().getBytes(StandardCharsets.UTF_8));
    }
}
