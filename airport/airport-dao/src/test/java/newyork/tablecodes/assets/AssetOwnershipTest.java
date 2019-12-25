package newyork.tablecodes.assets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import newyork.assets.Asset;
import newyork.assets.IAsset;
import newyork.organisational.BusinessUnit;
import newyork.organisational.Organisation;
import newyork.organisational.Role;
import newyork.tablescodes.assets.AssetClass;
import newyork.tablescodes.assets.AssetOwnership;
import newyork.tablescodes.assets.AssetType;
import newyork.tablescodes.assets.AssetTypeOwnership;
import newyork.tablescodes.assets.IAssetOwnership;
import newyork.tablescodes.assets.IAssetTypeOwnership;
import newyork.test_config.AbstractDaoTestCase;
import newyork.test_config.UniversalConstantsForTesting;
import ua.com.fielden.platform.dao.exceptions.EntityAlreadyExists;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.utils.IUniversalConstants;

/**
 * This is a test case for {@link AssetTypeOwnership}.
 * 
 * @author New-York-Team
 *
 */

public class AssetOwnershipTest extends AbstractDaoTestCase {
    
    @Test
    public void ownership_with_either_role_or_bu_or_org_is_acceptable() {
        final Asset asset = co(Asset.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<Asset>fetchFor("asset").fetchModel(), "1");
        assertNotNull(asset);
        final Role r1 = co(Role.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<Role>fetchFor("role").fetchModel(), "R1");
        assertNotNull(r1);
        final AssetType at1 = co(AssetType.class).findByKeyAndFetch(IAssetTypeOwnership.FETCH_PROVIDER.<AssetType>fetchFor("assetType").fetchModel(), "AT1");
        final BusinessUnit bu1 = co(BusinessUnit.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<BusinessUnit>fetchFor("bu").fetchModel(), "BU1");
        assertNotNull(bu1);
        final Organisation org1 = co(Organisation.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<Organisation>fetchFor("org").fetchModel(), "ORG1");
        assertNotNull(org1);
        
        final AssetOwnership o1 = save(co(AssetOwnership.class).new_()
                .setAsset(asset)
                .setAssetType(at1)
                .setStartDate(date("2019-12-17 00:00:00"))
                .setRole(r1));
        final AssetOwnership o2 = save(co(AssetOwnership.class).new_()
                .setAsset(asset)
                .setAssetType(at1)
                .setStartDate(date("2019-12-18 00:00:00"))
                .setBu(bu1));
        final AssetOwnership o3 = save(co(AssetOwnership.class).new_()
                .setAsset(asset)
                .setAssetType(at1)
                .setStartDate(date("2019-12-19 00:00:00"))
                .setOrg(org1));
    
    }

