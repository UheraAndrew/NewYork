package newyork.webapp;

import org.apache.commons.lang.StringUtils;

import newyork.assets.Asset;
import newyork.assets.AssetFinDet;
import newyork.config.personnel.PersonWebUiConfig;
import newyork.projects.Project;
import newyork.tablescodes.assets.AssetClass;

import newyork.tablescodes.assets.AssetType;
import newyork.tablescodes.assets.AssetManagement;
import newyork.tablescodes.assets.AssetTypeManagement;
import newyork.tablescodes.assets.AssetOwnership;
import newyork.tablescodes.assets.AssetTypeOperation;
import newyork.tablescodes.assets.AssetOperation;

import newyork.tablescodes.assets.AssetTypeOwnership;
import newyork.tablescodes.assets.ConditionRating;
import newyork.tablescodes.assets.ServiceStatus;
import newyork.webapp.config.assets.AssetFinDetWebUiConfig;
import newyork.webapp.config.assets.AssetWebUiConfig;
import newyork.webapp.config.personel.BusinessUnitWebUiConfig;
import newyork.webapp.config.personel.OrganisationWebUiConfig;
import newyork.webapp.config.personel.RoleWebUiConfig;
import newyork.webapp.config.projects.ProjectWebUiConfig;

import newyork.webapp.config.tablescodes.assets.AssetClassWebUiConfig;
import newyork.webapp.config.tablescodes.assets.AssetManagementWebUiConfig;
import newyork.webapp.config.tablescodes.assets.AssetTypeManagementWebUiConfig;
import newyork.webapp.config.tablescodes.assets.AssetOperationWebUiConfig;
import newyork.webapp.config.tablescodes.assets.AssetOwnershipWebUiConfig;
import newyork.webapp.config.tablescodes.assets.AssetTypeOperationWebUiConfig;

import newyork.webapp.config.tablescodes.assets.AssetTypeOwnershipWebUiConfig;
import newyork.webapp.config.tablescodes.assets.AssetTypeWebUiConfig;
import newyork.webapp.config.tablescodes.assets.ConditionRatingWebUiConfig;
import newyork.webapp.config.tablescodes.assets.ServiceStatusWebUiConfig;
import ua.com.fielden.platform.basic.config.Workflows;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.resources.webui.AbstractWebUiConfig;
import ua.com.fielden.platform.web.resources.webui.UserRoleWebUiConfig;
import ua.com.fielden.platform.web.resources.webui.UserWebUiConfig;

/**
 * App-specific {@link IWebApp} implementation.
 *
 * @author Generated
 *
 */
public class WebUiConfig extends AbstractWebUiConfig {

    private final String domainName;
    private final String path;
    private final int port;

    public WebUiConfig(final String domainName, final int port, final Workflows workflow, final String path) {
        super("New York Airport Asset Management (development)", workflow, new String[] { "newyork/" });
        if (StringUtils.isEmpty(domainName) || StringUtils.isEmpty(path)) {
            throw new IllegalArgumentException("Both the domain name and application binding path should be specified.");
        }
        this.domainName = domainName;
        this.port = port;
        this.path = path;
    }


