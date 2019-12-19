package newyork.assets.validators;

import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.select;

import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.Set;

import newyork.assets.AssetFinDet;
import newyork.projects.Project;
import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.entity.meta.impl.AbstractBeforeChangeEventHandler;
import ua.com.fielden.platform.entity.query.model.EntityResultQueryModel;
import ua.com.fielden.platform.error.Result;

public class AssetFinDetAcquireDateWithinProjectPeriod extends AbstractBeforeChangeEventHandler<Date> {
    public static final String ERR_OUTSIDE_PROJECT_PERIOD = "Value for acquire date is outside the project period.";

    @Override
    public Result handle(final MetaProperty<Date> property, final Date newValue, final Set<Annotation> mutatorAnnotations) {
        final AssetFinDet finDet = property.getEntity();
        if (finDet.getProject() == null || newValue == null) {
            return Result.successful(newValue);
        }
        
        final EntityResultQueryModel<Project> query = select(Project.class)
                .where().prop("id").eq().val(finDet.getProject()).and()
                .prop("startDate").le().val(newValue).and()
                .begin()
                    .prop("finishDate").isNull().or()
                    .prop("finishDate").ge().val(newValue)
                .end().model();
        return co(Project.class).exists(query) ? Result.successful(newValue) : Result.failure(ERR_OUTSIDE_PROJECT_PERIOD);

    }

}
