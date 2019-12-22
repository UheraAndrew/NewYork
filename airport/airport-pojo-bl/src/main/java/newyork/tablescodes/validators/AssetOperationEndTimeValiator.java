package newyork.tablescodes.validators;

import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.Set;

import newyork.tablescodes.assets.AssetOperation;
import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.entity.validation.IBeforeChangeEventHandler;
import ua.com.fielden.platform.error.Result;

public class AssetOperationEndTimeValiator implements IBeforeChangeEventHandler<Date> {
    public static final String ERR_END_DATE_MUST_BE_AFTER_START_DATE = "end date must be after start date";

    @Override
    public Result handle(MetaProperty<Date> property, Date newValue, Set<Annotation> mutatorAnnotations) {
        AssetOperation operation = property.getEntity();
        
        if (operation.getStartDate() == null || newValue.after(operation.getStartDate())) {
            return Result.successful("All is good");
        }

        return Result.failure(ERR_END_DATE_MUST_BE_AFTER_START_DATE);

    }

}
