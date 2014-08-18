package codist.garmin.uploader.util;

import org.dozer.CustomFieldMapper;
import org.dozer.classmap.ClassMap;
import org.dozer.fieldmap.FieldMap;
import org.hibernate.Hibernate;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;

/**
 * Custom dozer mapper for hibernate.
 * 
 * Usage:
 * DozerBeanMapper mapper = new DozerBeanMapper();
 * mapper.setCustomFieldMapper(new HibernateFieldMapper());
 * 
 * @author Xander Arling
 * 
 */
public class HibernateFieldMapper implements CustomFieldMapper {

    /**
     * Zorgt ervoor dat LAZY collections niet per ongeluk door de Dozer Mapper aangeroepen worden.
     * 
     * @param source
     * @param destination
     * @param sourceFieldValue
     * @param classMap
     * @param fieldMapping
     * @return
     * @see org.dozer.CustomFieldMapper#mapField(java.lang.Object, java.lang.Object, java.lang.Object, org.dozer.classmap.ClassMap,
     *      org.dozer.fieldmap.FieldMap)
     */
    @Override
    public boolean mapField(Object source, Object destination, Object sourceFieldValue, ClassMap classMap,
            FieldMap fieldMapping) {
        if ( !isHibernatePersistentCollectionOrHibernateProxy(sourceFieldValue)) {
            return false;
        } else {
            return !Hibernate.isInitialized(sourceFieldValue);
        }
    }

    /**
     * Checks that the sourceFieldValue is not a PersistentCollection and not a HibernateProxy
     * 
     * @param sourceFieldValue
     * @return
     */
    private boolean isHibernatePersistentCollectionOrHibernateProxy(Object sourceFieldValue) {
        return (sourceFieldValue instanceof PersistentCollection) || (sourceFieldValue instanceof HibernateProxy);
    }
}