    @Test
    public void currOwnership_for_asset_finds_the_current_ownership_based_on_the_value_of_now() {
        final Asset asset = co(Asset.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<Asset>fetchFor("asset").fetchModel(), "1");
        assertNotNull(asset);
        final Role r1 = co(Role.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<Role>fetchFor("role").fetchModel(), "R1");
        assertNotNull(r1);
        final AssetType at1 = co(AssetType.class).findByKeyAndFetch(IAssetTypeOwnership.FETCH_PROVIDER.<AssetType>fetchFor("assetType").fetchModel(), "AT1");
        final BusinessUnit bu1 = co(BusinessUnit.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<BusinessUnit>fetchFor("bu").fetchModel(), "BU1");
        assertNotNull(bu1);
        final Organisation org1 = co(Organisation.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<Organisation>fetchFor("org").fetchModel(), "ORG1");
        assertNotNull(org1);
       
        final AssetOwnership o0 = save(co(AssetOwnership.class).new_()
                .setAsset(asset)
                .setAssetType(at1)
                .setStartDate(date("2019-12-13 00:00:00"))
                .setOrg(org1));
        
        final AssetOwnership o1 = save(co(AssetOwnership.class).new_()
                .setAsset(asset)
                .setAssetType(at1)
                .setStartDate(date("2019-12-17 00:00:00"))
                .setRole(r1));
        final AssetOwnership o2 = save(co(AssetOwnership.class).new_()
                .setAsset(asset)
                .setAssetType(at1)
                .setStartDate(date("2019-12-18 00:00:00"))
                .setBu(bu1));
        final AssetOwnership o3 = save(co(AssetOwnership.class).new_()
                .setAsset(asset)
                .setAssetType(at1)
                .setStartDate(date("2019-12-19 00:00:00"))
                .setOrg(org1));
        
        final AssetOwnership o4 = save(co(AssetOwnership.class).new_()
                .setAsset(asset)
                .setAssetType(at1)
                .setStartDate(date("2020-01-03 00:00:00"))
                .setBu(bu1));
        
        final UniversalConstantsForTesting constants = (UniversalConstantsForTesting) getInstance(IUniversalConstants.class);
        constants.setNow(dateTime("2019-12-10 13:00:00"));
        
        final Asset assetWithCurrOwnership1 = co(Asset.class).findById(asset.getId(), IAsset.FETCH_PROVIDER.fetchModel());
        assertNull(assetWithCurrOwnership1.getCurrOwnership());
        
        constants.setNow(dateTime("2019-12-16 13:00:00"));
        final Asset assetWithCurrOwnership2 = co(Asset.class).findById(asset.getId(), IAsset.FETCH_PROVIDER.fetchModel());
        assertEquals(o0, assetWithCurrOwnership2.getCurrOwnership());
        
        constants.setNow(dateTime("2019-12-17 13:00:00"));
        final Asset assetWithCurrOwnership3 = co(Asset.class).findById(asset.getId(), IAsset.FETCH_PROVIDER.fetchModel());
        assertEquals(o1, assetWithCurrOwnership3.getCurrOwnership());
        
        constants.setNow(dateTime("2019-12-18 13:00:00"));
        final Asset assetWithCurrOwnership4 = co(Asset.class).findById(asset.getId(), IAsset.FETCH_PROVIDER.fetchModel());
        assertEquals(o2, assetWithCurrOwnership4.getCurrOwnership());
        
        constants.setNow(dateTime("2020-02-18 13:00:00"));
        final Asset assetWithCurrOwnership5 = co(Asset.class).findById(asset.getId(), IAsset.FETCH_PROVIDER.fetchModel());
        assertEquals(o4, assetWithCurrOwnership5.getCurrOwnership());
    
    }
    
