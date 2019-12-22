package newyork.tablescodes.assets;

import com.google.inject.Inject;

import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.annotation.EntityType;
import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.entity.query.IFilter;
/**
 * DAO implementation for companion object {@link IAssetOwnership}.
 *
 * @author Developers
 *
 */
@EntityType(AssetOperation.class)
public class AssetOperationDao extends CommonEntityDao<AssetOperation> implements IAssetOperation {

    @Inject
    public AssetOperationDao(final IFilter filter) {
        super(filter);
    }

    @Override
    public AssetOperation new_() {
        final AssetOperation operation = super.new_();
        operation.getProperty("role").setRequired(true);
        operation.getProperty("bu").setRequired(true);
        operation.getProperty("org").setRequired(true);
        return operation;
    }

    @Override
    protected IFetchProvider<AssetOperation> createFetchProvider() {
        return FETCH_PROVIDER;
    }

}
