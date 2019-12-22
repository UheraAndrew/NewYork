package newyork.tablescodes.assets;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link IAssetManagement}.
 *
 * @author Developers
 *
 */
@EntityType(AssetManagement.class)
public class AssetManagementDao extends CommonEntityDao<AssetManagement> implements IAssetManagement {

    @Inject
    public AssetManagementDao(final IFilter filter) {
        super(filter);
    }

    @Override
    protected IFetchProvider<AssetManagement> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}
