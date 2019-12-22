package newyork.organisational;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Organisation}.
 *
 * @author Developers
 *
 */
public interface IOrganisation extends IEntityDao<Organisation> {

    static final IFetchProvider<Organisation> FETCH_PROVIDER = EntityUtils.fetch(Organisation.class).with(
        // TODO: uncomment the following line and specify the properties, which are required for the UI. Then remove the line after.
        // "key", "desc");
        "Please specify the properties, which are required for the UI");

}