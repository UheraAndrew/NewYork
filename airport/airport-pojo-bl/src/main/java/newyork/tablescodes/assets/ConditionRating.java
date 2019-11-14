package newyork.tablescodes.assets;

import ua.com.fielden.platform.entity.ActivatableAbstractEntity;
import ua.com.fielden.platform.entity.DynamicEntityKey;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.DisplayDescription;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.DescRequired;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 *
 * @author Developers
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Condition Rating")
@CompanionObject(IConditionRating.class)
@MapEntityTo
@DescTitle("Condition Rating description")
@DisplayDescription
@DescRequired
public class ConditionRating extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(ConditionRating.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    @IsProperty
    @MapTo
    @Title(value = "Name", desc = "Condition Rating name")
    @CompositeKeyMember(1)
    private String name;

    @Observable
    public ConditionRating setName(final String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }
    
    @IsProperty
    @MapTo
    @Title(value = "Date", desc = "Asset Type maintance/replacement")
    private String planDate;

    @Observable
    public ConditionRating setPlanDate(final String planDate) {
        this.planDate = planDate;
        return this;
    }

    public String getPlanDate() {
        return planDate;
    }
    
    @IsProperty
    @MapTo
    @Title(value = "Asset Type", desc = "The class of this condition rating")
    private AssetType assetType;

    @Observable
    public ConditionRating setAssetType(final AssetType assetType) {
        this.assetType= assetType;
        return this;
    }

    public AssetType getAssetType() {
        return assetType;
    }

}
