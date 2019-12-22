package newyork.organisational;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IOrganisation}.
 *
 * @author Developers
 *
 */
@EntityType(Organisation.class)
public class OrganisationDao extends CommonEntityDao<Organisation> implements IOrganisation {

    @Inject
    public OrganisationDao(final IFilter filter) {
        super(filter);
    }

    @Override
    protected IFetchProvider<Organisation> createFetchProvider() {
         return FETCH_PROVIDER;
    }
}
