package newyork.webapp.config.tablescodes.assets;

import static java.lang.String.format;
import static newyork.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;
import static ua.com.fielden.platform.web.PrefDim.mkDim;

import java.util.Optional;

import com.google.inject.Injector;

import newyork.common.LayoutComposer;
import newyork.common.StandardActions;
import newyork.main.menu.tablescodes.assets.MiAssetTypeOwnership;
import newyork.organisational.BusinessUnit;
import newyork.organisational.Organisation;
import newyork.organisational.Role;
import newyork.tablescodes.assets.AssetType;
import newyork.tablescodes.assets.AssetTypeOwnership;
import ua.com.fielden.platform.web.PrefDim.Unit;
import ua.com.fielden.platform.web.action.CentreConfigurationWebUiConfig.CentreConfigActions;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.centre.api.EntityCentreConfig;
import ua.com.fielden.platform.web.centre.api.actions.EntityActionConfig;
import ua.com.fielden.platform.web.centre.api.impl.EntityCentreBuilder;
import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import ua.com.fielden.platform.web.view.master.api.IMaster;
import ua.com.fielden.platform.web.view.master.api.actions.MasterActions;
import ua.com.fielden.platform.web.view.master.api.impl.SimpleMasterBuilder;
/**
 * {@link AssetTypeOwnership} Web UI configuration.
 *
 * @author Developers
 *
 */
public class AssetTypeOwnershipWebUiConfig {

    public final EntityCentre<AssetTypeOwnership> centre;
    public final EntityMaster<AssetTypeOwnership> master;

    public static AssetTypeOwnershipWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new AssetTypeOwnershipWebUiConfig(injector, builder);
    }

    private AssetTypeOwnershipWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link AssetTypeOwnership}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<AssetTypeOwnership> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkVarGridForCentre(2, 3);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(AssetTypeOwnership.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(AssetTypeOwnership.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(AssetTypeOwnership.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(AssetTypeOwnership.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(AssetTypeOwnership.class, standardEditAction);

        final EntityCentreConfig<AssetTypeOwnership> ecc = EntityCentreBuilder.centreFor(AssetTypeOwnership.class)
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("assetType").asMulti().autocompleter(AssetType.class).also()
                .addCrit("startDate").asRange().date().also()
                .addCrit("role").asMulti().autocompleter(Role.class).also()
                .addCrit("bu").asMulti().autocompleter(BusinessUnit.class).also()
                .addCrit("org").asMulti().autocompleter(Organisation.class)
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("assetType").order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", AssetTypeOwnership.ENTITY_TITLE))
                    .withActionSupplier(builder.getOpenMasterAction(AssetType.class)).also()
                .addProp("startDate").order(2).desc().width(150).also()
                .addProp("role").order(3).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", Role.ENTITY_TITLE))
                    .withActionSupplier(builder.getOpenMasterAction(Role.class)).also()
                .addProp("bu").order(4).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", BusinessUnit.ENTITY_TITLE))
                    .withActionSupplier(builder.getOpenMasterAction(BusinessUnit.class)).also()
                .addProp("org").order(5).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", Organisation.ENTITY_TITLE))
                    .withActionSupplier(builder.getOpenMasterAction(Organisation.class))
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiAssetTypeOwnership.class, MiAssetTypeOwnership.class.getSimpleName(), ecc, injector, null);
    }

    /**
     * Creates entity master for {@link AssetTypeOwnership}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<AssetTypeOwnership> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkGridForMasterFitWidth(6, 1);

        final IMaster<AssetTypeOwnership> masterConfig = new SimpleMasterBuilder<AssetTypeOwnership>().forEntity(AssetTypeOwnership.class)
                .addProp("assetType").asAutocompleter().also()
                .addProp("startDate").asDatePicker().also()
                .addProp("role").asAutocompleter().also()
                .addProp("bu").asAutocompleter().also()
                .addProp("org").asAutocompleter().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(AssetTypeOwnership.class, masterConfig, injector);
    }
}