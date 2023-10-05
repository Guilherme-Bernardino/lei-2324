
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manage streaming service.
 *
 * @author POO
 * @version mar/2023
 */
public class StreamingService {

    private List<Account> activeAccounts;

    /**
     * Constructor of class StreamingService.
     */
    public StreamingService() {
        this.activeAccounts = new ArrayList<>();
    }

    /**
     * Get the list of accounts.
     *
     * @return This streaming service active accounts.
     */
    public List<Account> getActiveAccounts() {
        return activeAccounts;
    }

    /**
     * Add an account.
     *
     * @param account to add.
     */
    public void addAccount(Account account) {
        if(account == null){
            return;
        }

        for (Account acc:activeAccounts) {
            if(acc.equals(account)){
                return;
            }
        }

        activeAccounts.add(account);
    }

    /**
     * Remove an account.
     *
     * @param account to remove.
     */
    public void removeAccount(Account account) {
        Iterator<Account> iterator = activeAccounts.iterator();
        while(iterator.hasNext()){
            Account acc = iterator.next();

            if(account.equals(acc)){
                iterator.remove();
            }
        }
        activeAccounts.remove(account);
    }

    /**
     * Remove all accounts that didn't pay. Implementation using an iterator.
     */
    public void removeUnpaidAccounts() {
        Iterator<Account> iterator = activeAccounts.iterator();
        while(iterator.hasNext()){
            Account acc = iterator.next();
            if(!acc.isPaid()){
                iterator.remove();
            }
        }
    }

    /**
     * Clear all payments. Implementation using functional
     * programming.
     */
    public void setAllAccountsAsUnpaid() {
        for (Account acc: activeAccounts){
            if (acc.isPaid()){
                acc.setPaid(false);
            }
        }
    }

    /**
     * Remove all accounts that didn't pay. Implementation using removeIf method.
     */
    public void removeUnpaidAccountsRemoveIf() {
        activeAccounts.removeIf(account -> !account.isPaid());
    }

    /**
     * Get the list of accounts that didn't pay. Implementation using functional
     * programming.
     *
     * @return A list with all accounts that didn't pay.
     */
    public List<Account> getListOfUnpaidAccounts() {
        return activeAccounts.stream().filter(account -> !account.isPaid()).collect(Collectors.toList());
    }

    /**
     * Count the amount of accounts that didn't pay. Implementation using
     * functional programming.
     *
     * @return The amount of accounts that didn't pay.
     */
    public long countUnpaidAccounts() {
        return activeAccounts.stream().filter(account -> !account.isPaid()).count();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Contas do serviço de streaming: \n\n"));
        sb.append(String.format("Nome %s Pago\n", "               "));
        for (Account acc : activeAccounts) {
            sb.append(String.format(acc.getName() + "%s" + acc.isPaid() + "\n" ,"      "));
        }
        return sb.toString();
    }
}
