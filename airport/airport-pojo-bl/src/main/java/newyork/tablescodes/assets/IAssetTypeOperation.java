package newyork.tablescodes.assets;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link AssetTypeOperation}.
 *
 * @author Developers
 *
 */
public interface IAssetTypeOperation extends IEntityDao<AssetTypeOperation> {

    static final IFetchProvider<AssetTypeOperation> FETCH_PROVIDER = EntityUtils.fetch(AssetTypeOperation.class)
            .with("assetType", "role", "bu", "org", "startDate");
}
