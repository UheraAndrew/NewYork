package newyork.tablescodes.assets;

import java.util.Date;

import newyork.tablescodes.validators.ConditionRatingEndTimeValiator;
import ua.com.fielden.platform.entity.ActivatableAbstractEntity;
import ua.com.fielden.platform.entity.DynamicEntityKey;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.DateOnly;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Required;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.mutator.BeforeChange;
import ua.com.fielden.platform.entity.annotation.mutator.Handler;
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
    @Title(value = "Start Repair", desc = "The start date of the replacement/maintainance")
    @CompositeKeyMember(2)
    @DateOnly
    private Date startRepair;

    @IsProperty
    @MapTo
    @Title(value = "End Repair ", desc = "The end date of the replacement/maintainance")
    @CompositeKeyMember(3)
    @DateOnly
    @BeforeChange({
        @Handler(ConditionRatingEndTimeValiator.class)
    })
    private Date endRepair;

    @Observable
    public ConditionRating setStartRepair(final Date startRepair) {
        this.startRepair = startRepair;
        return this;
    }

    public Date getStartRepair() {
        return startRepair;
    }

    @Observable
    public ConditionRating setEndRepair(final Date endRepair) {
        this.endRepair = endRepair;
        return this;
    }

    public Date getEndRepair() {
        return endRepair;
    }

    @IsProperty
    @MapTo
    @Required
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
