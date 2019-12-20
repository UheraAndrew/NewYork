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
        // TODO: uncomment the following line and specify the properties, which are required for the UI in IOrganisation.FETCH_PROVIDER. Then remove the line after.
        // return FETCH_PROVIDER;
        throw new UnsupportedOperationException("Please specify the properties, which are required for the UI in IOrganisation.FETCH_PROVIDER");
    }
}