    @Override
    public String getDomainName() {
        return domainName;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public int getPort() {
        return port;
    }

    /**
     * Configures the {@link WebUiConfig} with custom centres and masters.
     */
    @Override
    public void initConfiguration() {
        super.initConfiguration();

        final IWebUiBuilder builder = configApp();
        builder.setDateFormat("DD/MM/YYYY").setTimeFormat("HH:mm").setTimeWithMillisFormat("HH:mm:ss")
        // Minimum tablet width defines the boundary below which mobile layout takes place.
        // For example for Xiaomi Redmi 4x has official screen size of 1280x640,
        // still its viewport sizes is twice lesser: 640 in landscape orientation and 360 in portrait orientation.
        // When calculating reasonable transition tablet->mobile we need to consider "real" viewport sizes instead of physical pixel sizes (http://viewportsizes.com).
        .setMinTabletWidth(600);

        // Personnel
        final PersonWebUiConfig personWebUiConfig = PersonWebUiConfig.register(injector(), builder);
        
        final BusinessUnitWebUiConfig businessUnitWebUiConfig = BusinessUnitWebUiConfig.register(injector(), builder);
        final OrganisationWebUiConfig organisationWebUiConfig = OrganisationWebUiConfig.register(injector(), builder);
        final RoleWebUiConfig roleWebUiConfig = RoleWebUiConfig.register(injector(), builder);
        
        final UserWebUiConfig userWebUiConfig = new UserWebUiConfig(injector());
        final UserRoleWebUiConfig userRoleWebUiConfig = new UserRoleWebUiConfig(injector());

        
        // Asset table codes
        final AssetClassWebUiConfig assetClassWebUiConfig = AssetClassWebUiConfig.register(injector(), builder);
        final AssetTypeWebUiConfig assetTypeWebUiConfig = AssetTypeWebUiConfig.register(injector(), builder);
        final ServiceStatusWebUiConfig serviceStatusWebUiConfig = ServiceStatusWebUiConfig.register(injector(), builder); 
        final ConditionRatingWebUiConfig conditionRatingWebUiConfig = ConditionRatingWebUiConfig.register(injector(), builder);
        final AssetTypeOwnershipWebUiConfig assetTypeOwnershipWebUiConfig = AssetTypeOwnershipWebUiConfig.register(injector(), builder);
        

        final AssetTypeManagementWebUiConfig assetTypeManagementWebUiConfig = AssetTypeManagementWebUiConfig.register(injector(), builder);
        final AssetManagementWebUiConfig assetManagementWebUiConfig = AssetManagementWebUiConfig.register(injector(), builder);

        final AssetTypeOperationWebUiConfig assetTypeOperationWebUiConfig = AssetTypeOperationWebUiConfig.register(injector(), builder);
        final AssetOperationWebUiConfig assetOperationWebUiConfig = AssetOperationWebUiConfig.register(injector(), builder);

      // Asset
        final AssetWebUiConfig assetWebUiConfig = AssetWebUiConfig.register(injector(), builder);
        final AssetFinDetWebUiConfig assetFinDetWebUiConfig = AssetFinDetWebUiConfig.register(injector(), builder);
        final AssetOwnershipWebUiConfig assetOwnershipWebUiConfig = AssetOwnershipWebUiConfig.register(injector(), builder);
        
        // Project related UI
        final ProjectWebUiConfig projectWebUiConfig = ProjectWebUiConfig.register(injector(), builder);
        
        // Configure application web resources such as masters and centres
        configApp()
        .addMaster(userWebUiConfig.master)
        .addMaster(userWebUiConfig.rolesUpdater)
        .addMaster(userRoleWebUiConfig.master)
        .addMaster(userRoleWebUiConfig.tokensUpdater)
        // user/personnel module
        .addCentre(userWebUiConfig.centre)
        .addCentre(userRoleWebUiConfig.centre);

        // Configure application menu
        configDesktopMainMenu().
            addModule("Asset Acquisition").
                description("Asset acquisition module").
                icon("mainMenu:equipment").
                detailIcon("mainMenu:equipment").
                bgColor("#80d6ff").
                captionBgColor("#42a5f5").menu()
                .addMenuItem(Asset.ENTITY_TITLE).description(String.format("%s Centre", Asset.ENTITY_TITLE)).centre(assetWebUiConfig.centre).done()
                .addMenuItem(AssetFinDet.ENTITY_TITLE).description(String.format("%s Centre", AssetFinDet.ENTITY_TITLE)).centre(assetFinDetWebUiConfig.centre).done()
                .addMenuItem(AssetOwnership.ENTITY_TITLE).description(String.format("%s Centre", AssetOwnership.ENTITY_TITLE)).centre(assetOwnershipWebUiConfig.centre).done()
                .addMenuItem(Project.ENTITY_TITLE).description(String.format("%s Centre", Project.ENTITY_TITLE)).centre(projectWebUiConfig.centre).done()
            .done().done().              
            addModule("Users / Personnel").
                description("Provides functionality for managing application security and personnel data.").
                icon("mainMenu:help").
                detailIcon("mainMenu:about").
                bgColor("#c8ff75").
                captionBgColor("#94ce44").menu()
                
                .addMenuItem("Personnel").description("Personnel related data")
                    .addMenuItem("Personnel").description("Personnel Centre").centre(personWebUiConfig.centre).done()
                    .done()
                .addMenuItem("Users").description("Users related data")
                    .addMenuItem("Users").description("User centre").centre(userWebUiConfig.centre).done()
                    .addMenuItem("User Roles").description("User roles centre").centre(userRoleWebUiConfig.centre).done()
                .done()
                
            .done().done().
            
            addModule("Table Codes").
                description("Table Codes Description").
                icon("mainMenu:tablecodes").
                detailIcon("mainMenu:tablecodes").
                bgColor("#FFE680").
                captionBgColor("#FFD42A").menu()
            .addMenuItem("Asset Table Codes").description("Various master data for assets.")
                .addMenuItem(AssetClass.ENTITY_TITLE).description(String.format("%s Centre", AssetClass.ENTITY_TITLE)).centre(assetClassWebUiConfig.centre).done()
                .addMenuItem(AssetType.ENTITY_TITLE).description(String.format("%s Centre", AssetType.ENTITY_TITLE)).centre(assetTypeWebUiConfig.centre).done()
                .addMenuItem(ServiceStatus.ENTITY_TITLE).description(String.format("%s Centre", ServiceStatus.ENTITY_TITLE)).centre(serviceStatusWebUiConfig.centre).done()
                .addMenuItem(ConditionRating.ENTITY_TITLE).description(String.format("%s Centre", ConditionRating.ENTITY_TITLE)).centre(conditionRatingWebUiConfig.centre).done()
                .addMenuItem(AssetTypeOwnership.ENTITY_TITLE).description(String.format("%s Centre", AssetTypeOwnership.ENTITY_TITLE)).centre(assetTypeOwnershipWebUiConfig.centre).done()
                

                .addMenuItem(AssetTypeManagement.ENTITY_TITLE).description(String.format("%s Centre", AssetTypeManagement.ENTITY_TITLE)).centre(assetTypeManagementWebUiConfig.centre).done()
                .addMenuItem(AssetManagement.ENTITY_TITLE).description(String.format("%s Centre", AssetManagement.ENTITY_TITLE)).centre(assetManagementWebUiConfig.centre).done()

                .addMenuItem(AssetTypeOperation.ENTITY_TITLE).description(String.format("%s Centre", AssetTypeOperation.ENTITY_TITLE)).centre(assetTypeOperationWebUiConfig.centre).done()
                .addMenuItem(AssetOperation.ENTITY_TITLE).description(String.format("%s Centre", AssetOperation.ENTITY_TITLE)).centre(assetOperationWebUiConfig.centre).done()
                
                .done()
                
                .addMenuItem("Asset Owners").description("Creation of Asset owners")
                    
                    .addMenuItem("Role").description("Role Centre").centre(roleWebUiConfig.centre).done()
          
                    .addMenuItem("Business Unit").description("Business Unit Centre").centre(businessUnitWebUiConfig.centre).done()
            
                    .addMenuItem("Organization").description("Organization Centre").centre(organisationWebUiConfig.centre).done()
            .done()
          
            .done().
            done()
        .setLayoutFor(Device.DESKTOP, null, "[[[{\"rowspan\":2}], []], [[]]]")
        .setLayoutFor(Device.TABLET, null,  "[[[{\"rowspan\":2}], []], [[]]]")
        .setLayoutFor(Device.MOBILE, null, "[[[]],[[]], [[]]]")
        .minCellWidth(100).minCellHeight(148).done();
        }

}
