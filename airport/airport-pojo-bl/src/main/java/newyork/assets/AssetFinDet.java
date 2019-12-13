package newyork.assets;

import java.util.Date;

import ua.com.fielden.platform.entity.AbstractPersistentEntity;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.types.Money;
import ua.com.fielden.platform.utils.Pair;

/**
 * An entity to capture financial details for an asset. 
 *
 * @author NewYork team
 *
 */
@KeyType(Asset.class)
@KeyTitle("Key")
@CompanionObject(IAssetFinDet.class)
@MapEntityTo
public class AssetFinDet extends AbstractPersistentEntity<Asset> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(AssetFinDet.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    @IsProperty
    @MapTo
    @Title(value = "Init Cost", desc = "Initial asset cost")
    private Money initCost;
    
    @IsProperty
    @MapTo
    @Title(value = "Acquire Date", desc = "The date when asset was made ot purchased.")
    private Date acquireDate;
    
    @Observable
    public AssetFinDet setAcquiredDate(final Date acquireDate) {
        this.acquireDate = acquireDate;
        return this;
    }
    
    public Date getAcquiredDate() {
        return acquireDate;
    }
    
    @Observable
    public AssetFinDet setInitCost(final Money initCost) {
        this.initCost = initCost;
        return this;
    }

    public Money getInitCost() {
        return initCost;
    }

}
