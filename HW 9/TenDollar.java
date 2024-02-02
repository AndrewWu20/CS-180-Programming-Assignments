public class TenDollar extends DollarBill{
    public TenDollar() {
        super(10, "Alexander Hamilton");
    }

    @Override
    /* Override acceptedVending method in coin class to
     * return false because vending machines can't accept ten dollar bills
     */
    public boolean acceptedVending() {
        return false;
    }
}