    @Test
    public void currOwnership_for_asset_and_asset_type_correspond_to_each_other() {
        final Asset asset = co(Asset.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<Asset>fetchFor("asset").fetchModel(), "1");
        assertNotNull(asset);
        final Role r1 = co(Role.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<Role>fetchFor("role").fetchModel(), "R1");
        assertNotNull(r1);
        final AssetType at1 = co(AssetType.class).findByKeyAndFetch(IAssetTypeOwnership.FETCH_PROVIDER.<AssetType>fetchFor("assetType").fetchModel(), "AT1");
        final BusinessUnit bu1 = co(BusinessUnit.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<BusinessUnit>fetchFor("bu").fetchModel(), "BU1");
        assertNotNull(bu1);
        final Organisation org1 = co(Organisation.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<Organisation>fetchFor("org").fetchModel(), "ORG1");
        assertNotNull(org1);
       
        final AssetOwnership o0 = save(co(AssetOwnership.class).new_()
                .setAsset(asset)
                .setAssetType(at1)
                .setStartDate(date("2019-12-13 00:00:00"))
                .setOrg(org1));
        
        final AssetOwnership o1 = save(co(AssetOwnership.class).new_()
                .setAsset(asset)
                .setAssetType(at1)
                .setStartDate(date("2019-12-17 00:00:00"))
                .setRole(r1));
        final AssetOwnership o2 = save(co(AssetOwnership.class).new_()
                .setAsset(asset)
                .setAssetType(at1)
                .setStartDate(date("2019-12-18 00:00:00"))
                .setBu(bu1));
        final AssetOwnership o3 = save(co(AssetOwnership.class).new_()
                .setAsset(asset)
                .setAssetType(at1)
                .setStartDate(date("2019-12-19 00:00:00"))
                .setOrg(org1));
        
        final AssetOwnership o4 = save(co(AssetOwnership.class).new_()
                .setAsset(asset)
                .setAssetType(at1)
                .setStartDate(date("2020-01-03 00:00:00"))
                .setBu(bu1));
        
        final UniversalConstantsForTesting constants = (UniversalConstantsForTesting) getInstance(IUniversalConstants.class);
        constants.setNow(dateTime("2019-12-10 13:00:00"));
        
        final Asset assetWithCurrOwnership1 = co(Asset.class).findById(asset.getId(), IAsset.FETCH_PROVIDER.fetchModel());
        assertNull(assetWithCurrOwnership1.getCurrOwnership());
        assertNull(assetWithCurrOwnership1.getAssetType().getCurrOwnership());
        
        constants.setNow(dateTime("2019-12-14 13:00:00"));
        final Asset assetWithCurrOwnership2 = co(Asset.class).findById(asset.getId(), IAsset.FETCH_PROVIDER.fetchModel());
        assertEquals(o0, assetWithCurrOwnership2.getCurrOwnership());
        assertNull(assetWithCurrOwnership2.getAssetType().getCurrOwnership());
        
        constants.setNow(dateTime("2019-12-17 13:00:00"));
        final Asset assetWithCurrOwnership3 = co(Asset.class).findById(asset.getId(), IAsset.FETCH_PROVIDER.fetchModel());
        assertEquals(o1, assetWithCurrOwnership3.getCurrOwnership());
        final AssetTypeOwnership assetTypeOwnership1 = co(AssetTypeOwnership.class).findByKeyAndFetch(IAsset.FETCH_PROVIDER.<AssetTypeOwnership>fetchFor("assetType.currOwnership").fetchModel(), assetWithCurrOwnership3.getAssetType(), date("2019-12-17 00:00:00"));
        assertEquals(assetTypeOwnership1, assetWithCurrOwnership3.getAssetType().getCurrOwnership());
        
        constants.setNow(dateTime("2020-02-18 13:00:00"));
        final Asset assetWithCurrOwnership5 = co(Asset.class).findById(asset.getId(), IAsset.FETCH_PROVIDER.fetchModel());
        assertEquals(o4, assetWithCurrOwnership5.getCurrOwnership());
        final AssetTypeOwnership assetTypeOwnership2 = co(AssetTypeOwnership.class).findByKeyAndFetch(IAsset.FETCH_PROVIDER.<AssetTypeOwnership>fetchFor("assetType.currOwnership").fetchModel(), assetWithCurrOwnership3.getAssetType(), date("2019-12-19 00:00:00"));
        assertEquals(assetTypeOwnership2, assetWithCurrOwnership5.getAssetType().getCurrOwnership());
    
    }
    
