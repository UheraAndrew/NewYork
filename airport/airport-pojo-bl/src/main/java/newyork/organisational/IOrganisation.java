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
            "name", "desc");
}
