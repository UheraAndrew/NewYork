package newyork.tablescodes.assets;

import java.util.Date;

import newyork.organisational.BusinessUnit;
import newyork.organisational.Organisation;
import newyork.organisational.Role;
import newyork.tablescodes.assets.definers.AssetTypeOwnershipExclusivityDefiner;
import newyork.tablescodes.validators.AssetTypeOwnershipEndTimeValiator;
import ua.com.fielden.platform.entity.AbstractPersistentEntity;
import ua.com.fielden.platform.entity.DynamicEntityKey;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.DateOnly;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.mutator.AfterChange;
import ua.com.fielden.platform.entity.annotation.mutator.BeforeChange;
import ua.com.fielden.platform.entity.annotation.mutator.Handler;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Base type for asset and asset type ownership entities.
 *
 * @author NewYork team
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Key")
@CompanionObject(IAssetTypeOwnership.class)
@MapEntityTo
public class AbstractOwnership extends AbstractPersistentEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(AbstractOwnership.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    @IsProperty
    @MapTo
    @Title(value = "Asset Type", desc = "Asset type ownership")
    @CompositeKeyMember(2)
    private AssetType assetType;

    @IsProperty
    @MapTo
    @Title(value = "Start Date", desc = "The start date of the ownership")
    @CompositeKeyMember(3)
    @DateOnly
    private Date startDate;

    @IsProperty
    @MapTo
    @Title(value = "Role", desc = "Role that owns assets of the specified asset type")
    @AfterChange(AssetTypeOwnershipExclusivityDefiner.class)
    private Role role;

    @IsProperty
    @MapTo
    @Title(value = "Business Unit", desc = "Business unit that owns assets of the specified asset type")
    @AfterChange(AssetTypeOwnershipExclusivityDefiner.class)
    private BusinessUnit bu;
    
    @IsProperty
    @MapTo
    @Title(value = "Organisation", desc = "Organisation that owns assets of the specified asset type")
    @AfterChange(AssetTypeOwnershipExclusivityDefiner.class)
    private Organisation org;

    @Observable
    public <E extends AbstractOwnership> E setStartDate(final Date startDate) {
        this.startDate = startDate;
        return (E) this;
    }

    public Date getStartDate() {
        return startDate;
    }
 
    @Observable
    public <E extends AbstractOwnership> E setOrg(final Organisation org) {
        this.org = org;
        return (E) this;
    }

    public Organisation getOrg() {
        return org;
    }

    @Observable
    public <E extends AbstractOwnership> E setBu(final BusinessUnit bu) {
        this.bu = bu;
        return (E) this;
    }

    public BusinessUnit getBu() {
        return bu;
    }

    @Observable
    public <E extends AbstractOwnership> E setRole(final Role role) {
        this.role = role;
        return (E) this;
    }

    public Role getRole() {
        return role;
    }
  
    @Observable
    public <E extends AbstractOwnership> E setAssetType(final AssetType assetType) {
        this.assetType = assetType;
        return (E) this;
    }

    public AssetType getAssetType() {
        return assetType;
    }

}
