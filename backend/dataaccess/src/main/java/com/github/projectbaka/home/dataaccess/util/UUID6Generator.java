package com.github.projectbaka.home.dataaccess.util;

import com.github.f4b6a3.uuid.UuidCreator;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.hibernate.type.descriptor.java.UUIDTypeDescriptor;

import java.io.Serializable;
import java.util.Properties;
import java.util.UUID;

public class UUID6Generator extends UUIDGenerator {

    private UUIDTypeDescriptor.ValueTransformer valueTransformer;

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        if (UUID.class.isAssignableFrom(type.getReturnedClass())) {
            this.valueTransformer = UUIDTypeDescriptor.PassThroughTransformer.INSTANCE;
        } else if (String.class.isAssignableFrom(type.getReturnedClass())) {
            this.valueTransformer = UUIDTypeDescriptor.ToStringTransformer.INSTANCE;
        } else {
            if (!byte[].class.isAssignableFrom(type.getReturnedClass())) {
                throw new HibernateException("Unanticipated return type [" + type.getReturnedClass().getName() + "] for UUID conversion");
            }
            this.valueTransformer = UUIDTypeDescriptor.ToBytesTransformer.INSTANCE;
        }
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return this.valueTransformer.transform(UuidCreator.getTimeOrderedWithRandom());
    }
}