    @Test
    public void different_ownerships_for_the_same_asset_on_the_same_date_is_not_permited() {
        final Asset asset = co(Asset.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<Asset>fetchFor("asset").fetchModel(), "1");
        assertNotNull(asset);
        final AssetType at1 = co(AssetType.class).findByKeyAndFetch(IAssetTypeOwnership.FETCH_PROVIDER.<AssetType>fetchFor("assetType").fetchModel(), "AT1");
        assertNotNull(at1);
        final Role r1 = co(Role.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<Role>fetchFor("role").fetchModel(), "R1");
        assertNotNull(r1);
        final BusinessUnit bu1 = co(BusinessUnit.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<BusinessUnit>fetchFor("bu").fetchModel(), "BU1");
        assertNotNull(bu1);
        final Organisation org1 = co(Organisation.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<Organisation>fetchFor("org").fetchModel(), "ORG1");
        assertNotNull(org1);
        
        final AssetOwnership o1 = save(co(AssetOwnership.class).new_()
                .setAsset(asset)
                .setAssetType(at1)
                .setRole(r1)
                .setStartDate(date("2019-12-17 00:00:00")));
        try {
            final AssetOwnership o2 = save(co(AssetOwnership.class).new_()
                    .setAsset(asset)
                    .setAssetType(at1)
                    .setBu(bu1)
                    .setStartDate(date("2019-12-17 00:00:00")));
            fail("Error was expected due to duplicate ownerships.");
        } catch (final EntityAlreadyExists ex) {
        }
        
        try {
            final AssetOwnership o3 = save(co(AssetOwnership.class).new_()
                    .setAsset(asset)
                    .setAssetType(at1)
                    .setOrg(org1)
                    .setStartDate(date("2019-12-17 00:00:00")));
            fail("Error was expected due to duplicate ownerships.");
        } catch (final EntityAlreadyExists ex) {   
        }
    }
    
    @Test
    public void exclusivity_holds_for_ownership_properties_for_new_not_persisted_instance() {
        final Asset asset = co(Asset.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<Asset>fetchFor("asset").fetchModel(), "1");
        assertNotNull(asset);
        final AssetType at1 = co(AssetType.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<AssetType>fetchFor("assetType").fetchModel(), "AT1");
        assertNotNull(at1);
        final Role r1 = co(Role.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<Role>fetchFor("role").fetchModel(), "R1");
        assertNotNull(r1);
        final BusinessUnit bu1 = co(BusinessUnit.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<BusinessUnit>fetchFor("bu").fetchModel(), "BU1");
        assertNotNull(bu1);
        final Organisation org1 = co(Organisation.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<Organisation>fetchFor("org").fetchModel(), "ORG1");
        assertNotNull(org1);
        final AssetOwnership ownership = co(AssetOwnership.class).new_()
                .setAsset(asset)
                .setAssetType(at1)
                .setStartDate(date("2019-12-17 00:00:00"));
        
        assertFalse(ownership.isValid().isSuccessful());
        assertNull(ownership.getRole());
        assertTrue(ownership.getProperty("role").isRequired());
        assertNull(ownership.getBu());
        assertTrue(ownership.getProperty("bu").isRequired());
        assertNull(ownership.getOrg());
        assertTrue(ownership.getProperty("org").isRequired());
        
        ownership.setRole(r1);
        assertTrue(ownership.isValid().isSuccessful());
        assertNotNull(ownership.getRole());
        assertTrue(ownership.getProperty("role").isRequired());
        assertNull(ownership.getBu());
        assertFalse(ownership.getProperty("bu").isRequired());
        assertNull(ownership.getOrg());
        assertFalse(ownership.getProperty("org").isRequired());
        
        ownership.setBu(bu1);
        assertTrue(ownership.isValid().isSuccessful());
        assertNull(ownership.getRole());
        assertFalse(ownership.getProperty("role").isRequired());
        assertNotNull(ownership.getBu());
        assertTrue(ownership.getProperty("bu").isRequired());
        assertNull(ownership.getOrg());
        assertFalse(ownership.getProperty("org").isRequired());
        
        ownership.setOrg(org1);
        assertTrue(ownership.isValid().isSuccessful());
        assertNull(ownership.getRole());
        assertFalse(ownership.getProperty("role").isRequired());
        assertNull(ownership.getBu());
        assertFalse(ownership.getProperty("bu").isRequired());
        assertNotNull(ownership.getOrg());
        assertTrue(ownership.getProperty("org").isRequired());
    }
    
