package newyork.tablescodes.assets;

import java.util.Date;

import newyork.organisational.BusinessUnit;
import newyork.organisational.Organisation;
import newyork.organisational.Role;
import newyork.tablescodes.assets.definers.AssetTypeOwnershipExclusivityDefiner;
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
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 *
 * @author Developers
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Key")
@CompanionObject(IAssetTypeOwnership.class)
@MapEntityTo
public class AssetTypeOwnership extends AbstractPersistentEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(AssetTypeOwnership.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    @IsProperty
    @MapTo
    @Title(value = "Asset Type", desc = "Desc")
    @CompositeKeyMember(1)
    private AssetType assetType;

    @IsProperty
    @MapTo
    @Title(value = "Start Date", desc = "The start date of the ownership")
    @CompositeKeyMember(2)
    @DateOnly
    private Date startDate;
    // TODO: if introducing finishDate: ensure there will be no overlaps or gaps in the ownership
    // else (leaving only startDate): ensure there would be appropriate next ownership and new startDate will be after this startDate 

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
    public AssetTypeOwnership setStartDate(final Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }
 
    @Observable
    public AssetTypeOwnership setOrg(final Organisation org) {
        this.org = org;
        return this;
    }

    public Organisation getOrg() {
        return org;
    }

    @Observable
    public AssetTypeOwnership setBu(final BusinessUnit bu) {
        this.bu = bu;
        return this;
    }

    public BusinessUnit getBu() {
        return bu;
    }

    @Observable
    public AssetTypeOwnership setRole(final Role role) {
        this.role = role;
        return this;
    }

    public Role getRole() {
        return role;
    }
  
    @Observable
    public AssetTypeOwnership setAssetType(final AssetType assetType) {
        this.assetType = assetType;
        return this;
    }

    public AssetType getAssetType() {
        return assetType;
    }
 
}
