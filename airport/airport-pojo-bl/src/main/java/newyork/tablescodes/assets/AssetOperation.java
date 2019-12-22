package newyork.tablescodes.assets;

import java.util.Date;

import newyork.assets.Asset;
import newyork.organisational.BusinessUnit;
import newyork.organisational.Organisation;
import newyork.organisational.Role;
import newyork.tablescodes.assets.definers.AssetOperationExclusivityDefiner;
import newyork.tablescodes.validators.AssetOperationEndTimeValiator;
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
 * Master entity object.
 *
 * @author Developers
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Key")
@CompanionObject(IAssetOperation.class)
@MapEntityTo
public class AssetOperation extends AbstractPersistentEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(AssetOperation.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    @IsProperty
    @MapTo
    @Title(value = "Asset", desc = "Asset Operation")
    @CompositeKeyMember(1)
    private Asset asset;

    @IsProperty
    @MapTo
    @Title(value = "Start Date", desc = "The start date of the operation")
    @CompositeKeyMember(2)
    @DateOnly
    private Date startDate;
    
    @IsProperty
    @MapTo
    @Title(value = "End Date ", desc = "The end date of the Operation")
    @CompositeKeyMember(3)
    @DateOnly
    @BeforeChange({
        @Handler(AssetOperationEndTimeValiator.class)
    })
    private Date endDate;

    @IsProperty
    @MapTo
    @Title(value = "Role", desc = "Role that performs operation on asset of the specified asset type")
    @AfterChange(AssetOperationExclusivityDefiner.class)
    private Role role;

    @IsProperty
    @MapTo
    @Title(value = "Business Unit", desc = "Business unit that performs operations on assets of the specified asset type")
    @AfterChange(AssetOperationExclusivityDefiner.class)
    private BusinessUnit bu;
    
    @IsProperty
    @MapTo
    @Title(value = "Organisation", desc = "Organisation that performs operations on assets of the specified asset type")
    @AfterChange(AssetOperationExclusivityDefiner.class)
    private Organisation org;
    
       
    @Observable
    public AssetOperation setAsset(final Asset asset) {
        this.asset = asset;
        return this;
    }

    public Asset getAsset() {
        return asset;
    }
     
    @Observable
    public AssetOperation setStartDate(final Date startDate) {
        this.startDate = startDate;
        return this;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    @Observable
    public AssetOperation setEndDate(final Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }
 
    @Observable
    public AssetOperation setOrg(final Organisation org) {
        this.org = org;
        return this;
    }

    public Organisation getOrg() {
        return org;
    }

    @Observable
    public AssetOperation setBu(final BusinessUnit bu) {
        this.bu = bu;
        return this;
    }

    public BusinessUnit getBu() {
        return bu;
    }

    @Observable
    public AssetOperation setRole(final Role role) {
        this.role = role;
        return this;
    }

    public Role getRole() {
        return role;
    }
    
}
