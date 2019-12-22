package newyork.tablescodes.assets;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link IAssetTypeManagement}.
 *
 * @author Developers
 *
 */
@EntityType(AssetTypeManagement.class)
public class AssetTypeManagementDao extends CommonEntityDao<AssetTypeManagement> implements IAssetTypeManagement {

    @Inject
    public AssetTypeManagementDao(final IFilter filter) {
        super(filter);
    }

    @Override
    protected IFetchProvider<AssetTypeManagement> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}
