package cn.com.gome.generator.config;

import com.gomeplus.frame.cache.GlobalApplicationCache;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * <Description> 代码生成器配置类</Description>
 * <ClassName> GeneratorConfigurartion</ClassName>
 *
 * @Author liuxianzhao
 * @Date 2017年12月04日 10:38
 */
@Component
public class GeneratorConfigurartion {

    @Bean
    public AppInitializer appInitializer() {
        GlobalApplicationCache.getInstance().put("jr.commons.version", "2.2.3");
        //other things
        return new AppInitializer();
    }
}
