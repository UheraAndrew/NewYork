package newyork.dev_mod.util;

import static java.lang.String.format;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.H2Dialect;

import newyork.assets.Asset;
import newyork.assets.AssetFinDet;
import newyork.assets.IAssetFinDet;
import newyork.config.ApplicationDomain;
import newyork.organisational.BusinessUnit;
import newyork.organisational.Organisation;
import newyork.organisational.Role;
import newyork.personnel.Person;
import newyork.projects.Project;
import newyork.tablescodes.assets.AssetClass;
import newyork.tablescodes.assets.AssetType;
import newyork.tablescodes.assets.AssetTypeOwnership;
import ua.com.fielden.platform.devdb_support.DomainDrivenDataPopulation;
import ua.com.fielden.platform.entity.AbstractEntity;
import ua.com.fielden.platform.persistence.HibernateUtil;
import ua.com.fielden.platform.security.user.User;
import ua.com.fielden.platform.test.IDomainDrivenTestCaseConfiguration;
import ua.com.fielden.platform.types.Money;
import ua.com.fielden.platform.utils.DbUtils;

/**
 * This is a convenience class for (re-)creation of the development database and its population.
 * 
 * It contains the <code>main</code> method and can be executed whenever the target database needs to be (re-)set.
 * <p>
 * 
 * <b>IMPORTANT: </b><i>One should be careful not to run this code against the deployment or production databases, which would lead to the loss of all data.</i>
 * 
 * <p>
 * 
 * @author TG Team
 * 
 */
public class PopulateDb extends DomainDrivenDataPopulation {
    private static final Logger LOGGER = Logger.getLogger(PopulateDb.class);

    private final ApplicationDomain applicationDomainProvider = new ApplicationDomain();

    private PopulateDb(final IDomainDrivenTestCaseConfiguration config, final Properties props) {
        super(config, props);
    }

    public static void main(final String[] args) throws Exception {
        LOGGER.info("Initialising...");
        final String configFileName = args.length == 1 ? args[0] : "application.properties";
        final Properties props = new Properties();
        try (final FileInputStream in = new FileInputStream(configFileName)) {
            props.load(in);
        }
        
        DOMConfigurator.configure(props.getProperty("log4j"));

        LOGGER.info("Obtaining Hibernate dialect...");
        final Class<?> dialectType = Class.forName(props.getProperty("hibernate.dialect"));
        final Dialect dialect = (Dialect) dialectType.newInstance();
        LOGGER.info(format("Running with dialect %s...", dialect));
        final DataPopulationConfig config = new DataPopulationConfig(props);
        LOGGER.info("Generating DDL and running it against the target DB...");

        // use TG DDL generation or
        // Hibernate DDL generation final List<String> createDdl = DbUtils.generateSchemaByHibernate()
        final List<String> createDdl = config.getDomainMetadata().generateDatabaseDdl(dialect);
        final List<String> ddl = dialect instanceof H2Dialect ?
                                 DbUtils.prependDropDdlForH2(createDdl) :
                                 DbUtils.prependDropDdlForSqlServer(createDdl);
        DbUtils.execSql(ddl, config.getInstance(HibernateUtil.class).getSessionFactory().getCurrentSession());

        final PopulateDb popDb = new PopulateDb(config, props);
        popDb.populateDomain();
    }

    @Override
    protected void populateDomain() {
        LOGGER.info("Creating and populating the development database...");
        
        setupUser(User.system_users.SU, "newyork");
        setupPerson(User.system_users.SU, "newyork");
        
        // Asset table codes
        final AssetClass ac1 = save(new_(AssetClass.class).setName("AC1").setDesc("asset class 1").setActive(true));
        save(new_(AssetClass.class).setName("AC2").setDesc("asset class 2").setActive(true));
        
        AssetType at1 = save(new_(AssetType.class).setName("AT1").setDesc("asset type 1").setAssetClass(ac1).setActive(true));
        
        // Assets
        final Asset asset1 = save(new_(Asset.class).setDesc("a demo asset 1").setAssetType(at1));
        final Asset asset2 = save(new_(Asset.class).setDesc("a demo asset 2").setAssetType(at1));
        final Asset asset3 = save(new_(Asset.class).setDesc("a demo asset 3").setAssetType(at1));
        
        final AssetFinDet finDet1 = co$(AssetFinDet.class).findById(asset1.getId(), IAssetFinDet.FETCH_PROVIDER.fetchModel());
        save(finDet1.setInitCost(Money.of("120.00")).setAcquireDate(date("2019-12-07 00:00:00")));
        final AssetFinDet finDet2 = co$(AssetFinDet.class).findById(asset2.getId(), IAssetFinDet.FETCH_PROVIDER.fetchModel());
        save(finDet2.setInitCost(Money.of("100.00")).setAcquireDate(date("2019-11-01 00:00:00")));
        final AssetFinDet finDet3 = co$(AssetFinDet.class).findById(asset3.getId(), IAssetFinDet.FETCH_PROVIDER.fetchModel());
        save(finDet3.setInitCost(Money.of("10.00")));
        
        // Projects
        Project p1 = save(new_(Project.class).setName("Project 1").setStartDate(date("2019-12-08 00:00:00")).setDesc("Project 1 description"));
        Project p2 = save(new_(Project.class).setName("Project 2").setStartDate(date("2020-01-02 00:00:00")).setDesc("Project 2 description"));
        
        // for AssetTypeOwnership
        Role r1 = save(new_(Role.class).setName("R1").setDesc("First role"));
        BusinessUnit bu1 = save(new_(BusinessUnit.class).setName("BU1").setDesc("First business unit"));
        Organisation o3 = save(new_(Organisation.class).setName("ORG1").setDesc("First organisation"));
        
        AssetTypeOwnership ao1 = save(new_(AssetTypeOwnership.class).setStartDate(date("2019-12-02 00:00:00")).setBu(bu1).setAssetType(at1).setDesc("Asset Type Ownership 1"));
        
        
        LOGGER.info("Completed database creation and population.");
	}

    private void setupPerson(final User.system_users defaultUser, final String emailDomain) {
        final User su = co(User.class).findByKey(defaultUser.name());
        save(new_(Person.class, defaultUser.name()).setActive(true).setUser(su).setDesc("Person who is a user").setEmail(defaultUser + "@" + emailDomain));
    }

    @Override
    protected List<Class<? extends AbstractEntity<?>>> domainEntityTypes() {
        return applicationDomainProvider.entityTypes();
    }

}