    @Test
    public void exclusivity_holds_for_ownership_properties_for_persisted_instance() {
        final Asset asset = co(Asset.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<Asset>fetchFor("asset").fetchModel(), "1");
        assertNotNull(asset);
        final AssetType at1 = co(AssetType.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<AssetType>fetchFor("assetType").fetchModel(), "AT1");
        assertNotNull(at1);
        final Role r1 = co(Role.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<Role>fetchFor("role").fetchModel(), "R1");
        assertNotNull(r1);
        final BusinessUnit bu1 = co(BusinessUnit.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<BusinessUnit>fetchFor("bu").fetchModel(), "BU1");
        assertNotNull(bu1);
        final Organisation org1 = co(Organisation.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.<Organisation>fetchFor("org").fetchModel(), "ORG1");
        assertNotNull(org1);
        save(co(AssetOwnership.class).new_()
                .setAsset(asset)
                .setAssetType(at1)
                .setStartDate(date("2019-12-17 00:00:00"))
                .setRole(r1));
        
        final AssetOwnership ownership = co$(AssetOwnership.class).findByKeyAndFetch(IAssetOwnership.FETCH_PROVIDER.fetchModel(), asset, at1, date("2019-12-17 00:00:00"));
        assertNotNull(ownership);
        
        assertTrue(ownership.isValid().isSuccessful());
        assertNotNull(ownership.getRole());
        assertTrue(ownership.getProperty("role").isRequired());
        assertNull(ownership.getBu());
        assertFalse(ownership.getProperty("bu").isRequired());
        assertNull(ownership.getOrg());
        assertFalse(ownership.getProperty("org").isRequired());
        
        ownership.setOrg(org1);
        assertTrue(ownership.isValid().isSuccessful());
        assertNull(ownership.getRole());
        assertFalse(ownership.getProperty("role").isRequired());
        assertNull(ownership.getBu());
        assertFalse(ownership.getProperty("bu").isRequired());
        assertNotNull(ownership.getOrg());
        assertTrue(ownership.getProperty("org").isRequired());
        
        assertNotNull(save(ownership).getOrg());
        
    }

    @Override
    public boolean saveDataPopulationScriptToFile() {
        return false;
    }

    @Override
    public boolean useSavedDataPopulationScript() {
        return false;
    }

    @Override
    protected void populateDomain() {
        // Need to invoke super to create a test user that is responsible for data population 
    	super.populateDomain();

    	// Here is how the Test Case universal constants can be set.
    	// In this case the notion of now is overridden, which makes it possible to have an invariant system-time.
    	// However, the now value should be after AbstractDaoTestCase.prePopulateNow in order not to introduce any date-related conflicts.
    	final UniversalConstantsForTesting constants = (UniversalConstantsForTesting) getInstance(IUniversalConstants.class);
    	constants.setNow(dateTime("2019-12-16 13:00:00"));

    	// If the use of saved data population script is indicated then there is no need to proceed with any further data population logic.
        if (useSavedDataPopulationScript()) {
            return;
        }
        
        final AssetClass ac1 = save(new_(AssetClass.class).setName("AC1").setDesc("First asset class").setActive(true));
        final AssetType at1 = save(new_(AssetType.class).setName("AT1").setDesc("First asset type").setAssetClass(ac1).setActive(true));
        final Role r1 = save(new_(Role.class).setName("R1").setDesc("First role"));
        final BusinessUnit bu1 = save(new_(BusinessUnit.class).setName("BU1").setDesc("First business unit"));
        final Organisation org1 = save(new_(Organisation.class).setName("ORG1").setDesc("First organisation"));
        
        save(new_(Asset.class).setDesc("some description").setAssetType(at1).setActive(true));
        
        final AssetTypeOwnership o1 = save(co(AssetTypeOwnership.class).new_()
                .setAssetType(at1)
                .setStartDate(date("2019-12-17 00:00:00"))
                .setRole(r1));
        final AssetTypeOwnership o2 = save(co(AssetTypeOwnership.class).new_()
                .setAssetType(at1)
                .setStartDate(date("2019-12-18 00:00:00"))
                .setBu(bu1));
        final AssetTypeOwnership o3 = save(co(AssetTypeOwnership.class).new_()
                .setAssetType(at1)
                .setStartDate(date("2019-12-19 00:00:00"))
                .setOrg(org1));
        
        
    }

}
