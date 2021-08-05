package br.com.zupacademy.mercadolivre.api.validation;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Long> {
    private String domainAttribute;
    private Class<?> clazz;
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ExistsId constraintAnnotation) {
        this.domainAttribute = constraintAnnotation.fieldName();
        this.clazz = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        String sql = String.format("select 1 from %s where %s = :id", clazz.getName(), domainAttribute);
        Query query = manager.createQuery(sql);
        query.setParameter("id", value);
        List<?> list = query.getResultList();

        Assert.state(list.size() <= 1, "Foi encontrado mais de um " + clazz + "com o atributo "
                + domainAttribute + " = " + value);

        return list.size() == 1;
    }
}
