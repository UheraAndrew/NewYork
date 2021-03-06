package newyork.assets;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Asset}.
 *
 * @author Developers
 *
 */
public interface IAsset extends IEntityDao<Asset> {

    static final IFetchProvider<Asset> FETCH_PROVIDER = EntityUtils.fetch(Asset.class)
            .with("number", "desc", "assetType")
            .with("assetType.currOwnership.role", "assetType.currOwnership.bu", "assetType.currOwnership.org", "assetType.currOwnership.startDate")
            .with("currOwnership.role", "currOwnership.bu", "currOwnership.org", "currOwnership.startDate");

    
    String DEFAULT_ASSET_NUMBER = "NEXT NUMBER WILL BE GENERATED UPON SAVE";
    
}
