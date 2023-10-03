package pt.pa.patterns.model;

/**
 *
 * @author amfs
 */
public class InvalidPackageException extends Exception {

    public InvalidPackageException(String code) {
        super("The package with the code (" + code + ") does not exist.");
    }

}
