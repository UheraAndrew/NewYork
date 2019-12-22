package newyork.tablescodes.assets;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IAssetOwnership}.
 *
 * @author Developers
 *
 */
@EntityType(AssetOwnership.class)
public class AssetOwnershipDao extends CommonEntityDao<AssetOwnership> implements IAssetOwnership {

    @Inject
    public AssetOwnershipDao(final IFilter filter) {
        super(filter);
    }

    @Override
    public AssetOwnership new_() {
        final AssetOwnership ownership = super.new_();
        ownership.getProperty("role").setRequired(true);
        ownership.getProperty("bu").setRequired(true);
        ownership.getProperty("org").setRequired(true);
        return ownership;
    }

    @Override
    protected IFetchProvider<AssetOwnership> createFetchProvider() {
        return FETCH_PROVIDER;
    }

}
