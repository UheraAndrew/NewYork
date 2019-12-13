package newyork.assets;

import java.util.Collection;
import java.util.List;

import com.google.inject.Inject;

import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import ua.com.fielden.platform.entity.annotation.EntityType;
import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.error.Result;
import ua.com.fielden.platform.keygen.IKeyNumber;
import ua.com.fielden.platform.keygen.KeyNumber;
/**
 * DAO implementation for companion object {@link IAsset}.
 *
 * @author New York
 *
 */
@EntityType(Asset.class)
public class AssetDao extends CommonEntityDao<Asset> implements IAsset {
    public static final String DEFAULT_ASSET_NUMBER = "NEXT NUMBER WILL BE GENERATED UPON SAVE";
    public static final String ERR_FAILED_SAVE = "Deliberate save exception.";

    @Inject
    public AssetDao(final IFilter filter) {
        super(filter);
    }
    
    @Override
    @SessionRequired
    public Asset save(final Asset asset) {
        // TODO: implement a solution for a failed transaction where ID was already assigned 
        // what if in this place we have an exception?
        // like the asset with number 3 is already created but your number 3 is already assigned
        // conflict!
        // would you check if your asset is persisted but also if you value is not the default one?
        // to revert the number assigned
        if (!asset.isPersisted()) {
            final IKeyNumber coKeyNumber = co(KeyNumber.class);
            final Integer nextNumber = coKeyNumber.nextNumber("ASSET_NUMBER");
            asset.setNumber(nextNumber.toString());
        }
        // TODO: length of six, to have not numbers but strings in form like 000001 or 000101
        return super.save(asset);
    }
    
    @SessionRequired
    public Asset saveWithError(final Asset asset) {
        save(asset);
        throw Result.failure(ERR_FAILED_SAVE); 
    }
    
    @Override
    public Asset new_() {
        final Asset asset = super.new_();
        asset.setNumber(DEFAULT_ASSET_NUMBER);
        
        return asset;
    }

    @Override
    @SessionRequired
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    public int batchDelete(final List<Asset> entities) {
        return defaultBatchDelete(entities);
    }

    @Override
    protected IFetchProvider<Asset> createFetchProvider() {
        return FETCH_PROVIDER;
        }
}
