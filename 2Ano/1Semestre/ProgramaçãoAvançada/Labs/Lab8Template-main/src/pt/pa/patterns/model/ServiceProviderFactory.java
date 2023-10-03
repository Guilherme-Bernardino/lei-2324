package pt.pa.patterns.model;

/**
 *
 * @author amfs
 */
public interface ServiceProviderFactory {

    public Package getPackage(String code) throws InvalidPackageException;
}
