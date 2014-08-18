package codist.garmin.uploader.config;

import org.dozer.CustomFieldMapper;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import codist.garmin.uploader.util.HibernateFieldMapper;

import com.google.common.collect.Lists;

@Configuration
public class DozerConfig {
	
	@Bean
    public DozerBeanMapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Lists.newArrayList("dozer-global-configuration.xml", "dozer-bean-mappings.xml"));
        mapper.setCustomFieldMapper(hibernateFieldMapper());
 
        return mapper;
    }
 
    /**
     * Custom field mapper om hibernate loading van lazy fields te voorkomen
     * 
     * @return
     */
    public CustomFieldMapper hibernateFieldMapper() {
        return new HibernateFieldMapper();
    }

}
