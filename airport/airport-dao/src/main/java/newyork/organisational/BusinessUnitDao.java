package newyork.organisational;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link IBusinessUnit}.
 *
 * @author Developers
 *
 */
@EntityType(BusinessUnit.class)
public class BusinessUnitDao extends CommonEntityDao<BusinessUnit> implements IBusinessUnit {

    @Inject
    public BusinessUnitDao(final IFilter filter) {
        super(filter);
    }

    @Override
    protected IFetchProvider<BusinessUnit> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}
