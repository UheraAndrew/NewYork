package newyork.tablescodes.assets;

import ua.com.fielden.platform.dao.IEntityDao;
import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;

/**
 * Companion object for entity {@link AssetOwnership}.
 *
 * @author Developers
 *
 */
public interface IAssetOperation extends IEntityDao<AssetOperation> {

    static final IFetchProvider<AssetOperation> FETCH_PROVIDER = EntityUtils.
            fetch(AssetOperation.class).
            with("asset", "role", "bu", "org", "startDate");

}
