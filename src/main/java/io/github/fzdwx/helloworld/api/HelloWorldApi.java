package io.github.fzdwx.helloworld.api;

import cn.hutool.core.util.RandomUtil;
import io.github.fzdwx.helloworld.dao.HelloWorldDao;
import io.github.fzdwx.helloworld.entity.HelloWorldEntity;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:likelovec@gmail.com">韦朕</a>
 * @date 2022/4/3 16:07
 */
@RestController
@NoArgsConstructor
public class HelloWorldApi {

    @Autowired
    private HelloWorldDao helloWorldDao;

    @GetMapping("hello")
    public String hello() {
        return "start success";
    }

    @GetMapping("get")
    public HelloWorldEntity get() {
        return helloWorldDao.getOne();
    }

    @GetMapping("save")
    public Object save() {
        final var entity = new HelloWorldEntity();
        entity.setSayHello(RandomUtil.randomString(8));
        entity.setYourName(RandomUtil.randomString(8));
        return helloWorldDao.save(entity);
    }

    @GetMapping("delete/{id}")
    public Object delete(@PathVariable final String id) {
        return this.helloWorldDao.logicDeleteById(id);
    }
}