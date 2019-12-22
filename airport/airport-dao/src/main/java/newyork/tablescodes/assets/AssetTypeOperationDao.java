package newyork.tablescodes.assets;

import com.google.inject.Inject;

import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.annotation.EntityType;
import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.entity.query.IFilter;
/**
 * DAO implementation for companion object {@link IAssetTypeOperation}.
 *
 * @author Developers
 *
 */
@EntityType(AssetTypeOperation.class)
public class AssetTypeOperationDao extends CommonEntityDao<AssetTypeOperation> implements IAssetTypeOperation {

    @Inject
    public AssetTypeOperationDao(final IFilter filter) {
        super(filter);
    }
    
    @Override
    public AssetTypeOperation new_() {
        final AssetTypeOperation operation = super.new_();
        operation.getProperty("role").setRequired(true);
        operation.getProperty("bu").setRequired(true);
        operation.getProperty("org").setRequired(true);
        return operation;
    }

    @Override
    protected IFetchProvider<AssetTypeOperation> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}
