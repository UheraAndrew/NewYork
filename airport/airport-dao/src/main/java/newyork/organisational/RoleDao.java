package newyork.organisational;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IRole}.
 *
 * @author Developers
 *
 */
@EntityType(Role.class)
public class RoleDao extends CommonEntityDao<Role> implements IRole {

    @Inject
    public RoleDao(final IFilter filter) {
        super(filter);
    }

    @Override
    protected IFetchProvider<Role> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}
